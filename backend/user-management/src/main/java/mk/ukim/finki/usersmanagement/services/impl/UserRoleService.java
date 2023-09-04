package mk.ukim.finki.usersmanagement.services.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.usersmanagement.domain.dtos.RoleDTO;
import mk.ukim.finki.usersmanagement.domain.models.Role;
import mk.ukim.finki.usersmanagement.domain.models.User;
import mk.ukim.finki.usersmanagement.domain.models.UserRole;
import mk.ukim.finki.usersmanagement.domain.models.ids.RoleId;
import mk.ukim.finki.usersmanagement.domain.repositories.UserRoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class UserRoleService {

    private final UserRoleRepository userRoleRepository;
    private final RoleService roleService;

    public UserRole getOrCreate(User user, RoleId roleId){
        Role role = roleService.findById(roleId).get();
        UserRole userRole = userRoleRepository.findByUserAndRole(user, role);

        if(userRole != null) return userRole;
        else return create(new UserRole(), user, role);
    }

    public UserRole create(UserRole userRole, User user, Role role){
        userRole.setUser(user);
        userRole.setRole(role);

        return userRoleRepository.save(userRole);
    }
}
