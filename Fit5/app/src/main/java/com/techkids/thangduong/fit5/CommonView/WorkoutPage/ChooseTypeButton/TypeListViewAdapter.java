package com.techkids.thangduong.fit5.CommonView.WorkoutPage.ChooseTypeButton;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.techkids.thangduong.fit5.R;


/**
 * Created by ThangDuong on 03-Mar-16.
 */
public class TypeListViewAdapter extends BaseAdapter {
    Context context;
    Bundle bundleData;
    String [] titleList;
    int [] imageId;
    int [] imageId2;
    String [] titleGroupList;
    int [] titleGroupIndex;
    String [] descriptionList;
    private static LayoutInflater inflater=null;
    TypeObjectManager objManager;
    public TypeListViewAdapter(Context context, Bundle bundleInput) {
        // TODO Auto-generated constructor stub
        this.context =context;
        bundleData = bundleInput;
//        this.targetCalTitleList = bundleData.getStringArray("targetCalTitleList");
//        imageId= bundleData.getIntArray("imageList");
//        titleGroupList = bundleData.getStringArray("titleGroupList");
//        titleGroupIndex = bundleData.getIntArray("titleGroupIndex");
//        targetCalDescriptionList = bundleData.getStringArray("targetCalDescriptionList");

        titleGroupList = bundleData.getStringArray("titleGroupList");
        titleGroupIndex = bundleData.getIntArray("titleGroupIndex");

        objManager = TypeObjectManager.getInstance();

        int size = objManager.getSize();
        titleList = new String[size] ;
        descriptionList = new String[size] ;
        imageId = new int[size] ;
        imageId2 = new int[size] ;

        for(int i =0;i< size;i++)
        {
            titleList[i] = objManager.getObject(i).getTitle();
            descriptionList[i] = objManager.getObject(i).getDescription();
            imageId[i] = objManager.getObject(i).getImageView1();
            imageId2[i] = objManager.getObject(i).getImageView2();
        }
        inflater = ( LayoutInflater ) this.context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return titleList.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView tv;
        ImageView img;
        TextView tv2;
        ImageView img2;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView = null;
            rowView = inflater.inflate(R.layout.workout_page_listview_adapter, null);
            holder.tv = (TextView) rowView.findViewById(R.id.workout_listview_txtview1);
            holder.tv2 = (TextView) rowView.findViewById(R.id.workout_listview_txtview2);
            holder.img = (ImageView) rowView.findViewById(R.id.workout_listview_imgview1);
            holder.img2 = (ImageView) rowView.findViewById(R.id.workout_listview_imgview2);
            holder.tv.setText(titleList[position]);
            holder.tv2.setText(descriptionList[position]);
            holder.img.setImageResource(imageId[position]);
            holder.img2.setImageResource(imageId2[position]);
        for(int i =0;i< titleGroupIndex.length;i++)
        {
            if(position == titleGroupIndex[i])
            {
                rowView = inflater.inflate(R.layout.workout_page_listview_title_adapter, null);
                holder.tv = (TextView) rowView.findViewById(R.id.workout_page_textViewTitle1);
                //holder.img = null;
                holder.tv.setText(titleGroupList[i]);
                objManager.getObject(position).setAvailable(false);
            }
        }

//        holder.img.setImageResource(imageId[position]);
        boolean isAvailable = objManager.getObject(position).isAvailable();

        if(isAvailable)
        {
            holder.img.setVisibility(View.VISIBLE);
            holder.img2.setVisibility(View.INVISIBLE);
        }
        else
        {
            holder.img.setVisibility(View.INVISIBLE);
            holder.img2.setVisibility(View.VISIBLE);
        }

//        rowView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
////                objManager.getObject(position).setAvailable(false);
////                notifyDataSetChanged();
//                Toast.makeText(context, "You Clicked "
//                        + targetCalTitleList[position], Toast.LENGTH_SHORT).show();
//            }
//        });
        return rowView;
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
}
