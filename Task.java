public class Task {
    private TaskTypeState TaskTypeState;
    private String taskID;
    private double size;


    public Task(String taskTypeID, double size) {
        this.size = size;
        this.taskID = taskTypeID;
        TaskTypeState= TaskTypeState.START;
    }
    public Task(String taskTypeID) {
        this.size = 0;
        this.taskID = taskTypeID;
        TaskTypeState= TaskTypeState.START;
    }

    public TaskTypeState getTaskTypeState() {
        return TaskTypeState;
    }



    public double getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getTaskTypeID() {
        return taskID;
    }

    public void setTaskTypeID(String taskTypeID) {
        this.taskID = taskTypeID;
    }
}
