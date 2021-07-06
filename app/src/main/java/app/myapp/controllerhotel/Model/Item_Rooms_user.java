package app.myapp.controllerhotel.Model;

public class Item_Rooms_user {

    private int id;
    private String name_room;
    private String type_room;
    private int price_room;
    private String image;
    private int admin_id;
    private String enable;

    public Item_Rooms_user() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName_room() {
        return name_room;
    }

    public void setName_room(String name_room) {
        this.name_room = name_room;
    }

    public String getType_room() {
        return type_room;
    }

    public void setType_room(String type_room) {
        this.type_room = type_room;
    }

    public int getPrice_room() {
        return price_room;
    }

    public void setPrice_room(int price_room) {
        this.price_room = price_room;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }








//
//
//    public static Comparator<Item_Rooms_user> comparator=new Comparator<Item_Rooms_user>() {
//        @Override
//        public int compare(Item_Rooms_user o1,Item_Rooms_user o2) {
//
//
//            return o2.getPrice_room().compareTo(o1.getPrice_room());
//        }
//    };
}
