package com.techkids.thangduong.fit5.CommonView;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;


/**
 * Created by ThangDuong on 03-Mar-16.
 */
public class MyViewPager extends ViewPager {

    static MyViewPager myViewPager = null;

    public MyViewPager(Context context) {
        super(context);
        myViewPager=this;
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        myViewPager=this;
    }

    public static MyViewPager getInstance()
    {
        return myViewPager;
    }
}
