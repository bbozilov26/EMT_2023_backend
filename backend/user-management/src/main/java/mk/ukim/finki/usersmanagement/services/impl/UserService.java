package mk.ukim.finki.usersmanagement.services.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.dailycheckinsmanagement.domain.dtos.UserDailyCheckInDTO;
import mk.ukim.finki.dailycheckinsmanagement.domain.models.UserDailyCheckIn;
import mk.ukim.finki.dailycheckinsmanagement.services.impl.UserDailyCheckInsService;
import mk.ukim.finki.quizmanagement.domain.dtos.QuizGivenAnswersDTO;
import mk.ukim.finki.quizmanagement.services.impl.QuizQuestionService;
import mk.ukim.finki.usersmanagement.domain.dtos.UserCreationDTO;
import mk.ukim.finki.usersmanagement.domain.dtos.UserDTO;
import mk.ukim.finki.usersmanagement.domain.dtos.UserFilter;
import mk.ukim.finki.usersmanagement.domain.exceptions.UserAlreadyExistsException;
import mk.ukim.finki.usersmanagement.domain.exceptions.UserNotFoundException;
import mk.ukim.finki.usersmanagement.domain.models.User;
import mk.ukim.finki.usersmanagement.domain.models.UserRole;
import mk.ukim.finki.usersmanagement.domain.models.ids.UserId;
import mk.ukim.finki.usersmanagement.domain.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PersonService personService;
    private final UserRoleService userRoleService;
    private final UserDailyCheckInsService userDailyCheckInsService;
    private final QuizQuestionService quizQuestionService;

    public User register(UserCreationDTO userDTO){
        return create(userDTO);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Page<User> findAllPaged(UserFilter userFilter, Pageable pageable) {
        int enabledCheck=-1;

        if (userFilter.getEnabled() != null) {
            if (userFilter.getEnabled().equals(true)) {
                enabledCheck = 1;
            } else if (userFilter.getEnabled().equals(false)) {
                enabledCheck = 0;
            }
        }

        return userRepository.findAllPaged(userFilter, enabledCheck, pageable);
    }

    public Optional<User> findById(UserId id) {
        return userRepository.findById(id);
    }

    public User create(UserCreationDTO userDTO) {
        User user = new User();
        user.setDateCreated(OffsetDateTime.now());
        user.setEnabled(true);
        user.setCreditBalance(0.0);

        return fillProperties(user, userDTO);
    }

    public User edit(UserId id, UserCreationDTO userDTO) {
        return fillProperties(findById(id).get(), userDTO);
    }

    private User fillProperties(User user, UserCreationDTO userDTO){
        if(findByEmail(userDTO.getEmail()) != null){
            throw new UserAlreadyExistsException();
        }

        user.setEmail(user.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setDateModified(OffsetDateTime.now());
        user.setPerson(user.getPerson() == null ?
                personService.createOrUpdate(null, userDTO.getPersonDTO())
                : personService.createOrUpdate(user.getPerson(), userDTO.getPersonDTO())
        );

        userRepository.save(user);
        List<UserRole> userRoles = new ArrayList<>();
        userDTO.getRoleIds().forEach(roleId ->
                userRoles.add(userRoleService.getOrCreate(user, roleId))
        );
        user.setUserRoles(userRoles);

        user.setUserDailyCheckIns(userDailyCheckInsService.bindWithUser(user));

        return userRepository.save(user);
    }

    public void delete(UserId id) {
        userRepository.deleteById(id);
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public void claimDailyCheckIn(UserDailyCheckInDTO userDailyCheckInDTO){
        User user = findById(userDailyCheckInDTO.getUserId()).isPresent() ?
                findById(userDailyCheckInDTO.getUserId()).get()
                : null;

        if(user == null) throw new UserNotFoundException();

        else {
            UserDailyCheckIn userDailyCheckIn = userDailyCheckInsService.claimDailyCheckIn(userDailyCheckInDTO);
            user.setCreditBalance(user.getCreditBalance() + userDailyCheckIn.getDailyCheckIn().getDailyReward());
            user.setDateModified(OffsetDateTime.now());

            userRepository.save(user);
        }
    }

    public void submitQuiz(QuizGivenAnswersDTO quizGivenAnswersDTO){
        User user = findById(quizGivenAnswersDTO.getUserId()).get();
        Double quizRewards = quizQuestionService.submitQuiz(quizGivenAnswersDTO);
        user.setCreditBalance(user.getCreditBalance() + quizRewards);

        userRepository.save(user);
    }
}
