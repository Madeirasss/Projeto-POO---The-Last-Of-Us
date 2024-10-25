import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class StaticImage extends Actor
{
    public StaticImage(String imageName, int width, int height)
    {
        // Carrega a imagem passada no construtor
        GreenfootImage image = new GreenfootImage(imageName);
        
        // Redimensiona a imagem para o tamanho desejado
        image.scale(width, height);
        
        // Define a imagem redimensionada para o ator
        setImage(image);
    }
}
