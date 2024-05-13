public class Task {
    //task attributes
    private String taskID;
    private String taskType;
    private int taskSize;
    private String stationID;


    public Task(String stationID, String taskID, int taskSize, String taskType) {
        this.stationID = stationID;
        this.taskID = taskID;
        this.taskSize = taskSize;
        this.taskType = taskType;
    }

    public Task() {
        setStationID("1");
        setTaskID("1");
        setTaskSize(1);
        setTaskType("1");

    }

    //methods
    //constructor, getTaskID, getTaskType, getTaskSize, getStationID
    public String getStationID() {
        return stationID;
    }

    public void setStationID(String stationID) {
        this.stationID = stationID;
    }

    public String getTaskID() {
        return taskID;
    }

    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }

    public int getTaskSize() {
        return taskSize;
    }

    public void setTaskSize(int taskSize) {
        this.taskSize = taskSize;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }


}
