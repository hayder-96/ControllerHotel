package app.myapp.controllerhotel.RegAndLogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import app.myapp.controllerhotel.MainActivity;
import app.myapp.controllerhotel.Model.User;
import app.myapp.controllerhotel.R;
import app.myapp.controllerhotel.ServerApi.Server;
import app.myapp.controllerhotel.Shaerd.SaveToken;
import app.myapp.controllerhotel.Shaerd.VolleySetting;

public class Register extends AppCompatActivity {

    EditText name,email,passwrd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        name=findViewById(R.id.name_reg);
        email=findViewById(R.id.email_reg);
        passwrd=findViewById(R.id.password_reg);


    }

    public void Register(View view) {

        saveRegi();
    }














    private void saveRegi(){

        final String name1=name.getText().toString().trim();
        final String email1=email.getText().toString().trim();
        final String password1=passwrd.getText().toString().trim();


        if (TextUtils.isEmpty(name1)){
            name.setError("Enter your name");
            name.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(email1)){
            email.setError("Enter your name");
            email.requestFocus();
            return;
        }





        if (TextUtils.isEmpty(password1)){
            passwrd.setError("Enter your password");
            passwrd.requestFocus();
            return;
        }


        StringRequest stringRequest=new StringRequest(Request.Method.POST, Server.REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.getBoolean("success")) {

                                JSONObject object = jsonObject.getJSONObject("data");
                                SaveToken.getInstanse(getBaseContext()).saveUser(new User(object.getString("token")));
                                startActivity(new Intent(getBaseContext(),MainActivity.class));
                                finish();

                            } else {
                                Toast.makeText(getBaseContext(), "message", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }



                    }

                } , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }


        }
        )

        {

            @Override
            protected Map<String, String> getParams() {

                Map<String, String> map=new HashMap<>();
                map.put("Content-Type","application/json");
                map.put("name",name1);
                map.put("email",email1);
                map.put("password",password1);
                map.put("c_password",password1);
                return map ;
            }
        };


        VolleySetting.getInstance(getBaseContext()).addRequest(stringRequest);
    }
}