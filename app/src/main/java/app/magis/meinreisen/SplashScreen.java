package app.magis.meinreisen;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


public class SplashScreen extends AppCompatActivity {

    Animation planeLanding, zoomBandara, fadePutih;
    ImageView pesawat;
    RelativeLayout bandara;
    LinearLayout putih;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);

        pesawat = (ImageView) findViewById(R.id.pesawat);
        bandara = (RelativeLayout) findViewById(R.id.bandara);
        putih = (LinearLayout) findViewById(R.id.putih);
        planeLanding = AnimationUtils.loadAnimation(SplashScreen.this, R.anim.landing);
        zoomBandara = AnimationUtils.loadAnimation(SplashScreen.this, R.anim.zoom);
        fadePutih = AnimationUtils.loadAnimation(SplashScreen.this, R.anim.fade_in);
        pesawat.startAnimation(planeLanding);
        bandara.startAnimation(zoomBandara);
        putih.startAnimation(fadePutih);

        new Handler().postDelayed(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this, Home.class);
                ActivityOptions options =
                        ActivityOptions.makeCustomAnimation(SplashScreen.this, 0, 0);
                startActivity(i, options.toBundle());
            }
        }, 2000);

    }
}
