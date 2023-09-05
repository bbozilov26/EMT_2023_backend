package mk.ukim.finki.ordersmanagement.xport.controllers;

import lombok.AllArgsConstructor;
import mk.ukim.finki.ordersmanagement.domain.converters.OrderedProductConverter;
import mk.ukim.finki.ordersmanagement.domain.dtos.OrderedProductDTO;
import mk.ukim.finki.ordersmanagement.domain.models.ids.OrderedProductId;
import mk.ukim.finki.ordersmanagement.domain.models.ids.ShoppingCartId;
import mk.ukim.finki.ordersmanagement.services.impl.OrderedProductService;
import mk.ukim.finki.productsmanagement.domain.models.ids.ProductId;
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
    public List<OrderedProductDTO> findAllByShoppingCartId(@PathVariable ShoppingCartId id){
        return orderedProductConverter.toDTOList(orderedProductService.findAllByShoppingCartId(id));
    }

    @GetMapping("/{orderedProductId}/{shoppingCartId}")
    public OrderedProductDTO findByIdShoppingCartId(@PathVariable OrderedProductId orderedProductId, @PathVariable ShoppingCartId shoppingCartId){
        return orderedProductConverter.toDTO(orderedProductService.findByIdAndShoppingCartId(orderedProductId, shoppingCartId));
    }

    @PostMapping("/add/{productId}/{shoppingCartId}")
    public OrderedProductDTO addProductToShoppingCart(@PathVariable ProductId productId, @PathVariable ShoppingCartId shoppingCartId){
        return orderedProductConverter.toDTO(orderedProductService.addProductToShoppingCart(productId, shoppingCartId));
    }

    @PostMapping("/remove/{orderedProductId}/{shoppingCartId}")
    public void removeProductToShoppingCart(@PathVariable OrderedProductId orderedProductId, @PathVariable ShoppingCartId shoppingCartId){
        orderedProductService.removeProductFromShoppingCart(orderedProductId, shoppingCartId);
    }
}
