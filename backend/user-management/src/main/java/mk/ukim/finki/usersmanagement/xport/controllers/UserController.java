package mk.ukim.finki.usersmanagement.xport.controllers;

import lombok.AllArgsConstructor;
import mk.ukim.finki.usersmanagement.domain.dtos.UserDTO;
import mk.ukim.finki.usersmanagement.domain.models.ids.UserId;
import mk.ukim.finki.usersmanagement.services.impl.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/all")
    public List<UserDTO> getAll(){
        return userConverter.toUserDTOList(userService.findAll());
    }

    @GetMapping("/{id}")
    public UserDTO getById(@PathVariable UserId id) {
        return userConverter.toUserDTO(userService.findById(id));
    }

//    @PreAuthorize("@privilegeGuard.loggedUserHasAllPrivileges(@privilegeGuard.READ_USERS)")
    @PostMapping("/all-paged")
    public Page<UserDTO> findAllPaged(
            @PageableDefault Pageable pageable,
            @RequestBody UserDTO userFilter
    ) {
        return userService.findAllPaged(userFilter, pageable).map(userConverter::toUserDTO);
    }

    @PostMapping("/create")
    public UserDTO create(@RequestBody UserDTO userDTO){
        return userService.create(userDTO);
    }

    @PutMapping("/edit/{id}")
    public UserDTO edit(@PathVariable UserId id, @RequestBody UserDTO userDTO){
        return userService.edit(id, userDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void create(@PathVariable UserId id){
        userService.delete(id);
    }
}
