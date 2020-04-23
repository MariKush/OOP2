package objects;

public class Order {
    private int id;
    private Car car;
    private String name;
    private String surname;
    private String passportID;
    private String creditCard;
    private String mobileNum;

    public Order(int id, Car car, String name, String surname, String passportID, String creditCard, String mobileNum) {
        this.id = id;
        this.car = car;
        this.name = name;
        this.surname = surname;
        this.passportID = passportID;
        this.creditCard = creditCard;
        this.mobileNum = mobileNum;
    }

    public int getId() {
        return id;
    }

    public Car getCar() {
        return car;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPassportID() {
        return passportID;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public String getMobileNum() {
        return mobileNum;
    }

}
