package ca.unb.mobiledev.task_village_take2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{

    //private Village arrList;
    private ArrayList<building> arrList;
    private Context context;


    public RecyclerViewAdapter(Context context,ArrayList<building> arrList){
        this.context = context;
        this.arrList = arrList;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_view,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        //List<building> buildingsToAdd = arrList.getBuildingList().subList(0, arrList.getNoBuildings()/2);
        //System.out.println("small size" + buildingsToAdd.size());
        //System.out.println("pos" + position);
        holder.imageView.setImageResource(arrList.get(position).getImage());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent houseIntent = new Intent(context.getApplicationContext(), Pop.class);
                houseIntent.putExtra("Inhabitant", arrList.get(position).getInhabitant());
                context.startActivity(houseIntent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return arrList.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageView);



        }


    }


    public void setArr(ArrayList<building> arrList){
        this.arrList = arrList;
    }

    public ArrayList<building> getArr(){
        return arrList;
    }
}
