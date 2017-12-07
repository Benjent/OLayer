package fr.enssat.charpentiermorvan.o_layer;

import java.util.ArrayList;

/**
 * Created by benjent on 07/12/17.
 */

public class VideoMetadata {

    int id;
    String name;
    ArrayList tags;

    public VideoMetadata(Object object) {

        /*this.id = object.getId();
        this.name = object.getName();
        this.tags = object.getTags();*/
        // TODO delete lines below when JSON parser is done
        this.id = 0;
        this.name = null;
        this.tags = null;

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList getTags() {
        return tags;
    }

    public void addTag(String tag) {
        if(tag != null) {
            this.tags.add(tag);
        }
    }
}
