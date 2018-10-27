package ebuinesscanvas.app.buildingenvironment.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import ebuinesscanvas.app.buildingenvironment.Fragments.FragmentActivity;
import ebuinesscanvas.app.buildingenvironment.Fragments.FragmentExpense;


public class ViewPagerContainer extends FragmentStatePagerAdapter {

    int tabCount;

    public ViewPagerContainer(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount= tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FragmentActivity();
            case 1:
                return new FragmentExpense();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}