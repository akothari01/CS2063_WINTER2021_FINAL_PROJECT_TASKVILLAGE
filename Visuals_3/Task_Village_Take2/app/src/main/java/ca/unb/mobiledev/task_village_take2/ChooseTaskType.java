package ca.unb.mobiledev.task_village_take2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.time.LocalDate;
import java.util.List;

public class ChooseTaskType extends AppCompatActivity implements View.OnClickListener{

    private Button urim;
    private Button urnim;
    private Button nurim;
    private Button nurnim;
    private Button overdue;

    private TaskViewModel taskViewModel;

    public static final String PRIO_CODE = "priorityCode";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_task_type);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#D6C09F")));
        urim = (Button)findViewById(R.id.button7);
        urnim = (Button)findViewById(R.id.button6);
        nurim = (Button)findViewById(R.id.button4);
        nurnim = (Button)findViewById(R.id.button8);
        overdue = (Button)findViewById(R.id.button12);

        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);


        /**
        urim.setText("Urgent and Important" + "\n" + taskViewModel.getTaskNumByPrio("Urgent and Important"));
        urnim.setText("Urgent and not Important" + "\n" +
                taskViewModel.getTaskNumByPrio("Urgent and not Important"));
        nurim.setText("Not Urgent but Important" + "\n" + taskViewModel.getTaskNumByPrio("Not Urgent but Important"));
        nurnim.setText("Not Urgent and not Important" + "\n" +
                taskViewModel.getTaskNumByPrio("Not Urgent and not Important"));
         */
        setNewText("Urgent and Important", urim);
        setNewText("Urgent and not Important", urnim);
        setNewText("Not Urgent but Important", nurim);
        setNewText("Not Urgent and not Important", nurnim);
        setNewText("Overdue", overdue);
        urim.setOnClickListener(this);
        urnim.setOnClickListener(this);
        nurim.setOnClickListener(this);
        nurnim.setOnClickListener(this);
        overdue.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(ChooseTaskType.this, TaskListActivity.class);
        intent.putExtra("ID", getIntent().getStringExtra("villageID"));
        switch (view.getId())
        {
            case(R.id.button7):
                intent.putExtra(ChooseTaskType.PRIO_CODE, "Urgent and Important");
                break;
            case(R.id.button6):
                intent.putExtra(ChooseTaskType.PRIO_CODE, "Urgent and not Important");
                break;
            case(R.id.button4):
                intent.putExtra(ChooseTaskType.PRIO_CODE, "Not Urgent but Important");
                break;
            case(R.id.button8):
                intent.putExtra(ChooseTaskType.PRIO_CODE, "Not Urgent and not Important");
                break;
            case(R.id.button12):
                intent.putExtra(ChooseTaskType.PRIO_CODE, "Overdue");
        }
        startActivity(intent);
    }

    private void setNewText(String prio, Button button)
    {
        if(!prio.equals("Overdue")) {
            final LiveData<Integer> countDataurim = taskViewModel.getTaskNumByPrio(prio);
            countDataurim.observe(this, new Observer<Integer>() {
                @Override
                public void onChanged(Integer integer) {
                    int i = 0;
                    if(integer != null) {
                        i = integer.intValue();
                    }
                    button.setText(prio + "\n" + i);
                }
            });
        }
        else
        {
            getOverDueCount();
        }
    }

    private void getOverDueCount()
    {
        final LiveData<List<Task>> taskData = taskViewModel.getEveryTask();
        taskData.observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                int count = 0;
                if(tasks != null) {
                    for (int i = 0; i < tasks.size(); i++) {
                        if (tasks.get(i).getDeadline().isBefore(LocalDate.now())) {
                            count++;
                        }
                    }
                }
                overdue.setText("Overdue" + "\n" + count);
            }
        });
    }
}