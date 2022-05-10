package milosz.artur.it.conference.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> getUserByLogin(String login);

    Optional<User> getUserByLoginAndEmail(String login, String email);
}
