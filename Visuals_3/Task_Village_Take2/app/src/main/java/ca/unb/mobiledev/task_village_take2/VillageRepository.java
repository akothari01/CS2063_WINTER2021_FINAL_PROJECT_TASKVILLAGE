package ca.unb.mobiledev.task_village_take2;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

public class VillageRepository {
    private VillageDao villageDao;

    public VillageRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        villageDao = db.villageDao();
    }

    public void insertVillage(String name, ArrayList<building> buildings, int no, int add)
    {
        Village village = new Village();
        village.setBuildingList(buildings);
        village.setNoBuildings(no);
        //village.setToAdd(add);
        village.setVilName(name);
        village.setMorale("High");
        insertVillage(village);
    }

    private void insertVillage(Village village)
    {
        AppDatabase.databaseWriterExecutor.execute(new Runnable() {
            @Override
            public void run() {
                villageDao.insert(village);
            }
        });
    }

    public void increaseBuilding(int id, int num)
    {
        AppDatabase.databaseWriterExecutor.execute(new Runnable() {
            @Override
            public void run() {
                villageDao.increaseB(id, num);
            }
        });
    }


    public LiveData<Village> getVillageById(int id)
    {
        return villageDao.getVillageFromId(id);
    }

    public LiveData<List<Village>> getVillages()
    {
        return villageDao.getAllVillages();
    }

    public void changeMorale(String mor, int id)
    {
        AppDatabase.databaseWriterExecutor.execute(new Runnable() {
            @Override
            public void run() {
                villageDao.changeMorale(mor, id);
            }
        });
    }
}
