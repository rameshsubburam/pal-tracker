package io.pivotal.pal.tracker;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {
    private final Map<Long,TimeEntry> timeEntryHashMap = new HashMap<>();
    private  final AtomicLong idCounter = new AtomicLong(1L);
    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        long i = idCounter.getAndIncrement();
        TimeEntry timeEntryRec =new TimeEntry( i,
                timeEntry.getProjectId(),
                timeEntry.getUserId(),
                timeEntry.getDate(),
                timeEntry.getHours());

        timeEntryHashMap.put(i++, timeEntryRec);
        
        return timeEntryRec;
    }

    @Override
    public TimeEntry find(long timeEntryId) {

        return  timeEntryHashMap.get(timeEntryId);
    }

    @Override
    public void delete(long timeEntryId) {
        timeEntryHashMap.remove(timeEntryId);

    }

    @Override
    public List<TimeEntry> list() {
        return new ArrayList<>(timeEntryHashMap.values());
    }

    @Override
    public TimeEntry update(long id, TimeEntry timeEntry) {
        if(timeEntryHashMap.containsKey(id)) {
            TimeEntry timeEntryRec = new TimeEntry(id,
                    timeEntry.getProjectId(),
                    timeEntry.getUserId(),
                    timeEntry.getDate(),
                    timeEntry.getHours());

            timeEntryHashMap.put(id, timeEntryRec);

            return timeEntryRec;
        }
       else
        {
            return null;
        }
    }
}

