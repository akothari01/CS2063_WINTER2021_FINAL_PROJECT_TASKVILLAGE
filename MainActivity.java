package ca.unb.mobiledev.task_village_take2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Date;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Task> Tasks;
    private ArrayAdapter<Task> itemsAdapter;
    private ListView listView;
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listviewID);
        button = findViewById(R.id.button);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addItem(v);

            }
        });

        Tasks = new ArrayList<>();
        itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Tasks);
        listView.setAdapter(itemsAdapter);

        setUpListViewListener();



    }

    private void setUpListViewListener() {

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Context context = getApplicationContext();
                Toast.makeText(context, "Task Removed", Toast.LENGTH_LONG).show();
                Tasks.remove(position);
                itemsAdapter.notifyDataSetChanged();
                return true;
            }
        });

    }

    private void addItem(View v) {

        Context context = getApplicationContext();



        EditText input = findViewById(R.id.editTextTaskName);
        DateFormat format = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Date currentDate = new Date();


        String itemText = input.getText().toString();
        Task newTask = new Task(itemText, currentDate);

        if(!(itemText.equals(""))){
            itemsAdapter.add(newTask);
            input.setText("");
        }

        else{
            Toast.makeText(context, "New Tasks need a name", Toast.LENGTH_LONG).show();
        }


    }


}