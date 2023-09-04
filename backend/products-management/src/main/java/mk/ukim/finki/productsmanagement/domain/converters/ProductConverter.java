package mk.ukim.finki.productsmanagement.domain.converters;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.productsmanagement.domain.dtos.ProductDTO;
import mk.ukim.finki.productsmanagement.domain.models.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductConverter {

    public ProductDTO toProductDTO(Product product){
        return new ProductDTO(
                product.getId(),
                product.getDateCreated(),
                product.getDateModified(),
                product.getQuantity(),
                product.getPrice(),
                product.getImage(),
                product.getTitle(),
                product.getDescription(),
                product.getCategory()
        );
    }

    public List<ProductDTO> toProductDTOList(List<Product> products){
        return products.stream()
                .map(this::toProductDTO)
                .collect(Collectors.toList());
    }
}
