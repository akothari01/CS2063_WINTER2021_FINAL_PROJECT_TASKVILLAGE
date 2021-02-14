package ca.unb.mobiledev.task_app;

import java.time.LocalDateTime;

public class Task {

    private String title;
    private String desc;
    private LocalDateTime initiated;
    private LocalDateTime deadline;
    private boolean isDeadLine;
    private boolean isDone;

    public Task(String title, String desc, LocalDateTime initiated,
                LocalDateTime deadline, boolean isDeadLine)
    {
        this.title = title;
        this.desc = desc;
        this.initiated = initiated;
        this.deadline = deadline;
        this.isDeadLine = isDeadLine;
        this.isDone = false;
    }

    public Task(String title, String desc, LocalDateTime initiated)
    {
        this.title = title;
        this.desc = desc;
        this.initiated = initiated;
        this.isDeadLine = false;
        this.isDone = false;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public LocalDateTime getInitiated() {
        return initiated;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public boolean isDeadLine() {
        return isDeadLine;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public void setDeadLine(boolean deadLine) {
        isDeadLine = deadLine;
        if(deadLine == false)
        {
            this.deadline = null;
        }
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public String toString()
    {
        String printed = "";
        printed += title + "\n\n\t" + desc + "\n\tFrom:" + this.initiated.toString() + " To" +
                    this.deadline.toString() + "\nTask Status: " + this.isDone;
        return printed;
    }
}
