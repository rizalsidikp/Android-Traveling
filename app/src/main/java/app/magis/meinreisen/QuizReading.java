package app.magis.meinreisen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by rizalsidikp on 18/09/17.
 */

public class QuizReading extends AppCompatActivity implements View.OnClickListener {

    TextView story;
    Button submit;
    ImageView satu, dua, tiga;
    EditText esatu, edua;
    int kesempatan = 1, index = 0;

    String[][] answer = {
            {"reist","gehen"},
            {"fahren","Auto"},
            {"Fahrt","Zug"},
            {"geht","Bahnhof"},
            {"reisen","fliegen"}
    };

    String[] pStory = {
            "Thomas (...1...) von den Niederlanden nach Deutschland. Er fährt mit dem Zug. Im Progamm besucht er Berlin. Er macht dort einen Spaziergang durch das Riegierungsviertel. Thomas will das Parlament besictigen, ϋber einen Flohmarkt bummeln. Am Abend will er auch ins Theater (...2...).",
            "Ich (...1...) mit meinen Freunden nach Berlin. Wir fahren mit dem (...2...) zum Brandenburger Tor. Wir sind sehr aufgeregt. Denn es ist unser erstes Mal in Berlin.",
            "Am 30.Februar machen wir eine (...1...) nach Rom. Wir fahren mit dem (...2...) um 11.30 Uhr ab. Wir sind um 15.00 Uhr am Hotel in Rom.",
            "Marlene (...1...) oft zum Hauptbahnhof. Denn sie arbeitet als Kartenverkäuferin am (...2...). Sie arbeitet da seit 4 Jahren.",
            "5. Im Urlaub (...1...) meine Familie und ich nach Brunei. Wir (...2...) mit dem Fluzeug, Da besuchen wir meiner Oma. Wir reisen dann zusammen nach Malaysia und Indonesien."
    };

    public void setpStory() {
        story.setText(pStory[index]);
        esatu.setText("");
        edua.setText("");
        esatu.requestFocus();
    }

    protected void salah(){
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
}
