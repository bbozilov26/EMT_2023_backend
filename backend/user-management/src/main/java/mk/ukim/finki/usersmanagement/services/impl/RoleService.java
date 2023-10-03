package mk.ukim.finki.usersmanagement.services.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.usersmanagement.domain.dtos.RoleDTO;
import mk.ukim.finki.usersmanagement.domain.models.Role;
import mk.ukim.finki.usersmanagement.domain.models.RolePrivilege;
import mk.ukim.finki.usersmanagement.domain.models.ids.RoleId;
import mk.ukim.finki.usersmanagement.domain.repositories.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class RoleService {

    private final RoleRepository roleRepository;
    private final RolePrivilegeService rolePrivilegeService;

    public List<Role> findAll(){
        return roleRepository.findAll();
    }

    public Optional<Role> findById(RoleId id){
        return roleRepository.findById(id);
    }

    public Role getOrCreate(RoleDTO roleDTO){
        return roleRepository.findByRole(roleDTO.getRole()).isPresent() ?
                roleRepository.findByRole(roleDTO.getRole()).get()
                : create(new Role(), roleDTO);
    }

    public Role create(Role role, RoleDTO roleDTO){
        role.setRole(roleDTO.getRole());
        role.setLabel(roleDTO.getLabel());

        roleRepository.save(role);
        List<RolePrivilege> rolePrivileges = new ArrayList<>();
        roleDTO.getPrivilegeDTOs().forEach(privilegeDTO ->
                rolePrivileges.add(rolePrivilegeService.getOrCreate(role, privilegeDTO))
        );
        role.setRolePrivileges(rolePrivileges);

        return roleRepository.save(role);
    }

    public Role findByLabel(String label){
        return roleRepository.findByLabel(label).isPresent() ?
                roleRepository.findByLabel(label).get() : null;
    }
}
