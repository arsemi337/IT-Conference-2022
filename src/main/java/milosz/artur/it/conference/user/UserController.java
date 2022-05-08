package milosz.artur.it.conference.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    UserController(UserService userService)
    {
        this.userService = userService;
    }

    @GetMapping("/users")
    List<User> all()  {
        return userService.getAll();
    }

    @GetMapping("/getByLogin{login}")
    User getByLogin(@PathVariable String login)
    {
        return userService.getByLogin(login);
    }
}
