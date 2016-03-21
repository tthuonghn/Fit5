package com.techkids.thangduong.fit5.CommonView.WorkoutPage.ChooseTargetCalButton;

import com.techkids.thangduong.fit5.CommonView.ObjectForAdapter;

import java.util.ArrayList;

/**
 * Created by ThangDuong on 04-Mar-16.
 */
public class TargetCalObjectManager {
    static ArrayList<ObjectForAdapter> objectsArray =null;
    static TargetCalObjectManager mObjManager = null;

    private TargetCalObjectManager() {
        super();
        mObjManager=this;
        objectsArray = new ArrayList<>();
    }

    public static TargetCalObjectManager getInstance()
    {
        if(mObjManager==null)
            mObjManager = new TargetCalObjectManager();

        return mObjManager;
    }
    public void addObject(ObjectForAdapter obj)
    {
        objectsArray.add(obj);
    }

    public ObjectForAdapter getObject(int index)
    {
        return objectsArray.get(index);
    }

    public int getSize()
    {
        return objectsArray.size();
    }

    public static void reset()
    {
        objectsArray.clear();
    }
}
