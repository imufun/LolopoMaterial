package com.example.imran.MaterialDesign;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.imran.lolopomaterial.R;

import java.util.Collections;
import java.util.List;


/**
 * Created by imran on 5/15/15.
 */
public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.MyViewHolder> {

    private static final String TAG = "IMRAN";
    public Context context;

    private LayoutInflater inflater;
    List<Information>data= Collections.emptyList(); //

    public ViewAdapter(Context context,List<Information>data) {
        inflater=LayoutInflater.from(context);

        this.data=data;
    }

    // Remoview item form recycler
    public void deleted(int position){
        notifyItemRemoved(position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.coustome_row, parent, false);// ekhane custome spec call kortrese
        Log.d("IMRAN","onCreateViewHolder called");
        MyViewHolder holder=new MyViewHolder(view);//
        return holder;

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Information current=data.get(position);//call Information class

        Log.d("IMRAN", "onBindViewHolder called" + position);
        //Log.d("IMRAN","onBindViewHolder called"+position);
        holder.title.setText(current.title);//information ---tile initialize

    }


    //- shob itam call hobe ei method er maddhome --getItemCount (android.support.v7.widget);
    @Override
    public int getItemCount() {
//        return 1;
        return data.size();
    }



    //Custome_row call hoise ekhane
    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // textview and icon call here

        TextView title;
        ImageView icon;


        public MyViewHolder(View itemView) {
            super(itemView);

            title=(TextView)itemView.findViewById(R.id.TextTile);
            icon=(ImageView)itemView.findViewById(R.id.ImgIcon);
            icon.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
             deleted(getAdapterPosition());// remove here

        }
    }


}
