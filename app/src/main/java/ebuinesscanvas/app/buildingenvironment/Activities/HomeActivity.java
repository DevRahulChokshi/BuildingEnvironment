package ebuinesscanvas.app.buildingenvironment.Activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import ebuinesscanvas.app.buildingenvironment.Adapter.ViewPagerContainer;
import ebuinesscanvas.app.buildingenvironment.R;

public class HomeActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener{

    private String TAG=HomeActivity.class.getSimpleName ();

    @BindView(R.id.tabLayout_trading_tab_view) TabLayout tabLayout;
    @BindView (R.id.pager_trading_tab_view) ViewPager viewPager;
    @BindView(R.id.toolbar_trading_tab_view) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind (this);

        setUpToolbar();

        tabLayout.addTab(tabLayout.newTab().setText("Activity"));
        tabLayout.addTab(tabLayout.newTab().setText("Expense"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        ViewPagerContainer adapter = new ViewPagerContainer(getSupportFragmentManager(), tabLayout.getTabCount());

        viewPager.setAdapter(adapter);
        tabLayout.setOnTabSelectedListener(this);

        toolbar.setNavigationOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                finish ();
            }
        });
    }

    private void setUpToolbar () {
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar ();
        if (actionBar!=null){
            actionBar.setTitle (R.string.home_title);
        }
    }

    @Override
    public void onTabSelected (TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
        String tabStr= (String) tab.getText ();
        Log.i (TAG,tabStr);
    }

    @Override
    public void onTabUnselected (TabLayout.Tab tab) {
        String tabStr= (String) tab.getText ();
        Log.i (TAG,tabStr);
    }

    @Override
    public void onTabReselected (TabLayout.Tab tab) {

    }
}