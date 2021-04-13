package ca.unb.mobiledev.task_village_take2;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDate;
import java.util.Date;

@Entity(tableName = "task_table")
public class Task {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "title")
    private String title;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCompleted(int completed)
    {
        this.completed = completed;
    }

    public int getCompleted()
    {
        return completed;
    }

    @ColumnInfo(name = "desc")
    private String desc;

    @ColumnInfo(name = "deadline")
    private LocalDate deadline;

    @ColumnInfo(name = "priority")
    private String priority;

    @ColumnInfo(name = "type")
    private String type;

    @ColumnInfo(name = "completed")
    private int completed;

    public String getTitle() {
        return title;
    }

    public String getPriority(){ return priority; }

    public LocalDate getDeadline(){return deadline;}

    public void setTitle(String title) {
        this.title = title;
    }

}
