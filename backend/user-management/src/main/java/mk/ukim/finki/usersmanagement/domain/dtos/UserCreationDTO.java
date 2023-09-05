package mk.ukim.finki.usersmanagement.domain.dtos;

import lombok.Data;
import mk.ukim.finki.ordersmanagement.domain.dtos.ShoppingCartCreationDTO;
import mk.ukim.finki.usersmanagement.domain.models.ids.RoleId;

import java.time.OffsetDateTime;
import java.util.List;

@Data
public class UserCreationDTO {
    private String email;
    private String password;
    private PersonCreationDTO personDTO;
    private List<RoleId> roleIds;
    private ShoppingCartCreationDTO shoppingCartCreationDTO;
}
