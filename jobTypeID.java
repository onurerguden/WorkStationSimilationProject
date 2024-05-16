import java.util.ArrayList;

public class jobTypeID {
    private String jobTypeID;
    private static ArrayList<Task> tasks = new ArrayList<>();

    public jobTypeID(String jobTypeID,ArrayList<Task> tasks) {
        this.jobTypeID = jobTypeID;
        this.tasks=tasks;
    }

    public String getJobTypeID() {

        return jobTypeID;
    }

    public void setJobTypeID(String jobTypeID) {
        this.jobTypeID = jobTypeID;
    }

    public static ArrayList<Task> getTasks() {

        return tasks;
    }


}
