package mk.ukim.finki.productsmanagement.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import mk.ukim.finki.productsmanagement.domain.models.enums.Category;
import mk.ukim.finki.productsmanagement.domain.models.ids.ProductId;
import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;

import javax.persistence.*;
import java.io.File;
import java.time.OffsetDateTime;

@Entity
@Table(name = "mm_product")
@Data
@AllArgsConstructor
public class Product extends AbstractEntity<ProductId> {

    private OffsetDateTime dateCreated;
    private OffsetDateTime dateModified;
    private Integer quantity;
    private Double price;
    private byte[] image;
    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private Category category;

    public Product() {
        super(ProductId.randomId(ProductId.class));
    }
}
