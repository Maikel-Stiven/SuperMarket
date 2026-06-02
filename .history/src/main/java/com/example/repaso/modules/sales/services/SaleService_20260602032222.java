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
    private final ProductRepository productRepository;

    @Transactional 
    public SaleResponseDTO createSale(SaleRequestDTO request) {
        
        
        Sale venta = new Sale();
        venta.setEmpleadoId(request.getEmpleadoId());
        venta.setFecha(LocalDateTime.now());
        venta.setTotal(0.0);
        venta = saleRepository.save(venta);

        Double totalFactura = 0.0;
        List<SaleDetail> detallesAGuardar = new ArrayList<>();
    

        for (SaleDetailRequestDTO detalles : request.getDetalles()) {
            Product producto = productRepository.findById(detalles.getProductoId())
                    .orElseThrow(() -> new RuntimeException("El producto con ID " + detalles.getProductoId() + " no existe"));
      
            if (producto.getStock() < detalles.getCantidad()) {
                throw new RuntimeException("Stock insuficiente para el producto: " + producto.getNombre() + ". Stock disponible: " + producto.getStock());
            }

            
            producto.setStock(producto.getStock() - detalles.getCantidad());
            productRepository.save(producto);
            
            Double subtotal = producto.getPrecio() * detalles.getCantidad();
            totalFactura += subtotal;
            
            SaleDetail detalle = new SaleDetail();
            detalle.setVentaId(venta.getId());
            detalle.setProductoId(producto.getId());
            detalle.setCantidad(detalles.getCantidad());
            detalle.setPrecioUnitario(producto.getPrecio());
            detalle.setSubtotal(subtotal);
            
            detallesAGuardar.add(detalle);
        }
        
        saleDetailRepository.saveAll(detallesAGuardar);
        venta.setTotal(totalFactura);
        saleRepository.save(venta);

        
        SaleResponseDTO respuesta = new SaleResponseDTO();
        respuesta.setId(venta.getId());
        respuesta.setFecha(venta.getFecha());
        respuesta.setTotal(venta.getTotal());
        respuesta.setIdEmpleado(venta.getEmpleadoId());
        
        return respuesta;
    }
}