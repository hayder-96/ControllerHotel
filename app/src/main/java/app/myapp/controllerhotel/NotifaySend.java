package app.myapp.controllerhotel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import app.myapp.controllerhotel.ServerApi.Server;
import app.myapp.controllerhotel.Shaerd.SaveToken;
import app.myapp.controllerhotel.Shaerd.VolleySetting;

public class NotifaySend extends AppCompatActivity {


    EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifay_send);


        editText=findViewById(R.id.edit_noty);


    }

    public void Send(View view) {

        if (TextUtils.isEmpty(editText.getText().toString().trim())){

            editText.setError("ادخل النص");
            editText.requestFocus();

            return;
        }

        Insert(editText.getText().toString().trim());
    }
















    private void Insert(String content) {

        final String token = SaveToken.getInstanse(getBaseContext()).getToken().getToken();


        JSONObject jsonObject = new JSONObject();
        try {
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