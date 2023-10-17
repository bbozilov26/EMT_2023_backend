package mk.ukim.finki.ordersmanagement.services.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.ordersmanagement.domain.models.OrderOrderedProduct;
import mk.ukim.finki.ordersmanagement.domain.models.OrderedProduct;
import mk.ukim.finki.ordersmanagement.domain.models.ids.OrderId;
import mk.ukim.finki.ordersmanagement.domain.models.ids.OrderedProductId;
import mk.ukim.finki.ordersmanagement.domain.repositories.OrderOrderedProductRepository;
import mk.ukim.finki.ordersmanagement.domain.repositories.OrderedProductRepository;
import mk.ukim.finki.productsmanagement.domain.models.Product;
import mk.ukim.finki.productsmanagement.domain.models.ids.ProductId;
import mk.ukim.finki.productsmanagement.services.impl.ProductService;
import mk.ukim.finki.usersmanagement.domain.models.User;
import mk.ukim.finki.usersmanagement.domain.models.ids.UserId;
import mk.ukim.finki.usersmanagement.services.impl.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class OrderedProductService {

    private final OrderedProductRepository orderedProductRepository;
    private final OrderOrderedProductRepository orderOrderedProductRepository;
    private final ProductService productService;
    private final UserService userService;

    public List<OrderedProduct> findAllByUserId(UserId userId){
        return orderedProductRepository.findAllByUserId(userId);
    }

    public List<OrderOrderedProduct> findAllByOrderId(OrderId orderId){
        return orderOrderedProductRepository.findAllByOrderId(orderId);
    }

    public Optional<OrderedProduct> findById(OrderedProductId id){
        return orderedProductRepository.findById(id);
    }

    public OrderedProduct findByIdAndUserId(OrderedProductId id, UserId userId){
        return orderedProductRepository.findByIdAndUserId(id, userId);
    }

    public OrderedProduct addProductToShoppingCart(ProductId productId, UserId userId){
        Product product = productService.findById(productId).get();
        User user = userService.findById(userId).get();

        OrderedProduct orderedProduct = orderedProductRepository.findByProductAndUser(product, user);
        if(orderedProduct == null) {
            orderedProduct = new OrderedProduct();
            return create(orderedProduct, product, user);
        }
        else{
            orderedProduct.setQuantity(orderedProduct.getQuantity() + 1);
            orderedProduct.setTotalPrice(orderedProduct.getPrice() * orderedProduct.getQuantity());
            orderedProduct.setDateModified(OffsetDateTime.now());
            return orderedProductRepository.save(orderedProduct);
        }
    }

    public OrderedProduct create(OrderedProduct orderedProduct, Product product, User user){
        orderedProduct.setDateCreated(OffsetDateTime.now());
        orderedProduct.setDateModified(OffsetDateTime.now());
        orderedProduct.setQuantity(1);
        orderedProduct.setPrice(product.getPrice());
        orderedProduct.setTotalPrice(orderedProduct.getQuantity() * orderedProduct.getPrice());
        orderedProduct.setTitle(product.getTitle());
        orderedProduct.setDescription(product.getDescription());
        orderedProduct.setImage(product.getImage());
        orderedProduct.setProduct(product);
        orderedProduct.setUser(user);

        return orderedProductRepository.save(orderedProduct);
    }

    public void removeProductFromShoppingCart(OrderedProductId orderedProductId, UserId userId){
        OrderedProduct orderedProduct = findByIdAndUserId(orderedProductId, userId);
        Integer orderedProductQuantity = orderedProduct.getQuantity();
        User user = orderedProduct.getUser();
        Double totalPrice = 0.0;
        for(OrderedProduct op : findAllByUserId(userId)){
            totalPrice += op.getTotalPrice();
        }

        if(orderedProductQuantity > 0 && orderedProductQuantity != 1){
            orderedProductQuantity -= 1;
            orderedProduct.setQuantity(orderedProductQuantity);
            orderedProduct.setTotalPrice(orderedProductQuantity * orderedProduct.getPrice());
            orderedProduct.setDateModified(OffsetDateTime.now());
            orderedProductRepository.save(orderedProduct);

            totalPrice -= orderedProduct.getPrice();
            user.setCreditDebt(totalPrice);
            userService.save(user);
        } else if(orderedProductQuantity == 1){
            totalPrice -= orderedProduct.getPrice();
            orderedProductRepository.deleteById(orderedProductId);
            user.setCreditDebt(totalPrice);
            userService.save(user);
        }
    }
}
