package app.magis.meinreisen;

import android.app.ActivityOptions;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class QuizResult extends AppCompatActivity {

    MediaPlayer sound;

    protected void playSound(Boolean status){
        if(sound != null){
            sound.stop();
        }
        if(status){
            sound = MediaPlayer.create(QuizResult.this, R.raw.success);
            sound.start();
        }else{
            sound = MediaPlayer.create(QuizResult.this, R.raw.fail);
            sound.start();
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        LinearLayout bg = (LinearLayout) findViewById(R.id.bg);
        ImageView gambar = (ImageView) findViewById(R.id.gambar);

        Intent i = getIntent();
        Boolean hasil = i.getBooleanExtra("hasilQuiz", true);
        if(hasil){
            bg.setBackgroundColor(getResources().getColor(R.color.success));
            gambar.setImageResource(R.drawable.success);
            int level = i.getIntExtra("level", 0);
            Connector con = new Connector(this);
            con.open_level(level);
            con.close();
        }else {
            bg.setBackgroundColor(getResources().getColor(R.color.fail));
            gambar.setImageResource(R.drawable.fail);
        }
        playSound(hasil);
        new Handler().postDelayed(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void run() {
                finish();
            }
        }, 3000);
    }
}
