package Models;

/**
 *
 * @author alsorc
 */
public class Player {
    
    private int id;
    private String name;
    private String position;
    private int age;

    public Player() {
    }

    public Player(int id, String name, String position, int age) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.age = age;
    }
    
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Player{" + "id=" + id + ", name=" + name + ", position=" + position + ", age=" + age + '}';
    }
    
}
