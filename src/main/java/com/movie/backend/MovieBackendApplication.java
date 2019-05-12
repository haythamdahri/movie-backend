package com.movie.backend;

import com.movie.backend.dao.RoleRepository;
import com.movie.backend.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class MovieBackendApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public static void main(String[] args) {
        SpringApplication.run(MovieBackendApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        /*
        // Create new Users
        User user1 = new User(null, "haytham.dahri@gmail.com","$2y$12$t0XSeFvlmo3udAqe9HfQauszoM9qNMPv3RLA1JeeZuRAshHAS1gK2",
                "haythamdahri", "haytham", "dahri", "http://www.haythamimage.net", null);
        User user2 = new User(null, "imrane.dahri@gmail.com","$2y$12$t0XSeFvlmo3udAqe9HfQauszoM9qNMPv3RLA1JeeZuRAshHAS1gK2",
                "imranedahri", "imrane", "dahri", "http://www.imraneimage.net", null);
        User user3 = new User(null, "asmae.dahri@gmail.com","$2y$12$t0XSeFvlmo3udAqe9HfQauszoM9qNMPv3RLA1JeeZuRAshHAS1gK2",
                "asmaedahri", "asmae", "dahri", "http://www.asmaeimage.net", null);

        // Save user into database
        user1 = this.userRepository.save(user1);
        user2 = this.userRepository.save(user2);
        user3 = this.userRepository.save(user3);

        // Create new roles
        Role admin = new Role(null, RoleType.ROLE_ADMIN, null);
        Role manager = new Role(null, RoleType.ROLE_MANAGER, null);
        Role moderator = new Role(null, RoleType.ROLE_MODERATOR, null);
        Role user = new Role(null, RoleType.ROLE_USER, null);

        // Save roles into database
        admin = this.roleRepository.save(admin);
        manager = this.roleRepository.save(manager);
        moderator = this.roleRepository.save(moderator);
        user = this.roleRepository.save(user);

        // Set role to the users
        user1.addRole(admin);
        user1.addRole(manager);
        user1.addRole(moderator);

        user2.addRole(admin);
        user2.addRole(manager);
        user2.addRole(user);

        user3.addRole(user);
        user3.addRole(moderator);

        // Save users
        user1 = this.userRepository.save(user1);
        user3 = this.userRepository.save(user2);
        user3 = this.userRepository.save(user3);
        */


    }

}
