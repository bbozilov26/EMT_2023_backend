package mk.ukim.finki.productsmanagement.domain.dtos;

import lombok.Data;
import mk.ukim.finki.productsmanagement.domain.models.enums.Category;
import mk.ukim.finki.productsmanagement.domain.models.ids.ProductId;

import java.io.File;
import java.time.OffsetDateTime;

@Data
public class ProductDTO {
    private final ProductId id;
    private final OffsetDateTime dateCreated;
    private final OffsetDateTime dateModified;
    private final Integer quantity;
    private final Double price;
    private final byte[] image;
    private final String title;
    private final String description;
    private final Category category;
}
