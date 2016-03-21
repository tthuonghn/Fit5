package com.techkids.thangduong.fit5.CommonView.TrackPage;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.techkids.thangduong.fit5.R;

/**
 * Created by ThangDuong on 05-Mar-16.
 */
public class TrackPageDialog extends Dialog implements View.OnClickListener {

    public Activity c;
    public Dialog d;
    public Button btn_ok;

    public TrackPageDialog(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.track_page_dialog_adapter);
        btn_ok = (Button) findViewById(R.id.btn_ok);
        btn_ok.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        dismiss();
    }
}