package app.magis.meinreisen;

import android.app.ActivityOptions;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by rizalsidikp on 22/09/17.
 */

public class QuizSpeaking extends AppCompatActivity implements View.OnClickListener {

    ImageView satu, dua, tiga;

    private final int REQ_CODE_SPEECH_INPUT = 100;

    Button speak;
    TextView result;
    int kesempatan = 1, index = 0;

    String[] kata = {
            "fliegen",
            "der Bahnhof",
            "die U-Bahn",
            "das Flugzeug",
            "die Straße"
    };

    private void inputSpeechDialog(){
        Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
//      dibawah ini adalah settingan untuk memanggil google speech input
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "de-DE");
        i.putExtra(RecognizerIntent.EXTRA_PROMPT, kata[index]);

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
                    if(hasil.get(0).equals(kata[index])){
                        benar();
                    }else{
                        salah();
                    }
                }
                break;
            }
        }
    }
    protected void salah(){
        result.setBackgroundColor(Color.parseColor("#FF0000"));
        if (kesempatan == 1){
            satu.setVisibility(View.INVISIBLE);
        }else if (kesempatan == 2){
            dua.setVisibility(View.INVISIBLE);
        }
        else{
            tiga.setVisibility(View.INVISIBLE);
            Intent i = new Intent(QuizSpeaking.this, QuizResult.class);
            i.putExtra("hasilQuiz", false);
            startActivity(i);
            finish();
        }
        kesempatan++;
    }
    protected void benar(){
        result.setBackgroundColor(Color.parseColor("#00FF00"));
        speak.setVisibility(View.INVISIBLE);
        new Handler().postDelayed(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void run() {
                if(index == 4){
                    Intent i = new Intent(QuizSpeaking.this, QuizMultipleChoice.class);
                    i.putExtra("kesempatan", kesempatan);
                    startActivity(i);
                    finish();
                }else{
                    index++;
                }
                speak.setVisibility(View.VISIBLE);
                result.setText("");
                result.setBackgroundColor(Color.parseColor("#DDDDDD"));
            }
        }, 2000);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speaking);

        satu = (ImageView) findViewById(R.id.k_one);
        dua = (ImageView) findViewById(R.id.k_two);
        tiga = (ImageView) findViewById(R.id.k_three);

        result = (TextView) findViewById(R.id.result);

        speak = (Button) findViewById(R.id.speak);

        Intent i = getIntent();
        kesempatan = i.getIntExtra("kesempatan", 1);
        if (kesempatan == 2){
            satu.setVisibility(View.INVISIBLE);
        }else if (kesempatan == 3){
            satu.setVisibility(View.INVISIBLE);
            dua.setVisibility(View.INVISIBLE);
        }

        speak.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        inputSpeechDialog();
    }
}
