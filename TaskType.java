public class TaskType {
    private TaskTypeState TaskTypeState;
    private String taskTypeID;
    private double size;

    public TaskType( String taskTypeID,double size) {
        this.size = size;
        this.taskTypeID = taskTypeID;
        TaskTypeState= TaskTypeState.TASK_TYPE_STATE_START;
    }


    public TaskTypeState getTaskTypeState() {
        return TaskTypeState;
    }

    public TaskType(String taskTypeID) {
        this.size = 1;
        this.taskTypeID = taskTypeID;
        TaskTypeState= TaskTypeState.TASK_TYPE_STATE_START;
    }

    public double getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getTaskTypeID() {
        return taskTypeID;
    }

    public void setTaskTypeID(String taskTypeID) {
        this.taskTypeID = taskTypeID;
    }
}
