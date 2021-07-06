package app.myapp.controllerhotel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;


import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import app.myapp.controllerhotel.RegAndLogin.Login;
import app.myapp.controllerhotel.Shaerd.SaveToken;

public class MainActivity extends AppCompatActivity {

    private RequestQueue requestQueue;
    private String token;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        requestQueue = Volley.newRequestQueue(this);
        if (SaveToken.getInstanse(this).is_Login()) {

            if (SaveToken.getInstanse(this).getToken() != null) {
                token = SaveToken.getInstanse(this).getToken().getToken();

            }
        } else {
            finish();


            startActivity(new Intent(this, Login.class));
            return;
        }


        toolbar=findViewById(R.id.toolpar);


        setSupportActionBar(toolbar);

        BottomNavigationView bottomNavigationView = findViewById(R.id.navgtion);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame,
                new RequestHotel()).commit();


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment=null;
                switch (item.getItemId()) {
                    case R.id.home:


                        fragment=new RequestHotel();

                        break;

                    case R.id.star:



                        break;



                    case R.id.done:

                        fragment=new RequestDone();

                        break;

                }

                getSupportFragmentManager().beginTransaction().replace(R.id.frame,
                        fragment).commit();
                return true;
            }
        });













    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.send_noty,menu);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        switch (item.getItemId()){

            case R.id.send:

                Intent intent=new Intent(getBaseContext(),NotifaySend.class);

                startActivity(intent);

                break;

            case R.id.logout:


                SaveToken.getInstanse(getBaseContext()).user_Logout();
                startActivity(new Intent(getBaseContext(),Login.class));
                finish();

                break;
        }



        return super.onOptionsItemSelected(item);
    }

}