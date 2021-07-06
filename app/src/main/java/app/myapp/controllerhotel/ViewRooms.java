package app.myapp.controllerhotel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import app.myapp.controllerhotel.Adapter.Adapter_RoomsUser;
import app.myapp.controllerhotel.Model.Item_Rooms_user;
import app.myapp.controllerhotel.ServerApi.Server;
import app.myapp.controllerhotel.Shaerd.SaveToken;
import app.myapp.controllerhotel.Shaerd.VolleySetting;

public class ViewRooms extends AppCompatActivity {

    RecyclerView recyclerView;
    Context context;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_rooms);


        recyclerView=findViewById(R.id.rec);

        Intent intent=getIntent();
        id=intent.getIntExtra("id",-1 );
        context=this;

        Toast.makeText(this,id+"id",Toast.LENGTH_SHORT).show();
        dataMessage(id);

    }









    private void dataMessage(int i) {

        final String token = SaveToken.getInstanse(getBaseContext()).getToken().getToken();

        ArrayList<Item_Rooms_user> arrayList1 =new ArrayList<>();





        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, Server.GET_IMAGE_HOTEL_URL+i, null, new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response) {

                try {


                    JSONArray jsonArray = response.getJSONArray("data");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject screen = jsonArray.getJSONObject(i);

                        Item_Rooms_user item_rooms=new Item_Rooms_user();





                        item_rooms.setId(screen.getInt("id"));
                        item_rooms.setImage(screen.getString("imageroom1"));
                        screen.getString("imageroom2");
                        screen.getString("imageroom3");
                        item_rooms.setName_room(screen.getString("nameroom"));
                        item_rooms.setType_room(screen.getString("typeroom"));
                        item_rooms.setPrice_room(Integer.parseInt(screen.getString("priceroom")));
                        item_rooms.setEnable(screen.getString("enable"));
                        item_rooms.setAdmin_id(screen.getInt("details_id"));

                        screen.getString("numbed");
                        screen.getString("typebed");
                        screen.getString("Facilities");
                        screen.getString("numguest");
                        screen.getString("kids");
                        screen.getString("animals");
                        screen.getString("breckfast");
                        screen.getString("numroom");
                        screen.getString("access");
                        screen.getString("meansofcomfort");


                        Toast.makeText(getBaseContext(),"kkk",Toast.LENGTH_SHORT).show();
                        arrayList1.add(item_rooms);


                    }


                    Adapter_RoomsUser adapter_rooms=new Adapter_RoomsUser(arrayList1,context);

                    recyclerView.setAdapter(adapter_rooms);
                    RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getBaseContext());

                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setHasFixedSize(true);

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
                map.put("Authorization", "Bearer  " + token);
                return map;
            }

        };
        VolleySetting.getInstance(getBaseContext()).addRequest(jsonObjectRequest);


    }
}