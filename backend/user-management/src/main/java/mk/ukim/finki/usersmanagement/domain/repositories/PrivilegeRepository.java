package mk.ukim.finki.usersmanagement.domain.repositories;

import mk.ukim.finki.usersmanagement.domain.models.Privilege;
import mk.ukim.finki.usersmanagement.domain.models.ids.PrivilegeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, PrivilegeId> {

    Optional<Privilege> findByPrivilege(String privilege);
}
