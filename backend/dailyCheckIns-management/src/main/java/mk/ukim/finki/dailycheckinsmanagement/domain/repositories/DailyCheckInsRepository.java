package mk.ukim.finki.dailycheckinsmanagement.domain.repositories;

import mk.ukim.finki.dailycheckinsmanagement.domain.models.DailyCheckIn;
import mk.ukim.finki.dailycheckinsmanagement.domain.models.ids.DailyCheckInId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyCheckInsRepository extends JpaRepository<DailyCheckIn, DailyCheckInId> {
}
