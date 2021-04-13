package ca.unb.mobiledev.task_village_take2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Date;

import android.content.pm.ActivityInfo;

import java.util.Calendar;
import java.util.List;
import java.util.Random;


public class MainActivity extends AppCompatActivity implements Serializable {



    private ArrayList<String> summaries;
    //private ArrayAdapter<Task> itemsAdapter;
    //private ListView listView;
    private RecyclerView recyclerView2;
    private Button button;
    private Button viewButton;
    private ImageView imageView;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerViewAdapter recyclerViewAdapter;
    RecyclerView2Adapter recyclerView2Adapter;
    RecyclerView.LayoutManager layoutManager2;

    SharedPreferences prefs = null;

    //int listOfHouses[] = {R.drawable.blackhouse, R.drawable.brightbluehouse, R.drawable.brightorangehouse, R.drawable.brightpurplehouse, R.drawable.brightredhouse, R.drawable.brownhouse, R.drawable.darkbluehouse, R.drawable.darkgreenhouse, R.drawable.darkorangehouse,
    //        R.drawable.darkpurplehouse, R.drawable.darkredhouse, R.drawable.greyhouse};

    private int villageId;
    private LiveData<Village> villageData;
    private TaskViewModel taskViewModel;
    private String morale = "Morale: ";
    private int comp = 0;
    private int incomp = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //village = new Village("Your Village");

        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#D6C09F")));

        //prefs = getSharedPreferences("ca.unb.mobiledev.task_village_take2", MODE_PRIVATE);
        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);

        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        villageId = Integer.parseInt(getIntent().getStringExtra("Village"));

        //listView = findViewById(R.id.listviewID);
        button = findViewById(R.id.button);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView2 = findViewById(R.id.listviewID);
        layoutManager = new GridLayoutManager(this,3);
        layoutManager2 = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView2.setLayoutManager(layoutManager2);
       // recyclerViewAdapter = new RecyclerViewAdapter(this,currentVillage);
        viewButton = findViewById(R.id.button5);
        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ChooseTaskType.class);
                intent.putExtra("villageID", villageId + "");
                startActivity(intent);
                //finish();
            }
        });

        updateMorale();

        summaries = new ArrayList<String>(3);
        summaries.add(0, "Completed tasks: " + comp);
        summaries.add(1, "Incomplete tasks: " + incomp);
        summaries.add(2, morale);
        //itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Tasks);
        //listView.setAdapter(itemsAdapter);
        recyclerView2Adapter = new RecyclerView2Adapter(MainActivity.this, summaries);
        recyclerView2.setAdapter(recyclerView2Adapter);

        //setUpListViewListener();
        LiveData<Integer> integerLiveData = taskViewModel.getTaskNumByComp(1);
        integerLiveData.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if(integer != null) {
                    comp = integer.intValue();
                    summaries.set(0, "Complete tasks: " + comp);
                    recyclerView2Adapter.notifyDataSetChanged();
                }
            }
        });
        LiveData<Integer> integerLiveData2 = taskViewModel.getTaskNumByComp(0);
        integerLiveData2.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if(integer != null) {
                    incomp = integer.intValue();
                    summaries.set(1, "Incomplete tasks: " + incomp);
                    recyclerView2Adapter.notifyDataSetChanged();
                }
            }
        });

        villageData = taskViewModel.getVillageById(villageId);
        villageData.observe(this, new Observer<Village>() {
            @Override
            public void onChanged(Village village) {
                if(village != null) {
                    boolean reachedLimit = false;
                    ArrayList<building> dabuild = new ArrayList<building>();
                    if(village.getNoBuildings() >= 4  && village.getMorale().equals("Low"))
                    {
                        taskViewModel.increaseNoBuildings(village.getId(), -2);
                        Toast.makeText(getApplicationContext(), "Morale is Low, Villagers are leaving, complete overdue tasks", Toast.LENGTH_LONG).show();
                    }
                    for(int i = 0; i < village.getNoBuildings()/2 && !reachedLimit; i++)
                    {
                        if(i == 11)
                        {
                            reachedLimit = true;
                        }
                        dabuild.add(village.getBuildingList().get(i));
                    }
                    if(reachedLimit)
                    {
                        Toast.makeText(getApplicationContext(), "Max size of village reached, create a new one to continue task progress.", Toast.LENGTH_LONG).show();
                    }
                    recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, dabuild);
                    /**
                    System.out.println(village.getId() + " " + village.getNoBuildings() + " " + village.getBuildingList().size());
                    for(int i = 0; i < village.getNoBuildings(); i++)
                    {
                        System.out.println(village.getBuildingList().get(i));
                    }
                     */
                    getSupportActionBar().setTitle(village.getVilName());
                    recyclerView.setAdapter(recyclerViewAdapter);
                }
                recyclerViewAdapter.notifyDataSetChanged();
            }
        });






        recyclerView.setHasFixedSize(true);





        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //addItem(v);
                Intent intent = new Intent(MainActivity.this, CreateTaskActiviy.class);
                startActivityForResult(intent, 1);

            }
        });


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

    private void updateMorale()
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
                if(count <= 5)
                {
                    System.out.println(count);
                    taskViewModel.changeMorale("High", villageId);
                    morale = "Morale: High :)";
                    summaries.set(2, morale);
                    recyclerView2Adapter.notifyDataSetChanged();
                }
                else
                {
                    taskViewModel.changeMorale("Low", villageId);
                    morale = "Morale: Low :(";
                    summaries.set(2, morale);
                    recyclerView2Adapter.notifyDataSetChanged();
                }
            }
        });
    }

    /**
    @Override
    protected void onResume() {
        super.onResume();

        if(prefs.getBoolean("first",true)){
            Intent intent = new Intent(MainActivity.this, CreateVillageActivity.class);
            startActivityForResult(intent,2);
            Intent start = getIntent();
            String name = start.getStringExtra("VilName");
            //village = new Village(name);

            prefs.edit().putBoolean("first", false).commit();
        }
    }
    */


    /**
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        super.onActivityResult(requestCode, resultCode, intent);
        if(requestCode == 1)
        {
            if(resultCode == RESULT_OK)
            {
                String nameTask = intent.getStringExtra("nameTask");
                DateFormat format = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
                Date currentDate = new Date();
                //Task newTask = new Task(nameTask, currentDate);
                //itemsAdapter.add(newTask);
            }

        }

        if(requestCode == 2){
            String name = intent.getStringExtra("VilName");
            //village = new Village(name);


        }
    }
    */

    /**
    private void setUpListViewListener() {

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Context context = getApplicationContext();
                Toast.makeText(context, "Task Cancelled", Toast.LENGTH_LONG).show();
                Tasks.remove(position);
                itemsAdapter.notifyDataSetChanged();
                return true;
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Context context = getApplicationContext();
                Toast.makeText(context,"Task Completed!", Toast.LENGTH_LONG).show();
                Tasks.remove(position);
                itemsAdapter.notifyDataSetChanged();

                int random = new Random().nextInt(listOfHouses.length);

                building newBuilding = new building(listOfHouses[random]);

                village.add(newBuilding);

                recyclerViewAdapter.notifyDataSetChanged();


            }
        });


    }
     */







/**
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

*/
}
