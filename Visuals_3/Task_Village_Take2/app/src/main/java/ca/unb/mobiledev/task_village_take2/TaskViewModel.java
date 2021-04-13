package ca.unb.mobiledev.task_village_take2;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskViewModel extends AndroidViewModel {
    private TaskRepository taskRepository;
    private VillageRepository villageRepository;

    public TaskViewModel(@NonNull Application application) {
        super(application);
        taskRepository = new TaskRepository(application);
        villageRepository = new VillageRepository(application);
    }

    public void addTask(String name, String desc, String prio, String type, LocalDate deadline)
    {
        taskRepository.insertRecord(name, desc, prio, type, deadline);
    }

    public LiveData<List<Task>> getTasksByPrio(String prio)
    {
        return taskRepository.getTasks(prio);
    }

    public LiveData<List<Task>> getEveryTask()
    {
        return taskRepository.getTasksAll();
    }

    public LiveData<Integer> getTaskNumByPrio(String prio)
    {
        return taskRepository.getTaskCountByPrio(prio);
    }

    public LiveData<Integer> getTaskNumByComp(int comp)
    {
        return taskRepository.getTaskCount(comp);
    }


    public void completeTask(int id)
    {
        taskRepository.completeTask(id);
    }

    public void addVillage(String name, ArrayList<building> buildings, int no, int add)
    {
        villageRepository.insertVillage(name, buildings, no, add);
    }

    public LiveData<Village> getVillageById(int id)
    {
        return villageRepository.getVillageById(id);
    }

    public LiveData<List<Village>> getVillageList()
    {
        return villageRepository.getVillages();
    }

    public void increaseNoBuildings(int id, int num)
    {
        villageRepository.increaseBuilding(id, num);
    }

    public void changeMorale(String mor, int id)
    {
        villageRepository.changeMorale(mor, id);
    }

}

