package id.ac.umn.uas_map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    ArrayList<id.ac.umn.uas_map.Employee> list = new ArrayList<>();

    public RVAdapter(Context ctx){
        this.context = ctx;
    }
    public void setItems(ArrayList<id.ac.umn.uas_map.Employee> emp){
        list.addAll(emp);
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item,parent, false);
        return new EmployeeVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        EmployeeVH vh = (EmployeeVH) holder;
        id.ac.umn.uas_map.Employee emp = list.get(position);
        vh.txt_name.setText(emp.getName());
        vh.txt_phone.setText(emp.getPhone());
        vh.txt_car.setText(emp.getCar());
        vh.txt_loc.setText(emp.getLoc());
        vh.txt_image.setText(emp.getImage());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
