public class Event {
    private int time;
    private EventType eventType;
    private Job job;

    public Event(int time, EventType eventType, Job job) {
        this.time = time;
        this.eventType = eventType;
        this.job = job;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }
}