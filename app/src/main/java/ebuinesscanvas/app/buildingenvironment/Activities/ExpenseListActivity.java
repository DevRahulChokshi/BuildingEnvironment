package ebuinesscanvas.app.buildingenvironment.Activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.jaredrummler.materialspinner.MaterialSpinner;

import butterknife.BindView;
import butterknife.ButterKnife;
import ebuinesscanvas.app.buildingenvironment.R;


public class ExpenseListActivity extends AppCompatActivity {

    @BindView(R.id.spnExpenseType) MaterialSpinner mSpinnerProName;
    @BindView(R.id.toolbar_expense) Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_list);

        ButterKnife.bind(this);

        // initialize toolbar and spinner item
        setUpToolbar();
        setUpSpinnerData();
    }

    //setUp toolbar
    private void setUpToolbar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.setTitle(R.string.activity_expense);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    //setup spinner item
    private void setUpSpinnerData() {
        mSpinnerProName.setItems("Expense Type", "Stationary", "Printing", "Office", "Books", "Travel", "Daily");
        mSpinnerProName.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                Toast.makeText(getApplicationContext(), "You Selected " + item.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
