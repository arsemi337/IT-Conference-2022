package milosz.artur.it.conference.user;

import milosz.artur.it.conference.models.ReadUserResponse;
import milosz.artur.it.conference.models.UpdateUserRequest;
import milosz.artur.it.conference.user.domain.User;
import milosz.artur.it.conference.user.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        List<ReadUserResponse> readUserResponse = userService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(readUserResponse);
    }

    @GetMapping("/getByLogin{login}")
    User getByLogin(@PathVariable String login)
    {
        return userService.getByLogin(login);
    }

    @PutMapping("/users/updateEmail")
    ResponseEntity<String> updateEmail(@RequestBody UpdateUserRequest updateUserRequest)
    {
        userService.updateEmail(updateUserRequest);
        return ResponseEntity.status(HttpStatus.OK).body("Email został zaaktualizowany");
    }
}
