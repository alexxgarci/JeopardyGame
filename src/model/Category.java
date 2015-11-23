package model;

public class Category {

    private String name;
    private String FILENAME = "";

    public Category(String name) {

        this.name = name;
        this.FILENAME = name + ".txt";
    }

    public String getCategoryName() {
        return this.name;
    }

}
