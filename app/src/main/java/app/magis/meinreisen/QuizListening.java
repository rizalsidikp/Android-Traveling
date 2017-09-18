package app.magis.meinreisen;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class QuizListening extends AppCompatActivity implements View.OnClickListener {

    int index = 0, kesempatan = 1, listen = 4;
    ImageView satu, dua, tiga, ksatu, kdua, ktiga;
    Button submit, bListen;
    EditText eAnswer;
    MediaPlayer sound;

    String[] answer = {
            "fliegen",
            "laufen",
            "der Urlaub",
            "das Fahrrad",
            "das Flugzeug"
    };

    protected void startListen(){
        if(sound != null){
            sound.stop();
        }
        switch (index){
            case 0:
                sound = MediaPlayer.create(QuizListening.this, R.raw.fliegen);
                sound.start();
                break;
            case 1:
                sound = MediaPlayer.create(QuizListening.this, R.raw.laufen);
                sound.start();
                break;
            case 2:
                sound = MediaPlayer.create(QuizListening.this, R.raw.der_urlaub);
                sound.start();
                break;
            case 3:
                sound = MediaPlayer.create(QuizListening.this, R.raw.der_fahrrad);
                sound.start();
                break;
            case 4:
                sound = MediaPlayer.create(QuizListening.this, R.raw.das_flugzeug);
                sound.start();
                break;
            default:
                break;
        }
    }

    protected void setListen() {
        listen--;
        if(listen >= 3){
            ksatu.setVisibility(View.VISIBLE);
        }else{
            ksatu.setVisibility(View.INVISIBLE);
        }
        if(listen >= 2){
            kdua.setVisibility(View.VISIBLE);
        }else{
            kdua.setVisibility(View.INVISIBLE);
        }
        if(listen >= 1){
            ktiga.setVisibility(View.VISIBLE);
        }else{
            ktiga.setVisibility(View.INVISIBLE);
        }
        if(listen == 0){
            bListen.setVisibility(View.INVISIBLE);
        }else{
            bListen.setVisibility(View.VISIBLE);
        }
    }

    protected void salah(){
        if (kesempatan == 1){
            satu.setVisibility(View.INVISIBLE);
        }else if (kesempatan == 2){
            dua.setVisibility(View.INVISIBLE);
        }
        else{
            tiga.setVisibility(View.INVISIBLE);
            Intent i = new Intent(QuizListening.this, QuizResult.class);
            i.putExtra("hasilQuiz", false);
            startActivity(i);
            finish();
        }
        kesempatan++;
    }

    protected void benar(){
        eAnswer.setText("");
        if(index == 4){
            Intent i = new Intent(QuizListening.this, QuizResult.class);
            i.putExtra("hasilQuiz", true);
            startActivity(i);
            finish();
        }else{
            index++;
            listen = 4;
            setListen();
        }
    }

    protected void cek_jawaban(){
        if(answer[index].equals(eAnswer.getText().toString())){
            benar();
        }else {
            salah();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listening);

        satu = (ImageView) findViewById(R.id.k_one);
        dua = (ImageView) findViewById(R.id.k_two);
        tiga = (ImageView) findViewById(R.id.k_three);
        ksatu = (ImageView) findViewById(R.id.ksatu);
        kdua = (ImageView) findViewById(R.id.kdua);
        ktiga = (ImageView) findViewById(R.id.ktiga);

        submit = (Button) findViewById(R.id.submit);
        bListen = (Button) findViewById(R.id.listen);

        eAnswer = (EditText) findViewById(R.id.answer);

        submit.setOnClickListener(this);
        bListen.setOnClickListener(this);

        Intent i = getIntent();
        kesempatan = i.getIntExtra("kesempatan", 1);
        if (kesempatan == 2){
            satu.setVisibility(View.INVISIBLE);
        }else if (kesempatan == 3){
            satu.setVisibility(View.INVISIBLE);
            dua.setVisibility(View.INVISIBLE);
        }

        setListen();


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.submit:
                cek_jawaban();
                break;
            case R.id.listen:
                setListen();
                startListen();
            default:
                break;
        }

    }
}
