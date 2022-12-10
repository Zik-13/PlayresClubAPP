package com.example.appforstaff;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptStaff extends BaseAdapter {
    Context context;
    LayoutInflater lInflater;
    ArrayList<Staff> staffs;
    int[] images;
    AdaptStaff(Context context, ArrayList<Staff> staffs,int[] images){
        this.context = context;
        this.staffs = staffs;
        this.images = images;
        lInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return staffs.size();
    }
    @Override
    public Object getItem(int i) {
        return staffs.get(i);
    }
    @Override
    public long getItemId(int i) {
        return i;
    }
    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int i, View vieww, ViewGroup viewGroup) {
        View view = vieww;
        if (view == null) {
            view = lInflater.inflate(R.layout.gridviewlayout, viewGroup,false);
        }
        Staff staff = getStaff(i);
        ImageView imagess = (ImageView) view.findViewById(R.id.imagestaff);
        ((TextView) view.findViewById(R.id.name)).setText(staff.name);
        ((TextView) view.findViewById(R.id.phone)).setText(staff.phone);
        ((TextView) view.findViewById(R.id.email)).setText(staff.email);
       imagess.setImageResource(images[0]);
        return view;
    }
    Staff getStaff(int pos){
        return ((Staff) getItem(pos));
    }
}
