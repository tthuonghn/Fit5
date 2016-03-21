package com.techkids.thangduong.fit5.CommonView.SettingPage;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import com.techkids.thangduong.fit5.CommonView.AchievementsPage.AchievementPageObjectManager;
import com.techkids.thangduong.fit5.CommonView.AchievementsPage.AchievementsPageFrag;
import com.techkids.thangduong.fit5.CommonView.TrackPage.TrackPageFrag;
import com.techkids.thangduong.fit5.CommonView.TypePage.TypePageObjectManager;
import com.techkids.thangduong.fit5.CommonView.WorkoutPage.ChooseTargetCalButton.TargetCalObjectManager;
import com.techkids.thangduong.fit5.CommonView.WorkoutPage.ChooseTypeButton.TypeObjectManager;
import com.techkids.thangduong.fit5.CommonView.WorkoutTypeObject;
import com.techkids.thangduong.fit5.CommonView.WorkoutTypeObjectManager;
import com.techkids.thangduong.fit5.R;


/**
 * Created by ThangDuong on 02-Mar-16.
 */
public class SettingPageFrag extends Fragment {
    ViewGroup rootView;
    static SettingPageFrag settingPageFrag;
    Switch showInfoSwitch;
    static boolean showInfo = true;
    SeekBar weightSeekBar;
    SeekBar stepLengthSeekBar;
    TextView weightTxtView;
    TextView stepLengthTxtView;
    static int weight=60;
    static int stepLength=70;
    Button resetBtn;

    public SettingPageFrag()
    {
        settingPageFrag=this;
    }

    public static SettingPageFrag getInstance()
    {
        if(settingPageFrag==null)
            settingPageFrag=new SettingPageFrag();
        return settingPageFrag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(
                R.layout.setting_page_layout, container, false);
        weightSeekBar=(SeekBar)rootView.findViewById(R.id.setting_page_weight_seekBar);
        stepLengthSeekBar=(SeekBar)rootView.findViewById(R.id.setting_page_step_length_seekBar);
        weightTxtView=(TextView)rootView.findViewById(R.id.setting_page_weight);
        stepLengthTxtView=(TextView)rootView.findViewById(R.id.setting_page_step_length);
        resetBtn=(Button)rootView.findViewById(R.id.setting_page_reset_button);
        weight = weightSeekBar.getProgress()*2;
        weightTxtView.setText(weight+" kg");
        stepLength = stepLengthSeekBar.getProgress()*2;
        stepLengthTxtView.setText(stepLength+" cm");
        weightSeekBar.setScaleY(2f);
        stepLengthSeekBar.setScaleY(2f);

        showInfoSwitch = (Switch)rootView.findViewById(R.id.show_info_switch);
        showInfoSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                showInfo=isChecked;
                if(isChecked)
                    Log.e("LOL","checked");
                else
                    Log.e("LOL","NOT checked");
            }
        });

        weightSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                weight = progress * 2;
                weightTxtView.setText(weight + " kg");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        stepLengthSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                stepLength=progress*2;
                stepLengthTxtView.setText(stepLength+" cm");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weight=60;
                stepLength=70;
                showInfo=true;
                weightSeekBar.setProgress(30);
                stepLengthSeekBar.setProgress(35);
                showInfoSwitch.setChecked(showInfo);
                try {
                    AchievementPageObjectManager.reset();
                }
                catch (Exception e)
                {}
                try {
                    TypePageObjectManager.reset();
                }
                catch (Exception e)
                {}
                try {
                    TypeObjectManager.reset();
                }
                catch (Exception e)
                {}
                try {
                    TargetCalObjectManager.reset();
                }
                catch (Exception e)
                {}
                try {
                    WorkoutTypeObjectManager.reset();
                }
                catch (Exception e)
                {}
                TrackPageFrag.reset();

                SharedPreferences sharedPref = getActivity().getSharedPreferences(
                        getString(R.string.saved_data_achievement_page), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.clear();
                editor.commit();
                sharedPref = getActivity().getSharedPreferences(
                        getString(R.string.saved_data_track_page), Context.MODE_PRIVATE);
                editor = sharedPref.edit();
                editor.clear();
                editor.commit();
                sharedPref = getActivity().getSharedPreferences(
                        getString(R.string.saved_data_type_page), Context.MODE_PRIVATE);
                editor = sharedPref.edit();
                editor.clear();
                editor.commit();
                sharedPref = getActivity().getSharedPreferences(
                        getString(R.string.saved_data_workout_page), Context.MODE_PRIVATE);
                editor = sharedPref.edit();
                editor.clear();
                editor.commit();
            }
        });
        return rootView;
    }
    public void getStoredData()
    {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        storeData();
    }

    public void storeData()
    {

    }

    public static boolean isShowInfo() {
        return showInfo;
    }

    public static int getWeight() {
        return weight;
    }

    public static double getStepLengthInMeter() {
        return (double)stepLength/100;
    }

    public static int getStepLength() {
        return stepLength;
    }

    public static void setWeight(int weight) {
        SettingPageFrag.weight = weight;
    }

    public static void setStepLength(int stepLength) {
        SettingPageFrag.stepLength = stepLength;
    }
}
