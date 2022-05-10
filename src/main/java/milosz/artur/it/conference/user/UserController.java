package milosz.artur.it.conference.user;

import milosz.artur.it.conference.models.ReadUserResponse;
import milosz.artur.it.conference.user.domain.User;
import milosz.artur.it.conference.user.services.UserService;
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
    ResponseEntity<List<ReadUserResponse>> findAll()  {
        List<ReadUserResponse> readUserRespons = userService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(readUserRespons);
    }

    @GetMapping("/getByLogin{login}")
    User getByLogin(@PathVariable String login)
    {
        return userService.getByLogin(login);
    }
}
