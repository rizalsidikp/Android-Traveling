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
import android.widget.ImageView;

public class QuizMultipleChoice extends AppCompatActivity implements View.OnClickListener {

    int index = 0, kesempatan = 1;
    ImageView satu, dua, tiga, gambar;
    Button a,b,c,d;

    String[] answer = {
            "das Fahrrad",
            "fahren",
            "die Stadt",
            "kommen",
            "die U-Bahn"
    };

    String[][] choice = {
            {"das Motorrad","die Straße","das Fahrrad","das Auto"},
            {"fahren","das Auto","der Bus","der Weg"},
            {"das Land","die Straße","der Urlaub","die Stadt"},
            {"gehen","kommen","rennen","laufen"},
            {"die U-Bahn","der Zug","der Bahnhof","das Flugzeug"}
    };

    MediaPlayer sound;

    protected void playSound(Boolean status){
        if(sound != null){
            sound.stop();
        }
        if(status){
            sound = MediaPlayer.create(QuizMultipleChoice.this, R.raw.correct);
            sound.start();
        }else{
            sound = MediaPlayer.create(QuizMultipleChoice.this, R.raw.incorrect);
            sound.start();
        }

    }

    protected void setChoice(){
        a.setText(choice[index][0]);
        b.setText(choice[index][1]);
        c.setText(choice[index][2]);
        d.setText(choice[index][3]);
    }

    protected void setImage() {
        switch (index){
            case 0:
                gambar.setImageResource(R.drawable.bicycle);
                break;
            case 1:
                gambar.setImageResource(R.drawable.todrive);
                break;
            case 2:
                gambar.setImageResource(R.drawable.city);
                break;
            case 3:
                gambar.setImageResource(R.drawable.atasnya);
                break;
            case 4:
                gambar.setImageResource(R.drawable.atasnya);
                break;
            default:
                break;
        }
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
            Intent i = new Intent(QuizMultipleChoice.this, QuizResult.class);
            i.putExtra("hasilQuiz", false);
            startActivity(i);
            finish();
        }
        kesempatan++;
    }

    protected void benar(){
        playSound(true);
        if(index == 4){
            Intent i = new Intent(QuizMultipleChoice.this, QuizResult.class);
            i.putExtra("hasilQuiz", true);
            i.putExtra("level", 2);
            startActivity(i);
            finish();
        }else{
            index++;
            setChoice();
            setImage();
        }
    }

    protected void cek_jawaban(int res){
        if(answer[index] == choice[index][res]){
            benar();
        }else{
            salah();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multichoice);

        satu = (ImageView) findViewById(R.id.k_one);
        dua = (ImageView) findViewById(R.id.k_two);
        tiga = (ImageView) findViewById(R.id.k_three);

        a = (Button) findViewById(R.id.a);
        b = (Button) findViewById(R.id.b);
        c = (Button) findViewById(R.id.c);
        d = (Button) findViewById(R.id.d);

        a.setOnClickListener(this);
        b.setOnClickListener(this);
        c.setOnClickListener(this);
        d.setOnClickListener(this);

        gambar = (ImageView) findViewById(R.id.img_q);

        Intent i = getIntent();
        kesempatan = i.getIntExtra("kesempatan", 1);
        if (kesempatan == 2){
            satu.setVisibility(View.INVISIBLE);
        }else if (kesempatan == 3){
            satu.setVisibility(View.INVISIBLE);
            dua.setVisibility(View.INVISIBLE);
        }

        setChoice();
        setImage();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.a:
                cek_jawaban(0);
                break;
            case R.id.b:
                cek_jawaban(1);
                break;
            case R.id.c:
                cek_jawaban(2);
                break;
            case R.id.d:
                cek_jawaban(3);
                break;
            default:
                break;
        }

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
