package mk.ukim.finki.ordersmanagement.domain.repositories;

import mk.ukim.finki.ordersmanagement.domain.models.OrderedProduct;
import mk.ukim.finki.ordersmanagement.domain.models.ShoppingCart;
import mk.ukim.finki.ordersmanagement.domain.models.ids.OrderedProductId;
import mk.ukim.finki.ordersmanagement.domain.models.ids.ShoppingCartId;
import mk.ukim.finki.productsmanagement.domain.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderedProductRepository extends JpaRepository<OrderedProduct, OrderedProductId> {

    @Query("select op from OrderedProduct op where op.id = :id and op.shoppingCart.id = :shoppingCartId")
    OrderedProduct findByIdAndShoppingCartId(OrderedProductId id, ShoppingCartId shoppingCartId);

    OrderedProduct findByProductAndShoppingCart(Product product, ShoppingCart shoppingCart);

    @Query("select op from OrderedProduct op where op.shoppingCart.id = :id")
    List<OrderedProduct> findAllByShoppingCartId(ShoppingCartId id);

}
