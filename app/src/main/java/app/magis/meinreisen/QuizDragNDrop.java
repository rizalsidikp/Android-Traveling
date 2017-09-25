package app.magis.meinreisen;

import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class QuizDragNDrop extends AppCompatActivity implements View.OnClickListener {
    TextView answerOne, answerTwo, chooseOne, chooseTwo, chooseThree, chooseFour, result;
    Button submit;
    ImageView satu, dua, tiga, gambar;
    int kesempatan = 1, index = 0;

    String[][] answer = {
            {"das","Schiff"},
            {"der","Zug"},
            {"die","Straße"},
            {"die","Stadt"},
            {"der","Bahnhof"}
    };

    String[][] pilihan = {
            {"die","das","der","Schiff"},
            {"die","das","der","Zug"},
            {"die","Straße","Weg","Stadt"},
            {"die","Straße","Schiff","Stadt"},
            {"das","der","Bahnhof","U-Bahn"}
    };

    MediaPlayer sound;

    protected void playSound(Boolean status){
        if(sound != null){
            sound.stop();
        }
        if(status){
            sound = MediaPlayer.create(QuizDragNDrop.this, R.raw.correct);
            sound.start();
        }else{
            sound = MediaPlayer.create(QuizDragNDrop.this, R.raw.incorrect);
            sound.start();
        }

    }

    protected void setChoice(){
        answerOne.setText("-");
        answerTwo.setText("-");
        chooseOne.setText(pilihan[index][0]);
        chooseTwo.setText(pilihan[index][1]);
        chooseThree.setText(pilihan[index][2]);
        chooseFour.setText(pilihan[index][3]);
    }

    protected void setImage() {
        switch (index){
            case 0:
                gambar.setImageResource(R.drawable.boat);
                break;
            case 1:
                gambar.setImageResource(R.drawable.train);
                break;
            case 2:
                gambar.setImageResource(R.drawable.street);
                break;
            case 3:
                gambar.setImageResource(R.drawable.city);
                break;
            case 4:
                gambar.setImageResource(R.drawable.train_station);
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
            Intent i = new Intent(QuizDragNDrop.this, QuizResult.class);
            i.putExtra("hasilQuiz", false);
            startActivity(i);
            finish();
        }
        kesempatan++;
    }

    protected void benar(){
        playSound(true);
        if(index == 4){
            Intent i = new Intent(QuizDragNDrop.this, QuizListening.class);
            i.putExtra("kesempatan", kesempatan);
            startActivity(i);
            finish();
        }else{
            index++;
            setChoice();
            setImage();
        }
    }

    protected void cek_jawaban(){
        if(answer[index][0].equals(answerOne.getText().toString())
                && answer[index][1].equals(answerTwo.getText().toString())){
            benar();
        }else{
            salah();
        }

    }

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dragndrop);

        answerOne = (TextView) findViewById(R.id.word_one);
        answerTwo = (TextView) findViewById(R.id.word_two);
        chooseOne = (TextView) findViewById(R.id.choose_one);
        chooseTwo = (TextView) findViewById(R.id.choose_two);
        chooseThree = (TextView) findViewById(R.id.choose_three);
        chooseFour = (TextView) findViewById(R.id.choose_four);
        result = (TextView) findViewById(R.id.result);
        submit = (Button) findViewById(R.id.submit);
        gambar = (ImageView) findViewById(R.id.img_q);


        answerOne.setOnTouchListener(new WordDragListener());
        answerTwo.setOnTouchListener(new WordDragListener());
        chooseOne.setOnTouchListener(new WordDragListener());
        chooseTwo.setOnTouchListener(new WordDragListener());
        chooseThree.setOnTouchListener(new WordDragListener());
        chooseFour.setOnTouchListener(new WordDragListener());

        answerOne.setOnDragListener(new WordDropListener());
        answerTwo.setOnDragListener(new WordDropListener());
        chooseOne.setOnDragListener(new WordDropListener());
        chooseTwo.setOnDragListener(new WordDropListener());
        chooseThree.setOnDragListener(new WordDropListener());
        chooseFour.setOnDragListener(new WordDropListener());

        satu = (ImageView) findViewById(R.id.k_one);
        dua = (ImageView) findViewById(R.id.k_two);
        tiga = (ImageView) findViewById(R.id.k_three);

        submit.setOnClickListener(this);
        setChoice();
        setImage();
    }

    @Override
    public void onClick(View view) {
        cek_jawaban();
    }

    private class WordDragListener implements View.OnTouchListener {

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent){
            if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                return true;
            }else{
                return false;
            }
        }
    }

    private class WordDropListener implements View.OnDragListener {

        @Override
        public boolean onDrag(View v, DragEvent event){
            View view = (View) event.getLocalState();
            TextView Target = (TextView) v;
            switch (event.getAction()){
                case DragEvent.ACTION_DRAG_STARTED:
                    view.setVisibility(View.INVISIBLE);
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
                    TextView dropped = (TextView) view;
                    String temp = dropped.getText().toString();
                    dropped.setText(Target.getText().toString());
                    Target.setText(temp);
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    view.setVisibility(View.VISIBLE);
                    break;
                default:
                    break;
            }
            return true;
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
