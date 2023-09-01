package mk.ukim.finki.dailycheckinsmanagement.xport.controllers;

import lombok.AllArgsConstructor;
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

    @GetMapping
    public List<DailyCheckIn> findAll(){
        return dailyCheckInsService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<DailyCheckIn> findById(@PathVariable DailyCheckInId id){
        return dailyCheckInsService.findById(id);
    }

    @PostMapping("/create")
    public DailyCheckIn create(@RequestBody DailyCheckInDTO dailyCheckInDTO){
        return dailyCheckInsService.create(dailyCheckInDTO);
    }

    @PutMapping("/edit/{id}")
    public DailyCheckIn edit(@PathVariable DailyCheckInId id, @RequestBody DailyCheckInDTO dailyCheckInDTO){
        return dailyCheckInsService.edit(id, dailyCheckInDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable DailyCheckInId id){
        dailyCheckInsService.delete(id);
    }
}
