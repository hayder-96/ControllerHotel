package app.myapp.controllerhotel.Shaerd;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleySetting {



    private static VolleySetting vollyLib_instance;
    private static Context context;
    private RequestQueue requestQueue;


    public VolleySetting(Context context) {
        this.context=context;
        this.requestQueue = getRequestQueue();
    }
    public static synchronized VolleySetting getInstance(Context context){

        if (vollyLib_instance==null){
            vollyLib_instance=new VolleySetting(context);
        }
        return vollyLib_instance;
    }
    public RequestQueue getRequestQueue(){

        if (requestQueue==null){
            requestQueue= Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }

    public <T>void addRequest(Request<T> addReq){

        getRequestQueue().add(addReq);

    }









}
