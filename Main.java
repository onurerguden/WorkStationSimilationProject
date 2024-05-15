import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;


public class Main {
    public static final String stars ="******************";
    static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {
       readDocuments();
    }

    private static ArrayList<Task> taskTypes = new ArrayList<>();
    private static ArrayList<Job> jobTypes = new ArrayList<>();
    private static ArrayList<Station> stations = new ArrayList<>();


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

            System.out.println("--Job File Contents--");

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
                String jobType = tokens[1];
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

                Job job = new Job(jobID, jobType, duration, startTime, deadline);
                jobTypes.add(job);
            }
            printJobs();
            reader.close();
        } catch (IOException e) {
            if (e instanceof FileNotFoundException) {
                System.out.println("Error: The job file does not exist or is not accessible.");
            } else {
                e.printStackTrace();
            }
        }
    }


    public static void printJobs(){
        for (Job job :jobTypes){
            System.out.println("Job ID: " + job.getJobID() + ", Job Type: " + job.getJobType() +
                    ", Start Time: " + job.getStartTime() + ", Duration: " + job.getDuration() +
                    " minutes, Deadline: " + job.getDeadline());
        }
    }


    public static String computeDeadline(int duration, String startTime) {
        int start = Integer.parseInt(startTime);
        int deadline = start + duration;
        return String.valueOf(deadline);
    }
}

