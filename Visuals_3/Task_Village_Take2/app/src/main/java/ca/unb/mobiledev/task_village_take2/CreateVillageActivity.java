package ca.unb.mobiledev.task_village_take2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import android.content.pm.ActivityInfo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CreateVillageActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_create_village_activity);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        EditText text = (EditText)findViewById(R.id.vilNameField);
        Button button = findViewById(R.id.enterVil);

        getSupportActionBar().hide();

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                String villageName = text.getText().toString();
                if(!(villageName.equals(""))){
                    Intent intent = new Intent();
                    intent.putExtra("VilName", villageName);
                    setResult(RESULT_OK, intent);
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Villages need a name", Toast.LENGTH_LONG).show();

                }
            }
        });
    }
}
