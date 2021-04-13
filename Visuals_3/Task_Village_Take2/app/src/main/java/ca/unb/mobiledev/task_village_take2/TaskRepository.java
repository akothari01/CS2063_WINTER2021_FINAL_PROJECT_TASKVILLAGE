package ca.unb.mobiledev.task_village_take2;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.time.LocalDate;
import java.util.List;

public class TaskRepository {
    private TaskDao taskDao;

    public TaskRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        taskDao = db.taskDao();
    }

    public void insertRecord(String name, String desc, String prio, String type, LocalDate deadline)
    {
        Task taskAdd = new Task();
        taskAdd.setDeadline(deadline);
        taskAdd.setDesc(desc);
        taskAdd.setPriority(prio);
        taskAdd.setTitle(name);
        taskAdd.setType(type);
        taskAdd.setCompleted(0);
        insertRecord(taskAdd);
    }
    private void insertRecord(final Task task)
    {
        AppDatabase.databaseWriterExecutor.execute(new Runnable() {
            @Override
            public void run() {
                taskDao.insert(task);
            }
        });
    }

    public LiveData<List<Task>> getTasks(String prio)
    {
        return taskDao.getTasksOnPrio(prio);
    }

    public LiveData<List<Task>> getTasksAll()
    {
        return taskDao.getAllTasks();
    }

    public LiveData<Integer> getTaskCount(int comp)
    {
        return taskDao.getTaskNumber(comp);
    }

    public LiveData<Integer> getTaskCountByPrio(String prio)
    {
        return taskDao.getTaskPrioNumber(prio);
    }

    public void completeTask(final int id)
    {
        AppDatabase.databaseWriterExecutor.execute(new Runnable() {
            @Override
            public void run() {
                taskDao.complete(id);
            }
        });
    }

}
