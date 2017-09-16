package app.magis.meinreisen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by rizalsidikp on 16/09/17.
 */

public class LearningMenu extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_menu);
        LinearLayout kerja = (LinearLayout) findViewById(R.id.kerja);
        kerja.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.kerja:
                Intent i = new Intent(LearningMenu.this, LearningKerja.class);
                startActivity(i);
                break;
            default:
                break;
        }
    }
}
