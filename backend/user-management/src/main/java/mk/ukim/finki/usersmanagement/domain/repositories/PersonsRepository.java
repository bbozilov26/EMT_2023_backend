package mk.ukim.finki.usersmanagement.domain.repositories;

import mk.ukim.finki.usersmanagement.domain.models.Persons;
import mk.ukim.finki.usersmanagement.domain.models.ids.PersonId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonsRepository extends JpaRepository<Persons, PersonId> {
}
