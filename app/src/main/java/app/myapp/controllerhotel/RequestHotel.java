package app.myapp.controllerhotel;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import app.myapp.controllerhotel.Adapter.Adapter_Home_Admin;
import app.myapp.controllerhotel.Model.InfoHotelAdmin;
import app.myapp.controllerhotel.ServerApi.Server;
import app.myapp.controllerhotel.Shaerd.SaveToken;
import app.myapp.controllerhotel.Shaerd.VolleySetting;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RequestHotel#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RequestHotel extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RequestHotel() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RequestHotel.
     */
    // TODO: Rename and change types and number of parameters
    public static RequestHotel newInstance(String param1, String param2) {
        RequestHotel fragment = new RequestHotel();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_request_hotel, container, false);

        RecyclerView recyclerView=view.findViewById(R.id.recy);



        dataMessage(recyclerView);

        return view;
    }














    private void dataMessage(RecyclerView r) {

        final String token = SaveToken.getInstanse(getActivity()).getToken().getToken();

        ArrayList<InfoHotelAdmin> arrayList1 =new ArrayList<>();





        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, Server.GET_HOTEL_NO, null, new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response) {

                try {


                    JSONArray jsonArray = response.getJSONArray("data");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject screen = jsonArray.getJSONObject(i);

                        InfoHotelAdmin infoHotel=new InfoHotelAdmin();

                        infoHotel.setId(screen.getInt("id"));
                        infoHotel.setNamehotel(screen.getString("namehotel"));
                        infoHotel.setEvaluation(screen.getString("evaluation"));
                        infoHotel.setImage1(screen.getString("image1"));
                        infoHotel.setImage2(screen.getString("image2"));
                        infoHotel.setImage3(screen.getString("image3"));
                        infoHotel.setManger(screen.getString("manger"));
                        infoHotel.setNumber(screen.getString("number"));
                        infoHotel.setCountry(screen.getString("country"));
                        infoHotel.setCity(screen.getString("city"));
                        infoHotel.setLatitude(screen.getString("latitude"));
                        infoHotel.setLongtude(screen.getString("longtude"));
                        infoHotel.setAdmin_id(screen.getInt("admin_id"));
                        infoHotel.setEmail(screen.getString("email"));
                        infoHotel.setEv(screen.getString("ev"));
                        infoHotel.setEnable(screen.getString("enable"));
                        infoHotel.setTime(screen.getString("created_at"));
                        screen.getString("updated_at");



                        arrayList1.add(infoHotel);


                    }

                    Adapter_Home_Admin adapter_home=new Adapter_Home_Admin(arrayList1,getActivity());

                    r.setAdapter(adapter_home);
                    r.setLayoutManager(new LinearLayoutManager(getActivity()));
                    r.setHasFixedSize(true);



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
        VolleySetting.getInstance(getActivity()).addRequest(jsonObjectRequest);


    }

}