import java.util.ArrayList;

public class Job {


    private String jobID;
    private jobType jobType;
    private double duration;
    private int deadline;
    private int startTime;
    private jobTypeID jobTypeID;




    public Job(String jobID,int startTime, double duration, jobTypeID jobTypeID ,int deadline, jobType jobType) {
        this.deadline = deadline;
        this.duration = duration;
        this.jobID = jobID;
        this.jobType = jobType;
        this.jobTypeID = jobTypeID;
        this.startTime = startTime;
    }


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



    public double getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getDeadline() {
        return deadline;
    }

    public void setDeadline(int deadline) {
        this.deadline = deadline;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
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
