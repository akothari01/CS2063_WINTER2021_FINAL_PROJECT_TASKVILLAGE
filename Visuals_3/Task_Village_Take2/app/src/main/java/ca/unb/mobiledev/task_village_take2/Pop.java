package ca.unb.mobiledev.task_village_take2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

import androidx.annotation.Nullable;

import org.w3c.dom.Text;

public class Pop extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.popupwindow);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getActionBar().hide();

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*0.8),(int)(height*0.6));

        Intent intent = getIntent();

        String inhabitant = intent.getStringExtra("Inhabitant");
        TextView txtView = findViewById(R.id.inName);
        txtView.setText(inhabitant);


    }
}
