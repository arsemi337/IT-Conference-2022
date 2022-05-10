package milosz.artur.it.conference.user.services;

import milosz.artur.it.conference.models.ReadUserResponse;
import milosz.artur.it.conference.models.UpdateUserRequest;
import milosz.artur.it.conference.user.domain.User;
import milosz.artur.it.conference.user.domain.UserRepository;
import milosz.artur.it.conference.user.ex.UserNotFoundException;
import milosz.artur.it.conference.user.ex.UserWithGivenLoginAlreadyExistsException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    UserService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    public List<ReadUserResponse> findAll()  {
        List<ReadUserResponse> responses = new ArrayList<>();
        List<User> users = userRepository.findAll();
        for (User user : users)
        {
            responses.add(user.toUserResponse());
        }
        return responses;
    }

    public User getByLogin(String login)
    {
        return userRepository.getUserByLogin(login).orElseThrow(() -> new UserNotFoundException(login));
    }

    public User tryToCreateNewUser(String login, String email)
    {
        Optional<User> userOptional = userRepository.getUserByLogin(login);
        if (userOptional.isEmpty())
        {
            return userRepository.save(new User(login, email));
        } else {
            User user = userOptional.get();
            if (!user.getEmail().equals(email))
            {
                throw new UserWithGivenLoginAlreadyExistsException();
            }
            return user;
        }
    }

    public void updateEmail(UpdateUserRequest updateUserRequest)
    {
        String login = updateUserRequest.getLogin();
        String oldEmail = updateUserRequest.getOldEmail();
        String newEmail = updateUserRequest.getNewEmail();
        User user = userRepository.getUserByLoginAndEmail(login, oldEmail)
                .orElseThrow(() -> new UserNotFoundException(login, oldEmail));
        user.setEmail(newEmail);
        userRepository.save(user);
    }
}
