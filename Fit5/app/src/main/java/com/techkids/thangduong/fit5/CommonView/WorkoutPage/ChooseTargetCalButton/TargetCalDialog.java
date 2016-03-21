package com.techkids.thangduong.fit5.CommonView.WorkoutPage.ChooseTargetCalButton;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SeekBar;

import com.techkids.thangduong.fit5.R;
import com.techkids.thangduong.fit5.CommonView.WorkoutPage.WorkoutPageFrag;


/**
 * Created by ThangDuong on 05-Mar-16.
 */
//public class TargetCalDialog extends Dialog implements View.OnClickListener {
//
//    public Activity c;
//    public Dialog d;
//    ListView dialogListView;
//    TypeListViewAdapter mAdapter = null;
//    static TargetCalObjectManager objManager =null;
//    public static String [] titleList={"","ONE","TWO","THREE","FOUR","FIVE"};
//    public static String [] descriptionList={"","20 minutes ~1000 calories","40 minutes ~2500 calories"
//            ,"60 minutes ~4000 calories","80 minutes ~5500 calories"
//            ,"60 minutes ~7000 calories"} ;
//    static TargetCalDialog mTargetCalDialog = null;
//    private TargetCalDialog(Activity a) {
//        super(a);
//        // TODO Auto-generated constructor stub
////        titleList = new String[]{"","ONE","TWO","THREE","FOUR","FIVE"};
////        descriptionList = new String[]{"","20 minutes ~1000 calories","40 minutes ~2500 calories"
////                ,"60 minutes ~4000 calories","80 minutes ~5500 calories"
////                ,"60 minutes ~7000 calories"};
//        mTargetCalDialog =this;
//        this.c = a;
//        objManager = TargetCalObjectManager.getInstance();
//        int index=0;
//        while(index<titleList.length)
//        {
//            ObjectForAdapter obj = new ObjectForAdapter(titleList[index], descriptionList[index]);
//            //obj.setName("achievementObj"+Integer.toString(index));
//            objManager.addObject(obj);
//            index++;
//        }
//    }
//    public static TargetCalDialog getInstance(Activity a)
//    {
//        if(mTargetCalDialog ==null)
//            mTargetCalDialog = new TargetCalDialog(a);
//
//        return mTargetCalDialog;
//    }
//    public static String [] titleGroupList ={"CIRCUIT"};
//    public static int[] titleGroupIndex ={0};
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//
//        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        setContentView(R.layout.workout_page_targetcal_dialog_adapter);
//
//        Bundle mBundle = new Bundle();
//        mBundle.putStringArray("titleGroupList", titleGroupList);
//        mBundle.putIntArray("titleGroupIndex", titleGroupIndex);
//
//        dialogListView = (ListView) findViewById(R.id.workout_dialog_listview);
//        dialogListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                WorkoutPageFrag.setTypeChosen(position);
//            }
//        });
//        mAdapter = new TypeListViewAdapter(getContext(),mBundle);
//        dialogListView.setAdapter(mAdapter);
//    }
//
//    @Override
//    public void onClick(View v) {
//        dismiss();
//    }
//}

/**
 * Created by ThangDuong on 05-Mar-16.
 */
public class TargetCalDialog extends Dialog implements View.OnClickListener {

    public Context c;
    public Dialog d;
    ListView dialogListView;
    TargetCalListViewAdapter mAdapter = null;
    static TargetCalObjectManager objManager = null;
    static TargetCalDialog mTargetCalDialog = null;
    SeekBar mySeekBar;
    public TargetCalDialog(Context a) {
        super(a);
        // TODO Auto-generated constructor stub
        mTargetCalDialog =this;
        this.c = a;
    }
//    public static TargetCalDialog getInstance(Context a)
//    {
//        if(mTargetCalDialog ==null)
//            mTargetCalDialog = new TargetCalDialog(a);
//
//        return mTargetCalDialog;
//    }
    public static String [] titleGroupList ={"TARGET CALORIES PER DAY"};
    public static int[] titleGroupIndex ={0};
    int customTargetCal;
    TargetCalObjectManager targetCalObjectManager;
    WorkoutPageFrag workoutPageFrag;
    Bundle mBundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.workout_page_targetcal_dialog_adapter);

        mBundle = new Bundle();
        mBundle.putStringArray("titleGroupList", titleGroupList);
        mBundle.putIntArray("titleGroupIndex", titleGroupIndex);

        dialogListView = (ListView) findViewById(R.id.workout_dialog_listview);
        dialogListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                WorkoutPageFrag.getInstance(c).setTargetCalChosen(position);
            }
        });
        mAdapter = new TargetCalListViewAdapter(getContext(),mBundle);
        dialogListView.setAdapter(mAdapter);
        workoutPageFrag=WorkoutPageFrag.getInstance(c);

        dialogListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                workoutPageFrag.setTargetCalChosen(position);
                workoutPageFrag.updateButtons();
                workoutPageFrag.setProgress();
                dismiss();
            }
        });

        mySeekBar = (SeekBar)findViewById(R.id.workout_dialog_seekBar);
        mySeekBar.setScaleY(2f);
        targetCalObjectManager=TargetCalObjectManager.getInstance();
        mySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                customTargetCal = mySeekBar.getProgress() * 2000/100;
                targetCalObjectManager.getObject(targetCalObjectManager.getSize()-1)
                        .setDescription(customTargetCal/8 + " minutes- ~"+customTargetCal+" CAL");

//                mAdapter.notifyDataSetChanged();
                mAdapter = new TargetCalListViewAdapter(getContext(),mBundle);
                dialogListView.setAdapter(mAdapter);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                WorkoutPageFrag.getInstance(c).setTargetCalChosen(customTargetCal);
                WorkoutPageFrag.getInstance(c).setTargetCal(customTargetCal);
                Log.e("LOL", "targetCal = " + WorkoutPageFrag.getInstance(c).getTargetCal());
                WorkoutPageFrag.getInstance(c).updateButtons();
                WorkoutPageFrag.getInstance(c).setProgress();

            }
        });
    }

    @Override
    public void onClick(View v) {
        dismiss();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    public int getCustomTargetCal() {
        return customTargetCal;
    }
}