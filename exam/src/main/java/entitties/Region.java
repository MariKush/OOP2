package entitties;

public class Region {
    private int id;
    private String name;
    private int square;
    private int personId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSquare() {
        return square;
    }

    public void setSquare(int square) {
        this.square = square;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    @Override
    public String toString() {
        return "id=" + id + '\n' +
                "name=" + name + '\n' +
                "square=" + square + '\n' +
                "personId=" + personId + '\n';
    }
}
