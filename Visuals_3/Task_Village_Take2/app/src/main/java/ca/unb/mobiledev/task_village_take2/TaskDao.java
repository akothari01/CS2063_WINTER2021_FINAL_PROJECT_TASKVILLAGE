package ca.unb.mobiledev.task_village_take2;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.IGNORE;

@Dao
public interface TaskDao
{

    @Insert(onConflict = IGNORE)
    void insert(Task task);

    @Query("SELECT * FROM task_table WHERE priority LIKE :prio AND completed = 0 ORDER BY deadline")
    LiveData<List<Task>> getTasksOnPrio(String prio);

    @Query("UPDATE task_table SET completed = 1 WHERE id = :id")
    void complete(int id);

    @Query("SELECT COUNT(*) FROM task_table WHERE completed = :comp")
    LiveData<Integer> getTaskNumber(int comp);

    @Query("SELECT * FROM task_table WHERE completed = 0 ORDER BY deadline")
    LiveData<List<Task>> getAllTasks();

    @Query("SELECT COUNT(*) FROM task_table WHERE completed = 0 AND priority LIKE :prio")
    LiveData<Integer> getTaskPrioNumber(String prio);

}

