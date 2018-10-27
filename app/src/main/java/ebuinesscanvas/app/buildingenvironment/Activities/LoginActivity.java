package ebuinesscanvas.app.buildingenvironment.Activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.afollestad.materialdialogs.MaterialDialog;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ebuinesscanvas.app.buildingenvironment.Model.Constant;
import ebuinesscanvas.app.buildingenvironment.R;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity implements DialogInterface.OnCancelListener {

     @BindView(R.id.txtInputUserName) TextInputLayout mTextInputUserName;
     @BindView(R.id.txtInputPassword) TextInputLayout mTextInputUserPassword;
     @BindView(R.id.txtInputEdtUserName) TextInputEditText mTextInputEdtUserName;
     @BindView(R.id.txtInputEdtPassword) TextInputEditText mTextInputEdtUserPassword;
     @BindView(R.id.btnLogin) Button mBtnLogin;

     private MaterialDialog materialDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        mTextInputEdtUserPassword.setTransformationMethod(new PasswordTransformationMethod());
    }

    @OnClick(R.id.btnLogin)
    public void onLogin(View view){

        String StrUserName=mTextInputEdtUserName.getText().toString();
        String StrUserPassword=mTextInputEdtUserPassword.getText().toString();

        if (StrUserName.isEmpty()){
            mTextInputUserName.setErrorEnabled(true);
            mTextInputUserName.setError("Please provide valid username");
        }else if (StrUserPassword.isEmpty()){
            mTextInputUserPassword.setErrorEnabled(true);
            mTextInputUserPassword.setError("Please provide valid password");
        }else {
            materialDialog=new MaterialDialog.Builder(this)
                    .title("Please wait")
                    .content("Logging in..")
                    .progress(true, 0)
                    .progressIndeterminateStyle(false)
                    .show();

            new UserLoginAsync().execute(StrUserName,StrUserPassword);
        }
    }

    @Override
    public void onCancel(DialogInterface dialogInterface) {
        dialogInterface.dismiss();
    }

        class UserLoginAsync extends AsyncTask<String,Void,Boolean> {

            OkHttpClient client = new OkHttpClient();

            @Override
            protected Boolean doInBackground(String... strings) {

                String UserName = strings[0];
                String UserPassword = strings[1];

                Log.i(UserLoginAsync.class.getSimpleName(), "Username:-"+UserName);
                Log.i(UserLoginAsync.class.getSimpleName(), "Response:-"+UserPassword);

                RequestBody formBody = new FormBody.Builder()
                        .add(Constant.USER_NAME,UserName)
                        .add(Constant.USER_PASSWORD,UserPassword)
                        .build();

                Request.Builder builder = new Request.Builder();
                builder.url(Constant.URL_USER_LOGIN);
                builder.post(formBody);
                Request request = builder.build();

                try {
                    Response response = client.newCall(request).execute();

                    if (response.isSuccessful()){

                        JSONObject jsonObject=new JSONObject(response.body().string());

                        String RESPONSE_STATUS=jsonObject.getString(Constant.RESPONSE_STATUS);

                        Log.i(LoginActivity.class.getSimpleName(),"Response:-"+RESPONSE_STATUS);

                        if (RESPONSE_STATUS.equals(Constant.STATUS_SUCCESS)){
                            String StrUserID=jsonObject.getString(Constant.USER_ID);
                            String StrUserName=jsonObject.getString(Constant.USER_NAME);

                            setSharedPreference(StrUserID,StrUserName);

                            Intent intent=new Intent(getApplicationContext(),HomeActivity.class);
                            startActivity(intent);
                            finish();
                        }

                    } else {
                        Log.i(LoginActivity.class.getSimpleName(),"Response Fail");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            }
        }

    private void setSharedPreference (String userID,String userName){
        Context context=getApplicationContext ();
        SharedPreferences sharedPreferences=context.getSharedPreferences (Constant.PREFERENCE_KEY,MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit ();
        editor.putString (Constant.USER_ID,userID);
        editor.putString (Constant.USER_NAME,userName);
        editor.apply ();
    }
}


