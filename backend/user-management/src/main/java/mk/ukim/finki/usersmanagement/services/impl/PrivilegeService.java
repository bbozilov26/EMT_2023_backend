package mk.ukim.finki.usersmanagement.services.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.usersmanagement.domain.dtos.PrivilegeDTO;
import mk.ukim.finki.usersmanagement.domain.models.Privilege;
import mk.ukim.finki.usersmanagement.domain.models.ids.PrivilegeId;
import mk.ukim.finki.usersmanagement.domain.repositories.PrivilegeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class PrivilegeService {

    private final PrivilegeRepository privilegeRepository;

    public List<Privilege> findAll(){
        return privilegeRepository.findAll();
    }

    public Optional<Privilege> findById(PrivilegeId id){
        return privilegeRepository.findById(id);
    }

    public Privilege getOrCreate(PrivilegeDTO privilegeDTO){
        return privilegeRepository.findByPrivilege(privilegeDTO.getPrivilege()).isPresent() ?
                privilegeRepository.findByPrivilege(privilegeDTO.getPrivilege()).get()
                : create(new Privilege(), privilegeDTO);
    }

    public Privilege create(Privilege privilege, PrivilegeDTO privilegeDTO){
        privilege.setPrivilege(privilegeDTO.getPrivilege());
        privilege.setLabel(privilegeDTO.getLabel());
        privilege.setDescription(privilegeDTO.getDescription());

        return privilegeRepository.save(privilege);
    }
}
