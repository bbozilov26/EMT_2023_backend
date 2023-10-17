package mk.ukim.finki.ordersmanagement.domain.converters;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.ordersmanagement.domain.dtos.OrderedProductDTO;
import mk.ukim.finki.ordersmanagement.domain.models.OrderOrderedProduct;
import mk.ukim.finki.ordersmanagement.domain.models.OrderedProduct;
import mk.ukim.finki.productsmanagement.domain.converters.ProductConverter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderedProductConverter {

    private final ProductConverter productConverter;

    public OrderedProductDTO toDTO(OrderedProduct orderedProduct){
        return new OrderedProductDTO(
                orderedProduct.getId(),
                orderedProduct.getDateCreated(),
                orderedProduct.getDateModified(),
                orderedProduct.getQuantity(),
                orderedProduct.getPrice(),
                orderedProduct.getTotalPrice(),
                orderedProduct.getTitle(),
                orderedProduct.getDescription(),
                orderedProduct.getImage(),
                productConverter.toProductDTO(orderedProduct.getProduct())
        );
    }

    public List<OrderedProductDTO> toDTOListOrder(List<OrderOrderedProduct> orderOrderedProducts){
        return orderOrderedProducts.stream()
                .map(oop -> toDTO(oop.getOrderedProduct()))
                .collect(Collectors.toList());
    }

    public List<OrderedProductDTO> toDTOListUser(List<OrderedProduct> orderedProducts){
        return orderedProducts.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
