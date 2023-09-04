package mk.ukim.finki.usersmanagement.domain.dtos;

import lombok.Data;
import mk.ukim.finki.sharedkernel.utils.NullableUtils;

@Data
public class UserFilter {
    String email;
    Boolean enabled;
    String firstName;
    String lastName;
    String phoneNumber;
    String role;


    public UserFilter(String email, Boolean enabled, String firstName, String lastName, String phoneNumber, String role){
        this.email = NullableUtils.getIfNotNullOrElse(email,String::toLowerCase, "");
        this.enabled = NullableUtils.getIfNotNull(enabled, Boolean::booleanValue);
        this.firstName = NullableUtils.getIfNotNullOrElse(firstName, String::toLowerCase, "");
        this.lastName = NullableUtils.getIfNotNullOrElse(lastName, String::toLowerCase, "");
        this.phoneNumber = NullableUtils.getIfNotNullOrElse(phoneNumber, String::toLowerCase, "");
        this.role = NullableUtils.getIfNotNullOrElse(role, String::toLowerCase, "");
    }
}
