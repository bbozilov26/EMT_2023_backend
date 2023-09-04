package mk.ukim.finki.productsmanagement.domain.dtos;

import lombok.Data;
import mk.ukim.finki.productsmanagement.domain.models.enums.Category;

import java.io.File;
import java.time.OffsetDateTime;

@Data
public class ProductCreationDTO {
    private Integer quantity;
    private Double price;
    private File image;
    private String title;
    private String description;
    private Category category;
}
