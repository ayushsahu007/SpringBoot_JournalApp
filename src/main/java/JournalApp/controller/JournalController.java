package JournalApp.controller;


import JournalApp.entity.JournalEntry;
import JournalApp.service.JournalService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/journals")
public class JournalController {

    @Autowired
    private JournalService journalService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<JournalEntry> all = journalService.getAllEntries();
         if (all != null && all.isEmpty()){
             return  new ResponseEntity<>(all,HttpStatus.OK);
         }
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry){
       try {
           myEntry.setDate(LocalDateTime.now());
           journalService.saveEntry(myEntry);
           return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
       }catch (Exception e){
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry >getEntryById(@PathVariable ObjectId myId){
        Optional<JournalEntry> journalEntry =  journalService.findByID(myId);
         if (journalEntry.isEmpty()){
             return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{myId}")
    public ResponseEntity<?> deleteEntry(@PathVariable ObjectId myId){
        journalService.deleteByID(myId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("id/{id}")
    public ResponseEntity<?> updateEntry(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry){
      JournalEntry old =    journalService.findByID(id).orElse(null);
        if (old!= null){
             old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals(" ") ? newEntry.getTitle() : old.getTitle());
             old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals(" ") ? newEntry.getContent() : old.getContent());
            journalService.saveEntry(old);
            return new ResponseEntity<>(old, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }



}
