package app.kingergarten.kingergarten.intint_setup;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Handler;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ProgressBar;

import app.kingergarten.kingergarten.R;
import app.kingergarten.kingergarten.utils.KindergartenBaseActivity;
import app.kingergarten.kingergarten.utils.KindergartenConstent;

public class Splach_screen extends KindergartenBaseActivity {
private ProgressBar progressBar;
private int progressStatus=0;
private Handler handler=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splach_screen);

        // code for progressBar
        progressBar= (ProgressBar) findViewById(R.id.progressBar);

        new Thread(new Runnable() {
            @Override
            public void run() {
                progressBar.getProgressDrawable().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
                while (progressStatus<100){
                    progressStatus++;
                    android.os.SystemClock.sleep(50);

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(progressStatus);
                        }
                    });
                }

            }
        }).start();


            //----------------------------------------

        getSupportActionBar().hide();

        //to hide notification bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //-----------------
        setContentView(R.layout.activity_splach_screen);

        // to start the language with english
//        if (getSharedPreferences(KindergartenConstent.LANG_FILE, MODE_PRIVATE).getString(KindergartenConstent.LANG, null) != null
//                && getSharedPreferences(KindergartenConstent.LANG_FILE, MODE_PRIVATE).getString(KindergartenConstent.LANG, null).
//                equalsIgnoreCase(KindergartenConstent.EN)) {
//            changeLang(KindergartenConstent.EN);
//        } else {
//            changeLang(KindergartenConstent.AR);
//        }
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent= new Intent(Splach_screen.this, FirstLogin.class);
                startActivity(intent);
                finish();
            }
        }, 5000);
    }
}
