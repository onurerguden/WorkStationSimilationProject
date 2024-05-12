public class Job {

    //private attributes
    //private String jobAttributes;

    private String jobID;
    private JobType jobType;
    private int duration;
    private String deadline;
    private String startTime;

    //constructor without String jobAttributes
    public Job(String jobID, JobType jobType, int duration, String deadline, String startTime) {

       // this.jobAttributes = jobAttributes;
        this.jobID = jobID;
        this.jobType = jobType;
        this.duration = duration;
        this.deadline = deadline;
        this.startTime = startTime;
    }
    public Job(){
        setJobID("1");
        setJobType(jobType.HARD);
        setDuration(1);
        setDeadline("1");
        setStartTime("0");
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

    public JobType getJobType() {
        return jobType;
    }

    public void setJobType(JobType jobType) {
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
