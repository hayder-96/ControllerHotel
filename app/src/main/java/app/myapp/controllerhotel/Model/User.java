package app.myapp.controllerhotel.Model;

public class User {


    private String token;


    private String id;

    public User() {
    }

    public User(String token) {
        this.token = token;
    }

    public User(String token, String id) {
        this.token = token;

        this.id=id;
    }




    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
