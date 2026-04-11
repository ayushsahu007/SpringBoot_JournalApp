package JournalApp.controller;


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
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAll();
    }



    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userInDB =  userService.findByUserName(userName);

          userInDB.setUserName(user.getUserName());
          userInDB.setPassword(user.getPassword());
          userService.saveNewUser(userInDB);

     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public void deleteUser(@RequestBody User user){
        userService.deleteByID(user.getId());
    }


}
