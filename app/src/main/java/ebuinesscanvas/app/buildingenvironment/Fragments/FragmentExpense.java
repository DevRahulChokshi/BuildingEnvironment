package ebuinesscanvas.app.buildingenvironment.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jaredrummler.materialspinner.MaterialSpinner;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ebuinesscanvas.app.buildingenvironment.Activities.ExpenseListActivity;
import ebuinesscanvas.app.buildingenvironment.R;


public class FragmentExpense extends Fragment {

    @BindView(R.id.spnProName) MaterialSpinner spinnerProName;
    @BindView(R.id.spnProMilestone) MaterialSpinner spinnerProMilestone;
    @BindView(R.id.spnProActivities) MaterialSpinner spinnerProActivity;
    @BindView(R.id.fabExpense) FloatingActionButton actionButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_expense,container,false);

        ButterKnife.bind(this,view);

        setUpSpinnerData();

        return view;
    }

    private void setUpSpinnerData() {

        spinnerProName.setItems("Select Project", "Rahul Chokshi", "Budevbhai Chokshi", "Ushaben Chokshi", "Paresh Chokshi", "Kalpesh Chokshi", "Lalitaben Chokshi");
        spinnerProName.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                Toast.makeText(getContext(), "You Selected " + item.toString(), Toast.LENGTH_LONG).show();
            }
        });

        spinnerProMilestone.setItems("Select Milestone", "Android Developer", "IOS", "Black Berry", "Linux", "MacOS", "Linux");
        spinnerProMilestone.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                Toast.makeText(getContext(), "You Selected " + item.toString(), Toast.LENGTH_LONG).show();
            }
        });

        spinnerProActivity.setItems("Select Activities", "ABC", "PQR", "XYZ");
        spinnerProActivity.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                Toast.makeText(getContext(), "You Selected " + item.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @OnClick(R.id.fabExpense)
    public void onFabAction(View view){
        switch (view.getId()){
            case R.id.fabExpense:
                Intent intent=new Intent(getActivity(),ExpenseListActivity.class);
                startActivity(intent);
                break;
        }
    }
}
