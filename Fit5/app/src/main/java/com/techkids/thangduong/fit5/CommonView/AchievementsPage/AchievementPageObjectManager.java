package com.techkids.thangduong.fit5.CommonView.AchievementsPage;

import com.techkids.thangduong.fit5.CommonView.ObjectForAdapter;

import java.util.ArrayList;

/**
 * Created by ThangDuong on 04-Mar-16.
 */
public class AchievementPageObjectManager {
    static ArrayList<ObjectForAdapter> achievementsObjectsArray=null;
    static AchievementPageObjectManager mObjManager = null;

//    public ArrayList<ObjectForAdapter> getAchievementsObjectsArray() {
//        return achievementsObjectsArray;
//    }

    private AchievementPageObjectManager() {
        mObjManager=this;
        achievementsObjectsArray = new ArrayList<>();
    }

    public static AchievementPageObjectManager getInstance()
    {
        if(mObjManager==null)
            mObjManager = new AchievementPageObjectManager();

        return  mObjManager;
    }

    public void addObject(ObjectForAdapter obj)
    {
        achievementsObjectsArray.add(obj);
    }

    public ObjectForAdapter getObject(int index)
    {
        return achievementsObjectsArray.get(index);
    }

    public int getSize()
    {
        return achievementsObjectsArray.size();
    }

//    public ObjectForAdapter getObject(String name)
//    {
//        ObjectForAdapter temp = null;
//        for(int i=0;i<getSize();i++)
//        {
//            if(getObject(i).getName().equals(name))
//                temp=getObject(i);
//        }
//        return temp;
//    }
    public static void reset()
    {
        achievementsObjectsArray.clear();
    }
}
