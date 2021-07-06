package app.myapp.controllerhotel;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import app.myapp.controllerhotel.ServerApi.Server;
import app.myapp.controllerhotel.Shaerd.SaveToken;
import app.myapp.controllerhotel.Shaerd.VolleySetting;

public class Details_hotel extends AppCompatActivity {

    ImageView imageView1,imageView2,imageView3;

    TextView nameroom,typeroom,priceroom,namebed,typebed,numguest,facities,comfort,kids,animal,access,breckfast,numroom;

    Button button;
    int id,admin_id,i,ii;
    String i1,i2,i3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_hotel);




        button=findViewById(R.id.but_10);





        imageView1=findViewById(R.id.image_detail1_user);
        imageView2=findViewById(R.id.image_detail2_user);
        imageView3=findViewById(R.id.image_detail3_user);

        nameroom=findViewById(R.id.detail_name_room_user);
        typeroom=findViewById(R.id.detali_type_room_user);
        priceroom=findViewById(R.id.tetail_price_room_user);
        namebed=findViewById(R.id.detail_name_bed_user);
        typebed=findViewById(R.id.detali_type_bed_user);
        numguest=findViewById(R.id.detail_numguest_user);
        facities=findViewById(R.id.detail_facilites_user);
        comfort=findViewById(R.id.detail_comfort_user);
        kids=findViewById(R.id.detail_kids_user);
        animal=findViewById(R.id.detail_animal_user);
        access=findViewById(R.id.detail_access_user);
        breckfast=findViewById(R.id.detail_breckfast_user);
        numroom=findViewById(R.id.detail_numroom_user);

        Intent intent=getIntent();
        id=intent.getIntExtra("id",-1);

        ii=intent.getIntExtra("idi",-1);
        Toast.makeText(this,ii+"ii",Toast.LENGTH_SHORT).show();
        admin_id=intent.getIntExtra("admin_id",-1);


        show(id);






        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getBaseContext(), ViewPhoto.class);
                intent.putExtra("1",1);
                intent.putExtra("i1",i1);
                intent.putExtra("i2",i2);
                intent.putExtra("i3",i3);
                startActivity(intent);


            }
        });


        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(),ViewPhoto.class);
                intent.putExtra("1",2);
                intent.putExtra("i1",i1);
                intent.putExtra("i2",i2);
                intent.putExtra("i3",i3);
                startActivity(intent);
            }
        });


        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(),ViewPhoto.class);
                intent.putExtra("1",3);
                intent.putExtra("i1",i1);
                intent.putExtra("i2",i2);
                intent.putExtra("i3",i3);
                startActivity(intent);
            }
        });


    }













    private void show(final int id){


        final String token= SaveToken.getInstanse(getBaseContext()).getToken().getToken();



        final JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, Server.HOTEL_IMAGE_URL+"/"+id,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    if (response.getBoolean("success")) {
                        JSONObject hotel = response.getJSONObject("data");





                        i=hotel.getInt("id");

                        Picasso.get().load(hotel.getString("imageroom1")).resize(200,500).into(imageView1);
                        Picasso.get().load(hotel.getString("imageroom2")).resize(200,500).into(imageView2);
                        Picasso.get().load(hotel.getString("imageroom3")).resize(200,500).into(imageView3);


                        i1=hotel.getString("imageroom1");
                        i2=hotel.getString("imageroom2");
                        i3=hotel.getString("imageroom3");



                        nameroom.setText(hotel.getString("nameroom"));
                        typeroom.setText(hotel.getString("typeroom"));
                        namebed.setText(hotel.getString("numbed"));
                        typebed.setText(hotel.getString("typebed"));
                        priceroom.setText(hotel.getString("priceroom"));
                        facities.setText(hotel.getString("Facilities"));
                        numguest.setText(hotel.getString("numguest"));
                        kids.setText(hotel.getString("kids"));
                        animal.setText(hotel.getString("animals"));
                        breckfast.setText(hotel.getString("breckfast"));
                        numroom.setText(hotel.getString("numroom"));
                        access.setText(hotel.getString("access"));
                        comfort.setText(hotel.getString("meansofcomfort"));

                        hotel.getString("enable");

                       String g=hotel.getString("show");

                       if (g.equals("no")){
                           button.setText("الغاء");
                       }

                        hotel.getInt("details_id");




                    }


                } catch (Exception e) {

                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }


        })
        {
            public Map<String,String> getHeaders(){
                Map<String, String> map=new HashMap<>();
                map.put("Accept","application/json");
                map.put("Authorization","Bearer "+ token);
                return map;
            }

        };
        VolleySetting.getInstance(getBaseContext()).addRequest(jsonObjectRequest);



    }


    public void show(View view) {

        String s=((Button)view).getText().toString().trim();
        if (s.equals("حجب")) {

            Alert();


        }else {
            upDate(id, "yes");
            button.setText("حجب");
        }
    }














        private void upDate(int id,String show) {

            final String token = SaveToken.getInstanse(getBaseContext()).getToken().getToken();


            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("show",show);
            } catch (Exception e) {
                e.printStackTrace();
            }

            final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, Server.GET_SHOW_ROOMS+id, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {


                    try {

                        if (response.getBoolean("success")) {
                            Toast.makeText(getBaseContext(), response.getString("message"), Toast.LENGTH_SHORT).show();





                        } else {

                            Toast.makeText(getBaseContext(), "error", Toast.LENGTH_SHORT).show();

                        }
                    } catch (Exception e) {

                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }


            }) {
                public Map<String, String> getHeaders() {
                    Map<String, String> map = new HashMap<>();
                    map.put("Accept", "application/json");
                    map.put("Authorization", "Bearer " + token);
                    return map;
                }

            };
            VolleySetting.getInstance(getBaseContext()).addRequest(jsonObjectRequest);


        }










    private void Insert(String spp) {

        final String token = SaveToken.getInstanse(getBaseContext()).getToken().getToken();


        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name",nameroom.getText().toString().trim());
            jsonObject.put("room_id",id);
            jsonObject.put("content",spp);
        } catch (Exception e) {
            e.printStackTrace();
        }

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, Server.INSERT_SHOW, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                try {

                    if (response.getBoolean("success")) {
                        Toast.makeText(getBaseContext(), response.getString("message"), Toast.LENGTH_SHORT).show();





                    } else {

                        Toast.makeText(getBaseContext(), "error", Toast.LENGTH_SHORT).show();

                    }
                } catch (Exception e) {

                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }


        }) {
            public Map<String, String> getHeaders() {
                Map<String, String> map = new HashMap<>();
                map.put("Accept", "application/json");
                map.put("Authorization", "Bearer " + token);
                return map;
            }

        };
        VolleySetting.getInstance(getBaseContext()).addRequest(jsonObjectRequest);


    }







        private void Alert(){

        AlertDialog.Builder builder=new AlertDialog.Builder(this);

        builder.setTitle("ادخل السبب");

        EditText editText=new EditText(this);

        builder.setView(editText);

        builder.setPositiveButton("ارسال", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (TextUtils.isEmpty(editText.getText().toString().trim())){

                    Toast.makeText(getBaseContext(),"ادخل السبب",Toast.LENGTH_SHORT).show();
                    return;
                }

                upDate(id, "no");
                Insert(editText.getText().toString().trim());
                button.setText("الغاء");




            }
        });

        builder.setNegativeButton("الغاء", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.show();



        }
    }

