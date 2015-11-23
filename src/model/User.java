package model;

public class User {

    private String name;
    private int score;
    private int turn;

    public User(String name) {
        this.name = name;
        this.score = 0;
        this.turn = 0;
    }

    //+ and - score
    public void changeScore(int points) {
        if (this.turn == 15) {
            points = points * 2;
        }
        this.score = score + points;
    }

    //Getters
    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public int getTurn() {
        return turn;
    }

    //Setters
    public void increaseTurn() {
        this.turn++;
    }

    @Override
    public String toString() {
        return name + "\n" + score;
    }

}
