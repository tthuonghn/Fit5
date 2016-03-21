package com.techkids.thangduong.fit5.CardboardView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.techkids.thangduong.fit5.R;

import java.util.List;

/**
 * Created by ThangDuong on 28-Feb-16.
 */
public class MyListAdapter extends BaseAdapter {

    /**
     * this is our own collection of data, can be anything we want it to be as long as we get the
     * abstract methods implemented using this data and work on this data (see getter) you should
     * be fine
     */
    private List<String> mData;
    private Context mContext;

    /**
     * our ctor for this adapter, we'll accept all the things we need here
     *
     * @param mData
     */
    public MyListAdapter(final Context context, final List<String> mData) {
        this.mData = mData;
        this.mContext = context;
    }

    public List<String> getData() {
        return mData;
    }
    MyViewHolder viewHolder;
// implement all abstract methods here
@Override
public View getView(int position, View v, ViewGroup parent) {
    // Keeps reference to avoid future findViewById()

    if (v == null) {
        LayoutInflater li = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = li.inflate(R.layout.cardboard_activity_menu_adapter_layout, parent, false);

        viewHolder = new MyViewHolder();
        viewHolder.textView1 = (TextView) v.findViewById(R.id.textView1);

        v.setTag(viewHolder);
    } else {
        viewHolder = (MyViewHolder) v.getTag();
    }
    viewHolder.textView1.setText(mData.get(position));


    return v;
}
//    public void changeBGitem(int position)
//    {
//        viewHolder.textView1.setBackgroundColor(Color.WHITE);
//    }

    static class MyViewHolder {
        public TextView textView1;
    }


    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mData.size();
    }
    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }
}
