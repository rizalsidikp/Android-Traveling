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
import android.widget.TextView;

public class QuizTrueFalse extends AppCompatActivity implements View.OnClickListener {

    ImageView satu, dua, tiga, gambar;
    TextView textTampil;

    Button option_true, option_false;
    int kesempatan = 1, index = 0;

    Boolean[] benerSalah = {
        true, false, true, true, false
    };

    String[] kata = {
            "das Motorrad",
            "rennen",
            "der Urlaub",
            "die U-Bahn",
            "das Flugzeug"
    };

    MediaPlayer sound;

    protected void playSound(Boolean status){
        if(sound != null){
            sound.stop();
        }
        if(status){
            sound = MediaPlayer.create(QuizTrueFalse.this, R.raw.correct);
            sound.start();
        }else{
            sound = MediaPlayer.create(QuizTrueFalse.this, R.raw.incorrect);
            sound.start();
        }

    }

    protected void setKata(){
        textTampil.setText(kata[index]);
    }

    protected void setImage() {
        switch (index){
            case 0:
                gambar.setImageResource(R.drawable.motorcycle);
                break;
            case 1:
                gambar.setImageResource(R.drawable.towalk);
                break;
            case 2:
                gambar.setImageResource(R.drawable.holiday);
                break;
            case 3:
                gambar.setImageResource(R.drawable.underground_train);
                break;
            case 4:
                gambar.setImageResource(R.drawable.tofly);
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
            Intent i = new Intent(QuizTrueFalse.this, QuizResult.class);
            i.putExtra("hasilQuiz", false);
            startActivity(i);
            finish();
        }
        kesempatan++;
    }
    protected void benar(){
        playSound(true);
        if(index == 4){
            Intent i = new Intent(QuizTrueFalse.this, QuizMultipleChoice.class);
            i.putExtra("kesempatan", kesempatan);
            startActivity(i);
            finish();
        }else{
            index++;
            setKata();
            setImage();
        }
    }

    protected void cek_jawaban(Boolean res){
        if(res == benerSalah[index]){
            benar();
        }else{
            salah();
        }

    }

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_truefalse);

        satu = (ImageView) findViewById(R.id.k_one);
        dua = (ImageView) findViewById(R.id.k_two);
        tiga = (ImageView) findViewById(R.id.k_three);
        textTampil = (TextView) findViewById(R.id.text_tampil);

        gambar = (ImageView) findViewById(R.id.img_q);

        option_false = (Button) findViewById(R.id.false_option);
        option_true = (Button) findViewById(R.id.true_option);

        option_false.setOnClickListener(this);
        option_true.setOnClickListener(this);
        setKata();
        setImage();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.true_option:
                cek_jawaban(true);
                break;
            case R.id.false_option:
                cek_jawaban(false);
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
        final AlertDialog a = alert.create();
        a.setOnShowListener(new DialogInterface.OnShowListener() {

            @Override
            public void onShow(DialogInterface dialog) {
                Button posButton = a.getButton(DialogInterface.BUTTON_POSITIVE);
                Button negButton = a.getButton(DialogInterface.BUTTON_NEGATIVE);
                posButton.setTextColor(getResources().getColor(R.color.success));
                negButton.setTextColor(getResources().getColor(R.color.fail));
            }
        });
        a.show();
    }

}
