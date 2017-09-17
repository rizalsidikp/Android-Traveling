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

public class LearningBenda extends AppCompatActivity implements View.OnClickListener {

    private final int REQ_CODE_SPEECH_INPUT = 100;
    MediaPlayer sound;
    int idx = 0;
    Button next, prev, play, speak;
    TextView textTampil, result;
    Boolean hasilnya = false;
    String[] kataKerja = {
            "das Motorrad",
            "das Schiff",
            "der Fahrrad",
            "der Zug",
            "das Flugzeug",
            "das Auto",
            "der Bus"
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
                sound = MediaPlayer.create(LearningBenda.this, R.raw.das_motorrad);
                sound.start();
                break;
            case 1:
                sound = MediaPlayer.create(LearningBenda.this, R.raw.das_schiff);
                sound.start();
                break;
            case 2:
                sound = MediaPlayer.create(LearningBenda.this, R.raw.der_fahrrad);
                sound.start();
                break;
            case 3:
                sound = MediaPlayer.create(LearningBenda.this, R.raw.der_zug);
                sound.start();
                break;
            case 4:
                sound = MediaPlayer.create(LearningBenda.this, R.raw.das_flugzeug);
                sound.start();
                break;
            case 5:
                sound = MediaPlayer.create(LearningBenda.this, R.raw.das_auto);
                sound.start();
                break;
            case 6:
                sound = MediaPlayer.create(LearningBenda.this, R.raw.der_bus);
                sound.start();
                break;
            default:
                break;
        }
    }

    private void tampilDialog(){
        final Dialog dialog = new Dialog(LearningBenda.this);
        dialog.setContentView(R.layout.dialog);
        TextView tHasilnya = (TextView) dialog.findViewById(R.id.hasilnya);
        tHasilnya.setText(String.valueOf(hasilnya));
        dialog.show();
    }

    private void inputSpeechDialog(){
        Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
//      dibawah ini adalah settingan untuk memanggil google speech input
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "de-DE");
        i.putExtra(RecognizerIntent.EXTRA_PROMPT, kataKerja[idx]);

//      ini adalah perintah untuk mengeksekusi intent dan memunculkan dialog speech input google
        try {
            startActivityForResult(i, REQ_CODE_SPEECH_INPUT);
        }catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(), getString(R.string.tidaksupport), Toast.LENGTH_SHORT).show();
        }
    }


    //  ini function ketika menerima hasil dari dialog google speech input
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if(resultCode == RESULT_OK && null != data){ //kalau resultCode nya sama dengan OK
//                  masukkan hasil suara kedalam array (biar bisa lebih dari 1 kata)
                    ArrayList<String> hasil = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
//                  masukkan hasil array ke text
                    result.setText(hasil.get(0));
                    if(hasil.get(0).equals(kataKerja[idx])){
                        hasilnya = true;
                    }else{
                        hasilnya = false;
                    }
                    tampilDialog();
                }
                break;
            }
        }
    }


    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning);
        textTampil = (TextView) findViewById(R.id.text_tampil);
        result = (TextView) findViewById(R.id.result);
        next = (Button) findViewById(R.id.next);
        prev = (Button) findViewById(R.id.prev);
        play = (Button) findViewById(R.id.play);
        speak = (Button) findViewById(R.id.speak);
        setWord();

        next.setOnClickListener(this);
        prev.setOnClickListener(this);
        play.setOnClickListener(this);
        speak.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.next:
                if(idx == 6){
                    idx = 0;
                }else{
                    idx++;
                }
                setWord();
                break;
            case R.id.prev:
                if(idx == 0){
                    idx = 6;
                }else {
                    idx--;
                }
                setWord();
                break;
            case R.id.play:
                playSound();
                break;
            case R.id.speak:
                inputSpeechDialog();
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
