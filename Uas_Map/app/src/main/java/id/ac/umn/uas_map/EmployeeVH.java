package id.ac.umn.uas_map;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EmployeeVH extends RecyclerView.ViewHolder {
    public TextView txt_name, txt_phone, txt_car, txt_loc, txt_image;
    public EmployeeVH(@NonNull View itemView){
        super(itemView);
        txt_name = itemView.findViewById(R.id.txt_name);
        txt_phone = itemView.findViewById(R.id.txt_phone);
        txt_car = itemView.findViewById(R.id.txt_car);
        txt_loc = itemView.findViewById(R.id.txt_loc);
        txt_image = itemView.findViewById(R.id.txt_image);
    }
}
