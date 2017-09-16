package app.magis.meinreisen;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by rizalsidikp on 16/09/17.
 */

public class Learning_Kerja extends AppCompatActivity implements View.OnClickListener {

    MediaPlayer sound;
    int idx = 0;
    Button next, prev, play, speak;
    TextView textTampil;
    String[] kataKerja = {
            "fahren",
            "fliegen",
            "gehen",
            "rennen",
            "laufen",
            "kommen"
    };

    protected void setWord(){
        textTampil.setText(kataKerja[idx]);
    }

    protected void playSound(){
        if(sound != null){
            sound.stop();
        }
        switch (idx){
            case 0:
                sound = MediaPlayer.create(Learning_Kerja.this, R.raw.fahren);
                sound.start();
                break;
            case 1:
                sound = MediaPlayer.create(Learning_Kerja.this, R.raw.fliegen);
                sound.start();
                break;
            case 2:
                sound = MediaPlayer.create(Learning_Kerja.this, R.raw.gehen);
                sound.start();
                break;
            case 3:
                sound = MediaPlayer.create(Learning_Kerja.this, R.raw.rennen);
                sound.start();
                break;
            case 4:
                sound = MediaPlayer.create(Learning_Kerja.this, R.raw.laufen);
                sound.start();
                break;
            case 5:
                sound = MediaPlayer.create(Learning_Kerja.this, R.raw.kommen);
                sound.start();
                break;
            default:
                break;
        }
    }

    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning);
        textTampil = (TextView) findViewById(R.id.text_tampil);
        next = (Button) findViewById(R.id.next);
        prev = (Button) findViewById(R.id.prev);
        play = (Button) findViewById(R.id.play);
        setWord();

        next.setOnClickListener(this);
        prev.setOnClickListener(this);
        play.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.next:
                if(idx == 5){
                    idx = 0;
                }else{
                    idx++;
                }
                break;
            case R.id.prev:
                if(idx == 0){
                    idx = 5;
                }else {
                    idx--;
                }
                break;
            case R.id.play:
                playSound();
                break;
            default:
                break;
        }
    }
}
