package mk.ukim.finki.ordersmanagement.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.ordersmanagement.domain.models.ids.OrderedProductId;
import mk.ukim.finki.productsmanagement.domain.models.Product;
import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.usersmanagement.domain.models.User;

import javax.persistence.*;
import java.io.File;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "mm_ordered_products", schema = "mm_orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderedProduct extends AbstractEntity<OrderedProductId> {

    private OffsetDateTime dateCreated;
    private OffsetDateTime dateModified;
    private Integer quantity;
    private Double price;
    private Double totalPrice;
    private String title;
    private String description;
    private File image;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mm_product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mm_order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ur_user_id")
    private User user;
}
