package ebuinesscanvas.app.buildingenvironment.Activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ebuinesscanvas.app.buildingenvironment.R;


public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed (new Runnable () {
            @Override
            public void run () {
//                checkSharedPreference ();
                Intent intent=new Intent(SplashScreenActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },2000);
    }

//    private void checkSharedPreference () {
//        SharedPreferences sharedPreferences=getSharedPreferences (Constants.PREFERENCE_KEY,MODE_PRIVATE);
//        Boolean aBoolean=sharedPreferences.contains (Constants.EMP_ID);
//
//        if (!aBoolean){
//            Intent intent=new Intent (this,ActivityLogin.class);
//            startActivity (intent);
//            finish ();
//        }else {
//            Intent intent=new Intent(this,ActivityContainer.class);
//            startActivity (intent);
//            finish ();
//        }
//    }

}
