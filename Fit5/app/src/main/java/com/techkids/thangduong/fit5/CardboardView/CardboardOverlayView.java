/*
 * Copyright 2014 Google Inc. All Rights Reserved.

 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.techkids.thangduong.fit5.CardboardView;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.techkids.thangduong.fit5.R;

/**
 * Contains two sub-views to provide a simple stereo HUD.
 */
public class CardboardOverlayView extends LinearLayout {
    private static final String TAG = CardboardOverlayView.class.getSimpleName();
    private CardboardOverlayEyeView mLeftView;
    private CardboardOverlayEyeView mRightView;
    private AlphaAnimation mTextFadeAnimation;

//    private ArrayList<String> listItem ;
//    private ArrayAdapter<String> adapter;
    private int desiredKcalEachSet;

    private LayoutParams params;
    private Context mContext;
    private AttributeSet mAttributeSet;
    private static int menuPosition;
    private static int colorID;
    public CardboardOverlayView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(HORIZONTAL);
        mContext=context;
        mAttributeSet=attrs;
        menuPosition=0;

        params = new LayoutParams(
            LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1.0f);
        params.setMargins(0, 0, 0, 0);

        mLeftView = new CardboardOverlayEyeView(context, attrs);
        mLeftView.setLayoutParams(params);
        addView(mLeftView);

        mRightView = new CardboardOverlayEyeView(context, attrs);
        mRightView.setLayoutParams(params);
        addView(mRightView);

        // Set some reasonable defaults.
        setDepthOffset(0.016f);
        setColor(Color.rgb(150, 255, 180));
        setVisibility(View.VISIBLE);

        mTextFadeAnimation = new AlphaAnimation(1.0f, 0.0f);
        mTextFadeAnimation.setDuration(5000);
    }
    public void resetViews()
    {
        removeAllViews();
        mLeftView = new CardboardOverlayEyeView(mContext, mAttributeSet);
        mLeftView.setLayoutParams(params);
        addView(mLeftView);

        mRightView = new CardboardOverlayEyeView(mContext, mAttributeSet);
        mRightView.setLayoutParams(params);
        addView(mRightView);
    }

    public void show3DToast(String message) {
        setText(message);
        setTextAlpha(1f);
        mTextFadeAnimation.setAnimationListener(new EndAnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                setTextAlpha(0f);
            }
        });
        startAnimation(mTextFadeAnimation);
    }

    private abstract class EndAnimationListener implements Animation.AnimationListener {
        @Override public void onAnimationRepeat(Animation animation) {}
        @Override public void onAnimationStart(Animation animation) {}
    }

    private void setDepthOffset(float offset) {
        mLeftView.setOffset(offset);
        mRightView.setOffset(-offset);
    }

    public void setText(String text) {
        mLeftView.setText(text);
        mRightView.setText(text);
    }

    private void setTextAlpha(float alpha) {
        mLeftView.setTextViewAlpha(alpha);
        mRightView.setTextViewAlpha(alpha);
    }

    private void setColor(int color) {
        mLeftView.setColor(color);
        mRightView.setColor(color);
    }

    public void setTime(String text) {
        mLeftView.setTime(text);
        mRightView.setTime(text);
    }

    public void setDistance(String text) {
        mLeftView.setDistance(text);
        mRightView.setDistance(text);
    }

    public void setCalories(String text) {
        mLeftView.setCalories(text);
        mRightView.setCalories(text);
    }

    public void setPace(String text) {
        mLeftView.setPace(text);
        mRightView.setPace(text);
    }

    public void setLayoutVisible(int visible)
    {
        mLeftView.setLayoutVisible(visible);
        mRightView.setLayoutVisible(visible);
    }

    public static int getMenuPosition() {
        return menuPosition;
    }

    public static void setMenuPosition(int menuPosition) {
        CardboardOverlayView.menuPosition = menuPosition;
    }

    public final int LEFTVIEW = 1;
    public final int RIGHTVIEW = 0;
    public ListView getMainMenuListView(int whichView) {
        if (whichView == LEFTVIEW)
            return mLeftView.getMainMenuListView();
        else
            return mRightView.getMainMenuListView();
    }

    public int getLayoutVisible()
    {
        return mLeftView.getLayoutVisible();
    }

//    public int getDesiredKcalEachSet() {
//        return desiredKcalEachSet;
//    }

//    public void setDesiredKcalEachSet(int input) {
//        adapter.remove(desiredKcalEachSet + " KCAL set.");
//        this.desiredKcalEachSet = input;
//        adapter.insert(desiredKcalEachSet + " KCAL set.", 0);
//
//    }
    public void setItemBG(int menuPosition,int colorID)
    {
        this.menuPosition=menuPosition;
        this.colorID=colorID;
    }

    public void setTimeLeft(int input)
    {
//        mLeftView.setProgress(input);
//        mRightView.setProgress(input);
        mLeftView.setTimeLeft(input);
        mRightView.setTimeLeft(input);
    }

    public void setProgress(double input)
    {
        mLeftView.setProgress((int) input);
        mRightView.setProgress((int) input);
    }

    public void setName(String strName) {
        mLeftView.setName(strName);
        mRightView.setName(strName);
    }

    public void setCycle(String strCycle) {
        mLeftView.setCycle(strCycle);
        mRightView.setCycle(strCycle);
    }

    public void setActivity(String strActivity)
    {
        mLeftView.setActivity(strActivity);
        mRightView.setActivity(strActivity);
    }

    /**
     * A simple view group containing some horizontally centered text underneath a horizontally
     * centered image.
     *
     * This is a helper class for CardboardOverlayView.
     */
    private class CardboardOverlayEyeView extends ViewGroup {
        //private final ImageView imageView;
        //private final TextView textView;
        private float offset;
        //private final RelativeLayout progressLayout;
        private final RelativeLayout informationLayout;
        private final RelativeLayout menuLayout;

        private RelativeLayout timeLayout;
        private ImageView timeImgView;
        private TextView timeStaticTextView;
        private TextView timeDynamicTextView;

        private RelativeLayout distanceLayout;
        private ImageView distanceImgView;
        private TextView distanceStaticTextView;
        private TextView distanceDynamicTextView;

        private RelativeLayout caloriesLayout;
        private ImageView caloriesImgView;
        private TextView caloriesStaticTextView;
        private TextView caloriesDynamicTextView;

        private RelativeLayout paceLayout;
        private ImageView paceImgView;
        private TextView paceStaticTextView;
        private TextView paceDynamicTextView;

        private RelativeLayout progressBarLayout;
        private ProgressBar circularProgressBar;
        private TextView progressTextView;

        private ListView mainMenuListView;

        private TextView cycleTextView;
        private TextView activityTextView;
        private TextView nameTextView;

        public ListView getMainMenuListView() {
            return mainMenuListView;
        }
        public CardboardOverlayEyeView(Context context, AttributeSet attrs) {
            super(context, attrs);
            /*imageView = new ImageView(context, attrs);
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            imageView.setAdjustViewBounds(true);  // Preserve aspect ratio.
            addView(imageView);

            textView = new TextView(context, attrs);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14.0f);
            textView.setTypeface(textView.getTypeface(), Typeface.BOLD);
            textView.setGravity(Gravity.CENTER);
            textView.setShadowLayer(3.0f, 0.0f, 0.0f, Color.DKGRAY);
            addView(textView);

            progressLayout = new RelativeLayout(context,attrs);
            addView(progressLayout);*/

            menuLayout = new RelativeLayout(context,attrs);
            mainMenuListView = new ListView(context,attrs);
            progressBarLayout = new RelativeLayout(context,attrs);

            circularProgressBar = new ProgressBar(context,attrs,android.R.attr.progressBarStyleHorizontal);
            progressBarLayout.addView(circularProgressBar);
            addView(progressBarLayout);
            progressTextView = generateTextView(progressTextView, "300 s", progressBarLayout, context, attrs);


            cycleTextView = generateTextView(cycleTextView, "Cycle 0"
                    , context, attrs);
            activityTextView = generateTextView(activityTextView, "Ready ?"
                    , context, attrs);
            nameTextView = generateTextView(nameTextView, "For Starter"
                    , context, attrs);
            addView(cycleTextView);
            addView(activityTextView);
            addView(nameTextView);
//            desiredKcalEachSet = 500;
//            listItem = new String[]{desiredKcalEachSet+" KCAL set.","Return."};
//            listItem = new ArrayList<String>();
//            listItem.add(desiredKcalEachSet+" KCAL set.");
//            listItem.add("Return.");

//            adapter = new ArrayAdapter<String>(context
//                    ,R.layout.cardboard_activity_menu_adapter_layout,R.id.textView1);
//            adapter.add(new String(desiredKcalEachSet+" KCAL set."));
//            adapter.add(new String("Return."));
//            mainMenuListView.setAdapter(adapter);

//            menuLayout.addView(mainMenuListView);
//            addView(menuLayout);

            informationLayout = new RelativeLayout(context,attrs);
            initializeInformationLayout(context, attrs);
//            informationLayout.addView(menuLayout);
            addView(informationLayout);
        }

        private void initializeInformationLayout(Context context,AttributeSet attrs)
        {
            timeLayout = new RelativeLayout(context,attrs);
            timeImgView = generateImageView(timeImgView
                    , R.drawable.ic_timer_white_48dp, timeLayout, context, attrs);
            timeStaticTextView = generateTextView(timeStaticTextView, "TIME"
                    , timeLayout, context, attrs);
            timeDynamicTextView = generateTextView(timeDynamicTextView, "00:00.00"
                    , timeLayout, context, attrs);

            distanceLayout = new RelativeLayout(context,attrs);
            distanceImgView = generateImageView(distanceImgView,
                    R.drawable.ic_directions_run_white_48dp, distanceLayout
                    , context, attrs);
            distanceStaticTextView = generateTextView(distanceStaticTextView, "DISTANCE"
                    , distanceLayout, context, attrs);
            distanceDynamicTextView = generateTextView(distanceDynamicTextView, "0.00KM"
                    , distanceLayout, context, attrs);

            caloriesLayout = new RelativeLayout(context,attrs);
            caloriesImgView = generateImageView(caloriesImgView,
                    R.drawable.ic_whatshot_white_48dp, caloriesLayout, context
                    , attrs);
            caloriesStaticTextView = generateTextView(caloriesStaticTextView, "CALORIES"
                    , caloriesLayout, context, attrs);
            caloriesDynamicTextView = generateTextView(caloriesDynamicTextView, "0 KCAL"
                    , caloriesLayout, context, attrs);

            paceLayout = new RelativeLayout(context,attrs);
            paceImgView = generateImageView(paceImgView
                    , R.drawable.ic_flash_on_white_48dp, paceLayout, context, attrs);
            paceStaticTextView = generateTextView(paceStaticTextView, "PACE/KM", paceLayout
                    , context, attrs);
            paceDynamicTextView = generateTextView(paceDynamicTextView, "0:00", paceLayout
                    , context, attrs);


            informationLayout.addView(timeLayout);
            informationLayout.addView(distanceLayout);
            informationLayout.addView(caloriesLayout);
            informationLayout.addView(paceLayout);
//            informationLayout.addView(cycleTextView);
//            informationLayout.addView(activityTextView);
        }

        private TextView generateTextView(TextView txtView,String content,RelativeLayout parentView, Context context,AttributeSet attrs)
        {
            txtView = new TextView(context,attrs);
            txtView.setText(content);
            txtView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12.0f);
            //txtView.setTypeface(txtView.getTypeface(), Typeface.BOLD);
            txtView.setShadowLayer(3.0f, 0.0f, 0.0f, Color.DKGRAY);
            txtView.setGravity(Gravity.CENTER);
            parentView.addView(txtView);
            return txtView;
        }

        private TextView generateTextView(TextView txtView,String content, Context context,AttributeSet attrs)
        {
            txtView = new TextView(context, attrs);
            txtView.setText(content);
            txtView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12.0f);
            //txtView.setTypeface(txtView.getTypeface(), Typeface.BOLD);
            txtView.setShadowLayer(3.0f, 0.0f, 0.0f, Color.DKGRAY);
            txtView.setGravity(Gravity.CENTER);
            return txtView;
        }

        private ImageView generateImageView(ImageView imgView,int ImgSrc,RelativeLayout parentView,Context context,AttributeSet attrs)
        {
            imgView = new ImageView(context, attrs);
            imgView.setImageResource(ImgSrc);
            imgView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            imgView.setAdjustViewBounds(true);  // Preserve aspect ratio.
            parentView.addView(imgView);
            return imgView;
        }

        private void setTime(String strTime) {
            timeDynamicTextView.setText(strTime);
        }

        private void setName(String strName) {
            nameTextView.setText(strName);
        }

        private void setCycle(String strCycle) {
            cycleTextView.setText(strCycle);
        }

        private void setActivity(String strActivity) {
            activityTextView.setText(strActivity);
        }

        private void setDistance(String strDistance) {
            distanceDynamicTextView.setText(strDistance);
        }

        private void setCalories(String strCalories) {
            caloriesDynamicTextView.setText(strCalories);
        }

        private void setProgress(int progress) {
            circularProgressBar.setProgress(progress);
//            circularProgressBar.setTitle(progress+ "%");
        }
        private void setTimeLeft(int timeLeft)
        {
//            String temp = String.format("%.2f",progress);
            progressTextView.setText(timeLeft + " s");
        }

        private void setPace(String strPace) {
            paceDynamicTextView.setText(strPace);
        }

        public void setColor(int color) {
//            imageView.setColorFilter(color);
//            textView.setTextColor(color);
        }

        public void setText(String text) {
            //textView.setText(text);
        }

        public void setTextViewAlpha(float alpha) {
            //textView.setAlpha(alpha);
        }


        public void setOffset(float offset) {
            this.offset = offset;
        }
        public void setItemBG(int menuPosition,int colorID)
        {
            try {
                mainMenuListView.getChildAt(menuPosition).setBackgroundColor(colorID);
            }
            catch (Exception e)
            {
//                Log.e("LOL","setItemBG; menuPosition = "+menuPosition);
//                Log.e("LOL","setItemBG; mainMenuListView.getAdapter().getCount() = "+mainMenuListView.getAdapter().getCount());
            }
        }

        @Override
        protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
            // Width and height of this ViewGroup.
            final int width = right - left;
            final int height = bottom - top;
            /*
            // The size of the image, given as a fraction of the dimension as a ViewGroup. We multiply
            // both width and heading with this number to compute the image's bounding box. Inside the
            // box, the image is the horizontally and vertically centered.
            final float imageSize = 0.12f;

            // The fraction of this ViewGroup's height by which we shift the image off the ViewGroup's
            // center. Positive values shift downwards, negative values shift upwards.
            final float verticalImageOffset = -0.07f;

            // Vertical position of the text, specified in fractions of this ViewGroup's height.
            final float verticalTextPos = 0.52f;

            // Layout ImageView
            float imageMargin = (1.0f - imageSize) / 2.0f;
            float leftMargin = (int) (width * (imageMargin + offset));
            float topMargin = (int) (height * (imageMargin + verticalImageOffset));
            imageView.layout(
                (int) leftMargin, (int) topMargin,
                (int) (leftMargin + width * imageSize), (int) (topMargin + height * imageSize));

            // Layout TextView
            leftMargin = offset * width;
            topMargin = height * verticalTextPos;
            textView.layout(
                (int) leftMargin, (int) topMargin,
                (int) (leftMargin + width), (int) (topMargin + height * (1.0f - verticalTextPos)));*/

            /*textView.layout(0, 0, width, height);
            setColor(Color.WHITE);
            progressLayout.layout(0, 365, width, height);
            progressLayout.bringToFront();*/

            ///////////////////////////////////////////////////////
            // Must declare the layout of each and everyView here//
            // The order of layouts declarations => order show layout//
            ///////////////////////////////////////////////////////



            informationLayout.layout(0,0, width, height);

            timeLayout.layout(width / 6, height/2, width / 2, height/2+height / 4);
            timeImgView.layout(0, height / 20, height / 15, height / 15 + height / 20);
            timeStaticTextView.layout(0, height / 16, width / 3, height / 8);
            timeDynamicTextView.layout(0, height / 9, width / 3, height / 4 + height / 9);

            distanceLayout.layout(width / 6, 11 * height / 16, width / 2, 15*height / 16);
            distanceImgView.layout(0, height / 20, height / 15, height / 15 + height / 20);
            distanceStaticTextView.layout(0, height / 16, width / 3, height / 8);
            distanceDynamicTextView.layout(0, height / 9, width / 3, height / 4 + height / 9);

            caloriesLayout.layout(width / 2, height / 2, 5 * width / 6, height / 2 + height / 4);
            caloriesLayout.setGravity(Gravity.RIGHT);
            caloriesImgView.layout(0, height / 20, height / 15, height / 15 + height / 20);
            caloriesStaticTextView.layout(0, height / 16, width / 3, height / 8);
            caloriesDynamicTextView.layout(0, height / 9, width / 3, height / 4 + height / 9);

            paceLayout.layout( width / 2, 11*height / 16, 5*width/6, 15*height / 16);
            paceLayout.setGravity(Gravity.RIGHT);
            paceImgView.layout(0, height / 20, height / 15, height / 15 + height / 20);
            paceStaticTextView.layout(0, height / 16, width / 3, height / 8);
            paceDynamicTextView.layout(0, height / 9, width / 3, height / 4 + height / 9);

            nameTextView.layout(width/6, 9*height / 32, width / 2, 11 * height / 32);
            cycleTextView.layout(width / 2, 9*height / 32, 5 * width / 6, 11 * height / 32);
//            cycleTextView.setBackgroundColor(Color.CYAN);
            activityTextView.layout(width / 2, 11 * height / 32, 5 * width / 6, 15 * height/32);
//            activityTextView.setBackgroundColor(Color.RED);

            progressBarLayout.layout(width / 8, 13*height / 32, 7 * width / 8, 23*height /32);
//            progressBarLayout.setBackgroundColor(getResources().getColor(R.color.colorAccent));
//            progressBarLayout.setAlpha(0.5f);
//            progressBarLayout.setGravity(Gravity.CENTER);
//            progressBarLayout.setBackgroundColor(Color.BLUE);
            circularProgressBar.layout(0, 0, 3*width / 4, height / 16);
//            circularProgressBar.setBackgroundColor(Color.BLUE);
            progressTextView.layout(0, height / 16, 3 * width / 4, height / 8);
//            progressTextView.setBackgroundColor(Color.RED);
//            setProgress(100);
//            progressTextView.setBackgroundColor(Color.YELLOW);
//            RelativeLayout.LayoutParams layoutParams =
//                    (RelativeLayout.LayoutParams)circularProgressBar.getLayoutParams();
//            layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
//            circularProgressBar.setLayoutParams(layoutParams);
//            circularProgressBar.
//            circularProgressBar.setBackgroundColor(getResources().getColor(R.color.colorPrimaryLight));
            circularProgressBar.setScaleY(3f);
//            circularProgressBar.setBottom(height / 4);
//            setTimeLeft(50);
//            Log.e("LOL", "circularProgressBar.getProgress() = " + circularProgressBar.getProgress());

            setTextColorAndSize(getResources().getColor(R.color.colorPrimaryLight) , 20.0f);

            menuLayout.layout(0, 0, width / 3, height / 2);
//            menuLayout.setBackgroundColor(Color.BLUE);
            mainMenuListView.layout(0, 0, width / 3, height / 2);
//            mainMenuListView.setBackgroundColor(Color.RED);
            setItemBG(menuPosition, colorID);
//            circularProgressBar.setSubTitle("setSubTitle completed");
//            circularProgressBar.setTitle("setTitle 50 %");

            cycleTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,15f );
            activityTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15f);
            nameTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15f);

        }

        private void setTextColorAndSize(int color,float textSize)
        {
            timeStaticTextView.setTextColor(color);
            timeDynamicTextView.setTextColor(color);
            distanceStaticTextView.setTextColor(color);
            distanceDynamicTextView.setTextColor(color);
            caloriesStaticTextView.setTextColor(color);
            caloriesDynamicTextView.setTextColor(color);
            paceStaticTextView.setTextColor(color);
            paceDynamicTextView.setTextColor(color);
            progressTextView.setTextColor(color);
            cycleTextView.setTextColor(color);
            activityTextView.setTextColor(color);
            nameTextView.setTextColor(color);

            timeDynamicTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
            distanceDynamicTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
            caloriesDynamicTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
            paceDynamicTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,textSize );
            progressTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
            cycleTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,textSize );
            activityTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);

//            circularProgressBar.setBackgroundColor(getResources().getColor(R.color.colorPrimaryLight));
//            circularProgressBar.setSubTitleColor(color);
//            circularProgressBar.setTitleColor(color);
        }

        private void setLayoutVisible(int visible)
        {
            informationLayout.setVisibility(visible);
            //mainMenuListView.setVisibility(visible);
        }

        private int getLayoutVisible()
        {
           return informationLayout.getVisibility();
        }
/*
        private void updateAdapter()
        {
            listItem = new String[]{desiredKcalEachSet+50+" KCAL set.","Return."};
//            adapter.notifyDataSetChanged();
            adapter=null;
            adapter = new ArrayAdapter<String>(getContext()
                ,R.layout.cardboard_activity_menu_adapter_layout,R.id.textView1,listItem);
            mainMenuListView.setAdapter(adapter);
        }*/
    }
}
