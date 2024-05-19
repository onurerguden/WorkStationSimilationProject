import java.util.ArrayList;

public class Job {


    private String jobID;
    private jobType jobType;
    private double duration;
    private int deadline;
    private int startTime;
    private jobTypeID jobTypeID;
    private double completionTime;




    public Job(String jobID,int startTime, double duration, jobTypeID jobTypeID ,int deadline, jobType jobType) {
        this.deadline = deadline;
        this.duration = duration;
        this.jobID = jobID;
        this.jobType = jobType;
        this.jobTypeID = jobTypeID;
        this.startTime = startTime;
        this.completionTime = -1;
    }

    public double getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(double completionTime) {
        this.completionTime = completionTime;
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


    public jobTypeID getJobTypeID() {
        return jobTypeID;
    }

    public void setJobTypeID(jobTypeID jobTypeID) {
        this.jobTypeID = jobTypeID;
    }
}
