package com.techkids.thangduong.fit5.CommonView.TypePage;

import android.content.Context;
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
public class GridViewAdapter extends BaseAdapter {
    Context context;
//    Bundle titleData;
//    String [] data;
    int [] imageId;
    int [] imageId2;
    String [] titleList;
    String [] descriptionList;
//    int [] titleIndex;
    private static LayoutInflater inflater=null;
    TypePageObjectManager objManager;
    public GridViewAdapter(Context context) {
        // TODO Auto-generated constructor stub
        this.context =context;
//        titleData = targetCalTitleList;
        objManager = TypePageObjectManager.getInstance();
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

//        data = titleData.getStringArray("contentList");
//        imageId=titleData.getIntArray("imageList");
//        targetCalTitleList = titleData.getStringArray("titleGroupList");
//        titleIndex=titleData.getIntArray("titleGroupIndex");
//        targetCalDescriptionList = titleData.getStringArray("targetCalDescriptionList");
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
        View rowView = inflater.inflate(R.layout.type_page_adapter, null);
            holder.tv = (TextView) rowView.findViewById(R.id.txtView1);
            holder.tv2 = (TextView) rowView.findViewById(R.id.txtView2);
            holder.img = (ImageView) rowView.findViewById(R.id.imgView);
            holder.img2 = (ImageView) rowView.findViewById(R.id.imgView2);
            holder.tv.setText(titleList[position]);
            holder.tv2.setText(descriptionList[position]);
            holder.img.setImageResource(imageId[position]);
            holder.img2.setImageResource(imageId2[position]);

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

//        for(int i =0;i<titleGroupIndex.length;i++)
//        {
//            if(position == titleGroupIndex[i])
//            {
//                rowView = inflater.inflate(R.layout.achievements_page_listview_title_adapter, null);
//                holder.tv = (TextView) rowView.findViewById(R.id.textViewTitle1);
//                holder.img = null;
//                holder.tv.setText(targetCalTitleList[i]);
//            }
//        }

//        holder.img.setImageResource(imageId[position]);
//        rowView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                Toast.makeText(context, "You Clicked " + targetCalTitleList[position], Toast.LENGTH_LONG).show();
//            }
//        });
        return rowView;
    }
}
