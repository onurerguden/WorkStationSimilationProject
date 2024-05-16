public class TaskType {

    private String taskTypeID;
    private int size;

    public TaskType( String taskTypeID,int size) {
        this.size = size;
        this.taskTypeID = taskTypeID;
    }

    public TaskType(String taskTypeID) {
        this.size = 1;
        this.taskTypeID = taskTypeID;
    }

    public int getSize() {
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
