package mk.ukim.finki.ordersmanagement.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.ordersmanagement.domain.models.ids.OrderedProductId;
import mk.ukim.finki.productsmanagement.domain.models.Products;
import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;

import javax.persistence.*;
import java.io.File;
import java.time.OffsetDateTime;

@Entity
@Table(name = "mm_ordered_products", schema = "metamodels")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderedProducts extends AbstractEntity<OrderedProductId> {

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
    private Products product;
}
