package com.example.quanlythuchi.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.quanlythuchi.R;
import com.example.quanlythuchi.chi;
import com.example.quanlythuchi.thu;

import java.util.List;

public class ChiAdapter extends BaseAdapter {
    private chi context;
    private int layout;
    private List<Thu> thuList;

    public ChiAdapter(chi context, int layout, List<Thu> thuList) {
        this.context = context;
        this.layout = layout;
        this.thuList = thuList;
    }

    @Override
    public int getCount() {
        return thuList.size();
    }

    @Override
    public Thu getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    private class ViewHolder{
        TextView txtTen, txtsotien;
        ImageButton btnxoa, btnsua;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ChiAdapter.ViewHolder holder;
        if(convertView == null){
            holder = new ChiAdapter.ViewHolder();
            //LayoutInflater inflater = LayoutInflater.from(context);
            // LayoutInflater inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // convertView = inflater

            /////



            //////
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //convertView = inflater.inflate(layout, null);
            convertView = inflater.inflate(layout, null);
            holder.txtTen = (TextView) convertView.findViewById(R.id.txttenkhoanthu);
            holder.txtsotien = (TextView)convertView.findViewById(R.id.txtsotien);
            holder.btnsua = (ImageButton) convertView.findViewById(R.id.btnsua);
            holder.btnxoa = (ImageButton)convertView.findViewById(R.id.btnxoa);
            convertView.setTag(holder);

        }
        else {
            holder = (ChiAdapter.ViewHolder) convertView.getTag();
        }



        final Thu thu = thuList.get(position);
        holder.txtTen.setText(thu.getTenkhoanthu());
        holder.txtsotien.setText(thu.getSotien());
        holder.btnsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                context.DialogSua(thu.getTenkhoanthu(), thu.getSotien(), thu.getId());
            }
        });

        holder.btnxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                context.DialogXoa(thu.getTenkhoanthu(), thu.getId());
            }
        });
        return convertView;
    }
}
