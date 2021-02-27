package ca.unb.mobiledev.task_village_take2;

import java.util.*;

import java.lang.reflect.Array;


public class sortTasks{

    public int compareTasks(Task task1, Task task2){

        if(task1.getPriority() > task2.getPriority()){
            return 1;
        }

        else if(task1.getPriority() == task2.getPriority()){
            return 0;
        }

        else{
            return -1;
        }

    }

    public ArrayList<Task> sortTasks(ArrayList<Task> taskList){

        if(taskList.size() <= 1){
            return taskList;
        }

        ArrayList<Task> sorted = new ArrayList<Task>();
        ArrayList<Task> lesser = new ArrayList<Task>();
        ArrayList<Task> greater = new ArrayList<Task>();

        Task pivot = taskList.get(taskList.size()-1);
        for(int i = 0; i < taskList.size()-1; i++){
            if((compareTasks(taskList.get(i), pivot)) < 0){
                lesser.add(taskList.get(i));
            }

            else{
                greater.add(taskList.get(i));
            }

        }

        lesser = sortTasks(lesser);
        greater = sortTasks(greater);

        lesser.add(pivot);
        lesser.addAll(greater);
        sorted = lesser;

        return sorted;



    }

}


