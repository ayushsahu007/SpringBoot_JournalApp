package JournalApp.controller;

import JournalApp.entity.JournalEntry;
import JournalApp.entity.User;
import JournalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @PostMapping("/create-user")
    public void createUser(@RequestBody User user){
        userService.saveNewUser(user);
    }


}
