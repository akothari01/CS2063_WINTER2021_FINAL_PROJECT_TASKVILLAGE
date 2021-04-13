package ca.unb.mobiledev.task_village_take2;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;

@Entity(tableName = "village_table")
public class Village implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name="villageName")
    private String vilName;

    @ColumnInfo(name="buildingsList")
    private ArrayList<building> buildingList;

    @ColumnInfo(name = "noOfBuildings")
    private int noBuildings;

    public String getMorale() {
        return morale;
    }

    public void setMorale(String morale) {
        this.morale = morale;
    }

    @ColumnInfo(name = "morale")
    private String morale;

    /**
    // 0 for false, 1 for true
    @ColumnInfo(name = "toAdd")
    private int toAdd;
    */

    public static final int MAX_BUILDINGS = 12;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVilName() {
        return vilName;
    }

    public void setVilName(String vilName) {
        this.vilName = vilName;
    }

    public ArrayList<building> getBuildingList() {
        return buildingList;
    }

    public void setBuildingList(ArrayList<building> buildingList) {
        this.buildingList = buildingList;
    }

    public int getNoBuildings() {
        return noBuildings;
    }

    public void setNoBuildings(int noBuildings) {
        this.noBuildings = noBuildings;
    }

    /**
    public int getToAdd() {
        return toAdd;
    }

    public void setToAdd(int toAdd) {
        this.toAdd = toAdd;
    }
     */
/**
    public Village(String vilName){
        this.vilName = vilName;
        buildingList = new ArrayList<building>();
    }


    public String getVilName(){
        return vilName;
    }

    public ArrayList<building> getBuildings(){
        return buildingList;
    }

    public void add(building newBuild){
        buildingList.add(newBuild);
    }
    */
    @Override
    public String toString(){
        String printed = "";
        printed += this.vilName;
        return printed;
    }


}
