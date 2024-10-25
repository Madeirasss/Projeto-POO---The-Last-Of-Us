import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Wall extends Actor
{
    public Wall(String imageName) {
        // Carrega e define a imagem da parede
        GreenfootImage wallImage = new GreenfootImage(imageName);
        setImage(wallImage);
    }
}
