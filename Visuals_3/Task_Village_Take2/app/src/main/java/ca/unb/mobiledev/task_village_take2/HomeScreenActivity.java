package ca.unb.mobiledev.task_village_take2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.pm.ActivityInfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class HomeScreenActivity extends AppCompatActivity implements Serializable {


    private ListView villageList;
    private ArrayList<Village> userVillages;
    private ArrayAdapter<Village> villageItemAdapter;
    private TextView textView;
    private Button addButton;
    private boolean empty;
    private TaskViewModel taskViewModel;
    private LiveData<List<Village>> villageData;
    private int listOfHouses[] = {R.drawable.blackhouse, R.drawable.brightbluehouse, R.drawable.brightorangehouse, R.drawable.brightpurplehouse, R.drawable.brightredhouse, R.drawable.brownhouse, R.drawable.darkbluehouse, R.drawable.darkgreenhouse, R.drawable.darkorangehouse,
            R.drawable.darkpurplehouse, R.drawable.darkredhouse, R.drawable.greyhouse};
    private Button instructions;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        /**
        userVillages = new ArrayList<>();

        getSupportActionBar().hide();
        if(userVillages.size() == 0){
            empty = true;
        }
        */

        textView = findViewById(R.id.textView4);
        addButton = findViewById(R.id.addButton);
        villageList = (ListView)findViewById(R.id.villages);
        instructions = findViewById(R.id.Inst_button);

        if(empty){
            textView.setText("No Villages Found");
        }
        else{
            textView.setText("Villages:");
        }

        instructions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScreenActivity.this, instruction_activity.class);
                startActivity(intent);
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addIntent = new Intent(HomeScreenActivity.this, CreateVillageActivity.class);
                startActivityForResult(addIntent, 1);
            }
        });

        //villageItemAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, userVillages);
        //villageList.setAdapter(villageItemAdapter);
        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        villageData = taskViewModel.getVillageList();
        villageData.observe(this, new Observer<List<Village>>() {
            @Override
            public void onChanged(List<Village> villages) {
                if(villages == null)
                {
                    textView.setText("No Villages Found");
                }
                else
                {
                    textView.setText("Villages:");
                    villageItemAdapter = new ArrayAdapter<>(HomeScreenActivity.this, android.R.layout.simple_list_item_1, villages);
                    villageList.setAdapter(villageItemAdapter);
                }
                villageItemAdapter.notifyDataSetChanged();

            }

        });
        setUpVillageList();

    }

    private void setUpVillageList(){

        villageList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent villageStart = new Intent(HomeScreenActivity.this, MainActivity.class);
                //villageStart.putExtra("Village", userVillages.get(position));
                villageData.observe(HomeScreenActivity.this, new Observer<List<Village>>() {
                    @Override
                    public void onChanged(List<Village> villages) {
                        villageStart.putExtra("Village", villages.get(position).getId() + "");
                    }
                });
                startActivity(villageStart);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                //System.out.println("REACHED HERE");
                String vilName = data.getStringExtra("VilName");
                //System.out.println("HERE AS WELL");
                ArrayList<building> buildingArrayList = new ArrayList<building>();
                //System.out.println(Village.MAX_BUILDINGS);
                //System.out.println("THEN HERE");
                //System.out.println(buildingArrayList.size());
                for(int i = 0; i < Village.MAX_BUILDINGS; i++)
                {
                    //System.out.println("FINALLY HERE");
                    int random = new Random().nextInt(listOfHouses.length);
                    building newBuilding = new building(listOfHouses[random]);
                    buildingArrayList.add(newBuilding);
                    //System.out.println(buildingArrayList.get(i));
                }
                //System.out.println(buildingArrayList.size());
                //System.out.println("AT LAST");
                taskViewModel.addVillage(vilName, buildingArrayList, 2, 0);
                //System.out.println("GG");
                //Village newVillage = new Village(vilName);
                //villageItemAdapter.add(newVillage);
                textView.setText("Villages: ");


            }
        }
    }
}
