package ca.unb.mobiledev.task_village_take2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskListActivity extends AppCompatActivity {

    private TaskViewModel taskViewModel;
    private TaskAdapter taskAdapter;
    private ListView taskListV;
    private LocalDate currentDate;
    private String priority;
    private TextView monthYear;
    private TextView none;
    private Button nextButton;
    private Button previousButton;
    private LiveData<List<Task>> tasksData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        taskListV = (ListView)findViewById(R.id.listview);
        priority = getIntent().getExtras().getString(ChooseTaskType.PRIO_CODE);
        monthYear = (TextView)findViewById(R.id.textView9);
        nextButton = (Button)findViewById(R.id.button10);
        previousButton = (Button)findViewById(R.id.button11);
        none = (TextView)findViewById(R.id.textView10);
        currentDate = LocalDate.now();
        monthYear.setText(currentDate.getMonth() + ", " + currentDate.getYear());
        getSupportActionBar().setTitle("");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#D6C09F")));

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentDate = currentDate.plusMonths(1);
                monthYear.setText(currentDate.getMonth() + ", " + currentDate.getYear());
                getTasks(priority);
            }
        });
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentDate = currentDate.minusMonths(1);
                monthYear.setText(currentDate.getMonth() + ", " + currentDate.getYear());
                getTasks(priority);
            }
        });
        if(priority.equals("Overdue"))
        {
            monthYear.setVisibility(View.INVISIBLE);
            nextButton.setVisibility(View.INVISIBLE);
            previousButton.setVisibility(View.INVISIBLE);
        }
        getTasks(priority);
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

    private void getTasks(String priority)
    {
        if(priority.equals("Overdue"))
        {
          tasksData = taskViewModel.getEveryTask();
        }
        else
        {
            tasksData = taskViewModel.getTasksByPrio(priority);
        }
        //final LiveData<List<Task>> tasksData = taskViewModel.getTasksByPrio(priority);
        tasksData.observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                if(tasks != null)
                {
                    List<Task> toUse = new ArrayList<Task>();
                    for(int i = 0; i < tasks.size(); i++)
                    {
                        //System.out.println("task: " + tasks.get(i).getDeadline().getYear() + ", " + tasks.get(i).getDeadline().getMonth());
                        //System.out.println("current: " + currentDate.getYear() + ", " + currentDate.getMonth());
                        if(priority.equals("Overdue"))
                        {
                            if(tasks.get(i).getDeadline().isBefore(currentDate))
                            {
                                toUse.add(tasks.get(i));
                            }
                        }
                        else {
                            if (tasks.get(i).getDeadline().getYear() == currentDate.getYear() && tasks.get(i).getDeadline().getMonth() == currentDate.getMonth()) {
                                toUse.add(tasks.get(i));
                            }
                        }
                    }
                    if(toUse.size() == 0)
                    {
                        none.setText("No tasks due");
                    }
                   taskAdapter = new TaskAdapter(getApplicationContext(), toUse, taskViewModel, Integer.parseInt(getIntent().getStringExtra("ID")));
                   taskListV.setAdapter(taskAdapter);
                }
                taskAdapter.notifyDataSetChanged();
            }
        });
    }
}