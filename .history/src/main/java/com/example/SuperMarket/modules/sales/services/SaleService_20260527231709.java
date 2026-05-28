package com.example.SuperMarket.modules.sales.services;

import com.example.SuperMarket.modules.personal.entities.Employee;
import com.example.SuperMarket.modules.personal.repositories.EmployeeRepository;
import com.example.SuperMarket.modules.inventory.entities.Product;
import com.example.SuperMarket.modules.inventory.repositories.ProductRepository;
import com.example.SuperMarket.modules.sales.dtos.CreateSaleDTO;
import com.example.SuperMarket.modules.sales.dtos.SaleItemDTO;
import com.example.SuperMarket.modules.sales.entities.Sale;
import com.example.SuperMarket.modules.sales.entities.SaleDetail;
import com.example.SuperMarket.modules.sales.repositories.SaleDetailRepository;
import com.example.SuperMarket.modules.sales.repositories.SaleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SaleService {
    
    private final SaleRepository saleRepository;
    private final SaleDetailRepository saleDetailRepository;
    private final EmployeeRepository employeeRepository;
    private final ProductRepository productRepository;

    public SaleService(SaleRepository saleRepository,
                       SaleDetailRepository saleDetailRepository,
                       EmployeeRepository employeeRepository,
                       ProductRepository productRepository) {
        this.saleRepository = saleRepository;
        this.saleDetailRepository = saleDetailRepository;
        this.employeeRepository = employeeRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public Sale registrarVenta(CreateSaleDTO dto){
        Employee empleado = employeeRepository.findById(dto.getEmpleadoId())
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado con ID: " + dto.getEmpleadoId()));

        Sale venta = new Sale();
        venta.setEmpleado(empleado);
        
        List<SaleDetail> detalles = new ArrayList<>();
        double subtotalAcumulado = 0.0;

        for (SaleItemDTO item : dto.getItems()){
            Product producto = productRepository.findById(item.getProductoId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + item.getProductoId()));

            if (producto.getStock() < item.getCantidad()){
                throw new RuntimeException("Stock insuficiente del producto " + producto.getNombre()
                        + " Stock actual: " + producto.getStock() + ", solicitado: " + item.getCantidad());
            }
            
            producto.setStock(producto.getStock() - item.getCantidad());
            productRepository.save(producto);
            
            SaleDetail detalle = new SaleDetail();
            detalle.setProducto(producto);
            detalle.setCantidad(item.getCantidad());
            detalle.setPrecioUnitario(producto.getPrecio());

            double subtotalItem = producto.getPrecio() * item.getCantidad();
            detalle.setSubtotal(subtotalItem);

            detalles.add(detalle);
            subtotalAcumulado += subtotalItem;
        }

        double impuesto = subtotalAcumulado * 0.18;
        double total = subtotalAcumulado + impuesto;

        venta.setSubtotal(subtotalAcumulado);
        venta.setIva(impuesto);
        venta.setTotal(total);
        venta.setDetalles(detalles);

        return saleRepository.save(venta);
    }
}
