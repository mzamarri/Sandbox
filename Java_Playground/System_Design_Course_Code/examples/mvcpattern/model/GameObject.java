package model;

public abstract class GameObject {
    private String name;

    public GameObject(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public String getType(String type) {
        return type;
    };

    public abstract String getType();

    public void printGameObjects(GameObject o) {
        System.out.println("Game Type: " + o.getType() + "\nName: " + o.getName() + "\n");
    }
}