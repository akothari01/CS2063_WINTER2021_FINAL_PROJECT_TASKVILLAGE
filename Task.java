package ca.unb.mobiledev.task_village_take2;

import java.util.Date;

public class Task {

    private String title;
    private Date initiated;


    public Task(String title, Date initiated)
    {
        this.title = title;
        this.initiated = initiated;
    }

    public Task(String title, String desc, Date initiated)
    {
        this.title = title;
        this.initiated = initiated;

    }

    public String getTitle() {
        return title;
    }


    public Date getInitiated() {
        return initiated;
    }


    public void setTitle(String title) {
        this.title = title;
    }



    @Override
    public String toString()
    {
        String printed = "";
        printed += title + "\nCreated on: " + this.initiated;
        return printed;
    }
}
