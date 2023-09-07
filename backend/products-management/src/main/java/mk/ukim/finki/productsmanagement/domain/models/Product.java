package mk.ukim.finki.productsmanagement.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.productsmanagement.domain.models.enums.Category;
import mk.ukim.finki.productsmanagement.domain.models.ids.ProductId;
import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.io.File;
import java.time.OffsetDateTime;

@Entity
@Table(name = "mm_products", schema = "metamodels")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product extends AbstractEntity<ProductId> {

    private OffsetDateTime dateCreated;
    private OffsetDateTime dateModified;
    private Integer quantity;
    private Double price;
    private File image;
    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private Category category;
}
