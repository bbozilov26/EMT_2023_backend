package mk.ukim.finki.productsmanagement.domain.dtos;

import lombok.Data;
import mk.ukim.finki.productsmanagement.domain.models.enums.Category;
import mk.ukim.finki.sharedkernel.utils.NullableUtils;
import mk.ukim.finki.sharedkernel.utils.StringUtils;

@Data
public class ProductFilter {
    private Double minPrice;
    private Double maxPrice;
    private String title;
    private Category category;

    public ProductFilter(Double minPrice, Double maxPrice, String title, Category category){
        this.minPrice = NullableUtils.getIfNotNullOrElse(minPrice, Double::doubleValue, 0.0);
        this.maxPrice = NullableUtils.getIfNotNullOrElse(minPrice, Double::doubleValue, 1000000.0);
        this.title = NullableUtils.getIfNotNullOrElse(title, String::toLowerCase, "");
        this.category = Category.valueOf(StringUtils.toLowerCaseIfNotNull(category.toString()));
    }
}
