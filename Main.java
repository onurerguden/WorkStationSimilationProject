import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public static final String stars = "******************";
    static Scanner sc = new Scanner(System.in);
    private static ArrayList<Job> jobTypes = new ArrayList<>();
    private static ArrayList<TaskTypeSpeedReeder> TaskTypeSpeedReeders = new ArrayList<>();
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static ArrayList<Station> stations = new ArrayList<>();
    private static ArrayList<Event> events = new ArrayList<>();
    private static ArrayList<Event> eventQueue = new ArrayList<>();
    private static ArrayList<jobTypeID> jobTypeIDS = new ArrayList<>();
    private static double highestDeadline;
    static double eventTime = 0;

    public static ArrayList<Event> getEvents() {
        return events;
    }

    public static void main(String[] args) {
        readDocuments();
        Event event1 = new Event(EventType.EVENT_START_TO_WAIT, eventTime, jobTypes, stations);
        events.add(event1);


        //createObjectsManually();
        createEventsManually();
        simulation();
        reportStationUtilization();
        reportAverageJobTardiness();
    }

    public static void waitForOneSecond() {
        try {
            Thread.sleep(300);
            System.out.println("                                -                            ");
            Thread.sleep(200);
            System.out.println("                                -                            ");
            Thread.sleep(200);
            System.out.println("                                -                            ");
            Thread.sleep(200);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Thread was interrupted, Failed to complete operation");
        }
    }

    public static boolean isAllTasksCompleted(){
        int a = tasks.size();
        int b = 0;
            for (Task task : tasks) {
                if(task.getTaskTypeState().equals(TaskTypeState.COMPLETE)){
                    b++;
                    if (b==a){
                        return true;
                    }
                }else {
                    return false;
                }

            }
        return false;
    }

    public static void simulation() {
        eventTime = 0;
        for (Event event : events) {
            event.setTimePassed(0);
            event.setEventTimes(0);

            while (event.getTimeRemaining() >= 0 && isAllTasksCompleted()==false) {

                while (!eventQueue.isEmpty() && event.getTimePassed() <= eventQueue.get(0).getEventTimes()) {
                    Event currentEvent = eventQueue.remove(0);
                    event.setTimePassed(currentEvent.getEventTimes());
                    event.setTimeRemaining(highestDeadline - event.getTimePassed());

                    if (event.getTimePassed() == currentEvent.getEventTimes()) {
                        sc.nextLine();
                    }

                    if (currentEvent.getEventType() == EventType.EXECUTING) {
                        giveStationsListsForStations();
                        printAllInfo(event);
                        stationsExecuteTasks();
                    }


                    if (event.getTimePassed() >1) {
                        // Check and update job execution status
                        for (Job job : jobTypes) {
                            isJobOnExecution(job);
                            isJobFinished(job);
                        }
                    }




                    printAllInfo(event);
                    eventQueue.remove(eventQueue.getFirst());
                }

                if (eventQueue.getLast().getEventTimes() ==event.getEventTimes()) {
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



    public static void stationsExecuteTasks() {

        for (Event event :events){
            tasks.removeAll(tasks);
            for (Station station : event.getStations()) {
                ArrayList<Task> stationTasks = station.getTasksForStations();
                for (Task task : stationTasks) {
                    tasks.add(task);
                }
            }
        }

        for (Event event :events){
            eventTime=0;
            System.out.println("--------------------------------------------------------------");
            System.out.println("--------------------------SIMULATION--------------------------");
            System.out.println("--------------------------------------------------------------");
            System.out.println("----------------------Press Enter to see-----------------------");
            sc.nextLine();
            for (int i = 0;i<6;i++){
                System.out.println();
            }

            for (Station station : event.getStations()) {
                ArrayList<Task> stationTasks = station.getTasksForStations();
                if (stationTasks != null && !stationTasks.isEmpty()) {
                    System.out.println("Station " + station.getStationID() + " starting task execution.");

                    for (Task task : stationTasks) {
                        if (task.getSize() != 0) {
                            double speed = station.getStationSpeed();


                            if (station.getStationSpeed() != 1.0) {
                                speed = station.getStationSpeed() * (1 + (Math.random() * station.getStationSpeed()*10)); // +-20% variability
                            }

                            double duration = task.getSize() / speed;
                            System.out.println("Executing Task " + task.getTaskTypeID() + " with size " + task.getSize() + " at speed " + speed + " will take " + duration + " minutes.");
                            waitForOneSecond();

                            task.setTaskTypeState(TaskTypeState.COMPLETE);
                            for (Job job : jobTypes){
                                isJobOnExecution(job);
                                isJobFinished(job);
                            }
                            task.setSize(0);


                            // Create event for task completion

                            createEvent(eventTime + duration, EventType.TASK_COMPLETE);
                            eventTime += duration;
                            event.setTimePassed(eventTime);
                            event.setTimeRemaining(event.getTimeRemaining()-Event.getTimePassed());
                            printEventTime();
                            printAllInfo(event);
                            sc.nextLine();
                            station.addBusyTime(duration); // Add to station's busy time
                        }
                    }

                    System.out.println("Station " + station.getStationID() + " completed all tasks.");
                } else {
                    System.out.println("Station " + station.getStationID() + " has no tasks to execute.");
                }
            }
        }
        updateJobCompletionStatus();
    }


    public static void isJobFinished(Job job) {
        try {
            if (job.getJobTypeID() == null) {
                throw new NullPointerException("JobTypeID is null for Job ID: " + job.getJobID());
            }
            if (job.getJobTypeID().getTasks() == null) {
                throw new NullPointerException("Tasks list is null for JobTypeID: " + job.getJobTypeID().getJobTypeID());
            }

            int jobTaskNo = job.getJobTypeID().getTasks().size();
            int k = 0;

            for (Task task : tasks) {
                if (task.getTaskTypeState() == TaskTypeState.COMPLETE) {
                    for (Task jobTasks : job.getJobTypeID().getTasks()) {
                        if (task.getTaskTypeID().equals(jobTasks.getTaskTypeID())) {
                            k++;
                            if (jobTaskNo == k) {
                                job.setJobType(jobType.COMPLETED);
                            }
                        }
                    }
                }
            }
        } catch (NullPointerException e) {
            System.err.println("Error in isJobFinished: " + e.getMessage());
            e.printStackTrace();
        }
    }



    public static void isJobOnExecution(Job job) {
        for (Event event : events) {
            for (Job job1 : event.getJobTypes()) {
                ArrayList<Task> jobTasks = job1.getJobTypeID().getTasks();
                if (jobTasks != null) { // Check if jobTasks is not null
                    for (Task task : jobTasks) {
                        if (task.getTaskTypeState() == TaskTypeState.IN_EXECUTION) {
                           // System.out.println("HELLO");
                            job.setJobType(jobType.EXECUTING);
                            return; // Exit after updating the job state
                        }
                    }
                }
            }
        }
        System.out.println("Job ID: " + job.getJobID() + " is not in EXECUTION.");
    }

    public static void updateJobCompletionStatus() {
        for (Event event : events){
            for (Job job : event.getJobTypes()) {
                ArrayList<Task> jobTasks = job.getJobTypeID().getTasks();
                int completedTasks = 0;
                for (Task task : jobTasks) {
                    if (task.getTaskTypeState() == TaskTypeState.COMPLETE) {
                        completedTasks++;
                    }
                }
                double completionPercentage = (double) completedTasks / jobTasks.size() * 100;
                System.out.println("Job ID: " + job.getJobID() + " completion: " + completionPercentage + "%");

                // Update job state based on completion percentage
                if (completionPercentage == 100) {
                    job.setJobType(jobType.COMPLETED);
                    job.setCompletionTime(eventTime); // Set job completion time
                    System.out.println("Job ID: " + job.getJobID() + " is COMPLETE.");
                } else if (completionPercentage > 0) {
                    job.setJobType(jobType.EXECUTING);
                    System.out.println("Job ID: " + job.getJobID() + " is IN_PROGRESS.");
                } else {
                    job.setJobType(jobType.WAITING_TO_START);
                    System.out.println("Job ID: " + job.getJobID() + " is WAITING_TO_START.");
                }
            }
        }
    }

    public static void createEvent(double eventTime, EventType eventType) {
        Event event = new Event(eventTime, eventType);
        eventQueue.add(event);
    }



    public static void createEventsManually() {
        Event event = new Event(0, EventType.EVENT_START_TO_WAIT);
        eventQueue.add(event);
        Event event2 = new Event(1, EventType.EXECUTING);
        eventQueue.add(event2);
        Event event3 = new Event(2, EventType.EXECUTING);
        eventQueue.add(event3);
    }

    public static void printEventQueue() {
        for (Event event : eventQueue) {
            System.out.println(event.getEventTimes());
        }
    }

    public static void readDocuments() {
        readWorkFlow();
        readJobFile();

    }

    public static void readWorkFlow() {
        System.out.println(stars);
        System.out.println("Enter workflow file name! -For example :sample_workflow.txt");
        String workflowName = sc.nextLine();
        Parser kDot = new Parser(workflowName);
        //tasks
        tasks = kDot.getTasks();
        //jobTypeID
        jobTypeIDS =kDot.getJobs();
        //stations
        stations = kDot.getStations();
        kDot.printErrors();
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

                if (tokens.length != 4) {
                    if (reader.readLine() == null) {
                        continue;
                    }
                    System.out.println("Syntax error at line " + lineNumber + ": " + line);
                    continue;
                }

                String jobID = tokens[0];
                String jobTypeIDname = tokens[1];

                jobTypeID jobTypeID = new jobTypeID(jobTypeIDname);
                jobTypeIDS.add(jobTypeID);

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

                int deadline = computeDeadline(duration, startTime);

                Job job = new Job(jobID, startTime, duration, jobTypeID, deadline, jobType.WAITING_TO_START);
                jobTypes.add(job);



            }

            highestDeadline = 0;
            for (Job job : jobTypes) {
                if (job.getDeadline() > highestDeadline) {
                    highestDeadline = job.getDeadline();
                    eventTime = highestDeadline;
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

    public static void createObjectsManually() {
        Task T1 = new Task("T1", 1);
        Task T2 = new Task("T2", 2);
        Task T3 = new Task("T3", 2.5);
        Task T4 = new Task("T4", 1);
        Task T5 = new Task("T3", 4);
        Task T_1 = new Task("T_1", 5);
        Task T21 = new Task("T21", 1);

        tasks.add(T1);
        tasks.add(T2);
        tasks.add(T3);
        tasks.add(T2);
        tasks.add(T3);
        tasks.add(T4);
        tasks.add(T2);
        tasks.add(T1);
        tasks.add(T2);
        tasks.add(T3);
        tasks.add(T2);
        tasks.add(T3);
        tasks.add(T4);

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

        TaskTypeSpeedReeder T1_S1 = new TaskTypeSpeedReeder("T1", 2);
        TaskTypeSpeedReeder T2_S2 = new TaskTypeSpeedReeder("T2", 3);
        TaskTypeSpeedReeder T2_S3 = new TaskTypeSpeedReeder("T2", 4);
        TaskTypeSpeedReeder T2_S4 = new TaskTypeSpeedReeder("T3", 1);
        TaskTypeSpeedReeder T2_S5 = new TaskTypeSpeedReeder("T4", 1);
        TaskTypeSpeedReeder T2_S6 = new TaskTypeSpeedReeder("T21", 2);

        ArrayList<TaskTypeSpeedReeder> ttsr1 = new ArrayList<>();
        ArrayList<TaskTypeSpeedReeder> ttsr2 = new ArrayList<>();
        ArrayList<TaskTypeSpeedReeder> ttsr3 = new ArrayList<>();
        ArrayList<TaskTypeSpeedReeder> ttsr4 = new ArrayList<>();

        ttsr1.add(T1_S1);
        ttsr1.add(T2_S2);

        ttsr2.add(T1_S1);
        ttsr2.add(T2_S3);

        ttsr3.add(T2_S4);

        ttsr4.add(T2_S5);
        ttsr4.add(T2_S6);

        Station S1 = new Station("S1", 1, false, false, ttsr1, 0.2);
        Station S2 = new Station("S2", 2, true, true, ttsr2);
        Station S3 = new Station("S3", 2, false, true, ttsr3);
        Station S4 = new Station("S4", 3, true, true, ttsr4, 0.5);

        stations.add(S1);
        stations.add(S2);
        stations.add(S3);
        stations.add(S4);

        Event event1 = new Event(EventType.EVENT_START_TO_WAIT, eventTime, jobTypes, stations);
        events.add(event1);
    }

    public static void printTasksJobJobtype() {
        for (Job job : jobTypes) {
            System.out.println("Job ID: " + job.getJobID());
            System.out.println("Associated Tasks:");
            for (Task task : job.getJobTypeID().getTasks()) {
                System.out.println(" - Task Type ID: " + task.getTaskTypeID() + ", Size: " + task.getSize());
            }
        }
    }

    public static void supplyTasksForStations() {
        for (Station station : stations) {
            if (Event.getTimePassed() == 1) {
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


    public static void giveStationsListsForStations() {
        for (Event event : events) {
            for (Station station : event.getStations()) {
                station.setTasksForStations(new ArrayList<>()); // Ensure each station has an empty task list
            }
            int stationIndex = 0;
            ArrayList<Task> tasksCopy = new ArrayList<>(tasks);
            while (!tasksCopy.isEmpty()) {
                Task currentTask = tasksCopy.remove(0); // Get and remove the first task from the copy list
                boolean taskAssigned = false;

                for (int i = 0; i < stations.size(); i++) {
                    Station station = stations.get(stationIndex);
                    stationIndex = (stationIndex + 1) % stations.size(); // Move to the next station

                    for (TaskTypeSpeedReeder taskTypeSpeedReeder : station.getTaskTypeSpeedReeders()) {
                        if (taskTypeSpeedReeder.getTaskTypeID().equals(currentTask.getTaskTypeID())) {
                            System.out.println("Assigning Task Type ID: " + currentTask.getTaskTypeID() + " to Station ID: " + station.getStationID());
                            Task taskForStation = new Task(currentTask.getTaskTypeID(), currentTask.getSize());
                            station.getTasksForStations().add(taskForStation);
                            taskAssigned = true;
                            break;
                        }
                    }
                    if (taskAssigned) {
                        break;
                    }
                }
                if (!taskAssigned) {
                    System.out.println("No station can handle the task with Task Type ID: " + currentTask.getTaskTypeID());
                    currentTask.setTaskTypeState(TaskTypeState.NO_STATION);
                    for (Task task : tasks) {
                        if (task.getTaskTypeID().equals(currentTask.getTaskTypeID())) {
                            task.setTaskTypeState(TaskTypeState.NO_STATION);
                        }
                    }
                }
            }

            for (Station station : stations) {
                System.out.println("Station ID: " + station.getStationID() + " has the following tasks assigned:");
                for (Task task : station.getTasksForStations()) {
                    System.out.println(" - Task Type ID: " + task.getTaskTypeID() + ", Size: " + task.getSize());
                }
            }
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

    public static void printEventTime(){
        System.out.println("|-------------------------------|");
        if (Event.getTimePassed() > eventTime) {
            System.out.println("|------EVENT TIME : DEADLINE PASSED------|");
        } else {
            System.out.println("|--EVENT TIME : " + eventTime + "---|");
        }
        System.out.println("|-------------------------------|");

    }

    public static void printAllInfo(Event event) {
        event.printTimePassed();
        System.out.println("-------------TASKS------------");
        event.printAllTasks();
        System.out.println("-------------JOBS------------");
        event.printJobs();

        System.out.println("----------STATIONS----------");
        event.printStations();
        event.printStationHandlingSituation();

        if (event.getTimeRemaining() < 0) {
            System.out.println("---------- EVENTS------------");
            event.printEventInfoFinish();
        } else {
            System.out.println("---------- EVENTS------------");
            event.printEventInfo();
        }

        System.out.println("----------------------------");
        System.out.println();
        System.out.println();
    }


    public static void reportAverageJobTardiness() {
        int jobTypeSize = jobTypes.size();
        int l = 0;
        for (Job job : jobTypes) {
            if (job.getJobType() == jobType.COMPLETED) {
                l++;
            }
        }
            double averageTardiness = (l/jobTypeSize)*100;
            System.out.println("Average Job Tardiness: " + averageTardiness +" % ");
            if (l==0){
                System.out.println("No completed jobs to calculate tardiness.");
            }
    }


    public static void reportStationUtilization() {
        for (Event event : events){
            double totalTime = eventTime; // Use eventTime to get the total simulation time
            for (Station station : event.getStations()) {
                double busyTime = station.getBusyTime();
                double utilization = (busyTime / totalTime) * 100;
                System.out.println("Station ID: " + station.getStationID() + ", Utilization: " + utilization + "%");
            }
        }
    }

}
