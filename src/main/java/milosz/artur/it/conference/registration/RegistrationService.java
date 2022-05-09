package milosz.artur.it.conference.registration;

import milosz.artur.it.conference.lecture.Lecture;
import milosz.artur.it.conference.lecture.LectureRepository;
import milosz.artur.it.conference.user.User;
import milosz.artur.it.conference.user.UserRepository;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class RegistrationService {
    private final RegistrationRepository registrationRepository;
    private final UserRepository userRepository;
    private final LectureRepository lectureRepository;

    RegistrationService(RegistrationRepository registrationRepository, UserRepository userRepository, LectureRepository lectureRepository)
    {
        this.registrationRepository = registrationRepository;
        this.userRepository = userRepository;
        this.lectureRepository = lectureRepository;
    }

    List<Registration> getAll()
    {
        return registrationRepository.findAll();
    }

    List<Registration> getRegistrationsByUserId(UUID id)
    {
        return registrationRepository.getRegistrationsByUserId(id);
    }

    public void createRegistration(User user, Lecture lecture)
    {
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
}
