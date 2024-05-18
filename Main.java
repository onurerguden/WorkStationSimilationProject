import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;


public class Main {
    public static final String stars ="******************";
    static Scanner sc = new Scanner(System.in);
    private static ArrayList<Job> jobTypes = new ArrayList<>();
    private static ArrayList<TaskTypeSpeedReeder> TaskTypeSpeedReeders = new ArrayList<>();
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static ArrayList<Station> stations=new ArrayList<>();
    private static ArrayList<Event> events =new ArrayList<>();
    private static ArrayList<Event> eventQueue = new ArrayList<>();
    private static ArrayList<jobTypeID> jobTypeIDS = new ArrayList<>();
    static double eventTime = 0;


    public static ArrayList<Station> getStations() {
        return stations;
    }


    public static void main(String[] args) {
        readDocuments();
        createObjectsManually();
        //simulateWithoutOutput();
        //createEventsManually();
        createEvents();
        //stationsExecuteTasks();
        //printEventQueue();
        simulation();
    }

    public static void sortEvents(ArrayList<Event> eventQueue) {
        for (int i = 1; i < eventQueue.size(); i++) {
            Event key = eventQueue.get(i);
            int j = i - 1;

            while (j >= 0 && eventQueue.get(j).getEventTimes() > key.getEventTimes()) {
                eventQueue.set(j + 1, eventQueue.get(j));
                j = j - 1;
            }
            eventQueue.set(j + 1, key);
        }
    }

    public static void createEvents(){
        Event eventReadyToStart = new Event(0,EventType.EVENT_START_TO_WAIT);
        Event eventStart = new Event(1,EventType.EVENT_START_TO_WAIT);
        eventQueue.add(eventReadyToStart);
        eventQueue.add(eventStart);
        stationsExecuteTasks();
        sortEvents(eventQueue);
    }

    public static void stationsExecuteTasks() {
        eventTime=0;
        for (Station station : stations) {
            ArrayList<Task> stationTasks = station.getTasksForStations();
            if (stationTasks != null && !stationTasks.isEmpty()) {
                System.out.println("Station " + station.getStationID() + " starting task execution.");

                for (Task task : stationTasks) {
                    if (task.getSize()!=0){
                        double speed = station.getStationSpeed();

                        // If the station has variable speed
                        if (station.getStationSpeed() != 1.0) {
                            speed = station.getStationSpeed() * (1 + (Math.random() * 0.4 - 0.2)); // +-20% variability
                        }

                        double duration = task.getSize() / speed;
                        System.out.println("Executing Task " + task.getTaskTypeID() + " with size " + task.getSize() + " at speed " + speed + " will take " + duration + " minutes.");
                        task.setSize(0); // Mark the task as completed (size 0)
                        task.setTaskTypeState(TaskTypeState.COMPLETE);

                        // Create event for task completion
                        createEvent(eventTime + duration, EventType.TASK_COMPLETE);
                        eventTime += duration;
                    }
                }

                System.out.println("Station " + station.getStationID() + " completed all tasks.");
            } else {
                System.out.println("Station " + station.getStationID() + " has no tasks to execute.");
            }
        }
        for (Event event:eventQueue){
            System.out.println(event.getEventTimes());
        }
    }

    public static void createEvent(double eventTime, EventType eventType) {
        Event event = new Event(eventTime, eventType);
        eventQueue.add(event);
    }




/*
    public static void simulateWithoutOutput() {
        // Initialize with the start event
        Event e0 = new Event(0, EventType.EVENT_START_TO_WAIT);
        Event e1 = new Event(1,EventType.EXECUTING);
        eventQueue.add(e0);

        double currentTime = 0;

        // Process each station and its tasks
        for (Station station : stations) {
            ArrayList<Task> stationTasks = station.getTasksForStations();
            if (stationTasks != null && !stationTasks.isEmpty()) {
                while (!stationTasks.isEmpty()) {
                    Task task = stationTasks.get(0); // Get the first task in the queue
                    double speed = station.getStationSpeed();

                    // If the station has variable speed
                    if (station.getStationSpeed() != 1.0) {
                        speed = station.getStationSpeed() * (1 + (Math.random() * 0.4 - 0.2)); // +-20% variability
                    }

                    double duration = task.getSize() / speed;
                    task.setSize(0); // Mark the task as completed (size 0)
                    task.setTaskTypeState(TaskTypeState.COMPLETE);

                    // Create event for task completion
                    createEvent(currentTime + duration, EventType.TASK_COMPLETE);
                    currentTime += duration; // Advance the current time

                    // Remove the completed task from the station's task list
                    stationTasks.remove(task);

                    // Create an event for the station handling the next task if there are more tasks
                    if (!stationTasks.isEmpty()) {
                        createEvent(currentTime, EventType.EXECUTING);
                    }
                }
            }
        }
    }

 */








    public void Req2(){

    }
    //parse tasktypes try-ons

    public static void giveStationsListsForStations() {

        for (Station station : stations) {
            station.setTasksForStations(new ArrayList<>()); // Ensure each station has an empty task list
        }

        int stationIndex = 0;

        while (!tasks.isEmpty()) {
            Task currentTask = tasks.getFirst();
            boolean taskAssigned = false;

            for (int i = 0; i < stations.size(); i++) {
                Station station = stations.get(stationIndex);
                stationIndex = (stationIndex + 1) % stations.size(); // Move to the next station

                for (TaskTypeSpeedReeder taskTypeSpeedReeder : station.getTaskTypeSpeedReeders()) {
                    if (taskTypeSpeedReeder.getTaskTypeID().equals(currentTask.getTaskTypeID())) {
                        System.out.println("Assigning Task Type ID: " + currentTask.getTaskTypeID() + " to Station ID: " + station.getStationID());

                        // Add the task to the station's task list
                        station.getTasksForStations().add(currentTask);

                        // Remove the task from the global task list
                        tasks.removeFirst();

                        taskAssigned = true;
                        break;
                    }
                }
                if (taskAssigned) {
                    break;
                }
            }
            // If no station could handle the task, log it and break to avoid infinite loop
            if (!taskAssigned) {
                System.out.println("No station can handle the task with Task Type ID: " + currentTask.getTaskTypeID());
                break;
            }
        }

        // Print the final assignment for debugging
        for (Station station : stations) {
            System.out.println("Station ID: " + station.getStationID() + " has the following tasks assigned:");
            for (Task task : station.getTasksForStations()) {
                System.out.println(" - Task Type ID: " + task.getTaskTypeID());
            }
        }

    }


    public void printReport(){

    }


    public static void createEventsManually() {
       for (int i = 0; i<=45;i=i+5){
           if (i == 5){
               Event event = new Event(1,EventType.EVENT_START_TO_WAIT);
               eventQueue.add(event);
           }
               Event event = new Event(i,EventType.EVENT_START_TO_WAIT);
               eventQueue.add(event);
       }
    }

    public static void printEventQueue(){
        for (Event event:eventQueue){
            System.out.println( event.getEventTimes());
        }
    }

    //Requirement1
    public static void readDocuments(){
        readWorkFlow();
        readJobFile();
    }


    public static void readWorkFlow() {
        System.out.println(stars);
        System.out.println("Enter workflow file name! -For example :sample_workflow.txt");
        String workflowName = sc.nextLine();
        String fileName = workflowName;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            System.out.println("--File is accessible--");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
        } catch (IOException e) {
            if (e instanceof FileNotFoundException) {
                System.out.println("Error: The file does not exist or is not accessible.");
            } else {
                e.printStackTrace();
            }
        }
    }
    public static void readJobFile() {

        System.out.println("Enter job file name! -For example :sample_jobFile.txt");
        String jobFileName = sc.nextLine();
        String fileName = jobFileName;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            int lineNumber = 0;
            HashSet<String> jobIDs = new HashSet<>(); // To check uniqueness of job IDs


            while ((line = reader.readLine()) != null) {
                lineNumber++;

                String[] tokens = line.split("\\s+");

                if (tokens.length != 4 ) {
                    if (reader.readLine()==null){
                        continue;
                    }
                    System.out.println("Syntax error at line " + lineNumber + ": " + line);
                    continue;
                }

                String jobID = tokens[0];
                String jobTypeIDname = tokens[1];


                    jobTypeID jobTypeID = new jobTypeID(jobTypeIDname);
                    jobTypeIDS .add(jobTypeID);




                int startTime = Integer.parseInt(tokens[2]);


                int duration;
                try {
                    duration = Integer.parseInt(tokens[3]);
                } catch (NumberFormatException e) {
                    System.out.println("Semantic error at line " + lineNumber + ": Duration must be a valid integer.");
                    continue;
                }

                // Check uniqueness of job ID
                if (!jobIDs.add(jobID)) {
                    System.out.println("Semantic error at line " + lineNumber + ": Duplicate job ID '" + jobID + "'.");
                    continue;
                }

                if (duration < 0) {
                    System.out.println("Semantic error at line " + lineNumber + ": Duration must be non-negative.");
                    continue;
                }

                int deadline = computeDeadline(duration,startTime);



                Job job = new Job(jobID,startTime,duration, jobTypeID ,deadline, jobType.WAITING_TO_START);
               jobTypes.add(job);
            }


            double higestDeadline = 0;
            for (Job job :jobTypes){
                if (job.getDeadline()>higestDeadline){
                    higestDeadline= job.getDeadline();
                    eventTime = higestDeadline;
                }
            }

            reader.close();
        } catch (IOException e) {
            if (e instanceof FileNotFoundException) {
                System.out.println("Error: The job file does not exist or is not accessible.");
            } else {
                e.printStackTrace();
            }
        }
    }



    public static void createObjectsManually(){

        Task T1 = new Task("T1",1);
        Task T2 = new Task("T2",2);
        Task T3 = new Task("T3",2.5);
        Task T4 = new Task("T4",1);
        Task T5 = new Task("T3",4);
        Task T_1 = new Task("T_1",5);
        Task T21 = new Task("T21",1);



        tasks.add(T1);
        tasks.add(T2);
        tasks.add(T3);

        tasks.add(T2);
        tasks.add(T3);
        tasks.add(T4);

        tasks.add(T2);
        //tasks.add(T4);
        //tasks.add(T5);
        //tasks.add(T_1);
        tasks.add(T1);
        tasks.add(T2);
        tasks.add(T3);

        tasks.add(T2);
        tasks.add(T3);
        tasks.add(T4);

        //Sort Task algorithm






        // Create task lists for each job
        ArrayList<Task> tasksForJob1 = new ArrayList<>();
        tasksForJob1.add(tasks.get(0)); // T1
        tasksForJob1.add(tasks.get(1)); // T2
        tasksForJob1.add(tasks.get(2)); // T3

        ArrayList<Task> tasksForJob2 = new ArrayList<>();
        tasksForJob2.add(tasks.get(1)); // T2
        tasksForJob2.add(tasks.get(2)); // T3
        tasksForJob2.add(tasks.get(5)); // T4

        ArrayList<Task> tasksForJob3 = new ArrayList<>();
        tasksForJob3.add(tasks.get(7)); // T3

        // Assign tasks to job types based on JobTypeID
        for (jobTypeID jobTypeId : jobTypeIDS) {
            switch (jobTypeId.getJobTypeID()) {
                case "J1":
                    jobTypeId.setTasks(tasksForJob1);
                    break;
                case "J2":
                    jobTypeId.setTasks(tasksForJob2);
                    break;
                case "J3":
                    jobTypeId.setTasks(tasksForJob3);
                    break;
            }
        }

        // Assign job type IDs to jobs
        for (Job job : jobTypes) {
            for (jobTypeID jobTypeId : jobTypeIDS) {
                if (job.getJobTypeID().getJobTypeID().equals(jobTypeId.getJobTypeID())) {
                    job.setJobTypeID(jobTypeId);
                }
            }
        }

        printTasksJobJobtype();


        sortTasks(tasks);


        TaskTypeSpeedReeder T1_S1 = new TaskTypeSpeedReeder("T1",2);
        TaskTypeSpeedReeder T2_S2 = new TaskTypeSpeedReeder("T2",3);
        TaskTypeSpeedReeder T2_S3 = new TaskTypeSpeedReeder("T2",4);
        TaskTypeSpeedReeder T2_S4 = new TaskTypeSpeedReeder("T3",1);
        TaskTypeSpeedReeder T2_S5 = new TaskTypeSpeedReeder("T4",1);
        TaskTypeSpeedReeder T2_S6 = new TaskTypeSpeedReeder("T21",2);


        ArrayList<TaskTypeSpeedReeder> ttsr1 =new ArrayList<>();
        ArrayList<TaskTypeSpeedReeder> ttsr2 =new ArrayList<>();
        ArrayList<TaskTypeSpeedReeder> ttsr3 =new ArrayList<>();
        ArrayList<TaskTypeSpeedReeder> ttsr4 =new ArrayList<>();



        ttsr1.add(T1_S1);
        ttsr1.add(T2_S2);


        ttsr2.add(T1_S1);
        ttsr2.add(T2_S3);

        ttsr3.add(T2_S4);

        ttsr4.add(T2_S5);
        ttsr4.add(T2_S6);

        Station S1 = new Station("S1",1,false,false,ttsr1,0.2);
        Station S2 = new Station("S2",2,true,true,ttsr2);
        Station S3 = new Station("S3",2,false,true,ttsr3);
        Station S4 = new Station("S4",3,true,true,ttsr4,0.5);

        stations.add(S1);
        stations.add(S2);
        stations.add(S3);
        stations.add(S4);


        System.out.println("|-------------------------------|");
        if (Event.getTimePassed()>eventTime){
            System.out.println("|------EVENT TIME : DEADLINE PASSED------|");
        }else {
            System.out.println("|------EVENT TIME : "+eventTime+"--------|");
        }

        System.out.println("|-------------------------------|");
        System.out.println();
        Event event1 = new Event(EventType.EVENT_START_TO_WAIT,eventTime,jobTypes,stations);
        events.add(event1);


    }

    public static void printTasksJobJobtype(){
        for (Job job : jobTypes) {
            System.out.println("Job ID: " + job.getJobID());
            System.out.println("Associated Tasks:");
            for (Task task : job.getJobTypeID().getTasks()) {
                System.out.println(" - Task Type ID: " + task.getTaskTypeID() + ", Size: " + task.getSize());
            }
        }
    }


    public static void supplyTasksForStations(){
        for (Station station : stations){
            if (Event.getTimePassed() == 1){
                station.setTasksForStations(tasks);
            }
        }
    }




    public static int computeDeadline(int duration, int startTime) {
        int deadline = startTime + duration;
        return deadline;
    }

    public static ArrayList<TaskTypeSpeedReeder> getTaskTypeSpeedReeders() {
        return TaskTypeSpeedReeders;
    }

    public static ArrayList<Task> getTasks() {
        return tasks;
    }



    public static void simulation() {
        int i = 0;
        for (Event event : events) {
            event.setTimePassed(0);
            while (event.getTimeRemaining() >= 0) {
                while (i < eventQueue.size() && event.getTimePassed() <= eventQueue.get(i).getEventTimes()) {
                    event.setTimePassed(eventQueue.get(i).getEventTimes());
                    event.setTimeRemaining(eventTime - Event.getTimePassed());

                    if (event.getTimePassed() == eventQueue.get(i).getEventTimes()) {
                        sc.nextLine();
                    }
                    if (i > eventQueue.size()) {
                        break;
                    }

                    i++;
                    if (event.getTimePassed() == 1) {
                        giveStationsListsForStations();
                        stationsExecuteTasks();
                    }
                    printAllInfo(event);
                }
                if (i >= eventQueue.size()) {
                    break;
                }
            }
            event.setTimePassed(event.getTimePassed() + 1);
            event.setEventType(EventType.TASK_COMPLETE);
            System.out.println();
            System.out.println("****************************");
            System.out.println("-------EVENT COMPLETED-------");
            System.out.println("****************************");
            System.out.println();
            System.out.println();
            printAllInfo(event);
        }
    }




    public static void sortTasks(ArrayList<Task> tasks) {
            for (int i = 1; i < tasks.size(); i++) {
                Task key = tasks.get(i);
                int j = i - 1;

                while (j >= 0 && tasks.get(j).getSize() > key.getSize()) {
                    tasks.set(j + 1, tasks.get(j));
                    j = j - 1;
                }
                tasks.set(j + 1, key);
            }
        }


    public static void printAllInfo(Event event){
        event.printTimePassed();
        System.out.println("-------------TASKS------------");
        event.printAllTasks();
        System.out.println("-------------JOBS------------");
        event.printJobs();

        System.out.println("----------STATIONS----------");
        event.printStations();
        event.printStationHandlingSituation();

        if (event.getTimeRemaining()<0){
            System.out.println("---------- EVENTS------------");
            event.printEventInfoFinish();
        }else {
            System.out.println("---------- EVENTS------------");
            event.printEventInfo();
        }


        System.out.println("----------------------------");
        System.out.println();
        System.out.println();
    }
}

