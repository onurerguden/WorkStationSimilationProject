import java.util.ArrayList;

public class Job {

    //private attributes
    //private String jobAttributes;

    private String jobID;
    private jobType jobType;
    private int duration;
    private String deadline;
    private String startTime;
    private jobTypeID jobTypeID;




    public Job(String jobID,String startTime, int duration, jobTypeID jobTypeID ,String deadline, jobType jobType) {
        this.deadline = deadline;
        this.duration = duration;
        this.jobID = jobID;
        this.jobType = jobType;
        this.jobTypeID = jobTypeID;
        this.startTime = startTime;
    }

    // public String getJobAttributes() {
   //     return jobAttributes;
   // }

   // public void setJobAttributes(String jobAttributes) {
   //     this.jobAttributes = jobAttributes;
    // }

    public String getJobID() {
        return jobID;
    }

    public void setJobID(String jobID) {
        this.jobID = jobID;
    }

    public jobType getJobType() {
        return jobType;
    }

    public void setJobType(jobType jobType) {
        this.jobType = jobType;
    }



    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    //getJobID, getJobType, getStartTime, getDuration, getDeadline + setters of these


    public jobTypeID getJobTypeID() {
        return jobTypeID;
    }

    public void setJobTypeID(jobTypeID jobTypeID) {
        this.jobTypeID = jobTypeID;
    }
}
