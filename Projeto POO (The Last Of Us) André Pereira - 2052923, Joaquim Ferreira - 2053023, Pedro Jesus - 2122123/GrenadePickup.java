import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class GrenadePickup extends Actor
{
    public GrenadePickup()
    {
        // Carrega a imagem da granada
        GreenfootImage image = new GreenfootImage("granada.png");
        
        // Define o novo tamanho
        int newWidth = 50;
        int newHeight = 50;
        image.scale(newWidth, newHeight);
        
        // Define a imagem redimensionada para o objeto granada
        setImage(image);
    }
    
    public void act()
    {
        // Comportamento da granada no mapa
    }
}
