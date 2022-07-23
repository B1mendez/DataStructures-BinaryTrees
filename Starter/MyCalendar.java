/**
 * Name: Brian Mendez
 * ID: A17211975
 * Email: b1mendez@ucsd.edu
 * Sources used: Zybooks and Professor Cao Lecture videos
 * 
 * This file represents a calendar using the file MyTreeMap 
 * to initialize the data. It uses the helper file to simulate a
 * a tree map.
 */

/**
 * Main class that implements two method for calendar and has one 
 * instance variable, that being the calender tree map. 
 */
public class MyCalendar {
    MyTreeMap<Integer, Integer> calendar;
    
    /**
     * Initializes the Instance varaible
     */
    public MyCalendar() {
        this.calendar = new MyTreeMap<>(); 
    }
    
    /**
     * Book the start through end times in the calender
     * If the booking conflicts with another time than 
     * return false for the method. 
     * 
     * @param start - start day of booking
     * @param end - end day of booking
     * @return whether the booking was successful
     */
    public boolean book(int start, int end) {
        if (start < 0 || start >= end){
            throw new IllegalArgumentException(); 
        }

        if (calendar.ceilingKey(start) != null && start < calendar.floorKey(start)){
            return false; 
        }

        if (calendar.ceilingKey(start) != null && end > calendar.floorKey(start)){
            return false; 
        }
        
        calendar.put(start, end); 
        return true; 
    }

    /**
     * Initializes the tree map helper file
     * 
     * @return the calender turned into tree map
     */
    public MyTreeMap getCalendar(){
        return this.calendar;
    }
}