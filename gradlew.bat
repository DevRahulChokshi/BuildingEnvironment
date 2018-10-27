package firebasedemo.ebc_003.buildingenvironment.Fragments;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import firebasedemo.ebc_003.buildingenvironment.R;

public class FragmentActivity extends Fragment {

    @BindView(R.id.spnProjectName) MaterialSpinner mSpinnerProName;
    @BindView(R.id.spnProjectMileStone) MaterialSpinner mSpinnerProMileStone;
    @BindView(R.id.spnProjectActivities) MaterialSpinner mSpinnerProActivities;
    @BindView(R.id.spnProjectHold) MaterialSpinner mSpinnerProHold;
    @BindView(R.id.edtRemark) TextInputEditText mEditTextRemark;
    @BindView(R.id.tilUserName) TextInputLayout mInputLayoutRemark;
    @BindView(R.id.btnHours) Button mBtnHours;
    @BindView(R.id.btnDate) Button mBtnDate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_activity,container,false);

        ButterKnife.bind(this,view);

        setUpSpinnerData();

        return view;
    }

    private void setUpSpinnerData() {
        mSpinnerProName.setItems("Select Project","Tripolarcon","RR Parkon","Dr Bandisthi","CRM","Invoice","Event Management");
        mSpinnerProName.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view