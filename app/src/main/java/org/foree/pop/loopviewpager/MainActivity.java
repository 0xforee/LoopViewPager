package org.foree.pop.loopviewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private MyViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.


        // Set up the ViewPager with the sections adapter.
        mViewPager = (MyViewPager) findViewById(R.id.container);
        mSectionsPagerAdapter = new SectionsPagerAdapter(mViewPager, getSupportFragmentManager());
        mViewPager.setAdapter(mSectionsPagerAdapter);

        mSectionsPagerAdapter.setPage(new SwitchPage());

    }


    private class SwitchPage implements SectionsPagerAdapter.UlimitPage {
        private static final String TAG = "UlimitPage";
        int i = 0;
        PlaceholderFragment[] fragments;

        private boolean init = true;

        public SwitchPage() {
            fragments = new PlaceholderFragment[]{
                    PlaceholderFragment.newInstance(0),
                    PlaceholderFragment.newInstance(1),
                    PlaceholderFragment.newInstance(2)
            };
        }

        // -3是zuo边缘, 5是you边缘
        @Override
        public void onDataChanged(int position) {
            int offset = position - 1;
            if(init){
                init = false;
            }else {
                if (offset < 0) {
                    Log.d(TAG, "[foree] onDataChanged: 向左滑动");
                    i--;
                } else if (offset > 0) {
                    Log.d(TAG, "[foree] onDataChanged: 向右滑动");
                    i++;
                }
            }


            if (offset != 0) {
                fragments[0].setText((i - 1));
                fragments[1].setText(i);
                fragments[2].setText(i + 1);
            }


            // 上一页已经无法获取，当前页到了最左边，禁止左滑动
            mViewPager.setPreScrollDisable((i - 1) < -3);

            // 下一页已经无法获取，当前页到了最左边，禁止左滑动
            mViewPager.setPostScrollDisable((i + 1) > 5);

        }

        @Override
        public Fragment getItem(int position) {
            return fragments[position];
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private TextView textView;
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            textView = (TextView) rootView.findViewById(R.id.section_label);

            return rootView;
        }

        public void setText(int position) {
            if (textView != null)
                textView.setText(getString(R.string.section_format) + position);
        }
    }


}
