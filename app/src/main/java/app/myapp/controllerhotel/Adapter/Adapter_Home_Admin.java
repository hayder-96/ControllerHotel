package app.myapp.controllerhotel.Adapter;

import android.content.Context;
import android.content.Intent;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import app.myapp.controllerhotel.DitailsHotel;
import app.myapp.controllerhotel.Model.InfoHotelAdmin;
import app.myapp.controllerhotel.R;


public class Adapter_Home_Admin extends RecyclerView.Adapter<Adapter_Home_Admin.MyHolder> {


    ArrayList<InfoHotelAdmin> arrayList;

    Context context;
    public Adapter_Home_Admin(ArrayList<InfoHotelAdmin> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context=context;
    }

    @NonNull
    @Override
    public Adapter_Home_Admin.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.split_home_admin,parent,false);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_Home_Admin.MyHolder holder, int position) {

        InfoHotelAdmin infoHotel=arrayList.get(position);


        holder.name_hotel.setText(infoHotel.getNamehotel());
        holder.time.setText(formatdate(infoHotel.getTime()));


        int id=infoHotel.getId();



        int admin_id=infoHotel.getAdmin_id();















        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(context,DitailsHotel.class);

                intent.putExtra("id",id);
                intent.putExtra("admin_id",admin_id);
                intent.putExtra("namehotel",infoHotel.getNamehotel());
                intent.putExtra("evaltion",infoHotel.getEvaluation());
                intent.putExtra("manger",infoHotel.getManger());
                intent.putExtra("email",infoHotel.getEmail());
                intent.putExtra("image1",infoHotel.getImage1());
                intent.putExtra("image2",infoHotel.getImage1());
                intent.putExtra("image3",infoHotel.getImage3());
                intent.putExtra("latitude",infoHotel.getLatitude());
                intent.putExtra("longtude",infoHotel.getLongtude());
                intent.putExtra("ev",infoHotel.getEv());
                intent.putExtra("country",infoHotel.getCountry());
                intent.putExtra("city",infoHotel.getCity());
                intent.putExtra("number",infoHotel.getNumber());
                intent.putExtra("enable",infoHotel.getEnable());

                context.startActivity(intent);
            }
        });




    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        TextView name_hotel,time;

        public MyHolder(@NonNull View itemView) {
            super(itemView);


            name_hotel=itemView.findViewById(R.id.text_namehotel);
            time=itemView.findViewById(R.id.text_time);






        }






    }












    private String formatdate(String newDate){



        CharSequence  niceDateStr=null;

        Date date=null;

        SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy H:m:s");

        try {
            inputFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            date=inputFormat.parse(newDate);
            niceDateStr = DateUtils.getRelativeTimeSpanString(date.getTime() , Calendar.getInstance().getTimeInMillis(), DateUtils.MINUTE_IN_MILLIS);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return niceDateStr.toString();

    }
}
