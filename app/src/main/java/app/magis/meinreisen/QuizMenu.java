package app.magis.meinreisen;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;


public class QuizMenu extends AppCompatActivity implements View.OnClickListener {

    LinearLayout level_one, level_two, level_three;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    protected void setLevel(){
        Connector con = new Connector(this);
        con.open();
        Cursor rs = con.database.rawQuery("select * from levels where level = 2", null);
        if(rs.moveToFirst()){
            if(rs.getInt(rs.getColumnIndex("status")) == 1){
                level_two.setOnClickListener(this);
                level_two.setBackground(getResources().getDrawable(R.drawable.normal));
            }else{
                level_two.setBackground(getResources().getDrawable(R.drawable.normal_lock));

            }
        }
        rs.close();
        rs = con.database.rawQuery("select * from levels where level = 3", null);
        if(rs.moveToFirst()){
            if(rs.getInt(rs.getColumnIndex("status")) == 1){
                level_three.setOnClickListener(this);
                level_three.setBackground(getResources().getDrawable(R.drawable.hard));
            }else{
                level_three.setBackground(getResources().getDrawable(R.drawable.hard_lock));
            }
        }
        rs.close();
        con.close();

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_vertical);
        level_one = (LinearLayout) findViewById(R.id.menu_one);
        level_two = (LinearLayout) findViewById(R.id.menu_two);
        level_three = (LinearLayout) findViewById(R.id.menu_three);

        level_one.setBackground(getResources().getDrawable(R.drawable.easy));

        setLevel();

        level_one.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onResume(){
        super.onResume();
        setLevel();
    }

    @Override
    public void onClick(View view) {
        Intent i;
        switch (view.getId()){
            case R.id.menu_one:
                 i = new Intent(QuizMenu.this, QuizTrueFalse.class);
                startActivity(i);
                break;
            case R.id.menu_two:
                i = new Intent(QuizMenu.this, QuizDragNDrop.class);
                startActivity(i);
                break;
            case R.id.menu_three:
                i = new Intent(QuizMenu.this, QuizReading.class);
                startActivity(i);
                break;
            default:
                break;
        }
    }
}
