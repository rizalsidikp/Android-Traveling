package app.magis.meinreisen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class QuizResult extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView res_quiz = (TextView) findViewById(R.id.res_quiz);

        Intent i = getIntent();
        Boolean hasil = i.getBooleanExtra("hasilQuiz", true);
        if(hasil){
            res_quiz.setText("Berhasil");
        }else {
            res_quiz.setText("Gagal");
        }
    }
}
