package JournalApp.service;

import JournalApp.entity.JournalEntry;
import JournalApp.entity.User;
import JournalApp.repository.JournalRepository;
import JournalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
           User user = userService.findByUserName(userName);
           journalEntry.setDate(LocalDateTime.now());
           JournalEntry saved =   journalRepository.save(journalEntry);
           user.getJournalEntries().add(saved);
           userService.saveEntry(user);
       }catch (Exception e){
            e.printStackTrace();

       }
      }

    public void saveEntry(JournalEntry journalEntry){
        journalRepository.save(journalEntry);

    }


    public List<JournalEntry> getAllEntries(){
        return journalRepository.findAll();
    }

    public Optional<JournalEntry> findByID(ObjectId id){
        return journalRepository.findById(id);
    }

    @Transactional
    public boolean deleteByID(ObjectId id,String userName){
        boolean removed = false;
       try {
           User user = userService.findByUserName(userName);
            removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
           if (removed){
               userService.saveEntry(user);
               journalRepository.deleteById(id);
           }
       }catch (Exception e){
           System.out.println(e);
           throw new RuntimeException("An Error occurred while deleteing the entry"+ e);
       }
       return removed;
    }



}

