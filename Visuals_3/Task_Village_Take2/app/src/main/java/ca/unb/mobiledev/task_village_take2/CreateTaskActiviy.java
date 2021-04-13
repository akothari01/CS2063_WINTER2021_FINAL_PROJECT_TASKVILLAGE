package ca.unb.mobiledev.task_village_take2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import android.content.pm.ActivityInfo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;


public class CreateTaskActiviy extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, AdapterView.OnItemSelectedListener {

    private Button createBtn;
    private Button cancelBtn;
    private EditText enterTitle;
    private EditText enterDesc;
    private TextView enterDeadline;
    private Spinner spinner1;
    private Spinner spinner2;

    private String taskName;
    private String description;
    private String priority;
    private String date = "YYYY/MM/DD";
    private LocalDate dateTime;
    private String type;

    private TaskViewModel mTaskView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task_activiy);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getSupportActionBar().hide();

        mTaskView = new ViewModelProvider(this).get(TaskViewModel.class);

        createBtn = findViewById(R.id.button2);
        cancelBtn = findViewById(R.id.button3);
        enterTitle = findViewById(R.id.editTextTextPersonName);
        enterDesc = findViewById(R.id.editTextTextPersonName2);
        enterDeadline = findViewById(R.id.textView8);
        enterDeadline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });

        spinner1 = (Spinner)findViewById(R.id.spinnerprio);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.priority_levels, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);

        spinner2 = (Spinner)findViewById(R.id.spinnerprio2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.types, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        spinner1.setOnItemSelectedListener(this);
        spinner2.setOnItemSelectedListener(this);

        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                taskName = enterTitle.getText().toString();
                description = enterDesc.getText().toString();
                if(!(taskName.equals("")) && !(description.equals("")) && !date.equals("YYYY/MM/DD")){
                    DateTimeFormatter dt = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                    System.out.println(date);
                    dateTime = LocalDate.parse(date, dt);
                    System.out.println(dateTime.getMonth());
                    mTaskView.addTask(taskName, description, priority, type, dateTime);
                    Toast.makeText(context, "Task added", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else{
                    Toast.makeText(context, "All the information hasn't been entered",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    private void showDatePickerDialog()
    {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this
                , this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
    {
        String date = "";
        month++;
        if(month < 10 && dayOfMonth < 10)
        {
            date = year + "/0" + month + "/0" + dayOfMonth;
        }
        else if(month < 10 || dayOfMonth < 10)
        {
            if(month < 10)
            {
                date = year + "/0" + month + "/" + dayOfMonth;
            }
            if(dayOfMonth < 10)
            {
                date = year + "/" + month + "/0" + dayOfMonth;
            }
        }
        else
        {
            date = year + "/" + month + "/" + dayOfMonth;
        }
        System.out.println(month);
        enterDeadline.setText(date);
        this.date = date;
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        Spinner spin = (Spinner)adapterView;
        if(spin.getId() == R.id.spinnerprio)
        {
            priority = spin.getItemAtPosition(i).toString();
        }
        if(spin.getId() == R.id.spinnerprio2)
        {
            type = spin.getItemAtPosition(i).toString();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
