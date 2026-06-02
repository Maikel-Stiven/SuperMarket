package com.example.repaso.modules.sales.services;

import com.example.repaso.modules.inventory.entities.Product;
import com.example.repaso.modules.inventory.repositories.ProductRepository;
import com.example.repaso.modules.sales.dtos.SaleDetailRequestDTO;
import com.example.repaso.modules.sales.dtos.SaleRequestDTO;
import com.example.repaso.modules.sales.dtos.SaleResponseDTO;
import com.example.repaso.modules.sales.entities.Sale;
import com.example.repaso.modules.sales.entities.SaleDetail;
import com.example.repaso.modules.sales.repositories.SaleDetailRepository;
import com.example.repaso.modules.sales.repositories.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleService {

    private final SaleRepository saleRepository;
    private final SaleDetailRepository saleDetailRepository;
    private final ProductRepository productRepository; // Inyectamos el repositorio del otro módulo para el stock

    @Transactional // 🔥 Asegura que si un producto falla, no se guarde nada de la venta (Atomicidad)
    public SaleResponseDTO createSale(SaleRequestDTO request) {
        
        // 1. Crear la cabecera de la venta inicialmente con total 0
        Sale venta = new Sale();
        venta.setEmpleadoId(request.getEmpleadoId());
        venta.setFecha(LocalDateTime.now());
        venta.setTotal(0.0);
        venta = saleRepository.save(venta);

        Double totalFactura = 0.0;
        List<SaleDetail> detallesAGuardar = new ArrayList<>();

        // 2. Procesar cada producto del DTO
        for (SaleDetailRequestDTO detalleDTO : request.getDetalles()) {
            Product producto = productRepository.findById(detalleDTO.getProductoId())
                    .orElseThrow(() -> new RuntimeException("El producto con ID " + detalleDTO.getProductoId() + " no existe"));

            // Validar Regla de Negocio: Stock Suficiente
            if (producto.getStock() < detalleDTO.getCantidad()) {
                throw new RuntimeException("Stock insuficiente para el producto: " + producto.getNombre() + ". Stock disponible: " + producto.getStock());
            }

            // Descontar el stock en la entidad y guardar en inventario
            producto.setStock(producto.getStock() - detalleDTO.getCantidad());
            productRepository.save(producto);

            // Calcular montos
            Double subtotal = producto.getPrecio() * detalleDTO.getCantidad();
            totalFactura += subtotal;

            // Armar el detalle plano
            SaleDetail detalle = new SaleDetail();
            detalle.setVentaId(venta.getId());
            detalle.setProductoId(producto.getId());
            detalle.setCantidad(detalleDTO.getCantidad());
            detalle.setPrecioUnitario(producto.getPrecio());
            detalle.setSubtotal(subtotal);
            
            detallesAGuardar.add(detalle);
        }

        // 3. Guardar todos los detalles y actualizar el total real de la factura
        saleDetailRepository.saveAll(detallesAGuardar);
        venta.setTotal(facturaTotal);
        saleRepository.save(venta);

        // 4. Mapear respuesta abreviada con Setters seguros
        SaleResponseDTO response = new SaleResponseDTO();
        response.setId(venta.getId());
        response.setFecha(venta.getFecha());
        response.setTotal(venta.getTotal());
        response.setIdEmpleado(venta.getEmpleadoId());
        
        return response;
    }
}