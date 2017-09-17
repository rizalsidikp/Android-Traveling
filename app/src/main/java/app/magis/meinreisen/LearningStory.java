package app.magis.meinreisen;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by rizalsidikp on 16/09/17.
 */

public class LearningStory extends AppCompatActivity implements View.OnClickListener {

    MediaPlayer sound;
    int idx = 0;
    Button next, prev, play;
    TextView storyTampil, storyKe;
    String[] story_ke = {
            "Story 1",
            "Story 2",
            "Story 3",
            "Story 4",
            "Story 5"
    };

    String[] story_tampil = {
            "Thomas reist von den Niederlanden nach Deutschland. Er fährt mit dem Zug. Im Progamm besucht er Berlin. Er macht dort einen Spaziergang durch das Riegierungsviertel. Thomas will das Parlament besictigen, ϋber einen Flohmarkt bummeln. Am Abend will er auch ins Theater gehen.",
            "Ich fahren mit meinen Freunden nach Berlin. Wir fahren mit dem Auto zum Brandenburger Tor. Wir sind sehr aufgeregt. Denn es ist unser erstes Mal in Berlin.",
            "Am 30.Februar machen wir eine Fahrt nach Rom. Wir fahren mit dem Zug um 11.30 Uhr ab. Wir sind um 15.00 Uhr am Hotel in Rom.",
            "Marlene geht oft zum Hauptbahnhof. Denn sie arbeitet als Kartenverkäuferin am Bahnhof. Sie arbeitet da seit 4 Jahren.",
            "Im Urlaub reisen meine Familie und ich nach Brunei. Wir fliegen mit dem Fluzeug, Da besuchen wir meiner Oma. Wir reisen dann zusammen nach Malaysia und Indonesien."
    };

    protected void setStroyKe(){
        storyKe.setText(story_ke[idx]);
    }
    protected void setStroyTampil(){
        storyTampil.setText(story_tampil[idx]);
    }

    protected void playSound(){
        if(sound != null){
            sound.stop();
        }
        switch (idx){
            case 0:
                sound = MediaPlayer.create(LearningStory.this, R.raw.story_one);
                sound.start();
                break;
            case 1:
                sound = MediaPlayer.create(LearningStory.this, R.raw.story_two);
                sound.start();
                break;
            case 2:
                sound = MediaPlayer.create(LearningStory.this, R.raw.story_three);
                sound.start();
                break;
            case 3:
                sound = MediaPlayer.create(LearningStory.this, R.raw.story_four);
                sound.start();
                break;
            case 4:
                sound = MediaPlayer.create(LearningStory.this, R.raw.story_five);
                sound.start();
                break;
            default:
                break;
        }
    }


    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);
        storyKe = (TextView) findViewById(R.id.story_ke);
        storyTampil = (TextView) findViewById(R.id.story_tampil);
        next = (Button) findViewById(R.id.next);
        prev = (Button) findViewById(R.id.prev);
        play = (Button) findViewById(R.id.play);
        setStroyKe();
        setStroyTampil();

        next.setOnClickListener(this);
        prev.setOnClickListener(this);
        play.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.next:
                if(idx == 4){
                    idx = 0;
                }else{
                    idx++;
                }
                if(sound != null){
                    sound.stop();
                }
                setStroyKe();
                setStroyTampil();
                break;
            case R.id.prev:
                if(idx == 0){
                    idx = 4;
                }else {
                    idx--;
                }
                if(sound != null){
                    sound.stop();
                }
                setStroyKe();
                setStroyTampil();
                break;
            case R.id.play:
                playSound();
                break;
            default:
                break;
        }
    }
    public void onBackPressed() {
        if(sound != null){
            sound.stop();
        }
        finish();
    }
}
