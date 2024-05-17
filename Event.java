
import java.util.ArrayList;

public class Event {
    private static double timePassed;
    private double eventTimes;
    private static double timeRemaining;
    private EventType eventType;
    private  ArrayList<Job> jobTypes = new ArrayList<Job>();
    private  ArrayList<Station> stations =new ArrayList<>();


    public Event(double eventTimes, EventType eventType) {
        this.eventTimes = eventTimes;
        this.eventType = eventType;
    }

    public double getEventTimes() {
        return eventTimes;
    }

    public void setEventTimes(double eventTimes) {
        this.eventTimes = eventTimes;
    }

    public  void setTimeRemaining(double timeRemaining) {
        this.timeRemaining = timeRemaining;
    }


    public Event(EventType eventType, double timeRemaining, ArrayList<Job> jobTypes, ArrayList<Station> stations) {
        this.eventType = eventType;
        this.timeRemaining = timeRemaining;
        this.jobTypes = jobTypes;
        this.stations=stations;
    }

    public static double getTimePassed() {
        return timePassed;
    }

    public  void setTimePassed(double timePassed) {
        this.timePassed = timePassed;
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

    public  ArrayList<Station> getStations() {
        return stations;
    }

    public  void setStations(ArrayList<Station> stations) {
        this.stations = stations;
    }

    public  double getTimeRemaining() {
        return timeRemaining;
    }

    public void setTime(double time) {
        this.timeRemaining = time;
    }

    public  void printJobs(){
        for (Job job :jobTypes){
            System.out.print("Job ID: " + job.getJobID() + ", Job TypeID: " + job.getJobTypeID().getJobTypeID() +
                    ", Start Time: " + job.getStartTime() + ", Duration: " + (job.getDuration()-getTimePassed()) +
                    " minutes, Deadline: " + job.getDeadline()+", Job Type: "+ job.getJobType()+" Tasks: ");
            printTasks();
            System.out.println();
        }
    }



    public  void printStations(){
        for (Station Station: stations){
            System.out.print("Station ID: " + Station.getStationID() + ", MaxCapacity : " + Station.getMaxCapacity() +
                    " , Multiflag : " + Station.isMultiFlag() + ", FifoFlag : " + Station.isFifoFlag());
            printTaskTypeSpeedReeder();
            System.out.println(" , Speed : "+ Station.getStationSpeed());

        }
        System.out.println();
    }
    public void printTasks(){
        for (Task Task : jobTypeID.getTasks() ){
            System.out.print(", "+ Task.getTaskTypeID()+" "+ Task.getSize());
        }
    }

    public void printAllTasks(){
        for (Task Task :Main.getTasks()){
            System.out.println("TASK TYPE ID : "+ Task.getTaskTypeID()+ ", TASK SIZE : "+ Task.getSize()+", TASK STATE : "+ Task.getTaskTypeState());
        }
    }

    public  void printTaskTypeSpeedReeder(){
        for (TaskTypeSpeedReeder TaskTypeSpeedReeder:Main.getTaskTypeSpeedReeders()){
            System.out.print(" , "+TaskTypeSpeedReeder.getTaskTypeID()+ "  "+TaskTypeSpeedReeder.getTaskTypeSpeed());
        }
    }

    public void printEventInfo(){
        System.out.println("EVENT TYPE : "+ getEventType() + " , DURATION : "+ getTimeRemaining()+" minutes");
    }

    public void printEventInfoFinish(){
        System.out.println("EVENT TYPE : "+ getEventType() + " , DURATION : DEADLINE PASSED");
    }

    public  void printTimePassed(){

        if (getTimePassed()>Main.eventTime){
            System.out.println("-------------------------------");
            System.out.println("--EVENT TIME: DEADLINE PASSED--");
        }else {
            System.out.println("----------TIME PASSED----------");
            System.out.println("             "+getTimePassed());
        }

    }

    public  void printStationHandlingSituation(){
        for (Station station : stations){
            for (int i = 0;i<station.getMaxCapacity();i++){
                System.out.print("STATION ID : "+station.getStationID());
                if (station.getTasksForStations() == null ){
                    System.out.println(" , HANDLING TASK : EMPTY STATION");
                }
                else {
                    System.out.print(" , HANDLING TASK : " );
                    station.printStringTasksForStations(station);
                    System.out.println();
                }
            }
            System.out.println();
        }
    }
}
