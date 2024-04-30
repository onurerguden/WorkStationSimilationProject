public class Job {

    //private attributes
    //private String jobAttributes;

    private String jobID;
    private jobType jobType;
    private int duration;
    private String deadline;
    private String startTime;

    //constructor without String jobAttributes
    public Job( String jobID, jobType jobType, int duration, String deadline, String startTime) {

       // this.jobAttributes = jobAttributes;
        this.jobID = jobID;
        this.jobType = jobType;
        this.duration = duration;
        this.deadline = deadline;
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
}
