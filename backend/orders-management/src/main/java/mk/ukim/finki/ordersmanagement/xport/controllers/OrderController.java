package mk.ukim.finki.ordersmanagement.xport.controllers;

import lombok.AllArgsConstructor;
import mk.ukim.finki.ordersmanagement.domain.converters.OrderConverter;
import mk.ukim.finki.ordersmanagement.domain.dtos.OrderCreationDTO;
import mk.ukim.finki.ordersmanagement.domain.dtos.OrderDTO;
import mk.ukim.finki.ordersmanagement.domain.models.ids.OrderId;
import mk.ukim.finki.ordersmanagement.services.impl.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin("*")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderConverter orderConverter;

    @GetMapping("/all")
    public List<OrderDTO> findAll(){
        return orderConverter.toOrderDTOList(orderService.findAll());
    }

    @GetMapping("/{id}")
    public OrderDTO findById(@PathVariable OrderId id){
        return orderConverter.toOrderDTO(orderService.findById(id).get());
    }

    @PostMapping("/create")
    public OrderDTO create(@RequestBody OrderCreationDTO orderCreationDTO){
        return orderConverter.toOrderDTO(orderService.create(orderCreationDTO));
    }

    @PutMapping("/update/{id}")
    public OrderDTO edit(@PathVariable OrderId id, @RequestBody OrderCreationDTO orderCreationDTO){
        return orderConverter.toOrderDTO(orderService.edit(id, orderCreationDTO));
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable OrderId id){
        orderService.deleteOrderHistory(id);
    }

    @PutMapping("/cancel-or-confirm/{id}")
    public OrderDTO cancelOrConfirm(@PathVariable OrderId id, OrderCreationDTO orderCreationDTO){
        return orderConverter.toOrderDTO(orderService.cancelOrConfirmOrder(id, orderCreationDTO));
    }
}
