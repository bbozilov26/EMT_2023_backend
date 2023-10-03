package mk.ukim.finki.usersmanagement.domain.repositories;


import mk.ukim.finki.usersmanagement.domain.models.LoginSession;
import mk.ukim.finki.usersmanagement.domain.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoginSessionRepository extends JpaRepository<LoginSession,Long> {

    Page<LoginSession> findAllByUser(User user, Pageable pageable);

    LoginSession findFirstByUserOrderByIdDesc(User user);

    List<LoginSession> findAllByUser(User user);
}
