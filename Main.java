import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;


public class Main {
    public static final String stars ="******************";
    static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {
        readDocuments();
        simulation();

    }


    private static ArrayList<Job> jobTypes = new ArrayList<>();
    private static ArrayList<TaskTypeSpeedReeder> TaskTypeSpeedReeders = new ArrayList<>();
    private static ArrayList<TaskType> tasks = new ArrayList<>();
    private static ArrayList<Station> stations=new ArrayList<>();
    private static ArrayList<Event> events =new ArrayList<>();


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



            createObjectsManually();



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
                String startTime = tokens[2];


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
                String deadline = computeDeadline(duration, startTime);



                Job job = new Job(jobID,startTime,duration, jobTypeID ,deadline, jobType.WAITING_TO_START);
                jobTypes.add(job);
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

        TaskType T1 = new TaskType("T1",1);
        TaskType T2 = new TaskType("T2");
        TaskType T3 = new TaskType("T3",3);

        tasks.add(T1);
        tasks.add(T2);
        tasks.add(T3);

        TaskTypeSpeedReeder T1_S = new TaskTypeSpeedReeder("T1",2);
        TaskTypeSpeedReeder T2_S = new TaskTypeSpeedReeder("T2",3);

        TaskTypeSpeedReeders.add(T1_S);
        TaskTypeSpeedReeders.add(T2_S);

        Station S1 = new Station("S1",1,false,false,TaskTypeSpeedReeders,0.20);
        stations.add(S1);

        System.out.println("Enter the event time!");
        double eventTime = sc.nextInt();
        Event event1 = new Event(EventType.JOB_START,eventTime,jobTypes,stations);

        events.add(event1);
    }


    public static String computeDeadline(int duration, String startTime) {
        int start = Integer.parseInt(startTime);
        int deadline = start + duration;
        return String.valueOf(deadline);
    }

    public static ArrayList<TaskTypeSpeedReeder> getTaskTypeSpeedReeders() {
        return TaskTypeSpeedReeders;
    }

    public static void simulation(){

        for (Event event:events){
            while (event.getTime()>=0){
                printAllInfo(event);
                sc.nextLine();
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


    public static void printAllInfo(Event event){

        System.out.println("-------------JOBS------------");
        event.printJobs();

        System.out.println("----------STATIONS----------");
        event.printStations();

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

