package ebuinesscanvas.app.buildingenvironment.Fragments;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.jaredrummler.materialspinner.MaterialSpinner;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ebuinesscanvas.app.buildingenvironment.Model.Constant;
import ebuinesscanvas.app.buildingenvironment.Model.Item;
import ebuinesscanvas.app.buildingenvironment.R;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.content.Context.MODE_PRIVATE;

public class FragmentActivity extends Fragment implements MaterialSpinner.OnItemSelectedListener {

    @BindView(R.id.spnProjectName) MaterialSpinner mSpinnerProName;
    @BindView(R.id.spnProjectMileStone) MaterialSpinner mSpinnerProMileStone;
    @BindView(R.id.spnProjectActivities) MaterialSpinner mSpinnerProActivities;
    @BindView(R.id.spnProjectHold) MaterialSpinner mSpinnerProHold;
    @BindView(R.id.edtRemark) TextInputEditText mEditTextRemark;
    @BindView(R.id.tilUserName) TextInputLayout mInputLayoutRemark;
    @BindView(R.id.btnHours) Button mBtnHours;
    @BindView(R.id.btnDate) Button mBtnDate;

    private MaterialDialog materialDialog;
    private List<String> mProName;
    private Map<Integer,String> stringMap;
    private static final String TAG=FragmentActivity.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_activity,container,false);

        ButterKnife.bind(this,view);

        mSpinnerProName.setOnItemSelectedListener(this);
        mSpinnerProMileStone.setOnItemSelectedListener(this);

//        materialDialog=new MaterialDialog.Builder(getContext())
//                .title("Please wait")
//                .content("Logging in..")
//                .progress(true, 0)
//                .progressIndeterminateStyle(false)
//                .show();

        setData();

        return view;
    }

    private void setData() {
        mSpinnerProMileStone.setItems("Select Milestone", "Android Developer", "IOS", "Black Berry", "Linux", "MacOS", "Linux");
        mSpinnerProMileStone.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                Toast.makeText(getContext(), "You Selected " + item.toString(), Toast.LENGTH_LONG).show();
            }
        });

        mSpinnerProActivities.setItems("Select Activities", "ABC", "PQR", "XYZ");
        mSpinnerProActivities.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                Toast.makeText(getContext(), "You Selected " + item.toString(), Toast.LENGTH_LONG).show();
            }
        });

        mSpinnerProHold.setItems("Select Project Status", "WIP", "Cancel", "Pending","Complete");
        mSpinnerProHold.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                Toast.makeText(getContext(), "You Selected " + item.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setUpSpinnerData() {
        mSpinnerProName.setItems(mProName);
        mSpinnerProName.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                Toast.makeText(getContext(),"You Selected:-" + item.toString(), Toast.LENGTH_LONG).show();
                String name =item.toString();
                String hashID = stringMap.get(position);

                Log.i(TAG,"Value is:-"+name);
                Log.i(TAG,"Value is:-"+hashID);
            }
        });
    }

    @Override
    public void onResume() {
        fetchProjectName();
        super.onResume();
    }

    private void fetchProjectName() {
        SharedPreferences sharedPreferences=getActivity().getSharedPreferences(Constant.PREFERENCE_KEY,MODE_PRIVATE);
        String id=sharedPreferences.getString (Constant.USER_ID,"N/A");
        Log.i(FragmentActivity.class.getSimpleName(),"User ID:-"+id);

        projectData("1");
    }

    private void projectData(String id) {
            new ProjectAsyncTask().execute(id);
    }

    @OnClick({R.id.btnHours,R.id.btnDate})
    public void onClickHours(View view){

        switch (view.getId()){
            case R.id.btnHours:
                    getHoursDialog();
                break;

            case R.id.btnDate:
                    getDateDialog();
                break;
        }
    }


    private void getHoursDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Choose an Hour");
        // add a list
        final String[] animals = {"1 hr", "2 hr", "3 hr", "4 hr", "5 hr", "6 hr", "7 hr", "8 hr", "9 hr", "10 hr", "11 hr", "12 hr"};
        builder.setItems(animals, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:{
                        String mStrHours=animals[which];
                        mBtnHours.setText(mStrHours);
                    }
                    case 1: {
                        String mStrHours=animals[which];
                        mBtnHours.setText(mStrHours);
                    }
                    case 2: {
                        String mStrHours=animals[which];
                        mBtnHours.setText(mStrHours);
                    }
                    case 3: {
                        String mStrHours=animals[which];
                        mBtnHours.setText(mStrHours);
                    }
                    case 4: {
                        String mStrHours=animals[which];
                        mBtnHours.setText(mStrHours);
                    }
                    case 5: {
                        String mStrHours=animals[which];
                        mBtnHours.setText(mStrHours);
                    }
                    case 6: {
                        String mStrHours=animals[which];
                        mBtnHours.setText(mStrHours);
                    }
                    case 7: {
                        String mStrHours=animals[which];
                        mBtnHours.setText(mStrHours);
                    }
                    case 8: {
                        String mStrHours=animals[which];
                        mBtnHours.setText(mStrHours);
                    }
                    case 9: {
                        String mStrHours=animals[which];
                        mBtnHours.setText(mStrHours);
                    }
                    case 10: {
                        String mStrHours=animals[which];
                        mBtnHours.setText(mStrHours);
                    }
                    case 11: {
                        String mStrHours=animals[which];
                        mBtnHours.setText(mStrHours);
                    }
                }
            }
        });

// create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void getDateDialog() {
        FragmentManager fragment=getFragmentManager();
        if (fragment!=null) {
            DialogFragment dialogFragment=new DateDialogFragment();
            Bundle args = new Bundle();
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            args.putInt("year",year);
            args.putInt("month",month);
            args.putInt("day",day);
            dialogFragment.setArguments(args);
            ((DateDialogFragment) dialogFragment).setCallBack(onDate);
            dialogFragment.show (getFragmentManager(),"date");
        }
    }

    DatePickerDialog.OnDateSetListener onDate = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth) {
            mBtnDate.setCompoundDrawablesWithIntrinsicBounds(0,0, 0, 0);
            String mStrDate=String.valueOf(dayOfMonth)+"-"+String.valueOf(monthOfYear+1)+"-"+ String.valueOf(year);
            mBtnDate.setText(mStrDate);
        }
    };

    @Override
    public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {

        int ViewID=view.getId();

        switch (ViewID){

            case R.id.spnProjectName:
                Toast.makeText(getContext(),"You Selected " + item.toString(), Toast.LENGTH_LONG).show();

                String name =item.toString();
                String hashID = stringMap.get(position);

                Log.i(TAG,"Value is:-"+name);
                Log.i(TAG,"Value is:-"+hashID);
                break;

            case R.id.spnProjectMileStone:
                Toast.makeText(getContext(),"You Selected " + item.toString(), Toast.LENGTH_LONG).show();
                Log.i(TAG,"You Selected " + item.toString());
                break;
        }

    }

    class ProjectAsyncTask extends AsyncTask<String,Void,Boolean> {

        OkHttpClient client = new OkHttpClient();

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected Boolean doInBackground(String... strings) {

            String UserID = strings[0];

            Log.i(ProjectAsyncTask.class.getSimpleName(),"UserID:-"+UserID);

            RequestBody formBody = new FormBody.Builder()
                    .add(Constant.USER_ID,"1")
                    .build();

            Request.Builder builder = new Request.Builder();
            builder.url("http://www.ebusinesscanvas.com/mayuri/country_list.php");
            builder.post(formBody);
            Request request = builder.build();

            try {
                Response response = client.newCall(request).execute();

                if (response!=null) {
                    JSONArray jsonArray = new JSONArray(response.body().string());
                    int size = jsonArray.length();
                    Log.i(TAG,""+size);


                    String status = jsonArray.getJSONObject(0).getString("status");

                    if (status.equals(Constant.STATUS_SUCCESS)) {

                        String strCountry = jsonArray.getJSONObject(0).getString("country");
                        Log.i(TAG, "country_id:-" + strCountry);

                        JSONArray array = new JSONArray(strCountry);
                        mProName=new ArrayList<>();
                        stringMap=new HashMap<>();

                        for (int i = 0; i < array.length(); i++) {

                            String country_id = array.getJSONObject(i).getString("country_id");
                            String country_name = array.getJSONObject(i).getString("country_name");

                            mProName.add(country_name);
                            stringMap.put(i,country_id);

                        }
                        Log.i(TAG, "Array List Size:-" + mProName);
                        Log.i(TAG, "MAP Size:-" + stringMap.size());
                        setUpSpinnerData();


                    } else if (status.equals(Constant.STATUS_FAIL)) {
                        Log.i(TAG, "FAIL");
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }
    }
}
