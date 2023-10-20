package mk.ukim.finki.usersmanagement.services.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.usersmanagement.domain.dtos.UserDailyCheckInClaimDTO;
import mk.ukim.finki.usersmanagement.domain.dtos.UserDailyCheckInDTO;
import mk.ukim.finki.usersmanagement.domain.models.Privilege;
import mk.ukim.finki.usersmanagement.domain.models.UserDailyCheckIn;
import mk.ukim.finki.usersmanagement.domain.dtos.UserCreationDTO;
import mk.ukim.finki.usersmanagement.domain.dtos.UserFilter;
import mk.ukim.finki.usersmanagement.domain.exceptions.InvalidUsernameOrPasswordException;
import mk.ukim.finki.usersmanagement.domain.exceptions.UserAlreadyExistsException;
import mk.ukim.finki.usersmanagement.domain.exceptions.UserNotFoundException;
import mk.ukim.finki.usersmanagement.domain.models.User;
import mk.ukim.finki.usersmanagement.domain.models.ids.UserId;
import mk.ukim.finki.usersmanagement.domain.repositories.UserRepository;
import mk.ukim.finki.usersmanagement.security.exceptions.InvalidUserCredentialsException;
import mk.ukim.finki.usersmanagement.security.exceptions.PasswordsDoNotMatchException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PersonService personService;
    private final RoleService roleService;
    private final UserDailyCheckInsService userDailyCheckInsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public User register(UserCreationDTO userDTO){
        String email = userDTO.getEmail();
        String password = userDTO.getPassword();
        String repeatPassword = userDTO.getRepeatPassword();

        if (email == null || email.isEmpty()
                || password == null || password.isEmpty())
            throw new InvalidUsernameOrPasswordException();

        if (!password.equals(repeatPassword))
            throw new PasswordsDoNotMatchException();

        if(this.userRepository.findByEmail(email).isPresent())
            throw new UserAlreadyExistsException(email);

        return create(userDTO);
    }

    public User save(User user){
        return userRepository.save(user);
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

    public List<User> filterUsers(UserFilter filter){
        int enabledCheck=-1;

        if (filter.getEnabled() != null) {
            if (filter.getEnabled().equals(true)) {
                enabledCheck = 1;
            } else if (filter.getEnabled().equals(false)) {
                enabledCheck = 0;
            }
        }

        return userRepository.findAllFiltered(filter, enabledCheck);
    }

    public Optional<User> findById(UserId id) {
        return userRepository.findById(id);
    }

    public User create(UserCreationDTO userDTO) {
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        user.setEnabled(true);
        user.setCreditBalance(0.0);
        user.setStreak(0);
        user.setDateCreated(OffsetDateTime.now());
        userRepository.save(user);

        return fillProperties(user, userDTO);
    }

    public User edit(UserId id, UserCreationDTO userDTO) {
        return fillProperties(findById(id).get(), userDTO);
    }

    private User fillProperties(User user, UserCreationDTO userDTO){
        user.setPerson(user.getPerson() == null ?
                personService.createOrUpdate(null, userDTO.getPersonDTO())
                : personService.createOrUpdate(user.getPerson(), userDTO.getPersonDTO())
        );
        userRepository.save(user);

        user.setRole(roleService.findByLabel("ROLE_CUSTOMER"));
        user.setUserDailyCheckIns(userDailyCheckInsService.bindWithUser(user));
        user.setDateModified(OffsetDateTime.now());

        return userRepository.save(user);
    }

    public void delete(UserId id) {
        userRepository.deleteById(id);
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email).get();
    }

    public User findByEmailAndPassword(String email, String password){
        User user = findByEmail(email);

        if(!bCryptPasswordEncoder.matches(password, user.getPassword())){
            throw new InvalidUserCredentialsException();
        }

        return user;
    }

    public User claimDailyCheckIn(UserDailyCheckInClaimDTO userDailyCheckInClaimDTO){
        User user = findById(userDailyCheckInClaimDTO.getUserId()).isPresent() ?
                findById(userDailyCheckInClaimDTO.getUserId()).get()
                : null;

        if(user == null) throw new UserNotFoundException();

        else {
            UserDailyCheckIn userDailyCheckIn = userDailyCheckInsService.claimDailyCheckIn(userDailyCheckInClaimDTO);
            user.setCreditBalance(user.getCreditBalance() + userDailyCheckIn.getDailyCheckIn().getDailyReward());
            user.setStreak(user.getStreak() + 1);
            user.setDateModified(OffsetDateTime.now());

            return userRepository.save(user);
        }
    }

    public void resetDailyCheckIns(){
        userRepository.findAll().forEach(user -> {
            userDailyCheckInsService.resetDailyCheckIn(user);
            userRepository.save(user);
        });
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return (UserDetails) userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(username));
//    }

    public List<Privilege> getUserPrivileges(String email) {
        User user = findByEmail(email);
        return userRepository.getUserPrivileges(user);
    }

    public boolean passwordMatches(User user, String password) {
        return bCryptPasswordEncoder.matches(password, user.getPassword());
    }
}
