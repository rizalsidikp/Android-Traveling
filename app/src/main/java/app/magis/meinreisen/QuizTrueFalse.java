package app.magis.meinreisen;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by rizalsidikp on 17/09/17.
 */

public class QuizTrueFalse extends AppCompatActivity implements View.OnClickListener {

    ImageView satu, dua, tiga;
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

    protected void setKata(){
        textTampil.setText(kata[index]);
    }

    protected void salah(){
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
        if(index == 4){
            Intent i = new Intent(QuizTrueFalse.this, QuizMultipleChoice.class);
            i.putExtra("kesempatan", kesempatan);
            startActivity(i);
            finish();
        }else{
            index++;
            setKata();
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

        option_false = (Button) findViewById(R.id.false_option);
        option_true = (Button) findViewById(R.id.true_option);

        option_false.setOnClickListener(this);
        option_true.setOnClickListener(this);
        setKata();

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
}
