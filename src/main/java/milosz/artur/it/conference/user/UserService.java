package milosz.artur.it.conference.user;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    UserService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    List<User> getAll()  {
        return userRepository.findAll();
    }

    User getByLogin(String login)
    {
        return userRepository.getUserByLogin(login);
    }
}
