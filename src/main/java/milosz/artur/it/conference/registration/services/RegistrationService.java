package milosz.artur.it.conference.registration.services;

import milosz.artur.it.conference.lecture.domain.Lecture;
import milosz.artur.it.conference.registration.domain.Registration;
import milosz.artur.it.conference.registration.domain.RegistrationRepository;
import milosz.artur.it.conference.registration.ex.RegistrationNotFound;
import milosz.artur.it.conference.user.domain.User;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class RegistrationService {
    private final RegistrationRepository registrationRepository;

    RegistrationService(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    public Registration getRegistrationById(UUID uuid) {
        return registrationRepository.findById(uuid).orElseThrow(() -> new RegistrationNotFound(uuid));
    }

    public void createRegistration(User user, Lecture lecture) {
        registrationRepository.save(new Registration(user.getId(), lecture.getId()));
    }

    public void sendConfirmationEmail(User user) throws IOException {
        FileWriter fileWriter = new FileWriter("powiadomienia", true);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        BufferedReader br = new BufferedReader(new FileReader("powiadomienia"));
        if (br.readLine() != null) {
            printWriter.append('\n');
        }
        printWriter.append(formatter.format(date) + '\t' + user.getEmail() + '\t' + "\"" + user.getLogin() + ", twoja rezerwacja została wykonana.\"");
        printWriter.close();
    }

    public void deleteRegistration(UUID uuid) {
        Registration registration = getRegistrationById(uuid);
        registrationRepository.delete(registration);
    }
}
