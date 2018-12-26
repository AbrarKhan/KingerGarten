package app.kingergarten.kingergarten.utils;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import app.kingergarten.kingergarten.R;
import java.util.Locale;
public class KindergartenBaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kindergarten_base);

    }
        public void changeLang(String lang) {
            Locale myLocale = new Locale(lang);
            Locale.setDefault(myLocale);
            android.content.res.Configuration config = new android.content.res.Configuration();
            config.locale = myLocale;
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().
                    getResources().getDisplayMetrics());
            SharedPreferences sharedPreferences = getSharedPreferences(KindergartenConstent.LANG_FILE, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(KindergartenConstent.LANG, lang);
            editor.commit();

    }


}

