package com.techkids.thangduong.fit5.CommonView;

import java.util.ArrayList;


/**
 * Created by ThangDuong on 04-Mar-16.
 */
public class WorkoutTypeObjectManager {
    static ArrayList<WorkoutTypeObject> workoutTypeObjectArray =null;
    static WorkoutTypeObjectManager mObjManager = null;

    private WorkoutTypeObjectManager() {
        mObjManager=this;
        workoutTypeObjectArray = new ArrayList<>();
    }

    public static WorkoutTypeObjectManager getInstance()
    {
        if(mObjManager==null)
            mObjManager = new WorkoutTypeObjectManager();

        return  mObjManager;
    }

    public void addObject(WorkoutTypeObject obj)
    {
        workoutTypeObjectArray.add(obj);
    }

    public WorkoutTypeObject getObject(int index)
    {
        return workoutTypeObjectArray.get(index);
    }

    public int getSize()
    {
        return workoutTypeObjectArray.size();
    }

    public static void reset()
    {
        workoutTypeObjectArray.clear();
    }
}
