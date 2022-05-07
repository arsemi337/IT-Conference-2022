package milosz.artur.it.conference;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface RegistrationRepository extends JpaRepository<Registration, UUID> {
}
