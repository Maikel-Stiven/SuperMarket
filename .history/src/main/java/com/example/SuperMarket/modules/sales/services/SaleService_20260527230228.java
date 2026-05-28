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

    public SaleService(SaleRepository saleRepository, SaleDetailRepository saleDetailRepository, EmployeeRepository employeeRepository){
        this.saleRepository = saleRepository;
        this.saleDetailRepository = saleDetailRepository;
        this.employeeRepository = employeeRepository;
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
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + item.getPrductoId()));

            if (producto.getStock() < item.getCantidad()){
                throw new RuntimeException("")
            }        
        }
    }
}
