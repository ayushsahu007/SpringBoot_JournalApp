package JournalApp.service;
import JournalApp.entity.User;
import JournalApp.repository.UserRepository;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void saveEntry(User journalEntry){
         userRepository.save(journalEntry);
    }

    public List<User> getAllEntries(){
        return userRepository.findAll();
    }

    public Optional<User> findByID(ObjectId id){
        return userRepository.findById(id);
    }

    public void deleteByID(ObjectId id){
        userRepository.deleteById(id);
    }

    public User findByUserName(String username) {
        return userRepository.findByUserName(username);

    }
}

