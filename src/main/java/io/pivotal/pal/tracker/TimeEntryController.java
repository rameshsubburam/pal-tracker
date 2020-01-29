package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
public class TimeEntryController {
    private final TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {

        this.timeEntryRepository = timeEntryRepository;
    }
    @PostMapping ("/time-entries")
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntryToCreate) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(timeEntryRepository.create(timeEntryToCreate));
    }

    @GetMapping("/time-entries/{timeEntryId}")
    public ResponseEntity<TimeEntry> read(@PathVariable long timeEntryId) {
        TimeEntry timeEntryFound = timeEntryRepository.find(timeEntryId);
        if(timeEntryFound == null){
            return ResponseEntity
                    .notFound().build();
        }
        else {
            return ResponseEntity
                    .ok()
                    .body(timeEntryFound);
        }
    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> listTimeEntry= timeEntryRepository.list();
        return ResponseEntity
                .ok()
                .body(listTimeEntry);

    }

    @PutMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> update(@PathVariable("id")  long timeEntryId,@RequestBody TimeEntry timeEntryToUpdate) {
        TimeEntry timeEntryFound = timeEntryRepository.update(timeEntryId,timeEntryToUpdate);
        if(timeEntryFound == null){
            return ResponseEntity
                    .notFound().build();
        }
        else {
            return ResponseEntity
                    .ok()
                    .body(timeEntryFound);
        }
    }
    @DeleteMapping("/time-entries/{timeEntryId}")
    public ResponseEntity<Void> delete(@PathVariable long timeEntryId) {
        timeEntryRepository.delete(timeEntryId);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT).build();
                //.body(timeEntryRepository.delete(timeEntryId));
    }
}
