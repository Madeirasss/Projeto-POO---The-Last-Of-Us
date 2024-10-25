import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class GameOverScreen extends Actor
{
    public GameOverScreen() {
        // Carrega a imagem de "Game Over"
        GreenfootImage gameOverImage = new GreenfootImage("gameover.png");

        // Ajusta o tamanho da imagem para cobrir todo o mundo, se necessário
        World world = getWorld();
        if (world != null) {
            gameOverImage.scale(world.getWidth(), world.getHeight());
        }

        // Define a imagem redimensionada como a imagem do ator
        setImage(gameOverImage);
    }

    public void addedToWorld(World world) {
        // Redimensiona a imagem se necessário quando o ator for adicionado ao mundo
        GreenfootImage gameOverImage = getImage();
        gameOverImage.scale(world.getWidth(), world.getHeight());
        setImage(gameOverImage);
    }

    public void act()
    {
        // Verifica se qualquer tecla foi pressionada
        if (Greenfoot.isKeyDown(null)) {
            // Volta para o menu principal ao pressionar qualquer tecla
            Greenfoot.setWorld(new MainMenu()); 
        }
    }
}
