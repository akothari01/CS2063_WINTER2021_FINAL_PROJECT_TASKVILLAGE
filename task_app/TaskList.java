package ca.unb.mobiledev.task_app;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class TaskList {

    private LinkedList<Task> taskList;

    public TaskList()
    {
        taskList = new LinkedList<Task>();
    }

    public void addTask(Task newTask){
        taskList.add(newTask);
    }

    public void removeTask(Task oldTask){
        taskList.remove(oldTask);
    }

    @Override
    public String toString()
    {
        String printed = "";
        for(Task t: taskList)
        {
            printed += t.toString() + "\n";
        }
        return printed;
    }


}
