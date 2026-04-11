package JournalApp.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordDebug {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void testPassword() {
        String raw = "password123";
        String encoded = "$2a$10$BxyhntVXUvSF7YEj4uFiH.2rIFFVzIU3vhLJJfa9eMmycUcHHWQWu";

        System.out.println("Password Match: " + passwordEncoder.matches(raw, encoded));
    }
}
