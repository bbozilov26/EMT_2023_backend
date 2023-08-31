package mk.ukim.finki.usersmanagement.domain.repositories;

import mk.ukim.finki.usersmanagement.domain.models.Privileges;
import mk.ukim.finki.usersmanagement.domain.models.ids.PrivilegeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegesRepository extends JpaRepository<Privileges, PrivilegeId> {
}
