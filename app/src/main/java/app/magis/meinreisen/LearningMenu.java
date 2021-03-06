package app.magis.meinreisen;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;


public class LearningMenu extends AppCompatActivity implements View.OnClickListener {

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_vertical);
        LinearLayout kerja = (LinearLayout) findViewById(R.id.menu_one);
        LinearLayout benda = (LinearLayout) findViewById(R.id.menu_two);
        LinearLayout tempat = (LinearLayout) findViewById(R.id.menu_three);
        kerja.setBackground(getResources().getDrawable(R.drawable.verb));
        benda.setBackground(getResources().getDrawable(R.drawable.noun));
        tempat.setBackground(getResources().getDrawable(R.drawable.place));
        kerja.setOnClickListener(this);
        benda.setOnClickListener(this);
        tempat.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i;
        switch (view.getId()){
            case R.id.menu_one:
                 i = new Intent(LearningMenu.this, LearningKerja.class);
                startActivity(i);
                break;
            case R.id.menu_two:
                i = new Intent(LearningMenu.this, LearningBenda.class);
                startActivity(i);
                break;
            case R.id.menu_three:
                i = new Intent(LearningMenu.this, LearningTempat.class);
                startActivity(i);
                break;
            default:
                break;
        }
    }
}
