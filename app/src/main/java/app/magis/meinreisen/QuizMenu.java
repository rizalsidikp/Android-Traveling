package app.magis.meinreisen;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;


public class QuizMenu extends AppCompatActivity implements View.OnClickListener {

    LinearLayout level_one, level_two, level_three;

    protected void setLevel(){
        Connector con = new Connector(this);
        con.open();
        Cursor rs = con.database.rawQuery("select * from levels where level = 2", null);
        if(rs.moveToFirst()){
            if(rs.getInt(rs.getColumnIndex("status")) == 1){
                level_two.setOnClickListener(this);
                level_two.setBackgroundColor(Color.parseColor("#00FFFF"));
            }else{
                level_two.setBackgroundColor(Color.LTGRAY);
            }
        }
        rs.close();
        rs = con.database.rawQuery("select * from levels where level = 3", null);
        if(rs.moveToFirst()){
            if(rs.getInt(rs.getColumnIndex("status")) == 1){
                level_three.setOnClickListener(this);
                level_three.setBackgroundColor(Color.parseColor("#FF00FF"));
            }else{
                level_three.setBackgroundColor(Color.LTGRAY);
            }
        }
        rs.close();
        con.close();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_vertical);
        level_one = (LinearLayout) findViewById(R.id.menu_one);
        level_two = (LinearLayout) findViewById(R.id.menu_two);
        level_three = (LinearLayout) findViewById(R.id.menu_three);

        setLevel();

        level_one.setOnClickListener(this);
    }

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
