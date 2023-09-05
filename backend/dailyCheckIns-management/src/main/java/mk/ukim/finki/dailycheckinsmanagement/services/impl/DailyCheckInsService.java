package mk.ukim.finki.dailycheckinsmanagement.services.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.dailycheckinsmanagement.domain.dtos.DailyCheckInCreationDTO;
import mk.ukim.finki.dailycheckinsmanagement.domain.dtos.DailyCheckInDTO;
import mk.ukim.finki.dailycheckinsmanagement.domain.models.DailyCheckIn;
import mk.ukim.finki.dailycheckinsmanagement.domain.models.ids.DailyCheckInId;
import mk.ukim.finki.dailycheckinsmanagement.domain.repositories.DailyCheckInsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class DailyCheckInsService {

    private final DailyCheckInsRepository dailyCheckInsRepository;

    public List<DailyCheckIn> findAll(){
        return dailyCheckInsRepository.findAll();
    }

    public Optional<DailyCheckIn> findById(DailyCheckInId id){
        return dailyCheckInsRepository.findById(id);
    }

    public DailyCheckIn create(DailyCheckInCreationDTO dailyCheckInDTO){
        return fillProperties(new DailyCheckIn(), dailyCheckInDTO);
    }

    public DailyCheckIn edit(DailyCheckInId id, DailyCheckInCreationDTO dailyCheckInDTO){
        return fillProperties(this.findById(id).get(), dailyCheckInDTO);
    }

    public DailyCheckIn fillProperties(DailyCheckIn dailyCheckIn, DailyCheckInCreationDTO dailyCheckInDTO){
        dailyCheckIn.setDailyReward(dailyCheckInDTO.getDailyReward());
        dailyCheckIn.setLabel(dailyCheckInDTO.getLabel());
        dailyCheckIn.setDescription(dailyCheckInDTO.getDescription());

        return dailyCheckInsRepository.save(dailyCheckIn);
    }

    public void delete(DailyCheckInId id){
        dailyCheckInsRepository.deleteById(id);
    }
}
