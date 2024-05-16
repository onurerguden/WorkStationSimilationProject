import java.util.ArrayList;

public abstract class Task {
    //task attributes
    private TaskType taskType;

    public Task(TaskType taskType) {
        this.taskType = taskType;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }


}
