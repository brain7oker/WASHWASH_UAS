package id.ac.umn.uas_map;

public class Employee {
    private String name;
    private String phone;
    private String car;
    private String loc;
    private String image;

    public Employee(){}
    public Employee(String name, String phone, String car, String loc, String image) {
        this.name = name;
        this.phone = phone;
        this.car = car;
        this.loc = loc;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
