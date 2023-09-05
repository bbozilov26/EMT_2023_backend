package mk.ukim.finki.ordersmanagement.domain.dtos;

import lombok.Data;
import mk.ukim.finki.ordersmanagement.domain.models.ids.OrderedProductId;
import mk.ukim.finki.ordersmanagement.domain.models.ids.ShoppingCartId;
import mk.ukim.finki.productsmanagement.domain.models.ids.ProductId;
import mk.ukim.finki.usersmanagement.domain.models.ids.UserId;

import java.io.File;
import java.time.OffsetDateTime;

@Data
public class OrderedProductDTO {
    private OrderedProductId id;
    private OffsetDateTime dateCreated;
    private OffsetDateTime dateModified;
    private Integer quantity;
    private Double price;
    private Double totalPrice;
    private String title;
    private String description;
    private File image;
    private ProductId productId;
    private ShoppingCartId shoppingCartId;
}
