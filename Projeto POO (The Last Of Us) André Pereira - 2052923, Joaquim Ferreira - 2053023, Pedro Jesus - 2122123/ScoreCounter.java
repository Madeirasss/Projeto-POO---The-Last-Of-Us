import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class ScoreCounter extends Actor
{
    private int score;

    public ScoreCounter(int initialScore) {
        this.score = initialScore;
        updateImage();
    }

    public void addScore(int points) {
        score += points;
        updateImage();
    }

    public int getScore() {
        return score;
    }

    private void updateImage() {
    setImage(new GreenfootImage("Score: " + score, 30, Color.WHITE, Color.BLACK));
    }
    
}
