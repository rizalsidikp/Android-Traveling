package app.magis.meinreisen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;


public class QuizMenu extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_vertical);
        LinearLayout level_one = (LinearLayout) findViewById(R.id.menu_one);
        LinearLayout level_two = (LinearLayout) findViewById(R.id.menu_two);
        LinearLayout level_three = (LinearLayout) findViewById(R.id.menu_three);
        level_one.setOnClickListener(this);
        level_two.setOnClickListener(this);
        level_three.setOnClickListener(this);
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
                i = new Intent(QuizMenu.this, LearningTempat.class);
                startActivity(i);
                break;
            default:
                break;
        }
    }
}
