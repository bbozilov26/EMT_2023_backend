package mk.ukim.finki.ordersmanagement.domain.repositories;

import mk.ukim.finki.ordersmanagement.domain.models.OrderedProduct;
import mk.ukim.finki.ordersmanagement.domain.models.ids.OrderedProductId;
import mk.ukim.finki.productsmanagement.domain.models.Product;
import mk.ukim.finki.usersmanagement.domain.models.User;
import mk.ukim.finki.usersmanagement.domain.models.ids.UserId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderedProductRepository extends JpaRepository<OrderedProduct, OrderedProductId> {

    @Query("select op from OrderedProduct op where op.id = :id and op.user.id = :userId")
    OrderedProduct findByIdAndUserId(OrderedProductId id, UserId userId);

    OrderedProduct findByProductAndUser(Product product, User shoppingCart);

    @Query("select op from OrderedProduct op where op.user.id = :id")
    List<OrderedProduct> findAllByUserId(UserId id);

}
