package app.myapp.controllerhotel;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
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

public class DitailsHotel extends AppCompatActivity {

    String nameHotel,evaltion,manger,email,image1,image2,image3,latitude,longtude,ev,country,city,number;
    int id,admin_id;
    ImageView imageView1,imageView2,imageView3,loc;
    TextView nh,mn,em,co,ci,eve,nu;
    RatingBar ratingBar;
    LinearLayout layout1,layout2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ditails_hotel);


        layout1=findViewById(R.id.layout1);
        layout2=findViewById(R.id.layout2);


        imageView1=findViewById(R.id.imageView);
        imageView2=findViewById(R.id.imageView2);
        imageView3=findViewById(R.id.imageView3);

        ratingBar=findViewById(R.id.evalotion_hotel);


        nh=findViewById(R.id.namehoteldetails);
        mn=findViewById(R.id.manger_hotel);
        em=findViewById(R.id.email_hotel);
        loc=findViewById(R.id.location_hotel);
        co=findViewById(R.id.country_hotel);
        ci=findViewById(R.id.city_hotel);
        eve=findViewById(R.id.eve_hotel);
        nu=findViewById(R.id.phone_hotel);




        Intent intent=getIntent();;

       id=intent.getIntExtra("id",-1);
        admin_id=intent.getIntExtra("admin_id",-1);
        nameHotel=intent.getStringExtra("namehotel");
        evaltion=intent.getStringExtra("evaltion");
        manger=intent.getStringExtra("manger");
        email=intent.getStringExtra("email");
        image1=intent.getStringExtra("image1");
        image2=intent.getStringExtra("image2");
        image3=intent.getStringExtra("image3");
        latitude=intent.getStringExtra("latitude");
        longtude=intent.getStringExtra("longtude");
        ev=intent.getStringExtra("ev");
        country=intent.getStringExtra("country");
        city=intent.getStringExtra("city");
        number=intent.getStringExtra("number");
        String e=intent.getStringExtra("enable");

        if (e.equals("no")){
            layout1.setVisibility(View.VISIBLE);

        }else {
            layout2.setVisibility(View.VISIBLE);
        }





        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1=new Intent(getBaseContext(),ViewRooms.class);
                intent1.putExtra("id",id);
                startActivity(intent1);
                Toast.makeText(getBaseContext(),"ok",Toast.LENGTH_SHORT).show();
            }
        });






        nh.setText(nameHotel);
        ratingBar.setRating(Float.parseFloat(evaltion));
        mn.setText(manger);
        em.setText(email);
        loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1=new Intent(DitailsHotel.this,MapsActivity.class);

                intent.putExtra("name",nameHotel);
                intent1.putExtra("lat",latitude);
                intent1.putExtra("log",longtude);

                startActivity(intent1);
            }
        });
        eve.setText(ev);
        ci.setText(city);
        nu.setText(number);
        co.setText(country);
        if (image1!=null && !image1.isEmpty()) {
            Picasso.get().load(image1).resize(500, 300).into(imageView1);
            Picasso.get().load(image2).resize(500, 300).into(imageView2);
            Picasso.get().load(image3).resize(500, 300).into(imageView3);
        }

    }

    public void Accept(View view) {

        upDate(id,"yes");
        Insert(admin_id,"تمت الموافقة على نشر طلبك");
    }

    public void refusal(View view) {


        Ale(admin_id);



    }

    public void Replay(View view) {
        upDate(id,"no");

    }

    public void Delete(View view) {

        Ale(admin_id);


    }





    private void upDate(int id,String n) {

        final String token = SaveToken.getInstanse(getBaseContext()).getToken().getToken();


        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("enable",n);
        } catch (Exception e) {
            e.printStackTrace();
        }

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, Server.UPDATE_ENABLE+id, jsonObject, new Response.Listener<JSONObject>() {
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














    private void DeleteHo(int id) {

        final String token = SaveToken.getInstanse(getBaseContext()).getToken().getToken();



        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.DELETE, Server.Delete_Hotel+id,null, new Response.Listener<JSONObject>() {
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








    private void Ale(int idi) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        EditText editText=new EditText(this);
        builder.setView(editText);


        builder.setMessage("ادخل سبب الرفض");
        builder.setPositiveButton("ارسال", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                if (TextUtils.isEmpty(editText.getText().toString().trim())){

                    Toast.makeText(getBaseContext(),"يجب ذكر السبب",Toast.LENGTH_SHORT).show();
                    return;
                }

                Insert(idi,editText.getText().toString().trim());

                DeleteHo(id);

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












    private void Insert(int admin,String content) {

        final String token = SaveToken.getInstanse(getBaseContext()).getToken().getToken();


        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("admin_id",admin);
            jsonObject.put("content",content);
            jsonObject.put("noty","no");
        } catch (Exception e) {
            e.printStackTrace();
        }

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, Server.NOTAYFAY, jsonObject, new Response.Listener<JSONObject>() {
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


        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(0,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        VolleySetting.getInstance(getBaseContext()).addRequest(jsonObjectRequest);


    }
}