package milosz.artur.it.conference.user;

import milosz.artur.it.conference.models.UserResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    UserService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    List<UserResponse> findAll()  {
        List<UserResponse> responses = new ArrayList<>();
        List<User> users = userRepository.findAll();
        for (User user : users)
        {
            responses.add(user.toUserResponse());
        }
        return responses;
    }

    User getByLogin(String login)
    {
        return userRepository.getUserByLogin(login);
    }
}
