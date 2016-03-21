package com.techkids.thangduong.fit5.CommonView.TypePage.InfoTypeActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.techkids.thangduong.fit5.R;
import com.techkids.thangduong.fit5.CommonView.WorkoutTypeObject;
import com.techkids.thangduong.fit5.CommonView.WorkoutTypeObjectManager;

import java.util.ArrayList;

public class InfoTypeActivity extends AppCompatActivity {
    WorkoutTypeObjectManager objectManager ;
    TextView methodName;
    TextView warmuplTime;
    TextView normalTime;
    TextView rushTime;
    TextView repeat;
//    ImageView methodImage;
    int workoutTypePosition;
    BarChart barChart;
    WorkoutTypeObject workoutTypeObject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.type_page_info_type_layout);
        objectManager = WorkoutTypeObjectManager.getInstance();
//        initializeObjectManager();
//        methodImage = (ImageView)findViewById(R.id.method_image);
        methodName = (TextView) findViewById(R.id.method_name);
        warmuplTime = (TextView) findViewById(R.id.method_warmup_time);
        normalTime = (TextView) findViewById(R.id.method_normal_time);
        rushTime = (TextView) findViewById(R.id.method_rush_time);
        repeat = (TextView) findViewById(R.id.method_repeat);
        barChart = (BarChart)findViewById(R.id.myBarChart);
//        Log.e("InfoTypeActivity", "String workoutTypePosition = " + intent.getStringExtra("position"));
//        Log.e("InfoTypeActivity","workoutTypePosition = "+workoutTypePosition);
        Intent intent = getIntent();
        workoutTypePosition = Integer.parseInt(intent.getStringExtra("position"));
        workoutTypeObject = objectManager.getObject(workoutTypePosition);
        setupBarChart();

//        methodImage.setImageResource(workoutTypeObject.getImgID());
        methodName.setText(workoutTypeObject.getName());
        warmuplTime.setText(Integer.toString(workoutTypeObject.getWarmupTime())+" seconds warm up.");
        normalTime.setText(Integer.toString(workoutTypeObject.getNormalTimeEachSet())+" seconds fast-walking.");
        rushTime.setText(Integer.toString(workoutTypeObject.getRushTimeEachSet())+" seconds rushing.");
        repeat.setText("Repeat for total " +workoutTypeObject.getRepeat()+" cycles.");
    }


    private void setupBarChart()
    {
        ArrayList<BarEntry> entries = new ArrayList<>();
        float normalIntensity = workoutTypeObject.getNormalIntensity();
        float rushIntensity = workoutTypeObject.getRushIntensity();

        entries.add(new BarEntry(2f, 0));
        for(int i =1;i<2*workoutTypeObject.getRepeat()+1;i++)
        {
            if(i%2==0)
                entries.add(new BarEntry(rushIntensity, i));
            else
                entries.add(new BarEntry(normalIntensity, i));
        }
//        entries.add(new BarEntry(normalIntensity, 1));
//        entries.add(new BarEntry(rushIntensity, 2));
//        entries.add(new BarEntry(normalIntensity, 3));
//        entries.add(new BarEntry(rushIntensity, 4));
//        entries.add(new BarEntry(normalIntensity, 5));
//        entries.add(new BarEntry(rushIntensity, 6));
//        entries.add(new BarEntry(normalIntensity, 7));
//        entries.add(new BarEntry(rushIntensity, 8));
//        entries.add(new BarEntry(normalIntensity, 9));
//        entries.add(new BarEntry(rushIntensity, 10));
//        entries.add(new BarEntry(normalIntensity, 11));
//        entries.add(new BarEntry(rushIntensity, 12));
        entries.add(new BarEntry(2f, 2*workoutTypeObject.getRepeat()+1));

        ArrayList<String> labels = new ArrayList<String>();
        for(int i =0;i<2*workoutTypeObject.getRepeat()+2;i++)
            labels.add("");
//        labels.add("");
//        labels.add("");
//        labels.add("");
//        labels.add("");
//        labels.add("");
//        labels.add("");
//        labels.add("");
//        labels.add("");
//        labels.add("");
//        labels.add("");
//        labels.add("");
//        labels.add("");
//        labels.add("");

        BarDataSet dataset = new BarDataSet(entries, "Intensity");
        dataset.setColors(ColorTemplate.LIBERTY_COLORS);

        BarData data = new BarData(labels, dataset);

        barChart.setData(data);
        barChart.setDescription("");
//        barChart.setNoDataTextDescription("");
        barChart.setTouchEnabled(false);
        barChart.setDragEnabled(false);
        barChart.setScaleXEnabled(false);
        barChart.setScaleYEnabled(false);
        barChart.setPinchZoom(false);
        barChart.setDoubleTapToZoomEnabled(false);
//        barChart.setBorderColor(Color.TRANSPARENT);
//        barChart.setDrawBarShadow(true);
        barChart.setDrawGridBackground(true);
//        barChart.setGridBackgroundColor(Color.TRANSPARENT);
        barChart.setDrawBorders(true);
        barChart.setDrawHighlightArrow(false);
    }

}
