import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class ShockWaveL extends ShockWave {

    public ShockWaveL(int speed) {
        super(speed); // Call the constructor of ShockWave with speed
        flipImages(SWImages);  // Flip the images horizontally
    }

    // Method to flip the images horizontally
    public void flipImages(GreenfootImage[] imageArray) {
        for (GreenfootImage image : imageArray) {
            if (image != null) {
                image.mirrorHorizontally();  // Flip each image horizontally
            }
        }
    }
}