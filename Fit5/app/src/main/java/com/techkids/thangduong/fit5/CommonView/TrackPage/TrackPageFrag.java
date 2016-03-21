package com.techkids.thangduong.fit5.CommonView.TrackPage;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.techkids.thangduong.fit5.CommonView.AchievementsPage.AchievementsPageFrag;
import com.techkids.thangduong.fit5.CommonView.CircularProgressBar;
import com.techkids.thangduong.fit5.CommonView.WorkoutPage.WorkoutPageFrag;
import com.techkids.thangduong.fit5.R;

import java.util.Calendar;


/**
 * Created by ThangDuong on 02-Mar-16.
 */
public class TrackPageFrag extends Fragment {
    ImageButton buttonHeart1;
    ImageButton buttonHeart2;
    ImageButton buttonHeart3;
//    TextView trackPercent;
//    TextView trackDay;
    LinearLayout centerLayout;
    static int intHeartLeft=3;
    static int intTrackPercent=0;
    static int intTrackDay=0;
    static int streakDay =0;
    static int targetDay = 150;
    static int currentDate = 0;
    ViewGroup rootView;
    CircularProgressBar circularProgressBar;
    Calendar c;
    static TrackPageFrag trackPageFrag;
    static Context context;

    public TrackPageFrag()
    {
        trackPageFrag=this;
    }

    public static TrackPageFrag getInstance()
    {
        if(trackPageFrag==null)
            trackPageFrag=new TrackPageFrag();
        return trackPageFrag;
    }
    public static TrackPageFrag getInstance(Context c)
    {
        context=c;
        if(trackPageFrag==null)
            trackPageFrag=new TrackPageFrag();
        return trackPageFrag;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(
                R.layout.track_page_layout, container, false);
        getStoredData();
        buttonHeart1 = (ImageButton) rootView.findViewById(R.id.buttonHeart1);
        buttonHeart2 = (ImageButton) rootView.findViewById(R.id.buttonHeart2);
        buttonHeart3 = (ImageButton) rootView.findViewById(R.id.buttonHeart3);
//        trackDay = (TextView) rootView.findViewById(R.id.trackDays);
//        trackPercent = (TextView) rootView.findViewById(R.id.trackPercent);
        centerLayout = (LinearLayout) rootView.findViewById(R.id.trackCenterLayout);
        centerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                setHeart(getHeart()-1);
                TrackPageDialog trackPageDialog =new TrackPageDialog(getActivity());
                trackPageDialog.show();
                storeData();
            }
        });
        intTrackPercent=100*intTrackDay/targetDay;


        updateEveryday();


        circularProgressBar = (CircularProgressBar)rootView.findViewById(R.id.track_page_cirProBar);
        circularProgressBar.setProgress(intTrackPercent);
        circularProgressBar.setTitle(Integer.toString(intTrackPercent) + " %");
        circularProgressBar.setSubTitle(Integer.toString(intTrackDay)+" days");
//        buttonHeart1.setImageResource(R.drawable.ic_launcher);
//        this.setButton(rootView);
//        trackDay.setText(Integer.toString(intTrackDay)+" days");
//        trackPercent.setText(Integer.toString(intTrackPercent)+" %");

//        Log.e("LOL", "SettingPageFrag : SettingPageFrag.getHeart()=" + SettingPageFrag.getHeart());
        switch (intHeartLeft)
        {
            default:
                buttonHeart3.setImageDrawable(getResources().getDrawable(R.drawable.ic_heart_off));
                buttonHeart2.setImageDrawable(getResources().getDrawable(R.drawable.ic_heart_off));
                buttonHeart1.setImageDrawable(getResources().getDrawable(R.drawable.ic_heart_off));
                break;
            case 3:
                buttonHeart3.setImageDrawable(getResources().getDrawable(R.drawable.ic_heart_on));
                buttonHeart2.setImageDrawable(getResources().getDrawable(R.drawable.ic_heart_on));
                buttonHeart1.setImageDrawable(getResources().getDrawable(R.drawable.ic_heart_on));
                break;
            case 2:
                buttonHeart3.setImageDrawable(getResources().getDrawable(R.drawable.ic_heart_off));
                buttonHeart2.setImageDrawable(getResources().getDrawable(R.drawable.ic_heart_on));
                buttonHeart1.setImageDrawable(getResources().getDrawable(R.drawable.ic_heart_on));
                break;
            case 1:
                buttonHeart3.setImageDrawable(getResources().getDrawable(R.drawable.ic_heart_off));
                buttonHeart2.setImageDrawable(getResources().getDrawable(R.drawable.ic_heart_off));
                buttonHeart1.setImageDrawable(getResources().getDrawable(R.drawable.ic_heart_on));
                break;
        }


        return rootView;
    }
    public void updateEveryday()
    {
        c = Calendar.getInstance();
        if(currentDate==0)//App run for the first time
        {
            currentDate=c.get(Calendar.DATE);
            return;
        }
//        Log.e("LOL", "SettingPageFrag.getHeart()=" + SettingPageFrag.getHeart());
        int tempDate = c.get(Calendar.DATE);
//        Log.e("LOL","c.get(Calendar.HOUR)="+tempDate+" c.get(Calendar.MINUTE)="+c.get(Calendar.MINUTE));
        if(tempDate!=currentDate)//a new day
        {
            currentDate=tempDate;
            if(WorkoutPageFrag.getCurrentCal()>=WorkoutPageFrag.getTargetCal())
            {
                setDay(getDay()+1);
                setStreakDay(getStreakDay()+1);
                AchievementsPageFrag.setProgress(100 * getDay() / getTargetDay());
            }
            else if (TrackPageFrag.getHeart()>1)
            {
                setHeart(getHeart()-1);
                setStreakDay(0);
            }
            else
            {
                setDay(0);
                setStreakDay(0);
                setHeart(3);
                AchievementsPageFrag.setProgress(0);
            }
            WorkoutPageFrag.getInstance(context).setCurrentCal(0);
            WorkoutPageFrag.getInstance(context).setWorkoutMorning(false);
            WorkoutPageFrag.getInstance(context).setWorkoutAfternoon(false);
            WorkoutPageFrag.getInstance(context).setWorkoutEvening(false);
            WorkoutPageFrag.getInstance(context).setWorkoutNight(false);
                //
//            Log.e("LOL", "tempDate==0");
        }

    }
    public static int getTargetDay() {
        return targetDay;
    }

    public static void setTargetDay(int targetDay) {
        TrackPageFrag.targetDay = targetDay;
    }

    public static void setDay(int input)
    {
        intTrackDay=input;
//        trackDay.setText(Integer.toString(input)+" days");
    }
    public static int getDay()
    {
        return intTrackDay;
    }

    public static void setPercent(int input)
    {
        intTrackPercent=input;
//        trackPercent.setText(Integer.toString(input)+" %");
    }
    public static int getPercent()
    {
        return intTrackPercent;
    }

    public static void setHeart(int input)
    {
        intHeartLeft=input;
        switch (input)
        {
            default:
                break;
            case 3:
//                buttonHeart3.setBackgroundColor(Color.BLUE);
//                buttonHeart2.setBackgroundColor(Color.BLUE);
//                buttonHeart1.setBackgroundColor(Color.BLUE);
                break;
            case 2:
//                buttonHeart3.setBackgroundColor(Color.BLUE);
//                buttonHeart2.setBackgroundColor(Color.BLUE);
                break;
            case 1:
//                buttonHeart3.setBackgroundColor(Color.BLUE);
                break;
        }
    }
    public static int getHeart()
    {
        return intHeartLeft;
    }

    public static int getStreakDay() {
        return streakDay;
    }

    public static void setStreakDay(int streakDay) {
        TrackPageFrag.streakDay = streakDay;
    }


    public void getStoredData()
    {
        SharedPreferences sharedPref = getActivity().getSharedPreferences(
                getString(R.string.saved_data_track_page), Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPref.edit();
//        editor.putInt(getString(R.string.saved_high_score), newHighScore);
//        editor.commit();

        intHeartLeft = Integer.parseInt(sharedPref.getString(getString(R.string.saved_heart_left)
        , "3"));
//        Log.e("LOL","typeChosen="+typeChosen);
        intTrackPercent = Integer.parseInt(sharedPref.getString(getString(R.string.saved_track_percent)
                , "0"));
//        Log.e("LOL","targetCalChosen="+targetCalChosen);
        intTrackDay = Integer.parseInt(sharedPref.getString(getString(R.string.saved_track_day)
                , "0"));
//        Log.e("LOL","typeChosen="+typeChosen);
        streakDay = Integer.parseInt(sharedPref.getString(getString(R.string.saved_streak_day)
                , "0"));
//        Log.e("LOL","targetCalChosen="+targetCalChosen);
        targetDay = Integer.parseInt(sharedPref.getString(getString(R.string.saved_target_day)
                , "150"));
//        Log.e("LOL","targetCalChosen="+targetCalChosen);
        currentDate = Integer.parseInt(sharedPref.getString(getString(R.string.saved_current_date)
                , "0"));
//        Log.e("LOL","targetCalChosen="+targetCalChosen);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        storeData();
    }

    public void storeData()
    {
        SharedPreferences sharedPref = getActivity().getSharedPreferences(
                getString(R.string.saved_data_track_page), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(getString(R.string.saved_heart_left), Integer.toString(intHeartLeft));
        editor.putString(getString(R.string.saved_track_percent), Integer.toString(intTrackPercent));
        editor.putString(getString(R.string.saved_track_day), Integer.toString(intTrackDay));
        editor.putString(getString(R.string.saved_streak_day), Integer.toString(streakDay));
        editor.putString(getString(R.string.saved_target_day), Integer.toString(targetDay));
        editor.putString(getString(R.string.saved_current_date), Integer.toString(currentDate));

        editor.commit();

    }

    public static void reset()
    {
        intHeartLeft=3;
        intTrackPercent=0;
        intTrackDay=0;
        streakDay =0;
        targetDay = 150;
        currentDate = 0;
    }
}
