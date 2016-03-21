package com.techkids.thangduong.fit5.CommonView.TypePage;

import com.techkids.thangduong.fit5.CommonView.ObjectForAdapter;

import java.util.ArrayList;

/**
 * Created by ThangDuong on 04-Mar-16.
 */
public class TypePageObjectManager {
    static ArrayList<ObjectForAdapter> infoWorkoutObjectsArray =null;
    static TypePageObjectManager mObjManager = null;

//    public ArrayList<ObjectForAdapter> getAchievementsObjectsArray() {
//        return infoWorkoutObjectsArray;
//    }

    private TypePageObjectManager() {
        mObjManager=this;
        infoWorkoutObjectsArray = new ArrayList<>();
    }

    public static TypePageObjectManager getInstance()
    {
        if(mObjManager==null)
            mObjManager = new TypePageObjectManager();

        return  mObjManager;
    }

    public void addObject(ObjectForAdapter obj)
    {
        infoWorkoutObjectsArray.add(obj);
    }

    public ObjectForAdapter getObject(int index)
    {
        return infoWorkoutObjectsArray.get(index);
    }

    public int getSize()
    {
        return infoWorkoutObjectsArray.size();
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
        infoWorkoutObjectsArray.clear();
    }
}
