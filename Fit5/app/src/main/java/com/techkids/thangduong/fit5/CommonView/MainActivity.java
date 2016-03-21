package com.techkids.thangduong.fit5.CommonView;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.techkids.thangduong.fit5.CommonView.AchievementsPage.AchievementsPageFrag;
import com.techkids.thangduong.fit5.CommonView.SettingPage.SettingPageFrag;
import com.techkids.thangduong.fit5.CommonView.TrackPage.TrackPageFrag;
import com.techkids.thangduong.fit5.CommonView.TypePage.TypePageFrag;
import com.techkids.thangduong.fit5.CommonView.WorkoutPage.ChooseTargetCalButton.TargetCalDialog;
import com.techkids.thangduong.fit5.CommonView.WorkoutPage.ChooseTypeButton.TypeDialog;
import com.techkids.thangduong.fit5.CommonView.WorkoutPage.WorkoutPageFrag;
import com.techkids.thangduong.fit5.R;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity{

    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 4;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private MyViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
//    private ScreenSlidePagerAdapter mPagerAdapter;

    private Toolbar toolbar;
    private TabLayout tabLayout;
//    Button ButtonGeneralInfo;
//    Button ButtonInfoWorkout;
//    Button ButtonAchievement;
//    Button ButtonTrack;
    WorkoutPageFrag workoutPageFrag;
    TypePageFrag typePageFrag;
    AchievementsPageFrag achievementsPageFrag;
    TrackPageFrag trackPageFrag;
    SettingPageFrag settingPageFrag;
    ViewPagerAdapter adapter;
    static MainActivity mainActivity = null;
    TypeDialog typeDialog;
    TargetCalDialog targetCalDialog;
    public MainActivity() {
        mainActivity=this;
    }

    public void setChooseTypeDialog(){
        typeDialog = new TypeDialog(MainActivity.this);
        try
        {

            typeDialog.show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Log.e("LOL", "Can not show typeDialog");
        }
    }

    public void setChooseTargetCalDialog(){
        targetCalDialog = new TargetCalDialog(MainActivity.this);
        try
        {

            targetCalDialog.show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Log.e("LOL", "Can not show targetCalDialog");
        }
    }

    public static MainActivity getInstance()
    {
        return mainActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (MyViewPager)findViewById(R.id.myViewPager);
        setupViewPager();
//        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
//        mPager.setAdapter(mPagerAdapter);
//        mPagerAdapter.update();
//        adapter.update();
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mPager);
        setupTabIcons();

//        setButtonsListener();
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_page_workout_normal);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_page_learn_normal);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_page_achievements_normal);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_page_track_normal);
        tabLayout.getTabAt(4).setIcon(R.drawable.ic_workout_collector_toast);
    }
    public void setupViewPager() {
        mPager.removeAllViews();
        adapter = null;
        workoutPageFrag=null;
        typePageFrag=null;
        achievementsPageFrag=null;
        trackPageFrag=null;
        settingPageFrag=null;

        workoutPageFrag = WorkoutPageFrag.getInstance(MainActivity.this.getApplicationContext());
        typePageFrag = TypePageFrag.getInstance(MainActivity.this);
        trackPageFrag = TrackPageFrag.getInstance(MainActivity.this);
        achievementsPageFrag = AchievementsPageFrag.getInstance();
        settingPageFrag = SettingPageFrag.getInstance();
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(workoutPageFrag, "Workout");
        adapter.addFragment(typePageFrag, "Type");
        adapter.addFragment(achievementsPageFrag, "Trophy");
        adapter.addFragment(trackPageFrag, "Track");
        adapter.addFragment(settingPageFrag, "Setting");
        mPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
        public void update()
        {
//            SettingPageFrag.setDay(15);
//            SettingPageFrag.setPercent(79);
//            SettingPageFrag.setHeart(2);
//            AchievementsPageFrag.setAvailable(3, false);
//            TypePageFrag.setAvailable(5, false);
//            WorkoutPageFrag.setTypeChosen(6);
//            WorkoutPageFrag.setTargetCalChosen(3);
//            WorkoutPageFrag.setTypeAvailable(2, false);
//            WorkoutPageFrag.setTargetCalAvailable(3, false);
//            WorkoutPageFrag.setTargetCal(2500);
//            WorkoutPageFrag.setCurrentCal(1200);

            notifyDataSetChanged();
        }
    }
    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        AchievementsPageFrag.getInstance().storeData();
//        SettingPageFrag.getInstance().storeData();
//        TypePageFrag.getInstance().storeData();
//        WorkoutPageFrag.getInstance().storeData();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    /**
     * A simple pager adapter that represents 5 WorkoutPageFrag objects, in
     * sequence.
     */
//    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
//        public ScreenSlidePagerAdapter(FragmentManager fm) {
//            super(fm);
//        }
//        public void update()
//        {
//            SettingPageFrag.setDay(15);
//            SettingPageFrag.setPercent(79);
//            SettingPageFrag.setHeart(2);
//            AchievementsPageFrag.setAvailable(3, false);
//            TypePageFrag.setAvailable(5, false);
//            WorkoutPageFrag.setTypeChosen(6);
//            WorkoutPageFrag.setTargetCalChosen(3);
//            WorkoutPageFrag.setTypeAvailable(2, false);
//            WorkoutPageFrag.setTargetCalAvailable(3, false);
//            WorkoutPageFrag.setTargetCal(2500);
//            WorkoutPageFrag.setCurrentCal(1200);
//            notifyDataSetChanged();
//        }
//        @Override
//        public Fragment getItem(int position) {
//            Fragment temp ;
//            switch (position)
//            {
//                default:
//                    //workoutPageFrag = new WorkoutPageFrag();
//                    temp = workoutPageFrag;
//                    break;
//                case 1:
//                    //typePageFrag = new TypePageFrag();
//                    temp = typePageFrag;
//                    break;
//                case 2:
////                    achievementsPageFrag = new AchievementsPageFrag();
//                    temp = achievementsPageFrag;
//                    break;
//                case 3:
////                    trackPageFrag = new SettingPageFrag();
//                    temp = trackPageFrag;
//                    break;
//            }
//
////            if(temp == trackPageFrag )
////                Log.e("LOL", "Return trackPageFrag");
////            if(temp == achievementsPageFrag )
////                Log.e("LOL", "Return achievementsPageFrag");
////            if(temp == typePageFrag )
////                Log.e("LOL", "Return typePageFrag");
////            if(temp == workoutPageFrag )
////                Log.e("LOL", "Return workoutPageFrag");
//            return temp;
//        }
//
//        @Override
//        public int getCount() {
//            return NUM_PAGES;
//        }
//
//    }

    public void setButtonsListener()
    {
//        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
//        ButtonGeneralInfo = (Button) findViewById(R.id.buttonGeneralInfo);
//        ButtonInfoWorkout = (Button) findViewById(R.id.buttonInfoWorkoutType);
//        ButtonAchievement = (Button) findViewById(R.id.buttonAchievements);
//        ButtonTrack = (Button) findViewById(R.id.buttonTrack);
//
//        ButtonGeneralInfo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mPager.setCurrentItem(0, true);
//            }
//        });
//        ButtonInfoWorkout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mPager.setCurrentItem(1, true);
//            }
//        });
//        ButtonAchievement.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mPager.setCurrentItem(2, true);
////                Toast.makeText(getApplicationContext(),"WorkoutPageFrag.getTypeChosen() = "
////                        +WorkoutPageFrag.getTypeChosen(),Toast.LENGTH_SHORT).show();
//            }
//        });
//        ButtonTrack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mPager.setCurrentItem(3, true);
//            }
//        });



    }
}
