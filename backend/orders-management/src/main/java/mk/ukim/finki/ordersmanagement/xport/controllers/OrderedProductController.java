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
@RequestMapping("/ordered-products")
@CrossOrigin("*")
@AllArgsConstructor
public class OrderedProductController {

    private final OrderedProductService orderedProductService;
    private final OrderedProductConverter orderedProductConverter;

    @GetMapping("/all/{userId}")
    public List<OrderedProductDTO> findAllByShoppingCartId(@PathVariable UserId userId){
        return orderedProductConverter.toDTOList(orderedProductService.findAllByUserId(userId));
    }

    @GetMapping("/{orderedProductId}/{userId}")
    public OrderedProductDTO findByIdShoppingCartId(@PathVariable OrderedProductId orderedProductId, @PathVariable UserId userId){
        return orderedProductConverter.toDTO(orderedProductService.findByIdAndUserId(orderedProductId, userId));
    }

    @GetMapping("/add/{productId}/{userId}")
    public OrderedProductDTO addProductToShoppingCart(@PathVariable ProductId productId, @PathVariable UserId userId){
        return orderedProductConverter.toDTO(orderedProductService.addProductToShoppingCart(productId, userId));
    }

    @DeleteMapping("/remove/{orderedProductId}/{userId}")
    public void removeProductToShoppingCart(@PathVariable OrderedProductId orderedProductId, @PathVariable UserId userId){
        orderedProductService.removeProductFromShoppingCart(orderedProductId, userId);
    }
}
