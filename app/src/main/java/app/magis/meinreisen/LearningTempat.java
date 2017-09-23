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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;



public class LearningTempat extends AppCompatActivity implements View.OnClickListener {

    private final int REQ_CODE_SPEECH_INPUT = 100;
    MediaPlayer sound;
    int idx = 0;
    TextView textTampil;
    Boolean hasilnya = false;
    ImageView gambar, next, prev, play, speak;
    String[] kataKerja = {
            "der Urlaub",
            "das Land",
            "der Weg",
            "die Stadt",
            "die U-Bahn",
            "die Straße",
            "der Bahnhof"
    };

    protected void setWord(){
        textTampil.setText(kataKerja[idx]);
    }

    protected void setImage() {
        switch (idx){
            case 0:
                gambar.setImageResource(R.drawable.holiday);
                break;
            case 1:
                gambar.setImageResource(R.drawable.atasnya);
                break;
            case 2:
                gambar.setImageResource(R.drawable.atasnya);
                break;
            case 3:
                gambar.setImageResource(R.drawable.city);
                break;
            case 4:
                gambar.setImageResource(R.drawable.atasnya);
                break;
            case 5:
                gambar.setImageResource(R.drawable.atasnya);
                break;
            default:
                break;
        }
    }


    protected void playSound(){
        if(sound != null){
            sound.stop();
        }
        switch (idx){
            case 0:
                sound = MediaPlayer.create(LearningTempat.this, R.raw.der_urlaub);
                sound.start();
                break;
            case 1:
                sound = MediaPlayer.create(LearningTempat.this, R.raw.das_land);
                sound.start();
                break;
            case 2:
                sound = MediaPlayer.create(LearningTempat.this, R.raw.der_weg);
                sound.start();
                break;
            case 3:
                sound = MediaPlayer.create(LearningTempat.this, R.raw.die_stadt);
                sound.start();
                break;
            case 4:
                sound = MediaPlayer.create(LearningTempat.this, R.raw.die_u_bahn);
                sound.start();
                break;
            case 5:
                sound = MediaPlayer.create(LearningTempat.this, R.raw.die_strasse);
                sound.start();
                break;
            case 6:
                sound = MediaPlayer.create(LearningTempat.this, R.raw.der_bahnhof);
                sound.start();
                break;
            default:
                break;
        }
    }

    private void tampilDialog(){
        final Dialog dialog = new Dialog(LearningTempat.this);
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
        next = (ImageView) findViewById(R.id.next);
        prev = (ImageView) findViewById(R.id.prev);
        play = (ImageView) findViewById(R.id.play);
        speak = (ImageView) findViewById(R.id.speak);
        gambar = (ImageView) findViewById(R.id.img_view);
        setWord();
        setImage();

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
                setImage();
                break;
            case R.id.prev:
                if(idx == 0){
                    idx = 6;
                }else {
                    idx--;
                }
                setWord();
                setImage();
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
