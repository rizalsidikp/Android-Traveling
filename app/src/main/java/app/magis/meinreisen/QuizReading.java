package app.magis.meinreisen;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;



public class QuizReading extends AppCompatActivity implements View.OnClickListener {

    TextView story;
    Button submit;
    ImageView satu, dua, tiga;
    EditText esatu, edua;
    int kesempatan = 1, index = 0;

    String[][] answer = {
            {"reist","gehen"},
            {"fahre","Auto"},
            {"Fahrt","Zug"},
            {"geht","Bahnhof"},
            {"reisen","fliegen"}
    };

    String[] pStory = {
            "Thomas (...1...) von den Niederlanden nach Deutschland. Er fährt mit dem Zug. Er besucht Berlin. Er macht dort einen Spaziergang durch das Riegierungsviertel. Thomas will das Parlament besictigen und über einen Flohmarkt bummeln. Am Abend will er auch ins Theater (...2...).",
            "Ich (...1...) mit meinen Freunden nach Berlin. Wir fahren mit dem (...2...) zum Brandenburger Tor. Wir sind sehr aufgeregt. Denn es ist unsere erstes Mal in Berlin.",
            "Am 30 Januar machen wir eine (...1...) nach Rom. Wir fahren mit dem (...2...) um 11.30 Uhr ab. Wir sind um 15.00 Uhr am Hotel in Rom.",
            "Marlene (...1...) oft zum Hauptbahnhof. Denn sie arbeitet als Fahrkartenverkäuferin am (...2...). Sie arbeitet da seit 4 Jahren.",
            "Im Urlaub (...1...) meine Familie und ich nach Brunei. Wir (...2...) mit dem Fluzeug, Da besuchen wir meiner Oma. Wir reisen dann zusammen nach Malaysien und Indonesien."
    };

    MediaPlayer sound;

    protected void playSound(Boolean status){
        if(sound != null){
            sound.stop();
        }
        if(status){
            sound = MediaPlayer.create(QuizReading.this, R.raw.correct);
            sound.start();
        }else{
            sound = MediaPlayer.create(QuizReading.this, R.raw.incorrect);
            sound.start();
        }

    }

    public void setpStory() {
        story.setText(pStory[index]);
        esatu.setText("");
        edua.setText("");
        esatu.requestFocus();
    }

    protected void salah(){
        playSound(false);
        if (kesempatan == 1){
            satu.setVisibility(View.INVISIBLE);
        }else if (kesempatan == 2){
            dua.setVisibility(View.INVISIBLE);
        }
        else{
            tiga.setVisibility(View.INVISIBLE);
            Intent i = new Intent(QuizReading.this, QuizResult.class);
            i.putExtra("hasilQuiz", false);
            startActivity(i);
            finish();
        }
        kesempatan++;
    }

    protected void benar(){
        playSound(true);
        if(index == 4){
            Intent i = new Intent(QuizReading.this, QuizSpeaking.class);
            i.putExtra("kesempatan", kesempatan);
            startActivity(i);
            finish();
        }else{
            index++;
            setpStory();
        }
    }

    protected void cek_jawaban(){
        if(answer[index][0].equals(esatu.getText().toString()) &&
                answer[index][1].equals(edua.getText().toString())){
            benar();
        }else{
            salah();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading);

        story = (TextView) findViewById(R.id.story);
        submit = (Button) findViewById(R.id.submit);
        satu = (ImageView) findViewById(R.id.k_one);
        dua = (ImageView) findViewById(R.id.k_two);
        tiga = (ImageView) findViewById(R.id.k_three);
        esatu = (EditText) findViewById(R.id.answer_one);
        edua = (EditText) findViewById(R.id.answer_two);

        submit.setOnClickListener(this);


        setpStory();
    }

    @Override
    public void onClick(View view) {
        cek_jawaban();
    }

    public void onBackPressed() {
        Context c= this;
        AlertDialog.Builder alert = new AlertDialog.Builder(c);
        alert.setMessage("Quiz beenden? Wirklich?");
        alert.setCancelable(false);
        alert.setPositiveButton("Ja", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();

            }
        });

        alert.setNegativeButton("Nein", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();

            }
        });
        alert.show();
    }
}
