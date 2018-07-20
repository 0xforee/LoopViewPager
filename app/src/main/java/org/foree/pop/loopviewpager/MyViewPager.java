package org.foree.pop.loopviewpager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

public class MyViewPager extends ViewPager {
    private static final String TAG = MyViewPager.class.getSimpleName();
    private float mOldX;
    public MyViewPager(@NonNull Context context) {
        this(context, null);
    }

    public MyViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private boolean mPreScrollDisable, mPostScrollDisable;

    public void setPreScrollDisable(boolean state){
        Log.d(TAG, "[foree] setPreScrollDisable: " + state);
        mPreScrollDisable = state;
    }

    public void setPostScrollDisable(boolean state){
        Log.d(TAG, "[foree] setPostScrollDisable: " + state);
        mPostScrollDisable = state;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                mOldX = ev.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                float offset = ev.getX() - mOldX;
                if (offset < 0){
                    Log.d(TAG, "[foree] onTouchEvent: 向右滑动");
                    if(mPostScrollDisable){
                        return true;
                    }
                }else{
                    Log.d(TAG, "[foree] onTouchEvent: 向左滑动");
                    if(mPreScrollDisable){
                        return true;
                    }
                }
                break;
            default:

        }

        return super.onTouchEvent(ev);
    }
}
