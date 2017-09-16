package app.magis.meinreisen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Home extends AppCompatActivity implements View.OnClickListener {

    LinearLayout learning, story;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        learning = (LinearLayout) findViewById(R.id.learning);
        story = (LinearLayout) findViewById(R.id.story);
        learning.setOnClickListener(this);
        story.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent i;
        switch (view.getId()){
            case R.id.learning:
                i = new Intent(Home.this, LearningMenu.class);
                startActivity(i);
                break;
            case R.id.story:
                i = new Intent(Home.this, LearningStory.class);
                startActivity(i);
                break;
            default:
                break;
        }
    }
}
