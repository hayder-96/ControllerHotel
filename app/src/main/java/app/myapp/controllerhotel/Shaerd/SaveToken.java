package app.myapp.controllerhotel.Shaerd;

import android.content.Context;
import android.content.SharedPreferences;

import app.myapp.controllerhotel.Model.User;



public class SaveToken {






    private static final String SHARED_TOKEN="shared_token";
    private static final String TOKEN_SHARED="token";
    private static final String TOKEN_ID="id";

    private static SaveToken vollySing_instanse;
    private static Context context;

    public SaveToken(Context context) {
        this.context=context;
    }

    public static synchronized SaveToken getInstanse(Context context){
        if (vollySing_instanse==null){
            vollySing_instanse=new SaveToken(context);
        }

        return vollySing_instanse;
    }

    public void saveUser(User user){

        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_TOKEN,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(TOKEN_SHARED,user.getToken());
        editor.putString(TOKEN_ID,user.getId());
        editor.apply();
    }

    public User getToken(){
        SharedPreferences sharedPreferences=context.getSharedPreferences(SHARED_TOKEN,Context.MODE_PRIVATE);

        return new User(sharedPreferences.getString(TOKEN_SHARED,null),sharedPreferences.getString(TOKEN_ID,null));


    }



    public boolean is_Login(){

        SharedPreferences sharedPreferences=context.getSharedPreferences(SHARED_TOKEN,Context.MODE_PRIVATE);

        return sharedPreferences.getString(TOKEN_SHARED,null)!=null;
    }
    public void user_Logout(){
        SharedPreferences sharedPreferences=context.getSharedPreferences(SHARED_TOKEN,Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();


    }
}
