import java.util.ArrayList;

public class jobTypeID {
    private String jobTypeID;
    private   ArrayList<Task> tasks = new ArrayList<>();

    public jobTypeID(String jobTypeID,ArrayList<Task> tasks) {
        this.jobTypeID = jobTypeID;
        this.tasks=tasks;
    }

    public jobTypeID(String jobTypeID) {
        this.jobTypeID = jobTypeID;
        this.tasks=null;
    }



    public String getJobTypeID() {

        return jobTypeID;
    }

    public void setJobTypeID(String jobTypeID) {
        this.jobTypeID = jobTypeID;
    }

    public  ArrayList<Task> getTasks() {
        return tasks;
    }

    public  void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }


    public void giveTasks(ArrayList<Task> tasks){
        this.tasks=tasks;
    }
}
