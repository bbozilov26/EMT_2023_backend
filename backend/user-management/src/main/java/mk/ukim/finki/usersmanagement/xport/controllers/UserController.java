package mk.ukim.finki.usersmanagement.xport.controllers;

import lombok.AllArgsConstructor;
import mk.ukim.finki.dailycheckinsmanagement.domain.models.ids.DailyCheckInId;
import mk.ukim.finki.usersmanagement.domain.converters.UserConverter;
import mk.ukim.finki.usersmanagement.domain.converters.UserDailyCheckInsConverter;
import mk.ukim.finki.usersmanagement.domain.dtos.UserCreationDTO;
import mk.ukim.finki.usersmanagement.domain.dtos.UserDTO;
import mk.ukim.finki.usersmanagement.domain.dtos.UserDailyCheckInDTO;
import mk.ukim.finki.usersmanagement.domain.dtos.UserFilter;
import mk.ukim.finki.usersmanagement.domain.models.ids.UserId;
import mk.ukim.finki.usersmanagement.services.impl.UserDailyCheckInsService;
import mk.ukim.finki.usersmanagement.services.impl.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserConverter userConverter;
    private final UserDailyCheckInsService userDailyCheckInsService;
    private final UserDailyCheckInsConverter userDailyCheckInsConverter;

    @GetMapping("/all")
    public List<UserDTO> getAll(){
        return userConverter.toUserDTOList(userService.findAll());
    }

    @GetMapping("/{id}")
    public UserDTO getById(@PathVariable UserId id) {
        return userConverter.toUserDTO(userService.findById(id).get());
    }

    @PostMapping("/all-paged")
    public Page<UserDTO> findAllPaged(
            @PageableDefault Pageable pageable,
            @RequestBody UserFilter userFilter
    ) {
        return userService.findAllPaged(userFilter, pageable).map(userConverter::toUserDTO);
    }

    @PostMapping("/create")
    public UserDTO create(@RequestBody UserCreationDTO userCreationDTO){
        return userConverter.toUserDTO(userService.create(userCreationDTO));
    }

    @PutMapping("/update/{id}")
    public UserDTO edit(@PathVariable UserId id, @RequestBody UserCreationDTO userCreationDTO){
        return userConverter.toUserDTO(userService.edit(id, userCreationDTO));
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable UserId id){
        userService.delete(id);
    }

    @PutMapping("/claim-daily-check-in")
    public void claimDailyCheckIn(@RequestBody UserDailyCheckInDTO userDailyCheckInDTO){
        userService.claimDailyCheckIn(userDailyCheckInDTO);
    }

    @Scheduled(cron = "0 0 0 * * *")
    @PostMapping("/reset-daily-check-ins")
    public void resetDailyCheckIns(){
        userService.resetDailyCheckIns();
    }

    @GetMapping("/all-daily-check-ins/{userId}")
    public List<UserDailyCheckInDTO> getAllDailyCheckInsByUser(@PathVariable UserId userId){
        return userDailyCheckInsConverter.toDTOList(userDailyCheckInsService.findAllByUser(userId));
    }
}
