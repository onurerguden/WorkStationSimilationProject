public class TaskTypeSpeedReeder {

    private String TaskTypeID;
    private double TaskTypeSpeed;

    public TaskTypeSpeedReeder( String taskTypeID,double taskTypeSpeed) {
        TaskTypeSpeed = taskTypeSpeed;
        TaskTypeID = taskTypeID;
    }

    public String getTaskTypeID() {
        return TaskTypeID;
    }



    public void setTaskTypeID(String taskTypeID) {
        TaskTypeID = taskTypeID;
    }

    public double getTaskTypeSpeed() {
        return TaskTypeSpeed;

    }

    public void setTaskTypeSpeed(double takTypeSpeed) {
        TaskTypeSpeed = takTypeSpeed;
    }
}
