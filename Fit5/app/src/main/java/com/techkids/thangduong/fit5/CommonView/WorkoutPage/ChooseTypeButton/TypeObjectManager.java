package com.techkids.thangduong.fit5.CommonView.WorkoutPage.ChooseTypeButton;

import com.techkids.thangduong.fit5.CommonView.ObjectForAdapter;

import java.util.ArrayList;

/**
 * Created by ThangDuong on 04-Mar-16.
 */
public class TypeObjectManager {

    static ArrayList<ObjectForAdapter> objectsArray =null;
    static TypeObjectManager mObjManager = null;

//    public ArrayList<ObjectForAdapter> getAchievementsObjectsArray() {
//        return objectsArray;
//    }

    protected TypeObjectManager() {
        mObjManager=this;
        objectsArray = new ArrayList<>();
    }

    public static TypeObjectManager getInstance()
    {
        if(mObjManager==null)
            mObjManager = new TypeObjectManager();

        return  mObjManager;
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
        objectsArray.clear();
    }
}
