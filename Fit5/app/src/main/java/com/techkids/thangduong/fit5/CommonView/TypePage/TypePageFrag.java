package com.techkids.thangduong.fit5.CommonView.TypePage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.techkids.thangduong.fit5.CommonView.ObjectForAdapter;
import com.techkids.thangduong.fit5.CommonView.TrackPage.TrackPageFrag;
import com.techkids.thangduong.fit5.CommonView.WorkoutPage.ChooseTypeButton.TypeObjectManager;
import com.techkids.thangduong.fit5.R;
import com.techkids.thangduong.fit5.CommonView.TypePage.InfoTypeActivity.InfoTypeActivity;

//import com.techkids.thangduong.fit5.TypePage.InfoTypeActivity.InfoTypeActivity;

/**
 * Created by ThangDuong on 02-Mar-16.
 */
public class TypePageFrag extends Fragment {
    GridView gridView;
    static Context context;
    static TypePageObjectManager objManager;
    ViewGroup rootView;
    GridViewAdapter mGridViewAdapter;
    static TypePageFrag typePageFrag;

    public TypePageFrag()
    {
        typePageFrag=this;
    }

    public static TypePageFrag getInstance(Context c)
    {
        context = c;
        if(typePageFrag==null)
            typePageFrag=new TypePageFrag();
        return typePageFrag;
    }

    private void initialize() {
        objManager = TypePageObjectManager.getInstance();
        if(objManager.getSize()!=0)
            return;
        objManager.addObject(new ObjectForAdapter(R.drawable.ic_child_care_black_48dp
                ,R.drawable.ic_workout_learn_locked_normal,getResources().getString(R.string.workout_exercise_0)
                ,getResources().getString(R.string.description_workout_exercise_0)));
        objManager.addObject(new ObjectForAdapter(R.drawable.ic_directions_walk_black_48dp
                ,R.drawable.ic_workout_learn_locked_normal,getResources().getString(R.string.workout_exercise_1)
                ,getResources().getString(R.string.description_workout_exercise_1)));
        objManager.addObject(new ObjectForAdapter(R.drawable.ic_directions_run_black_48dp
                ,R.drawable.ic_workout_learn_locked_normal,getResources().getString(R.string.workout_exercise_2)
                ,getResources().getString(R.string.description_workout_exercise_2)));
        objManager.addObject(new ObjectForAdapter(R.drawable.ic_battery_charging_full_black_48dp
                ,R.drawable.ic_workout_learn_locked_normal,getResources().getString(R.string.workout_exercise_3)
                ,getResources().getString(R.string.description_workout_exercise_3)));
        objManager.addObject(new ObjectForAdapter(R.drawable.ic_send_black_48dp
                ,R.drawable.ic_workout_learn_locked_normal,getResources().getString(R.string.workout_exercise_4)
                ,getResources().getString(R.string.description_workout_exercise_4)));
        objManager.addObject(new ObjectForAdapter(R.drawable.ic_thumb_up_black_48dp
                ,R.drawable.ic_workout_learn_locked_normal,getResources().getString(R.string.workout_exercise_5)
                ,getResources().getString(R.string.description_workout_exercise_5)));
        objManager.addObject(new ObjectForAdapter(R.drawable.ic_favorite_black_48dp
                ,R.drawable.ic_workout_learn_locked_normal,getResources().getString(R.string.workout_exercise_6)
                ,getResources().getString(R.string.description_workout_exercise_6)));
        objManager.addObject(new ObjectForAdapter(R.drawable.ic_forward_30_black_48dp
                ,R.drawable.ic_workout_learn_locked_normal,getResources().getString(R.string.workout_exercise_7)
                ,getResources().getString(R.string.description_workout_exercise_7)));
        objManager.addObject(new ObjectForAdapter(R.drawable.ic_fitness_center_black_48dp
                ,R.drawable.ic_workout_learn_locked_normal,getResources().getString(R.string.workout_exercise_8)
                ,getResources().getString(R.string.description_workout_exercise_8)));
        objManager.addObject(new ObjectForAdapter(R.drawable.ic_whatshot_black_48dp
                ,R.drawable.ic_workout_learn_locked_normal,getResources().getString(R.string.workout_exercise_9)
                ,getResources().getString(R.string.description_workout_exercise_9)));

        for(int i=0;i<objManager.getSize();i++)
        {
            if(i!=0)
                objManager.getObject(i).setAvailable(false);
            else
                objManager.getObject(i).setAvailable(true);
        }

    }

//    public static int [] prgmImages;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(
                R.layout.type_page_layout, container, false);

        context=rootView.getContext();
        initialize();
        getStoredData();
//        objManager = TypePageObjectManager.getInstance();
//        int index=0;
//        while(index<targetCalTitleList.length)
//        {
//            ObjectForAdapter obj = new ObjectForAdapter(targetCalTitleList[index], targetCalDescriptionList[index]);
//            //obj.setName("achievementObj"+Integer.toString(index));
//            objManager.addObject(obj);
//            index++;
//        }
//
//        Bundle mBundle = new Bundle();
//        mBundle.putStringArray("contentList", targetCalTitleList);
//        mBundle.putIntArray("imageList", prgmImages);
//        mBundle.putStringArray("targetCalDescriptionList", targetCalDescriptionList);
        gridView=(GridView) rootView.findViewById(R.id.achievementsListView);
        mGridViewAdapter = new GridViewAdapter(context);
        gridView.setAdapter(mGridViewAdapter);
        gridView.setScaleY(1f);
        setOnclickListener();
//        mGridViewAdapter.notifyDataSetChanged();
        unlockType();
        return rootView;
    }
    public void storeData()
    {
        SharedPreferences sharedPref = getActivity().getSharedPreferences(
                getString(R.string.saved_data_type_page), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < objManager.getSize(); i++) {
            sb2.append(Boolean.toString(objManager.getObject(i).isAvailable())).append(",");
        }
//        Log.e("LOL","store type = "+sb2.toString());
        editor.putString(getString(R.string.saved_type_available_type_page), sb2.toString());

        editor.commit();

    }
    public void getStoredData()
    {
        SharedPreferences sharedPref = getActivity().getSharedPreferences(
                getString(R.string.saved_data_type_page), Context.MODE_PRIVATE);

        String defaultTypeAvailable= "true,false,false,false,false,false,false,false,false,false";
        String[] typeAvailable = sharedPref.getString(getString(R.string.saved_type_available_type_page)
                ,defaultTypeAvailable).split(",");
//        Log.e("LOL","type.length = "+typeAvailable.length);
        for (int i = 0; i < objManager.getSize(); i++) {
//            Log.e("LOL","typeAvailable["+i+"] = "+typeAvailable[i]);
            objManager.getObject(i).setAvailable(Boolean.parseBoolean(typeAvailable[i]));
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        storeData();
    }

    private void unlockType()
    {
       for( int i =0;i<objManager.getSize();i++)
            if(TrackPageFrag.getDay()==14*i)
            {
                objManager.getObject(i).setAvailable(true);
//                TypeObjectManager.getInstance().getObject(i).setAvailable(true);
            }
    }
    private void setOnclickListener()
    {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(context, "You Clicked Position : "
//                        + position, Toast.LENGTH_SHORT).show();

//                setAvailable(position,!getAvailable(position));
//                objManager.getObject(9).setAvailable(true);
//                setAvailable(position,true);
                storeData();
                Intent intent = new Intent(getContext(),InfoTypeActivity.class);
                intent.putExtra("position",Integer.toString(position));
                startActivity(intent);
            }
        });
    }
    public void setAvailable(int position, boolean isAvailable)
    {
        objManager.getObject(position).setAvailable(isAvailable);
    }
    public static boolean getAvailable(int position)
    {
        return objManager.getObject(position).isAvailable();
//        mGridViewAdapter.notifyDataSetChanged();
    }
}
