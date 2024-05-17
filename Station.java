import java.util.ArrayList;

public class Station {
    private String stationID;
    private int maxCapacity;
    private boolean multiFlag;
    private boolean fifoFlag;
    private double stationSpeed;
    private ArrayList<TaskTypeSpeedReeder> TaskTypeSpeedReeders = new ArrayList<>();
    private static ArrayList<Task> tasksForStations = new ArrayList<>();

    public Station(String stationID, int maxCapacity, boolean multiFlag, boolean fifoFlag, ArrayList<TaskTypeSpeedReeder>TaskTypeSpeedReeders, double stationSpeed) {
        this.stationID = stationID;
        this.maxCapacity = maxCapacity;
        this.multiFlag = multiFlag;
        this.fifoFlag = fifoFlag;
        this.TaskTypeSpeedReeders =TaskTypeSpeedReeders;
        this.stationSpeed = stationSpeed;
        this.tasksForStations = null;
    }

    public Station(String stationID, int maxCapacity, boolean multiFlag, boolean fifoFlag, ArrayList<TaskTypeSpeedReeder> TaskTypeSpeedReeders) {
        this.stationID = stationID;
        this.maxCapacity = maxCapacity;
        this.multiFlag = multiFlag;
        this.fifoFlag = fifoFlag;
        this.TaskTypeSpeedReeders=TaskTypeSpeedReeders;
        this.stationSpeed = 1.0;
        this.tasksForStations = null;
    }



    public static ArrayList<Task> getTasksForStations() {
        return tasksForStations;
    }

    public static void printStringTasksForStations(Station station){
        for (Task task : tasksForStations){
            System.out.print(task.getTaskTypeID()+" , ");
        }
    }


    public static void setTasksForStations(ArrayList<Task> tasksForStations) {
        Station.tasksForStations = tasksForStations;
    }

    public void setStationSpeed(double stationSpeed) {
        this.stationSpeed = stationSpeed;
    }

    public ArrayList<TaskTypeSpeedReeder> getTaskTypeSpeedReeders() {
        return TaskTypeSpeedReeders;
    }

    public void setTaskTypeSpeedReeders(ArrayList<TaskTypeSpeedReeder> taskTypeSpeedReeders) {
        TaskTypeSpeedReeders = taskTypeSpeedReeders;
    }

    public String getStationID() {
        return stationID;
    }

    public void setStationID(String stationID) {
        this.stationID = stationID;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public boolean isMultiFlag() {
        return multiFlag;
    }

    public void setMultiFlag(boolean multiFlag) {
        this.multiFlag = multiFlag;
    }

    public boolean isFifoFlag() {
        return fifoFlag;
    }

    public void setFifoFlag(boolean fifoFlag) {
        this.fifoFlag = fifoFlag;
    }

    public double getStationSpeed() {
        return stationSpeed;
    }

    public void setStationSpeed(int stationSpeed) {
        this.stationSpeed = stationSpeed;
    }


    public void removeTask() {

    }

    public boolean canHandleTask(Task task) {
        // Assume this method checks if the station can handle the given task type
        return true;
    }

    public double getUtilization() {
        // Assume this method calculates station utilization
        return 0;
    }


}
