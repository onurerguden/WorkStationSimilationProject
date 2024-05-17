import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        readDocuments();
        createObjectsManually();
        simulation();

    }
    public static final String stars ="******************";
    static Scanner sc = new Scanner(System.in);


    private static ArrayList<Job> jobTypes = new ArrayList<>();
    private static ArrayList<TaskTypeSpeedReeder> TaskTypeSpeedReeders = new ArrayList<>();
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static ArrayList<Station> stations=new ArrayList<>();
    private static ArrayList<Event> events =new ArrayList<>();
    static double eventTime = 0;


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
                jobTypeID jobTypeID = new jobTypeID(jobTypeIDname,tasks);
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
        Task T4 = new Task("T4");
        Task T5 = new Task("T3",4);
        Task T_1 = new Task("T_1",5);
        Task T21 = new Task("T21");

        tasks.add(T1);
        tasks.add(T2);
        tasks.add(T3);
        tasks.add(T4);
        tasks.add(T5);
        tasks.add(T_1);
        tasks.add(T21);

        //Sort Task algorithm
        sortTasks(tasks);

        TaskTypeSpeedReeder T1_S = new TaskTypeSpeedReeder("T1",2);
        TaskTypeSpeedReeder T2_S = new TaskTypeSpeedReeder("T2",3);

        TaskTypeSpeedReeders.add(T1_S);
        TaskTypeSpeedReeders.add(T2_S);

        Station S1 = new Station("S1",1,false,false,getTaskTypeSpeedReeders(),0.2);
        Station S2 = new Station("S2",2,true,true,getTaskTypeSpeedReeders());
        Station S3 = new Station("S3",2,false,true,getTaskTypeSpeedReeders());
        Station S4 = new Station("S4",3,true,true,getTaskTypeSpeedReeders(),0.5);

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
        Event event1 = new Event(EventType.JOB_START,eventTime,jobTypes,stations);
        events.add(event1);
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




    public static void simulation(){
        for (Event event:events){
            event.setTimePassed(0);
            while (event.getTime()>=0){
                if (event.getTimePassed()==1){
                    supplyTasksForStations();
                }
                printAllInfo(event);
                sc.nextLine();
                event.setTimePassed(event.getTimePassed()+1);
                event.setTime(event.getTime()-1);
            }
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
        Event.printTimePassed();
        System.out.println("-------------TASKS------------");
        event.printAllTasks();
        System.out.println("-------------JOBS------------");
        event.printJobs();

        System.out.println("----------STATIONS----------");
        event.printStations();
        Event.printStationHandlingSituation();

        if (event.getTime()<0){
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

