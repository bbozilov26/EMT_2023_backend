package mk.ukim.finki.ordersmanagement.services.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.ordersmanagement.domain.dtos.ShoppingCartCreationDTO;
import mk.ukim.finki.ordersmanagement.domain.models.OrderedProduct;
import mk.ukim.finki.ordersmanagement.domain.models.ShoppingCart;
import mk.ukim.finki.ordersmanagement.domain.models.ids.OrderedProductId;
import mk.ukim.finki.ordersmanagement.domain.models.ids.ShoppingCartId;
import mk.ukim.finki.ordersmanagement.domain.repositories.ShoppingCartRepository;
import mk.ukim.finki.productsmanagement.domain.models.Product;
import mk.ukim.finki.productsmanagement.domain.models.ids.ProductId;
import mk.ukim.finki.productsmanagement.services.impl.ProductService;
import mk.ukim.finki.usersmanagement.domain.models.User;
import mk.ukim.finki.usersmanagement.domain.models.ids.UserId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;

    public Optional<ShoppingCart> findById(ShoppingCartId id){
        return shoppingCartRepository.findById(id);
    }

    public ShoppingCart findByIdAndUserId(ShoppingCartId id, UserId userId){
        return shoppingCartRepository.findByIdAndUserId(id, userId);
    }

    public ShoppingCart create(User user, ShoppingCartCreationDTO shoppingCartCreationDTO){
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        shoppingCart.setDateCreated(OffsetDateTime.now());

        return fillProperties(shoppingCart, shoppingCartCreationDTO);
    }

    public ShoppingCart edit(ShoppingCartId id, ShoppingCartCreationDTO shoppingCartCreationDTO){
        return fillProperties(findById(id).get(), shoppingCartCreationDTO);
    }

    public ShoppingCart fillProperties(ShoppingCart shoppingCart, ShoppingCartCreationDTO shoppingCartCreationDTO){
        shoppingCart.setDateModified(OffsetDateTime.now());
        shoppingCart.setTotalPrice(shoppingCartCreationDTO.getTotalPrice());

//        List<OrderedProduct> alreadyOrderedProducts = shoppingCart.getOrderedProducts();
//        List<OrderedProduct> newOrderedProducts = new ArrayList<>();
//        if(!alreadyOrderedProducts.isEmpty()){
//            List<OrderedProductId> alreadyOrderedProductIds = alreadyOrderedProducts.stream().map(OrderedProduct::getId).toList();
//            shoppingCartCreationDTO.getOrderedProductIds().forEach(orderedProductId -> {
//
//            });
//        }
//        shoppingCart.setOrderedProducts(newOrderedProducts);

        return shoppingCartRepository.save(shoppingCart);
    }

    public void save(ShoppingCart shoppingCart){
        shoppingCartRepository.save(shoppingCart);
    }
}
