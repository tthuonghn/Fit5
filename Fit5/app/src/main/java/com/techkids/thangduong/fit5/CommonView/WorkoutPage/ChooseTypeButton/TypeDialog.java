package com.techkids.thangduong.fit5.CommonView.WorkoutPage.ChooseTypeButton;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.techkids.thangduong.fit5.R;
import com.techkids.thangduong.fit5.CommonView.WorkoutPage.WorkoutPageFrag;


/**
 * Created by ThangDuong on 05-Mar-16.
 */
public class TypeDialog extends Dialog implements View.OnClickListener {

    private Context c;
    public Dialog d;
    ListView dialogListView;
    TypeListViewAdapter mAdapter = null;

    static TypeDialog mTypeDialog = null;
    public TypeDialog(Context a) {
        super(a);
        // TODO Auto-generated constructor stub

        mTypeDialog =this;
        this.c = a;
    }
//    public static TypeDialog getInstance(Context a)
//    {
//        c = a;
//        if(mTypeDialog ==null)
//            mTypeDialog = new TypeDialog(a);
//
//        return mTypeDialog;
//    }
    public static String [] titleGroupList ={"GENERAL","SPECIAL","MY OWN"};
    public static int[] titleGroupIndex ={0,6,12};
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.workout_page_type_dialog_adapter);

        Bundle mBundle = new Bundle();
        mBundle.putStringArray("titleGroupList", titleGroupList);
        mBundle.putIntArray("titleGroupIndex", titleGroupIndex);

        dialogListView = (ListView) findViewById(R.id.workout_dialog_listview);
        dialogListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                WorkoutPageFrag.getInstance(c).setTypeChosen(position);
            }
        });
        mAdapter = new TypeListViewAdapter(getContext(),mBundle);
        dialogListView.setAdapter(mAdapter);
        dialogListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (TypeObjectManager.getInstance().getObject(position).isAvailable()) {
                    WorkoutPageFrag.getInstance(c).setTypeChosen(position);
                    WorkoutPageFrag.getInstance(c).updateButtons();
                    dismiss();
                } else
                    Toast.makeText(getContext(), "You need to unlock this exercise first.", Toast.LENGTH_SHORT).show();
            }
        });
//        unlockType();
    }

    @Override
    public void onClick(View v) {
        dismiss();
    }
}