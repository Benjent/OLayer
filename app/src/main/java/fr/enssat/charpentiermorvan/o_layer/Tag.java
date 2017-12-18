package fr.enssat.charpentiermorvan.o_layer;

/**
 * Created by benjent on 18/12/17.
 */

public class Tag {

    String label;
    Integer timeStamp;

    public Tag(String label, Integer timeStamp) {
        this.label = label;
        this.timeStamp = timeStamp;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Integer timeStamp) {
        this.timeStamp = timeStamp;
    }
}
