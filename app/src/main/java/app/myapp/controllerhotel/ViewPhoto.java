package app.myapp.controllerhotel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class ViewPhoto extends AppCompatActivity {

    String[] photo;
    ImageView imageView;
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_photo);



        imageView=findViewById(R.id.image_all);

        Intent intent=getIntent();

        int ii=intent.getIntExtra("1",-1);
        String im1=intent.getStringExtra("i1");
        String im2=intent.getStringExtra("i2");
        String im3=intent.getStringExtra("i3");
        Toast.makeText(this,im1+"i",Toast.LENGTH_SHORT).show();
        if (ii==1){

            Picasso.get().load(im1).resize(200,400).into(imageView);
            photo=new String[]{im1,im2,im3};
            i=0;
            Toast.makeText(this,i+"i",Toast.LENGTH_SHORT).show();
        }else if (ii==2){
            Picasso.get().load(im2).resize(200,400).into(imageView);
            photo=new String[]{im2,im3,im1};
            i=1;
            Toast.makeText(this,i+"i",Toast.LENGTH_SHORT).show();
        }else if (ii==3){

            Picasso.get().load(im3).resize(200,400).into(imageView);
            photo=new String[]{im3,im1,im2};
            i=2;
            Toast.makeText(this,i+"i",Toast.LENGTH_SHORT).show();

        }

    }

    public void back(View view) {


        Picasso.get().load(photo[i]).resize(200,400).into(imageView);

        i--;
        if (i<0){

            i=2;
        }
    }

    public void next(View view) {



        Picasso.get().load(photo[i]).resize(200,400).into(imageView);

        i++;
        if (i>2){

            i=0;
        }
    }

}