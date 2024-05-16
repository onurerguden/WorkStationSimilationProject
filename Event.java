import java.util.ArrayList;

public class Event {
    private double time;
    private EventType eventType;
    private static ArrayList<Job> jobTypes = new ArrayList<Job>();
    private static ArrayList<Station> stations =new ArrayList<>();


    public Event(EventType eventType, double time, ArrayList<Job> jobTypes, ArrayList<Station> stations) {
        this.eventType = eventType;
        this.time = time;
        this.jobTypes = jobTypes;
        this.stations=stations;

    }

    public  EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public ArrayList<Job> getJobTypes() {
        return jobTypes;
    }

    public void setJobTypes(ArrayList<Job> jobTypes) {
        this.jobTypes = jobTypes;
    }

    public static ArrayList<Station> getStations() {
        return stations;
    }

    public static void setStations(ArrayList<Station> stations) {
        Event.stations = stations;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public static void printJobs(){
        for (Job job :jobTypes){
            System.out.print("Job ID: " + job.getJobID() + ", Job TypeID: " + job.getJobTypeID().getJobTypeID() +
                    ", Start Time: " + job.getStartTime() + ", Duration: " + job.getDuration() +
                    " minutes, Deadline: " + job.getDeadline()+", Job Type: "+ job.getJobType()+" Tasks: ");
            printTasks();
            System.out.println();
        }
    }

    public static void printStations(){
        for (Station Station: stations){
            System.out.print("Station ID: " + Station.getStationID() + ", MaxCapacity : " + Station.getMaxCapacity() +
                    " , Multiflag : " + Station.isMultiFlag() + ", FifoFlag : " + Station.isFifoFlag());
            printTaskTypeSpeedReeder();
            System.out.print(" , Speed : "+ Station.getStationSpeed());

        }
        System.out.println();
    }
    public static void printTasks(){
        for (TaskType TaskType: jobTypeID.getTasks() ){
            System.out.print(", "+TaskType.getTaskTypeID()+" "+ TaskType.getSize());
        }
    }

    public static void printTaskTypeSpeedReeder(){
        for (TaskTypeSpeedReeder TaskTypeSpeedReeder:Main.getTaskTypeSpeedReeders()){
            System.out.print("  "+TaskTypeSpeedReeder.getTaskTypeID()+ "  "+TaskTypeSpeedReeder.getTaskTypeSpeed());
        }
    }

    public void printEventInfo(){
        System.out.println("EVENT TYPE : "+ getEventType() + " , DURATION : "+ getTime()+" minutes");
    }

    public void printEventInfoFinish(){
        System.out.println("EVENT TYPE : "+ getEventType() + " , DURATION : DEADLINE PASSED");
    }
}
