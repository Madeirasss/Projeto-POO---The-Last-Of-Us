import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class GameOverScreen extends Actor {
    public GameOverScreen() {
        // Carrega a imagem de "Game Over"
        GreenfootImage gameOverImage = new GreenfootImage("gameover.png");
        setImage(gameOverImage);
    }

    public void addedToWorld(World world) {
        GreenfootImage gameOverImage = getImage();
        gameOverImage.scale(world.getWidth(), world.getHeight());
        setImage(gameOverImage);
        BackgroundMusicManager.stopMusic();  // Para a música do FirstLevel
    }

    public void act() {
        // Retorna ao menu principal se qualquer tecla for pressionada
        if (Greenfoot.getKey() != null) {
            MainMenu.stopMusic(); // Para a música do menu se estiver tocando
            Greenfoot.setWorld(new MainMenu()); 
        }
    }
}
