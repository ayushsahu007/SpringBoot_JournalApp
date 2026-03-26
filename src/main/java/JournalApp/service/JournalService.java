package JournalApp.service;

import JournalApp.entity.JournalEntry;
import JournalApp.repository.JournalRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JournalService {

    @Autowired
    private JournalRepository journalRepository;

    public JournalEntry saveEntry(JournalEntry journalEntry){
      return   journalRepository.save(journalEntry);
    }


    public List<JournalEntry> getAllEntries(){
        return journalRepository.findAll();
    }

    public Optional<JournalEntry> findByID(ObjectId id){
        return journalRepository.findById(id);
    }

    public void deleteByID(ObjectId id){
        journalRepository.deleteById(id);
    }

}

