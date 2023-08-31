package mk.ukim.finki.usersmanagement.services.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.usersmanagement.domain.dtos.PrivilegeDTO;
import mk.ukim.finki.usersmanagement.domain.models.Privilege;
import mk.ukim.finki.usersmanagement.domain.models.Role;
import mk.ukim.finki.usersmanagement.domain.models.RolePrivilege;
import mk.ukim.finki.usersmanagement.domain.repositories.RolePrivilegeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class RolePrivilegeService {

    private final RolePrivilegeRepository rolePrivilegeRepository;
    private final PrivilegeService privilegeService;

    public RolePrivilege getOrCreate(Role role, PrivilegeDTO privilegeDTO){
        Privilege privilege = privilegeService.getOrCreate(privilegeDTO);
        RolePrivilege rolePrivilege = rolePrivilegeRepository.findByRoleAndPrivilege(role, privilege);

        if(rolePrivilege != null) return rolePrivilege;
        else return create(new RolePrivilege(), role, privilege);
    }

    public RolePrivilege create(RolePrivilege rolePrivilege, Role role, Privilege privilege){
        rolePrivilege.setRole(role);
        rolePrivilege.setPrivilege(privilege);

        return rolePrivilegeRepository.save(rolePrivilege);
    }
}
