package mk.ukim.finki.dailycheckinsmanagement.xport.controllers;

import lombok.AllArgsConstructor;
import mk.ukim.finki.dailycheckinsmanagement.domain.converters.DailyCheckInConverter;
import mk.ukim.finki.dailycheckinsmanagement.domain.dtos.DailyCheckInCreationDTO;
import mk.ukim.finki.dailycheckinsmanagement.domain.dtos.DailyCheckInDTO;
import mk.ukim.finki.dailycheckinsmanagement.domain.models.DailyCheckIn;
import mk.ukim.finki.dailycheckinsmanagement.domain.models.ids.DailyCheckInId;
import mk.ukim.finki.dailycheckinsmanagement.services.impl.DailyCheckInsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/daily_check_ins")
@CrossOrigin("*")
@AllArgsConstructor
public class DailyCheckInController {

    private final DailyCheckInsService dailyCheckInsService;
    private final DailyCheckInConverter dailyCheckInConverter;

    @GetMapping("/all")
    public List<DailyCheckInDTO> findAll(){
        return dailyCheckInConverter.toDTOList(dailyCheckInsService.findAll());
    }

    @GetMapping("/{id}")
    public DailyCheckInDTO findById(@PathVariable DailyCheckInId id){
        return dailyCheckInConverter.toDTO(dailyCheckInsService.findById(id).get());
    }

    @PostMapping("/create")
    public DailyCheckInDTO create(@RequestBody DailyCheckInCreationDTO dailyCheckInDTO){
        return dailyCheckInConverter.toDTO(dailyCheckInsService.create(dailyCheckInDTO));
    }

    @PutMapping("/edit/{id}")
    public DailyCheckInDTO edit(@PathVariable DailyCheckInId id, @RequestBody DailyCheckInCreationDTO dailyCheckInDTO){
        return dailyCheckInConverter.toDTO(dailyCheckInsService.edit(id, dailyCheckInDTO));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable DailyCheckInId id){
        dailyCheckInsService.delete(id);
    }
}
