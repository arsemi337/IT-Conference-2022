package milosz.artur.it.conference;

import milosz.artur.it.conference.lecture.domain.Lecture;
import milosz.artur.it.conference.lecture.domain.LectureRepository;
import milosz.artur.it.conference.registration.domain.RegistrationRepository;
import milosz.artur.it.conference.user.domain.User;
import milosz.artur.it.conference.user.domain.UserRepository;
import org.springframework.context.annotation.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, LectureRepository lectureRepository, RegistrationRepository registrationRepository) {

        return args -> {
            log.info("Preloading " + userRepository.save(new User("Thief", "thief@email.com")));
            log.info("Preloading " + userRepository.save(new User("Crust", "crust@email.com")));

            log.info(("Preloading " + lectureRepository.save(new Lecture("First path", "First lecture", "10:00", 5))));
            log.info(("Preloading " + lectureRepository.save(new Lecture("Second path", "Second lecture", "12:00", 5))));
            log.info(("Preloading " + lectureRepository.save(new Lecture("Third path", "Third lecture", "14:00", 5))));

            log.info(("Preloading " + lectureRepository.save(new Lecture("First path", "First lecture", "10:00", 5))));
            log.info(("Preloading " + lectureRepository.save(new Lecture("Second path", "Second lecture", "12:00", 5))));
            log.info(("Preloading " + lectureRepository.save(new Lecture("Third path", "Third lecture", "14:00", 5))));

            log.info(("Preloading " + lectureRepository.save(new Lecture("First path", "First lecture", "10:00", 5))));
            log.info(("Preloading " + lectureRepository.save(new Lecture("Second path", "Second lecture", "12:00", 5))));
            log.info(("Preloading " + lectureRepository.save(new Lecture("Third path", "Third lecture", "14:00", 5))));
        };
    }
}