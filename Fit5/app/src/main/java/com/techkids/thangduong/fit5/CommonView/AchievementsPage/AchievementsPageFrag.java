package com.techkids.thangduong.fit5.CommonView.AchievementsPage;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.techkids.thangduong.fit5.CommonView.ObjectForAdapter;
import com.techkids.thangduong.fit5.CommonView.TrackPage.TrackPageFrag;
import com.techkids.thangduong.fit5.CommonView.WorkoutPage.WorkoutPageFrag;
import com.techkids.thangduong.fit5.R;


/**
 * Created by ThangDuong on 02-Mar-16.
 */
public class AchievementsPageFrag extends Fragment {
    ListView lv;
    Context context;
    static AchievementPageObjectManager objManager ;
    static int progress;

//    public AchievementsPageFrag() {
//        objManager = AchievementPageObjectManager.getInstance();
//        int index=0;
//        while(index<titleList.length)
//        {
//            ObjectForAdapter obj = new ObjectForAdapter(titleList[index], descriptionList[index]);
//            //obj.setName("achievementObj"+Integer.toString(index));
//            objManager.addObject(obj);
//            index++;
//        }
//    }
    static AchievementsPageFrag achievementsPageFrag;

    public AchievementsPageFrag()
    {
        achievementsPageFrag=this;
    }

    public static AchievementsPageFrag getInstance()
    {
        if(achievementsPageFrag==null)
            achievementsPageFrag=new AchievementsPageFrag();
        return achievementsPageFrag;
    }
    private void initialize() {
        objManager = AchievementPageObjectManager.getInstance();
        if(objManager.getSize()!=0)
            return;
        objManager.addObject(new ObjectForAdapter("",""));
        objManager.addObject(new ObjectForAdapter(R.drawable.ic_achievement_unbreakmyheart,R.drawable.ic_achievement_unbreakmyheart,getResources().getString(R.string.achievement_0)
                ,getResources().getString(R.string.description_achievement_0)));
        objManager.addObject(new ObjectForAdapter(R.drawable.ic_achievement_creatingahabit,R.drawable.ic_achievement_creatingahabit,getResources().getString(R.string.achievement_1)
                ,getResources().getString(R.string.description_achievement_1)));
        objManager.addObject(new ObjectForAdapter(R.drawable.ic_achievement_soclose,R.drawable.ic_achievement_soclose,getResources().getString(R.string.achievement_2)
                ,getResources().getString(R.string.description_achievement_2)));
        objManager.addObject(new ObjectForAdapter(R.drawable.ic_achievement_sevenmonthchampion,R.drawable.ic_achievement_sevenmonthchampion,getResources().getString(R.string.achievement_3)
                ,getResources().getString(R.string.description_achievement_3)));
        objManager.addObject(new ObjectForAdapter(R.drawable.ic_achievement_thereisnosevenmonth,R.drawable.ic_achievement_thereisnosevenmonth,getResources().getString(R.string.achievement_4)
                ,getResources().getString(R.string.description_achievement_4)));
        objManager.addObject(new ObjectForAdapter("",""));
        objManager.addObject(new ObjectForAdapter(R.drawable.ic_achievement_challengeaccepted,R.drawable.ic_achievement_challengeaccepted,getResources().getString(R.string.achievement_5)
                ,getResources().getString(R.string.description_achievement_5)));
        objManager.addObject(new ObjectForAdapter(R.drawable.ic_achievement_hattrick,R.drawable.ic_achievement_hattrick,getResources().getString(R.string.achievement_6)
                ,getResources().getString(R.string.description_achievement_6)));
        objManager.addObject(new ObjectForAdapter(R.drawable.ic_achievement_intenseweek,R.drawable.ic_achievement_intenseweek,getResources().getString(R.string.achievement_7)
                ,getResources().getString(R.string.description_achievement_7)));
        objManager.addObject(new ObjectForAdapter(R.drawable.ic_achievement_isthisachallenge,R.drawable.ic_achievement_isthisachallenge,getResources().getString(R.string.achievement_8)
                ,getResources().getString(R.string.description_achievement_8)));
        objManager.addObject(new ObjectForAdapter(R.drawable.ic_achievement_onemonthdown,R.drawable.ic_achievement_onemonthdown,getResources().getString(R.string.achievement_9)
                ,getResources().getString(R.string.description_achievement_9)));
        objManager.addObject(new ObjectForAdapter(R.drawable.ic_achievement_thisquarter,R.drawable.ic_achievement_thisquarter,getResources().getString(R.string.achievement_10)
                ,getResources().getString(R.string.description_achievement_10)));
        objManager.addObject(new ObjectForAdapter("",""));
        objManager.addObject(new ObjectForAdapter(R.drawable.ic_circuit_two,R.drawable.ic_circuit_two,getResources().getString(R.string.achievement_11)
                ,getResources().getString(R.string.description_achievement_11)));
        objManager.addObject(new ObjectForAdapter(R.drawable.ic_circuit_three,R.drawable.ic_circuit_three,getResources().getString(R.string.achievement_12)
                ,getResources().getString(R.string.description_achievement_12)));
        objManager.addObject(new ObjectForAdapter(R.drawable.ic_circuit_four,R.drawable.ic_circuit_four,getResources().getString(R.string.achievement_13)
                ,getResources().getString(R.string.description_achievement_13)));
        objManager.addObject(new ObjectForAdapter(R.drawable.ic_circuit_five,R.drawable.ic_circuit_five,getResources().getString(R.string.achievement_14)
                ,getResources().getString(R.string.description_achievement_14)));
        objManager.addObject(new ObjectForAdapter("",""));
        objManager.addObject(new ObjectForAdapter(R.drawable.ic_achievement_earlybird,R.drawable.ic_achievement_earlybird,getResources().getString(R.string.achievement_15)
                ,getResources().getString(R.string.description_achievement_15)));
        objManager.addObject(new ObjectForAdapter(R.drawable.ic_achievement_afternoonboost,R.drawable.ic_achievement_afternoonboost,getResources().getString(R.string.achievement_16)
                ,getResources().getString(R.string.description_achievement_16)));
        objManager.addObject(new ObjectForAdapter(R.drawable.ic_achievement_bedtimeworkout,R.drawable.ic_achievement_bedtimeworkout,getResources().getString(R.string.achievement_17)
                ,getResources().getString(R.string.description_achievement_17)));
        objManager.addObject(new ObjectForAdapter(R.drawable.ic_achievement_nightowl,R.drawable.ic_achievement_nightowl,getResources().getString(R.string.achievement_18)
                ,getResources().getString(R.string.description_achievement_18)));
        objManager.addObject(new ObjectForAdapter(R.drawable.ic_achievement_workingdayandnight,R.drawable.ic_achievement_workingdayandnight,getResources().getString(R.string.achievement_19)
                ,getResources().getString(R.string.description_achievement_19)));
        objManager.addObject(new ObjectForAdapter("",""));
        //position : 26
        objManager.addObject(new ObjectForAdapter(R.drawable.ic_child_care_black_48dp
                ,R.drawable.ic_child_care_black_48dp
                ,getResources().getString(R.string.achievement_20),getResources().getString(R.string.description_achievement_20)));
        objManager.addObject(new ObjectForAdapter(R.drawable.ic_directions_walk_black_48dp
                ,R.drawable.ic_directions_walk_black_48dp,getResources().getString(R.string.achievement_21)
                ,getResources().getString(R.string.description_achievement_21)));
        objManager.addObject(new ObjectForAdapter(R.drawable.ic_directions_run_black_48dp
                ,R.drawable.ic_directions_run_black_48dp,getResources().getString(R.string.achievement_22)
                ,getResources().getString(R.string.description_achievement_22)));
        objManager.addObject(new ObjectForAdapter(R.drawable.ic_battery_charging_full_black_48dp
                ,R.drawable.ic_battery_charging_full_black_48dp,getResources().getString(R.string.achievement_23)
                ,getResources().getString(R.string.description_achievement_23)));
        objManager.addObject(new ObjectForAdapter(R.drawable.ic_send_black_48dp
                ,R.drawable.ic_send_black_48dp,getResources().getString(R.string.achievement_24)
                ,getResources().getString(R.string.description_achievement_24)));
        objManager.addObject(new ObjectForAdapter(R.drawable.ic_thumb_up_black_48dp
                ,R.drawable.ic_thumb_up_black_48dp,getResources().getString(R.string.achievement_25)
                ,getResources().getString(R.string.description_achievement_25)));
        objManager.addObject(new ObjectForAdapter(R.drawable.ic_favorite_black_48dp
                ,R.drawable.ic_favorite_black_48dp,getResources().getString(R.string.achievement_26)
                ,getResources().getString(R.string.description_achievement_26)));
        objManager.addObject(new ObjectForAdapter(R.drawable.ic_forward_30_black_48dp
                ,R.drawable.ic_forward_30_black_48dp,getResources().getString(R.string.achievement_27)
                ,getResources().getString(R.string.description_achievement_27)));
        objManager.addObject(new ObjectForAdapter(R.drawable.ic_fitness_center_black_48dp
                ,R.drawable.ic_fitness_center_black_48dp,getResources().getString(R.string.achievement_28)
                ,getResources().getString(R.string.description_achievement_28)));
        objManager.addObject(new ObjectForAdapter(R.drawable.ic_whatshot_black_48dp
                ,R.drawable.ic_whatshot_black_48dp,getResources().getString(R.string.achievement_29)
                ,getResources().getString(R.string.description_achievement_29)));
        objManager.addObject(new ObjectForAdapter(R.drawable.ic_workout_collector_toast
                ,R.drawable.ic_workout_collector_toast,getResources().getString(R.string.achievement_30)
                ,getResources().getString(R.string.description_achievement_30)));
        //Position :
        for(int i=0;i<objManager.getSize();i++)
            objManager.getObject(i).setAvailable(false);

    }
    public static String [] titleGroupList ={"5 MONTHS CHALLENGE","CONSECUTIVE DAYS","CALORIES BURNED"
            ,"WORKOUT TIME","UNLOCKED WORKOUTS"};
    public static int[] titleGroupIndex ={0,6,13,18,24};
//    public static int [] imageList;
    ViewGroup rootView;
    ProgressBar mProgressbar;
    TextView percent;
    static ListViewAdapter mListViewAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(
                R.layout.achievements_page_layout, container, false);

        initialize();
        getStoredData();
        mProgressbar = (ProgressBar)rootView.findViewById(R.id.mProgressBar);
        mProgressbar.setScaleY(3f);
        context=rootView.getContext();
        percent = (TextView) rootView.findViewById(R.id.achievementsPercentTxtViw);
        setProgress();

//        objManager = AchievementPageObjectManager.getInstance();
//        int index=0;
//        while(index<targetCalTitleList.length)
//        {
//            ObjectForAdapter obj = new ObjectForAdapter(targetCalTitleList[index], targetCalDescriptionList[index]);
//            //obj.setName("achievementObj"+Integer.toString(index));
//            objManager.addObject(obj);
//            index++;
//        }
        unlockAchievements();
        Bundle mBundle = new Bundle();
        mBundle.putStringArray("titleGroupList", titleGroupList);
        mBundle.putIntArray("titleGroupIndex", titleGroupIndex);
//        mBundle.putStringArray("targetCalTitleList", targetCalTitleList);
//        mBundle.putIntArray("imageList", imageList);
//        mBundle.putStringArray("targetCalDescriptionList", targetCalDescriptionList);
        lv=(ListView) rootView.findViewById(R.id.achievementsListView);
        mListViewAdapter = new ListViewAdapter(context,mBundle);
        lv.setAdapter(mListViewAdapter);
//        mListViewAdapter.notifyDataSetChanged();
        //setOnclickListener();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(context, "You Clicked Position : "
//                        + position, Toast.LENGTH_SHORT).show();
//                setAvailable(position,false);

//                objManager.getObject(position).setAvailable(true);
//                mListViewAdapter.notifyDataSetChanged();
            }
        });
        return rootView;
    }

    public void storeData()
    {
        SharedPreferences sharedPref = getActivity().getSharedPreferences(
                getString(R.string.saved_data_achievement_page), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < objManager.getSize(); i++) {
            sb2.append(Boolean.toString(objManager.getObject(i).isAvailable())).append(",");
        }
//        Log.e("LOL","store type = "+sb2.toString());
        editor.putString(getString(R.string.saved_achievement_available), sb2.toString());

        editor.commit();

    }
    public void getStoredData()
    {
        SharedPreferences sharedPref = getActivity().getSharedPreferences(
                getString(R.string.saved_data_achievement_page), Context.MODE_PRIVATE);

        String defaultAchievementAvailable= "false,false,false,false,false,false,false,false,false,false" +
                ",false,false,false,false,false,false,false,false,false,false" +
                ",false,false,false,false,false,true,false,false,false,false" +
                "false,false,false,false,false,false,false,";
        String[] achievementAvailable = sharedPref.getString(getString(R.string.saved_achievement_available)
                ,defaultAchievementAvailable).split(",");
        Log.e("LOL", "type.length = " + achievementAvailable.length);
        Log.e("LOL", "objManager.getSize() = " + objManager.getSize());
        for (int i = 0; i < objManager.getSize(); i++) {
//            Log.e("LOL","achievementAvailable["+i+"] = "+achievementAvailable[i]);
//            objManager.getObject(i).setAvailable(Boolean.parseBoolean(achievementAvailable[i]));
            storeData();
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        storeData();
    }

    private void unlockAchievements()
    {
        for (int i =0;i<objManager.getSize();i++)
        {
            //TODO
            //Update unlocked achievement from data base here
        }
        //Unlock exercises
        for( int i =0;i<10;i++)
        {
            if(TrackPageFrag.getDay()==14*i)
            {
                objManager.getObject(25+i).setAvailable(true);
//                TypeObjectManager.getInstance().getObject(i).setAvailable(true);
            }
            if(TrackPageFrag.getDay()==14*9)
                objManager.getObject(35).setAvailable(true);//The last achievement when unlock all exercises
        }
        //Unbreak my heart
        if(TrackPageFrag.getDay()>=30&&TrackPageFrag.getHeart()>0)
            objManager.getObject(2).setAvailable(true);
        //Creating a Habit
        if(TrackPageFrag.getDay()>=90&&TrackPageFrag.getHeart()>0)
            objManager.getObject(3).setAvailable(true);
        //So close I can taste it
        if(TrackPageFrag.getDay()>=180&&TrackPageFrag.getHeart()>0)
            objManager.getObject(4).setAvailable(true);
        //Made it to 7 months
        if(TrackPageFrag.getDay()>=210&&TrackPageFrag.getHeart()>0)
            objManager.getObject(5).setAvailable(true);
        //Last for the whole year
        if(TrackPageFrag.getDay()>=360&&TrackPageFrag.getHeart()>0)
            objManager.getObject(6).setAvailable(true);
        //Challenge accepted
        if(TrackPageFrag.getStreakDay()>=1)
            objManager.getObject(8).setAvailable(true);
        //Hat-Trick
        if(TrackPageFrag.getStreakDay()>=3)
            objManager.getObject(9).setAvailable(true);
        //Intense Week
        if(TrackPageFrag.getStreakDay()>=7)
            objManager.getObject(10).setAvailable(true);
        //Is this a challenge?
        if(TrackPageFrag.getStreakDay()>=14)
            objManager.getObject(11).setAvailable(true);
        //One month down
        if(TrackPageFrag.getStreakDay()>=30)
            objManager.getObject(12).setAvailable(true);
        //This quarter is looking good
        if(TrackPageFrag.getStreakDay()>=90)
            objManager.getObject(13).setAvailable(true);
        //Going Athlete
        if(WorkoutPageFrag.getCurrentCal()>=500)
            objManager.getObject(15).setAvailable(true);
        //All Good Thing Are 3
        if(TrackPageFrag.getStreakDay()>=600)
            objManager.getObject(16).setAvailable(true);
        //80 Minutes of Pain
        if(TrackPageFrag.getStreakDay()>=700)
            objManager.getObject(17).setAvailable(true);
        //Calories Master
        if(TrackPageFrag.getStreakDay()>=800)
            objManager.getObject(18).setAvailable(true);
        //Early Bird
        if(WorkoutPageFrag.isWorkoutMorning())
            objManager.getObject(20).setAvailable(true);
        //Afternoon Boost
        if(WorkoutPageFrag.isWorkoutAfternoon())
            objManager.getObject(21).setAvailable(true);
        //Bedtime Workout
        if(WorkoutPageFrag.isWorkoutEvening())
            objManager.getObject(22).setAvailable(true);
        //Night Owl
        if(WorkoutPageFrag.isWorkoutNight())
            objManager.getObject(23).setAvailable(true);
        //Working day and night
        if(WorkoutPageFrag.isWorkoutMorning()&&WorkoutPageFrag.isWorkoutAfternoon()
                &&WorkoutPageFrag.isWorkoutEvening()&&WorkoutPageFrag.isWorkoutNight())
            objManager.getObject(24).setAvailable(true);
    }
    public void setProgress()
    {
        mProgressbar.setProgress(progress);
        percent.setText(Integer.toString(progress)+"%");
    }

    public static int getProgress() {
        return progress;
    }

    public static void setProgress(int progress) {
        AchievementsPageFrag.progress = progress;
    }

    //    public void setAvailable(String ObjectName, boolean isAvailable)
//    {
//        for(int i=0;i<objManager.getSize();i++)
//        {
//            if(objManager.getObject(i).getName()==ObjectName)
//            {
//                objManager.getObject(i).setAvailable(isAvailable);
//            }
//        }
//    }
    private void setOnclickListener()
    {
//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(context, "You Clicked Position : "
//                        + position, Toast.LENGTH_SHORT).show();
////                setAvailable(position,false);
//            }
//        });
    }
//    public static void setAvailable(int position)
//    {
//        objManager.getObject(position).setAvailable
//                (!objManager.getObject(position).isAvailable());
////        mListViewAdapter.notifyDataSetChanged();
//    }
    public static void setAvailable(int position, boolean isAvailable)
    {
        objManager.getObject(position).setAvailable(isAvailable);
//        mGridViewAdapter.notifyDataSetChanged();
    }
    public static boolean getAvailable(int position)
    {
        return objManager.getObject(position).isAvailable();
//        mGridViewAdapter.notifyDataSetChanged();
    }
//
//    public void setAvailable(String name)
//    {
//        objManager.getObject(name).setAvailable
//                (!objManager.getObject(name).isAvailable());
//        mListViewAdapter.notifyDataSetChanged();
//    }
}
