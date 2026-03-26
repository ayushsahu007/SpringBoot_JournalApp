package JournalApp.controller;


import JournalApp.entity.JournalEntry;
import JournalApp.service.JournalService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/journals")
public class JournalController {

    @Autowired
    private JournalService journalService;

    @GetMapping
    public List<JournalEntry> getAll(){
        return journalService.getAllEntries();
    }

    @PostMapping
    public JournalEntry createEntry(@RequestBody JournalEntry myEntry){
        myEntry.setDate(LocalDateTime.now());
         journalService.saveEntry(myEntry);
         return myEntry;
    }

    @GetMapping("id/{myId}")
    public JournalEntry getEntryById(@PathVariable ObjectId myId){
        return journalService.findByID(myId).orElse(null);
    }

    @DeleteMapping("id/{myId}")
    public boolean deleteEntry(@PathVariable ObjectId myId){
        journalService.deleteByID(myId);
        return true;
    }

    @PutMapping("id/{id}")
    public JournalEntry updateEntry(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry){
      JournalEntry old =    journalService.findByID(id).orElse(null);
        if (old!= null){
             old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals(" ") ? newEntry.getTitle() : old.getTitle());
             old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals(" ") ? newEntry.getContent() : old.getContent());
        }
        journalService.saveEntry(old);
        return old ;
    }



}
