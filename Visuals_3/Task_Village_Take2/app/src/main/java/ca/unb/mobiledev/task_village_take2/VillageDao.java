package ca.unb.mobiledev.task_village_take2;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.IGNORE;

@Dao
public interface VillageDao {

    @Insert(onConflict = IGNORE)
    void insert(Village village);

    @Query("SELECT * FROM village_table WHERE id = :id")
    LiveData<Village> getVillageFromId(int id);

    @Query("SELECT * FROM village_table")
    LiveData<List<Village>> getAllVillages();

    @Query("UPDATE village_table SET noOfBuildings = (noOfBuildings + :num) WHERE id = :id")
    void increaseB(int id, int num);

    @Query("UPDATE village_table SET morale = :mor WHERE id = :id")
    void changeMorale(String mor, int id);
}
