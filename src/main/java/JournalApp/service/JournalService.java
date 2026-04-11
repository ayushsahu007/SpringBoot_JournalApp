package JournalApp.service;

import JournalApp.entity.JournalEntry;
import JournalApp.entity.User;
import JournalApp.repository.JournalRepository;
import JournalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JournalService {



    @Autowired
    private JournalRepository journalRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void saveEntry(JournalEntry journalEntry,String userName){
       try {
           User user =     userService.findByUserName(userName);
           journalEntry.setDate(LocalDateTime.now());
           JournalEntry saved =   journalRepository.save(journalEntry);
           user.getJournalEntries().add(saved);
           userRepository.save(user);
       }catch (Exception e){
            e.printStackTrace();
       }
      }


    public List<JournalEntry> getAllEntries(){
        return journalRepository.findAll();
    }

    public Optional<JournalEntry> findByID(ObjectId id){
        return journalRepository.findById(id);
    }

    public void deleteByID(ObjectId id,String userName){
        User user = userService.findByUserName(userName);
        user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        userService.saveEntry(user);
        journalRepository.deleteById(id);
    }

    public void saveEntry(JournalEntry journalEntry){
        journalRepository.save(journalEntry);

    }

}

