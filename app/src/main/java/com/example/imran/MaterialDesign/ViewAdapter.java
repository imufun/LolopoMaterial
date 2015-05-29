package com.example.imran.MaterialDesign;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.imran.lolopomaterial.R;

import java.util.Collections;
import java.util.List;


/**
 * Created by imran on 5/15/15.
 */
public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    List<Information>data= Collections.emptyList(); //

    public ViewAdapter(Context context,List<Information>data) {
        inflater=LayoutInflater.from(context);

        this.data=data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.coustome_row,parent,false);// ekhane custome row call kortrese
        MyViewHolder holder=new MyViewHolder(view);//
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Information current=data.get(position);//call Information class

        holder.title.setText(current.title);//information ---tile initialize

        holder.icon.setImageResource(current.iconId);//Information--icon initialize

    }


    //- shob itam call hobe ei method er maddhome --getItemCount (android.support.v7.widget);
    @Override
    public int getItemCount() {
//        return 1;
        return data.size();
    }


    //Custome_row call hoise ekhane
    class MyViewHolder extends RecyclerView.ViewHolder{
        // textview and icon call here

        TextView title;
        ImageView icon;


        public MyViewHolder(View itemView) {
            super(itemView);

            title=(TextView)itemView.findViewById(R.id.TextTile);
            icon=(ImageView)itemView.findViewById(R.id.ImgIcon);
        }
    }


}
