package com.techkids.thangduong.fit5.CommonView.WorkoutPage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.techkids.thangduong.fit5.CardboardView.CardboardActivity;
import com.techkids.thangduong.fit5.CommonView.CircularProgressBar;
import com.techkids.thangduong.fit5.CommonView.MainActivity;
import com.techkids.thangduong.fit5.CommonView.ObjectForAdapter;
import com.techkids.thangduong.fit5.CommonView.SettingPage.SettingPageFrag;
import com.techkids.thangduong.fit5.CommonView.TrackPage.TrackPageFrag;
import com.techkids.thangduong.fit5.CommonView.WorkoutTypeObject;
import com.techkids.thangduong.fit5.CommonView.WorkoutTypeObjectManager;
import com.techkids.thangduong.fit5.R;
import com.techkids.thangduong.fit5.CommonView.WorkoutPage.ChooseTargetCalButton.TargetCalDialog;
import com.techkids.thangduong.fit5.CommonView.WorkoutPage.ChooseTargetCalButton.TargetCalObjectManager;
import com.techkids.thangduong.fit5.CommonView.WorkoutPage.ChooseTypeButton.TypeDialog;
import com.techkids.thangduong.fit5.CommonView.WorkoutPage.ChooseTypeButton.TypeObjectManager;

import java.util.Calendar;

/**
 * Created by ThangDuong on 02-Mar-16.
 */
public class WorkoutPageFrag extends Fragment {

    Button btnChooseType;
    Button btnTargetCalories;
    Button btnMusic;
    Button btnStartWorkout;
    CircularProgressBar circularProgressBar;
    static TypeObjectManager typeObjectManager = TypeObjectManager.getInstance();
    static TargetCalObjectManager targetCalObjectManager = TargetCalObjectManager.getInstance();

    static String [] typeTitleList;
    static String [] typeDescriptionList;
    static String [] targetCalTitleList;
    static String [] targetCalDescriptionList;
    ViewGroup rootView;
    static WorkoutPageFrag workoutPageFrag;
    WorkoutTypeObjectManager objectManager;
    static int typeChosen;
    static int targetCalChosen;
    static double currentCal;
    static double targetCal ;
    static boolean workoutMorning ;
    static boolean workoutAfternoon ;
    static boolean workoutEvening ;
    static boolean workoutNight ;
    static Context myContext;

    //Add data here so we can manipulate in the MainActivity
    public WorkoutPageFrag() {
        workoutPageFrag=this;
    }
//    public WorkoutPageFrag(Context context) {
//        workoutPageFrag=this;
//        myContext=context;
//    }
    public static WorkoutPageFrag getInstance(Context c)
    {
        Log.e("CanhBX", "getInstance called");
        myContext = c;
        if(workoutPageFrag==null)
            workoutPageFrag=new WorkoutPageFrag();
        return workoutPageFrag;
    }

    private void initialize()
    {
        targetCal = 400;
        workoutMorning = false;
        workoutAfternoon = false;
        workoutEvening = false;
        workoutNight = false;

        typeTitleList = new String[]{"",getResources().getString(R.string.workout_exercise_0)
                ,getResources().getString(R.string.workout_exercise_1),getResources().getString(R.string.workout_exercise_2)
                ,getResources().getString(R.string.workout_exercise_3),getResources().getString(R.string.workout_exercise_4)
                ,"",getResources().getString(R.string.workout_exercise_5),getResources().getString(R.string.workout_exercise_6)
                ,getResources().getString(R.string.workout_exercise_7),getResources().getString(R.string.workout_exercise_8)
                ,getResources().getString(R.string.workout_exercise_9),""};
        typeDescriptionList = new String[] {"",getResources().getString(R.string.description_workout_exercise_0)
                ,getResources().getString(R.string.description_workout_exercise_1)
                ,getResources().getString(R.string.description_workout_exercise_2)
                ,getResources().getString(R.string.description_workout_exercise_3)
                ,getResources().getString(R.string.description_workout_exercise_4),""
                ,getResources().getString(R.string.description_workout_exercise_5)
                ,getResources().getString(R.string.description_workout_exercise_6)
                ,getResources().getString(R.string.description_workout_exercise_7)
                ,getResources().getString(R.string.description_workout_exercise_8)
                ,getResources().getString(R.string.description_workout_exercise_9),""};
        int index=0;
        typeObjectManager = TypeObjectManager.getInstance();
        while(index< typeTitleList.length)
        {
            ObjectForAdapter obj = new ObjectForAdapter(typeTitleList[index], typeDescriptionList[index]);
            //obj.setName("achievementObj"+Integer.toString(index));
            typeObjectManager.addObject(obj);
            index++;
        }
        typeObjectManager.getObject(1).setImageView1(R.drawable.ic_child_care_black_48dp);
        typeObjectManager.getObject(2).setImageView1(R.drawable.ic_directions_walk_black_48dp);
        typeObjectManager.getObject(3).setImageView1(R.drawable.ic_directions_run_black_48dp);
        typeObjectManager.getObject(4).setImageView1(R.drawable.ic_battery_charging_full_black_48dp);
        typeObjectManager.getObject(5).setImageView1(R.drawable.ic_send_black_48dp);
        typeObjectManager.getObject(7).setImageView1(R.drawable.ic_thumb_up_black_48dp);
        typeObjectManager.getObject(8).setImageView1(R.drawable.ic_favorite_black_48dp);
        typeObjectManager.getObject(9).setImageView1(R.drawable.ic_forward_30_black_48dp);
        typeObjectManager.getObject(10).setImageView1(R.drawable.ic_fitness_center_black_48dp);
        typeObjectManager.getObject(11).setImageView1(R.drawable.ic_whatshot_black_48dp);
        for(int i=0;i<typeObjectManager.getSize();i++)
        {
            typeObjectManager.getObject(i).setImageView2(R.drawable.ic_workout_learn_locked_normal);
//            Log.e("LOL", "typeObjectManager.getObject(i).getImageView2() = "
//                    + typeObjectManager.getObject(i).getImageView2());
        }
        typeChosen= typeObjectManager.getSize();

        targetCalTitleList = new String[]{"",getResources().getString(R.string.target_cal_0)
                ,getResources().getString(R.string.target_cal_1)
                ,getResources().getString(R.string.target_cal_2)
                ,getResources().getString(R.string.target_cal_3)
                ,getResources().getString(R.string.target_cal_4)
                ,getResources().getString(R.string.target_cal_my_own)};
        targetCalDescriptionList = new String[] {"",getResources().getString(R.string.description_target_cal_0)
                ,getResources().getString(R.string.description_target_cal_1)
                ,getResources().getString(R.string.description_target_cal_2)
                ,getResources().getString(R.string.description_target_cal_3)
                ,getResources().getString(R.string.description_target_cal_4)
                ,getResources().getString(R.string.description_target_cal_my_own)} ;

        targetCalObjectManager = TargetCalObjectManager.getInstance();
        index=0;
        while(index< targetCalTitleList.length)
        {
            ObjectForAdapter obj = new ObjectForAdapter(targetCalTitleList[index], targetCalDescriptionList[index]);
            //obj.setName("achievementObj"+Integer.toString(index));
            targetCalObjectManager.addObject(obj);
            targetCalObjectManager.getObject(index).setImageView2(R.drawable.ic_workout_learn_locked_normal);
            index++;
        }
        targetCalObjectManager.getObject(1).setImageView1(R.drawable.ic_circuit_one);
        targetCalObjectManager.getObject(2).setImageView1(R.drawable.ic_circuit_two);
        targetCalObjectManager.getObject(3).setImageView1(R.drawable.ic_circuit_three);
        targetCalObjectManager.getObject(4).setImageView1(R.drawable.ic_circuit_four);
        targetCalObjectManager.getObject(5).setImageView1(R.drawable.ic_circuit_five);
        targetCalObjectManager.getObject(6).setImageView1(R.drawable.ic_achievement_workout_collector);
        targetCalChosen=targetCalObjectManager.getSize();
//        for(int i=0;i<targetCalObjectManager.getSize();i++)
//        {
//            targetCalObjectManager.getObject(i).setImageView2(R.drawable.ic_workout_learn_locked_normal);
////            Log.e("LOL", "typeObjectManager.getObject(i).getImageView2() = "
////                    + targetCalObjectManager.getObject(i).getImageView2());
//        }

        for(int i=0;i<typeObjectManager.getSize();i++)
        {
            if(i!=1)
                typeObjectManager.getObject(i).setAvailable(false);
            else
                typeObjectManager.getObject(i).setAvailable(true);
        }
        getStoredData();
    }

    private void getStoredData()
    {
        SharedPreferences sharedPref = getActivity().getSharedPreferences(
                getString(R.string.saved_data_workout_page), Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPref.edit();
//        editor.putInt(getString(R.string.saved_high_score), newHighScore);
//        editor.commit();

        currentCal = Double.parseDouble(sharedPref.getString(getString(R.string.saved_current_cal)
                , Double.toString(0)));
//        Log.e("LOL","currentCal="+currentCal);
        targetCal = Double.parseDouble(sharedPref.getString(getString(R.string.saved_target_cal)
                , Double.toString(400)));
//        Log.e("LOL","targetCal="+targetCal);
        typeChosen = Integer.parseInt(sharedPref.getString(getString(R.string.saved_type_chosen)
                , Integer.toString(typeObjectManager.getSize())));
//        Log.e("LOL","typeChosen="+typeChosen);
        targetCalChosen = Integer.parseInt(sharedPref.getString(getString(R.string.saved_target_cal_chosen)
                , Integer.toString(targetCalObjectManager.getSize())));
//        Log.e("LOL","targetCalChosen="+targetCalChosen);
        workoutMorning = Boolean.parseBoolean(sharedPref.getString(getString(R.string.saved_workout_morning)
                , Boolean.toString(false)));
//        Log.e("LOL","workoutMorning="+workoutMorning);
        workoutAfternoon = Boolean.parseBoolean(sharedPref.getString(getString(R.string.saved_workout_afternoon)
                , Boolean.toString(false)));
//        Log.e("LOL","workoutAfternoon="+workoutAfternoon);
        workoutEvening = Boolean.parseBoolean(sharedPref.getString(getString(R.string.saved_workout_evening)
                , Boolean.toString(false)));
//        Log.e("LOL","workoutEvening="+workoutEvening);
        workoutNight = Boolean.parseBoolean(sharedPref.getString(getString(R.string.saved_workout_night)
                , Boolean.toString(false)));
//        Log.e("LOL","workoutNight="+workoutNight);

        String defaultTargetCalAvailable= "true,true,true,true,true,true,";
        String defaultTypeAvailable= "true,true,false,false,false,false,false,false,false,false,false,false,false,";
        String[] typeAvailable = sharedPref.getString(getString(R.string.saved_type_available),defaultTypeAvailable).split(",");
//        Log.e("LOL","type.length = "+typeAvailable.length);
        for (int i = 0; i < typeObjectManager.getSize(); i++) {
//            Log.e("LOL","typeAvailable["+i+"] = "+typeAvailable[i]);
            typeObjectManager.getObject(i).setAvailable(Boolean.parseBoolean(typeAvailable[i]));
        }

        String[] targetCalAvailable = sharedPref.getString(getString(R.string.saved_target_cal_available),defaultTargetCalAvailable).split(",");
//        Log.e("LOL","targetCal.length = "+targetCalAvailable.length);
        for (int i = 0; i < targetCalAvailable.length; i++) {
//            Log.e("LOL","targetCalAvailable["+i+"] = "+targetCalAvailable[i]);
            targetCalObjectManager.getObject(i).setAvailable(Boolean.parseBoolean(targetCalAvailable[i]));
        }

        int tempWeight=Integer.parseInt(sharedPref.getString(getString(R.string.saved_weight),"60"));
        int tempStepLength=Integer.parseInt(sharedPref.getString(getString(R.string.saved_step_length),"70"));
        SettingPageFrag.setWeight(tempWeight);
        SettingPageFrag.setStepLength(tempStepLength);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        storeData();
    }

    public void storeData()
    {
        SharedPreferences sharedPref = getActivity().getSharedPreferences(
                getString(R.string.saved_data_workout_page), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(getString(R.string.saved_current_cal), Double.toString(currentCal));
        editor.putString(getString(R.string.saved_target_cal), Double.toString(targetCal));
        editor.putString(getString(R.string.saved_type_chosen), Integer.toString(typeChosen));
        editor.putString(getString(R.string.saved_target_cal_chosen), Integer.toString(targetCalChosen));
        editor.putString(getString(R.string.saved_workout_morning), Boolean.toString(workoutMorning));
        editor.putString(getString(R.string.saved_workout_afternoon), Boolean.toString(workoutAfternoon));
        editor.putString(getString(R.string.saved_workout_evening), Boolean.toString(workoutEvening));
        editor.putString(getString(R.string.saved_weight), Integer.toString(SettingPageFrag.getWeight()));
        editor.putString(getString(R.string.saved_step_length), Integer.toString(SettingPageFrag.getStepLength()));

        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < typeObjectManager.getSize(); i++) {
            sb2.append(Boolean.toString(typeObjectManager.getObject(i).isAvailable())).append(",");
        }
//        Log.e("LOL","store type = "+sb2.toString());
        editor.putString(getString(R.string.saved_type_available), sb2.toString());

        StringBuilder sb3 = new StringBuilder();
        for (int i = 0; i < targetCalObjectManager.getSize(); i++) {
            sb3.append(Boolean.toString(targetCalObjectManager.getObject(i).isAvailable())).append(",");
        }
//        Log.e("LOL","store targetCal = "+sb3.toString());
        editor.putString(getString(R.string.saved_target_cal_available), sb3.toString());

        editor.commit();

    }

    public int getProgress()
    {
        return circularProgressBar.getProgress();
    }
    public void setProgress()
    {
        circularProgressBar.setProgress((int)(100 * currentCal / targetCal));
        String temp = String.format("%.2f",100 * currentCal / targetCal);
        circularProgressBar.setTitle(temp + " %");
        if(targetCal>currentCal)
        circularProgressBar.setSubTitle(Integer.toString((int)(targetCal-currentCal)) + " cal to go");
    else
        circularProgressBar.setSubTitle(Integer.toString((int)(currentCal-targetCal)) + " cal extra today");
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e("CanhBX", "onCreateView called");
        rootView = (ViewGroup) inflater.inflate(
                R.layout.workout_page_layout, container, false);
        if(targetCalObjectManager.getSize()==0&&typeObjectManager.getSize()==0)
            initialize();
        objectManager=WorkoutTypeObjectManager.getInstance();
        initializeExercisesList();
//        this.setButton(rootView);
        btnChooseType = (Button) rootView.findViewById(R.id.buttonChooseWorkoutType);
        btnTargetCalories = (Button) rootView.findViewById(R.id.buttonTargetCal);
        btnMusic = (Button) rootView.findViewById(R.id.buttonMusic);
        btnStartWorkout = (Button) rootView.findViewById(R.id.buttonStartWorkout);

        btnChooseType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("CanhBX", "btnChooseType.setOnClickListener called");
                MainActivity.getInstance().setChooseTypeDialog();
                storeData();
            }
        });

        btnTargetCalories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("CanhBX", "btnTargetCalories.setOnClickListener called");
//                targetCalDialog = TargetCalDialog.getInstance(myContext);
//                try
//                {
//                    targetCalDialog.show();
//                }
//                catch (Exception e)
//                {
//                    Log.e("LOL","Can not show targetCalDialog");
//                }
//                TypePageObjectManager.getInstance().getObject(9).setAvailable(true);
                MainActivity.getInstance().setChooseTargetCalDialog();
                storeData();
            }
        });
        btnMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("CanhBX", "btnMusic.setOnClickListener called");
                try {
                    Intent intent = new Intent(MediaStore.INTENT_ACTION_MEDIA_PLAY_FROM_SEARCH);
                    startActivity(intent);
                } catch (Exception e) {
                    Log.e("LOL", "No mucsic player in this phone.");
                }
            }
        });
        btnStartWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Update data for achievement frag
                Log.e("CanhBX", "btnStartWorkout.setOnClickListener called");
                Calendar c;
                c = Calendar.getInstance();
                int tempH = c.get(Calendar.HOUR_OF_DAY);
                if(4<=tempH&&tempH>=7)
                    setWorkoutMorning(true);
                if(12<=tempH&&tempH>=16)
                    setWorkoutAfternoon(true);
                if(20<=tempH&&tempH>=23)
                    setWorkoutEvening(true);
                if(0<=tempH&&tempH>=4)
                    setWorkoutNight(true);

                android.hardware.Camera cam = CardboardActivity.getCamera();
                if (cam != null)
                    cam.release();
                Intent intent = new Intent(getContext(), CardboardActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                storeData();
            }
        });
//        Log.e("LOL", "WorkoutPageFrag : Before currentCal = " + currentCal);
//        Log.e("LOL", "WorkoutPageFrag : Before calories = " + CardboardActivity.getCalories());
//        currentCal+=CardboardActivity.getCalories();
//        setCurrentCal(getCurrentCal()+CardboardActivity.getCalories());
//        CardboardActivity.setCalories(0);
//        Log.e("LOL", "WorkoutPageFrag : After currentCal = " + currentCal);
//        Log.e("LOL","WorkoutPageFrag : After calories = " + CardboardActivity.getCalories());
        unlockType();
        updateButtons();

        circularProgressBar = (CircularProgressBar)rootView.findViewById(R.id.workout_page_cirProBar);
        setProgress();
//        double temp = 100 * currentCal / targetCal;
//        Log.e("LOL", "progress = " + temp);
//        Log.e("LOL", "BEFORE circularProgressBar.getProgress() = " + circularProgressBar.getProgress());
//        circularProgressBar.setTimeLeft(temp);
//        Log.e("LOL", "AFTER circularProgressBar.getProgress() = " + circularProgressBar.getProgress());

        return rootView;
    }

//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        try {
//            targetCalDialog.dismiss();
//            typeDialog.dismiss();
//            Log.e("LOL", "Dismiss dialogs.");
//        } catch (Exception e)
//        {
//            Log.e("LOL","Can not dismiss dialogs.");
//        }
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        try {
//            targetCalDialog.dismiss();
//            typeDialog.dismiss();
//            Log.e("LOL", "Dismiss dialogs.");
//        } catch (Exception e)
//        {
//            Log.e("LOL","Can not dismiss dialogs.");
//        }
//    }

    private void unlockType()
    {
        getStoredData();
        for( int i =0;i<typeObjectManager.getSize();i++)
            if(TrackPageFrag.getDay()==14*i)
            {
                typeObjectManager.getObject(i).setAvailable(true);
//                TypeObjectManager.getInstance().getObject(i).setAvailable(true);
            }
        storeData();
    }

    public static double getCurrentCal() {
        return currentCal;
    }

    public void setCurrentCal(double currentCal) {
        WorkoutPageFrag.currentCal = currentCal;
        storeData();
    }

    public static double getTargetCal() {
        return targetCal;
    }

    public void setTargetCal(double targetCal) {
        WorkoutPageFrag.targetCal = targetCal;
        storeData();
    }

    public static int getTypeChosen() {
        return typeChosen;
    }

    public void setTypeChosen(int typeChosen) {
        WorkoutPageFrag.typeChosen = typeChosen;
        storeData();
    }

    public static int getTargetCalChosen() {
        return targetCalChosen;
    }

    public void setTargetCalChosen(int targetCalChosen) {
        WorkoutPageFrag.targetCalChosen = targetCalChosen;
        switch (targetCalChosen)
        {
            //case 0 : is the title of the listView
            case 1:
                targetCal=400;
                break;
            case 2:
                targetCal=500;
                break;
            case 3:
                targetCal=600;
                break;
            case 4:
                targetCal=700;
                break;
            case 5:
                targetCal=800;
                break;
            default:
                targetCal=1;
                break;
        }
        storeData();
    }
    public void setTypeAvailable(int position, boolean isAvailable)
    {
        typeObjectManager.getObject(position).setAvailable(isAvailable);
        storeData();
    }
    public static boolean getTypeAvailable(int position)
    {
        return typeObjectManager.getObject(position).isAvailable();
    }
    public void setTargetCalAvailable(int position, boolean isAvailable)
    {
        targetCalObjectManager.getObject(position).setAvailable(isAvailable);
        storeData();
    }
    public static boolean getTargetCalAvailable(int position)
    {
        return targetCalObjectManager.getObject(position).isAvailable();
    }
    public void updateButtons()
    {
        if(targetCalChosen!=targetCalObjectManager.getSize())
        {
            String title=null;
            try {
                //Normal case
                title = targetCalObjectManager.getObject(targetCalChosen).getTitle();
            }
            catch (Exception e)
            {
                //Choose your own target
                title=getString(R.string.target_cal_my_own_title)+" "+targetCal+" Cal";
            }
            btnTargetCalories.setText(title);
            btnTargetCalories.setTextSize(10f);
        }
        if(typeChosen!= typeObjectManager.getSize())
        {
            btnChooseType.setText(typeObjectManager.getObject(typeChosen).getTitle());
            btnChooseType.setTextSize(10f);
        }
    }

    public static boolean isWorkoutNight() {
        return workoutNight;
    }

    public void setWorkoutNight(boolean workoutNight) {
        WorkoutPageFrag.workoutNight = workoutNight;
        storeData();
    }

    public static boolean isWorkoutMorning() {
        return workoutMorning;
    }

    public void setWorkoutMorning(boolean workoutMorning) {
        WorkoutPageFrag.workoutMorning = workoutMorning;
        storeData();
    }

    public static boolean isWorkoutAfternoon() {
        return workoutAfternoon;
    }

    public void setWorkoutAfternoon(boolean workoutAfternoon) {
        WorkoutPageFrag.workoutAfternoon = workoutAfternoon;
        storeData();
    }

    public static boolean isWorkoutEvening() {
        return workoutEvening;
    }

    public void setWorkoutEvening(boolean workoutEvening) {
        WorkoutPageFrag.workoutEvening = workoutEvening;
        storeData();
    }

    private void initializeExercisesList()
    {
        if(objectManager.getSize()!=0)
            return;
        objectManager.addObject(new WorkoutTypeObject(getResources().getString(R.string.workout_exercise_0),20,12, 20,3,4,6));
        objectManager.addObject(new WorkoutTypeObject(getResources().getString(R.string.workout_exercise_1),600,180, 60,4,4,7));
        objectManager.addObject(new WorkoutTypeObject(getResources().getString(R.string.workout_exercise_2),900,120, 60,6,4,7));
        objectManager.addObject(new WorkoutTypeObject(getResources().getString(R.string.workout_exercise_3),300,30, 60,8,4,6));
        objectManager.addObject(new WorkoutTypeObject(getResources().getString(R.string.workout_exercise_4), 300, 60,60,6,3,3));
        objectManager.addObject(new WorkoutTypeObject(getResources().getString(R.string.workout_exercise_5), 180, 75, 60, 12,3,6));
        objectManager.addObject(new WorkoutTypeObject(getResources().getString(R.string.workout_exercise_6),900,180, 180,3,3,7));
        objectManager.addObject(new WorkoutTypeObject(getResources().getString(R.string.workout_exercise_7),900,180, 30,6,4,9));
        objectManager.addObject(new WorkoutTypeObject(getResources().getString(R.string.workout_exercise_8),600,120, 30,8,4,9));
        objectManager.addObject(new WorkoutTypeObject(getResources().getString(R.string.workout_exercise_9), 180, 10, 20, 8, 5, 8));

    }
}
