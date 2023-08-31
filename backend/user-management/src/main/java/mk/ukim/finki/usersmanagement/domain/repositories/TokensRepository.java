package mk.ukim.finki.usersmanagement.domain.repositories;

import mk.ukim.finki.usersmanagement.domain.models.Tokens;
import mk.ukim.finki.usersmanagement.domain.models.ids.TokenId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokensRepository extends JpaRepository<Tokens, TokenId> {
}
