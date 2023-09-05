package mk.ukim.finki.ordersmanagement.services.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.ordersmanagement.domain.models.OrderedProduct;
import mk.ukim.finki.ordersmanagement.domain.models.ShoppingCart;
import mk.ukim.finki.ordersmanagement.domain.models.ids.OrderedProductId;
import mk.ukim.finki.ordersmanagement.domain.models.ids.ShoppingCartId;
import mk.ukim.finki.ordersmanagement.domain.repositories.OrderedProductRepository;
import mk.ukim.finki.productsmanagement.domain.models.Product;
import mk.ukim.finki.productsmanagement.domain.models.ids.ProductId;
import mk.ukim.finki.productsmanagement.services.impl.ProductService;
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
    private final ProductService productService;
    private final ShoppingCartService shoppingCartService;

    public List<OrderedProduct> findAllByShoppingCartId(ShoppingCartId shoppingCartId){
        return orderedProductRepository.findAllByShoppingCartId(shoppingCartId);
    }

    public Optional<OrderedProduct> findById(OrderedProductId id){
        return orderedProductRepository.findById(id);
    }

    public OrderedProduct findByIdAndShoppingCartId(OrderedProductId id, ShoppingCartId shoppingCartId){
        return orderedProductRepository.findByIdAndShoppingCartId(id, shoppingCartId);
    }

    public OrderedProduct addProductToShoppingCart(ProductId productId, ShoppingCartId shoppingCartId){
        Product product = productService.findById(productId).get();
        ShoppingCart shoppingCart = shoppingCartService.findById(shoppingCartId).get();

        OrderedProduct orderedProduct = orderedProductRepository.findByProductAndShoppingCart(product, shoppingCart);
        if(orderedProduct == null) {
            orderedProduct = new OrderedProduct();
            return create(orderedProduct, product, shoppingCart);
        }
        else{
            shoppingCart.setTotalPrice(shoppingCart.getTotalPrice() + orderedProduct.getPrice());
            shoppingCart.setDateModified(OffsetDateTime.now());
            shoppingCartService.save(shoppingCart);

            orderedProduct.setQuantity(orderedProduct.getQuantity() + 1);
            orderedProduct.setTotalPrice(orderedProduct.getPrice() * orderedProduct.getQuantity());
            orderedProduct.setDateModified(OffsetDateTime.now());
            return orderedProductRepository.save(orderedProduct);
        }
    }

    public OrderedProduct create(OrderedProduct orderedProduct, Product product, ShoppingCart shoppingCart){
        orderedProduct.setDateCreated(OffsetDateTime.now());
        orderedProduct.setDateModified(OffsetDateTime.now());
        orderedProduct.setQuantity(1);
        orderedProduct.setPrice(product.getPrice());
        orderedProduct.setTotalPrice(orderedProduct.getQuantity() * orderedProduct.getPrice());
        orderedProduct.setTitle(product.getTitle());
        orderedProduct.setDescription(product.getDescription());
        orderedProduct.setImage(product.getImage());
        orderedProduct.setProduct(product);
        orderedProduct.setShoppingCart(shoppingCart);

        return orderedProductRepository.save(orderedProduct);
    }

    public void removeProductFromShoppingCart(OrderedProductId orderedProductId, ShoppingCartId shoppingCartId){
        OrderedProduct orderedProduct = findByIdAndShoppingCartId(orderedProductId, shoppingCartId);
        Integer orderedProductQuantity = orderedProduct.getQuantity();
        ShoppingCart shoppingCart = orderedProduct.getShoppingCart();
        Double totalPrice = shoppingCart.getTotalPrice();

        if(orderedProductQuantity > 0 && orderedProductQuantity != 1){
            orderedProductQuantity -= 1;
            orderedProduct.setQuantity(orderedProductQuantity);
            orderedProduct.setTotalPrice(orderedProductQuantity * orderedProduct.getPrice());
            orderedProduct.setDateModified(OffsetDateTime.now());
            orderedProductRepository.save(orderedProduct);

            totalPrice -= orderedProduct.getPrice();
            shoppingCart.setTotalPrice(totalPrice);
            shoppingCart.setDateModified(OffsetDateTime.now());
            shoppingCartService.save(shoppingCart);
        } else if(orderedProductQuantity == 1){
            totalPrice -= orderedProduct.getPrice();
            shoppingCart.setTotalPrice(totalPrice);
            shoppingCart.setDateModified(OffsetDateTime.now());
            shoppingCartService.save(shoppingCart);
            orderedProductRepository.deleteById(orderedProductId);
        }
    }
}
