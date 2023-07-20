package org.example;

public class Client {
    private String name;
    private long ID;

    public Client(String name, long ID) {
        this.name = name;
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public long getID() {
        return ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", ID=" + ID +
                '}' + "\n";
    }
}
