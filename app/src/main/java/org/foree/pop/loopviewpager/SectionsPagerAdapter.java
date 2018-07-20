package org.foree.pop.loopviewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.ViewGroup;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {
    private static final String TAG = "UlimitPage";

    private UlimitPage mPage;
    private MyViewPager mViewPager;
    interface UlimitPage{
        void onDataChanged(int position);
        Fragment getItem(int position);
    }

    public SectionsPagerAdapter(ViewPager viewPager, FragmentManager fm) {
        super(fm);
        mViewPager = (MyViewPager)viewPager;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return mPage.getItem(position);
    }

    public void setPage(UlimitPage page){
        mPage = page;
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }

    @Override
    public void finishUpdate(ViewGroup container) {
        super.finishUpdate(container);
        if(mPage != null && mDataChanged){
            mPage.onDataChanged(mPosition);
            mDataChanged = false;
        }
    }

    private boolean mDataChanged = true;
    private int mPosition = 0;
    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        Log.d(TAG, "[foree] setPrimaryItem: position = " + position + ", object = " + object);

        if(position != 1){
            mPosition = position;
            mDataChanged = true;
            mViewPager.setCurrentItem(1, false);
        }
    }

}
