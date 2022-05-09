package milosz.artur.it.conference.user;

import milosz.artur.it.conference.models.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    ResponseEntity<List<UserResponse>> findAll()  {
        List<UserResponse> userResponses = userService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(userResponses);
    }

    @GetMapping("/getByLogin{login}")
    User getByLogin(@PathVariable String login)
    {
        return userService.getByLogin(login);
    }
}
