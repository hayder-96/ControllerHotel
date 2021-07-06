package app.myapp.controllerhotel.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import app.myapp.controllerhotel.Details_hotel;
import app.myapp.controllerhotel.Model.Item_Rooms_user;
import app.myapp.controllerhotel.R;


public class Adapter_RoomsUser extends RecyclerView.Adapter<Adapter_RoomsUser.MyHolder> {


    ArrayList<Item_Rooms_user> arrayList;
    Context context;
    int idi;

    public Adapter_RoomsUser(ArrayList<Item_Rooms_user> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
        this.idi=idi;
    }

    @NonNull
    @Override
    public Adapter_RoomsUser.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.split_rooms_user, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_RoomsUser.MyHolder holder, int position) {

        Item_Rooms_user item_rooms = arrayList.get(position);

        int id=item_rooms.getId();
        int admi_id=item_rooms.getAdmin_id();
        holder.name.setText(item_rooms.getName_room());
        holder.type.setText(item_rooms.getType_room());
        holder.price.setText("$"+item_rooms.getPrice_room());


        String e=item_rooms.getEnable();
        if (e.equals("no")){

            for (int i = 0; i < holder.layout.getChildCount(); i++) {
                View child = holder.layout.getChildAt(i);
                child.setEnabled(false);
                holder.m.setVisibility(View.VISIBLE);
                holder.layout.setBackgroundColor(Color.parseColor("#C6B4B4"));
            }
          //  holder.layout.setVisibility(View.GONE);


        }

        if (item_rooms.getImage() != null && !item_rooms.getImage().isEmpty()) {
            Picasso.get().load(item_rooms.getImage()).resize(400, 500).into(holder.imageView);
        }

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(context, Details_hotel.class);
                intent.putExtra("id",id);
                intent.putExtra("admin_id",admi_id);
                intent.putExtra("idi",idi);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView name,type,price,m;
        ImageView imageView;
        LinearLayout layout;
        public MyHolder(@NonNull View itemView) {
            super(itemView);

            layout=itemView.findViewById(R.id.lia);
            name=itemView.findViewById(R.id.text_name_room_user);
            m=itemView.findViewById(R.id.text_mahjos);
            type=itemView.findViewById(R.id.text_type_rooms_user);
            price=itemView.findViewById(R.id.text_price_rooms_user);
            imageView=itemView.findViewById(R.id.image_rooms_user);
        }
    }
}
