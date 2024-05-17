
import java.util.ArrayList;

public class Event {
    private static double timePassed;
    private static double time;
    private EventType eventType;
    private static ArrayList<Job> jobTypes = new ArrayList<Job>();
    private static ArrayList<Station> stations =new ArrayList<>();


    public Event(EventType eventType, double time, ArrayList<Job> jobTypes, ArrayList<Station> stations) {
        this.eventType = eventType;
        this.time = time;
        this.jobTypes = jobTypes;
        this.stations=stations;
    }

    public static double getTimePassed() {
        return timePassed;
    }

    public static void setTimePassed(double timePassed) {
        Event.timePassed = timePassed;
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

    public static double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public static void printJobs(){
        for (Job job :jobTypes){
            System.out.print("Job ID: " + job.getJobID() + ", Job TypeID: " + job.getJobTypeID().getJobTypeID() +
                    ", Start Time: " + job.getStartTime() + ", Duration: " + (job.getDuration()-Event.getTimePassed()) +
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
            System.out.println(" , Speed : "+ Station.getStationSpeed());

        }
        System.out.println();
    }
    public static void printTasks(){
        for (Task Task : jobTypeID.getTasks() ){
            System.out.print(", "+ Task.getTaskTypeID()+" "+ Task.getSize());
        }
    }

    public static void printAllTasks(){
        for (Task Task :Main.getTasks()){
            System.out.println("TASK TYPE ID : "+ Task.getTaskTypeID()+ ", TASK SIZE : "+ Task.getSize()+", TASK STATE : "+ Task.getTaskTypeState());
        }
    }

    public static void printTaskTypeSpeedReeder(){
        for (TaskTypeSpeedReeder TaskTypeSpeedReeder:Main.getTaskTypeSpeedReeders()){
            System.out.print(" , "+TaskTypeSpeedReeder.getTaskTypeID()+ "  "+TaskTypeSpeedReeder.getTaskTypeSpeed());
        }
    }

    public void printEventInfo(){
        System.out.println("EVENT TYPE : "+ getEventType() + " , DURATION : "+ getTime()+" minutes");
    }

    public void printEventInfoFinish(){
        System.out.println("EVENT TYPE : "+ getEventType() + " , DURATION : DEADLINE PASSED");
    }

    public static void printTimePassed(){


        if (Event.getTimePassed()>Main.eventTime){
            System.out.println("-------------------------------");
            System.out.println("--EVENT TIME: DEADLINE PASSED--");
        }else {
            System.out.println("----------TIME PASSED----------");
            System.out.println("             "+Event.getTimePassed());
        }

    }

    public static void printStationHandlingSituation(){
        for (Station station : stations){
            for (int i = 0;i<station.getMaxCapacity();i++){
                System.out.print("STATION ID : "+station.getStationID());
                if (station.getTasksForStations() == null ){
                    System.out.println(" , HANDLING TASK : EMPTY STATION");
                }
                else {
                    System.out.print(" , HANDLING TASK : " );
                    Station.printStringTasksForStations(station);
                    System.out.println();
                }
            }
            System.out.println();
        }
    }



}
