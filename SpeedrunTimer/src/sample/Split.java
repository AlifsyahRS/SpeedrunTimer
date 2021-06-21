package sample;

import javafx.beans.property.SimpleStringProperty;
// Each Split object represents a segment of the speedrun
public class Split {
    // TableView objects make use of SimpleStringProperties instead of regular strings
    // If splitTitle and timeCompleted were regular strings, the data won't show up on the table
    private SimpleStringProperty splitTitle; // Name of the segment
    private SimpleStringProperty timeCompleted; // Time that it's completed (Doesn't update yet)


    public Split(String splitTitle){
        this.splitTitle = new SimpleStringProperty(splitTitle);
        timeCompleted = new SimpleStringProperty("00:00:00");
    }

    // Setter and getter methods
    // Here, we have to use .set() and .get() since we're working with SimpleStringProperties
    public String getSplitTitle(){
        return splitTitle.get();
    }

    public String getTimeCompleted(){
        return timeCompleted.get();
    }

    public void setSplitTitle(String splitTitle){
        this.splitTitle.set(splitTitle);
    }

    public void setSplitTime(Stopwatch timer){
        this.timeCompleted.set(timer.getTimeLabelText()); // Using getTimeLabelText from Stopwatch class
    }
}
