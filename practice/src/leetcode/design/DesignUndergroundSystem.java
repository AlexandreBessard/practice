package leetcode.design;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/design-underground-system/
public class DesignUndergroundSystem {
    /*
    An underground railway system is keeping track of customer travel times between different stations. They are using this data to calculate the average time it takes to travel from one station to another.

    Implement the UndergroundSystem class:

    void checkIn(int id, string stationName, int t)
    A customer with a card ID equal to id, checks in at the station stationName at time t.
    A customer can only be checked into one place at a time.
    void checkOut(int id, string stationName, int t)
    A customer with a card ID equal to id, checks out from the station stationName at time t.
    double getAverageTime(string startStation, string endStation)
    Returns the average time it takes to travel from startStation to endStation.
    The average time is computed from all the previous traveling times from startStation to endStation that happened directly, meaning a check in at startStation followed by a check out from endStation.
    The time it takes to travel from startStation to endStation may be different from the time it takes to travel from endStation to startStation.
    There will be at least one customer that has traveled from startStation to endStation before getAverageTime is called.
    You may assume all calls to the checkIn and checkOut methods are consistent. If a customer checks in at time t1 then checks out at time t2, then t1 < t2. All events happen in chronological order.
     */
    public static void main(String[] args) {
        UndergroundSystem undergroundSystem = new UndergroundSystem();
        undergroundSystem.checkIn(45, "Leyton", 3);
        undergroundSystem.checkIn(32, "Paradise", 8);
        undergroundSystem.checkIn(27, "Leyton", 10);
        undergroundSystem.checkOut(45, "Waterloo", 15);  // Customer 45 "Leyton" -> "Waterloo" in 15-3 = 12
        undergroundSystem.checkOut(27, "Waterloo", 20);  // Customer 27 "Leyton" -> "Waterloo" in 20-10 = 10
        undergroundSystem.checkOut(32, "Cambridge", 22); // Customer 32 "Paradise" -> "Cambridge" in 22-8 = 14
        System.out.println(undergroundSystem.getAverageTime("Paradise", "Cambridge")); // return 14.00000. One trip "Paradise" -> "Cambridge", (14) / 1 = 14
        undergroundSystem.getAverageTime("Leyton", "Waterloo");    // return 11.00000. Two trips "Leyton" -> "Waterloo", (10 + 12) / 2 = 11
        undergroundSystem.checkIn(10, "Leyton", 24);
        System.out.println(undergroundSystem.getAverageTime("Leyton", "Waterloo"));    // return 11.00000
        undergroundSystem.checkOut(10, "Waterloo", 38);  // Customer 10 "Leyton" -> "Waterloo" in 38-24 = 14
        System.out.println(undergroundSystem.getAverageTime("Leyton", "Waterloo"));    // return 12.00000. Three trips "Leyton" -> "Waterloo", (10 + 12 + 14) / 3 = 12
    }

    //Correction: https://leetcode.com/problems/design-underground-system/discuss/672744/Java-solution-for-easy-understanding-using-OOPS
    static class UndergroundSystem {
        //Key: Id of passenger, Value: passenger associated to this key
        Map<Integer, Trip> currentPassengerMap;
        //Use to calculate our average per route
        //Key: string composed [startLocation + endLocation], Value: the object info for this route
        Map<String, Route> routeMap;
        private static final String SEPARATOR = ",";
        public UndergroundSystem() {
            currentPassengerMap = new HashMap<>();
            routeMap = new HashMap<>();
        }

        /*
        A customer with a card ID equal to id, checks in at the station stationName at time t.
        A customer can only be checked into one place at a time.
         */
        public void checkIn(int id, String stationName, int t) {
            if(!currentPassengerMap.containsKey(id)) {
                Trip passenger = new Trip(stationName, t);
                currentPassengerMap.put(id, passenger);
            }
        }

        //A customer with a card ID equal to id, checks out from the station stationName at time t.
        public void checkOut(int id, String stationName, int t) {
            if(currentPassengerMap.containsKey(id)) {
                Trip passenger = currentPassengerMap.get(id);
                passenger.checkout(stationName, t);
                //Compose the key:
                var strBuilder = new StringBuilder()
                        .append(passenger.checkinLocation)
                        .append(SEPARATOR) //Separator
                        .append(passenger.checkoutLocation);
                String routeKey = strBuilder.toString();
                //Returns the value to which the specified key is mapped, or defaultValue if this map contains no mapping for the key.
                Route route = routeMap.getOrDefault(routeKey, new Route(passenger.checkinLocation, passenger.checkoutLocation));
                //Calculate how long it takes to travel from start to finish for this trip
                route.addTrip(passenger.checkinTime, passenger.checkoutTime);
                routeMap.put(routeKey, route);
                //Remove passenger because it is a checkout
                currentPassengerMap.remove(id);
            }
        }

        /*
        Returns the average time it takes to travel from startStation to endStation.
        The average time is computed from all the previous traveling times from startStation to endStation that happened directly, meaning a check in at startStation followed by a check out from endStation.
        The time it takes to travel from startStation to endStation may be different from the time it takes to travel from endStation to startStation.
        There will be at least one customer that has traveled from startStation to endStation before getAverageTime is called.
         */
        public double getAverageTime(String startStation, String endStation) {
            var strBuilder = new StringBuilder(startStation)
                    .append(SEPARATOR)
                    .append(endStation);
            //Get the corresponding trip from the map and calculate the average
            return routeMap.get(strBuilder.toString()).getAverageTime();
        }

    }

    static class Route {
        String startStation;
        String endStation;
        int totalNumberOfTrips;
        long totalTimeSpentInTrips;

        public Route(String startStation, String endStation) {
            this.startStation = startStation;
            this.endStation = endStation;
        }

        public double getAverageTime() {
            return (double) totalTimeSpentInTrips / totalNumberOfTrips;
        }

        public void addTrip(int startTime, int endTime) {
            //Calculate time it took to travel
            totalTimeSpentInTrips += endTime - startTime;
            //count number of trip
            totalNumberOfTrips++;
        }
    }

    static class Trip {
        //CheckIn
        int checkinTime;
        String checkinLocation;
        //CheckOut
        int checkoutTime;
        String checkoutLocation;

        public Trip(String checkinLocation, int checkinTime) {
            this.checkinLocation = checkinLocation;
            this.checkinTime = checkinTime;
        }

        public void checkout(String checkoutLocation, int checkoutTime) {
            this.checkoutLocation = checkoutLocation;
            this.checkoutTime = checkoutTime;
        }
    }

}
