public class Station {

    //String StationID

    //int maxCapacity
    //Boolean multiFlag

    //boolean fifoFlag

    // enum taskTypes
    //int stationSpeed

    //methods constructor, getStationID, getMaxCapacity, isMultiFlag, isFifoFlag, getTaskTypes, getStationSpeed

        private String stationID;
        private int maxCapacity;
        private boolean multiFlag;
        private boolean fifoFlag;

        private int stationSpeed;



        // Consstructor
        public Station(String stationID, int maxCapacity, boolean multiFlag, boolean fifoFlag, String taskType, int stationSpeed) {
            this.stationID = stationID;
            this.maxCapacity = maxCapacity;
            this.multiFlag = multiFlag;
            this.fifoFlag = fifoFlag;

            this.stationSpeed = stationSpeed;
        }
    public Station(String stationID, int maxCapacity, boolean multiFlag, boolean fifoFlag, String taskType, double stationSpeed) {
         this.stationID = "";
        this.maxCapacity = 0;
        this.multiFlag = false;
        this.fifoFlag = false;

        this.stationSpeed = 0;
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


        public int getStationSpeed() {
            return stationSpeed;
        }

        public void setStationSpeed(int stationSpeed) {
            this.stationSpeed = stationSpeed;
        }
}


//fix




