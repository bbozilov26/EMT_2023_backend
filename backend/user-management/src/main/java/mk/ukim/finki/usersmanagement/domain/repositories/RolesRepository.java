package mk.ukim.finki.usersmanagement.domain.repositories;

import mk.ukim.finki.usersmanagement.domain.models.Roles;
import mk.ukim.finki.usersmanagement.domain.models.ids.RoleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<Roles, RoleId> {
}
