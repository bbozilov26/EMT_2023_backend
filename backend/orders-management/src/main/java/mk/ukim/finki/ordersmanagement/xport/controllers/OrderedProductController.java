package mk.ukim.finki.ordersmanagement.xport.controllers;

import lombok.AllArgsConstructor;
import mk.ukim.finki.ordersmanagement.domain.converters.OrderedProductConverter;
import mk.ukim.finki.ordersmanagement.domain.dtos.OrderedProductDTO;
import mk.ukim.finki.ordersmanagement.domain.models.ids.OrderedProductId;
import mk.ukim.finki.ordersmanagement.services.impl.OrderedProductService;
import mk.ukim.finki.productsmanagement.domain.models.ids.ProductId;
import mk.ukim.finki.usersmanagement.domain.models.ids.UserId;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ordered_products")
@CrossOrigin("*")
@AllArgsConstructor
public class OrderedProductController {

    private final OrderedProductService orderedProductService;
    private final OrderedProductConverter orderedProductConverter;

    @GetMapping("/all/{id}")
    public List<OrderedProductDTO> findAllByShoppingCartId(@PathVariable UserId id){
        return orderedProductConverter.toDTOList(orderedProductService.findAllByUserId(id));
    }

    @GetMapping("/{orderedProductId}/{userId}")
    public OrderedProductDTO findByIdShoppingCartId(@PathVariable OrderedProductId orderedProductId, @PathVariable UserId userId){
        return orderedProductConverter.toDTO(orderedProductService.findByIdAndUserId(orderedProductId, userId));
    }

    @PostMapping("/add/{productId}/{userId}")
    public OrderedProductDTO addProductToShoppingCart(@PathVariable ProductId productId, @PathVariable UserId userId){
        return orderedProductConverter.toDTO(orderedProductService.addProductToShoppingCart(productId, userId));
    }

    @PostMapping("/remove/{orderedProductId}/{userId}")
    public void removeProductToShoppingCart(@PathVariable OrderedProductId orderedProductId, @PathVariable UserId userId){
        orderedProductService.removeProductFromShoppingCart(orderedProductId, userId);
    }
}
