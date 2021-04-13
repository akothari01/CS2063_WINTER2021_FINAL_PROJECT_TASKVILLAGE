package ca.unb.mobiledev.task_village_take2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerView2Adapter extends RecyclerView.Adapter<RecyclerView2Adapter.MyViewHolder>{

    //private Village arrList;
    private ArrayList<String> arrList;
    private Context context;


    public RecyclerView2Adapter(Context context,ArrayList<String> arrList){
        this.context = context;
        this.arrList = arrList;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout_ii,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        //List<building> buildingsToAdd = arrList.getBuildingList().subList(0, arrList.getNoBuildings()/2);
        //System.out.println("small size" + buildingsToAdd.size());
        //System.out.println("pos" + position);
        final String string = arrList.get(position);
        holder.textView.setText(string);
    }

    @Override
    public int getItemCount() {
        return arrList.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView11);



        }


    }


    public void setArr(ArrayList<String> arrList){
        this.arrList = arrList;
    }

    public ArrayList<String> getArr(){
        return arrList;
    }
}
