package mk.ukim.finki.usersmanagement.domain.repositories;

import mk.ukim.finki.usersmanagement.domain.models.Privilege;
import mk.ukim.finki.usersmanagement.domain.models.Role;
import mk.ukim.finki.usersmanagement.domain.models.RolePrivilege;
import mk.ukim.finki.usersmanagement.domain.models.ids.RolePrivilegesId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolePrivilegeRepository extends JpaRepository<RolePrivilege, RolePrivilegesId> {

    RolePrivilege findByRoleAndPrivilege(Role role, Privilege privilege);
}
