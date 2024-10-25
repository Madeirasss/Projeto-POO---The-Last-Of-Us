import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class StartButton extends Button {
    public StartButton() {
        super("Start Game", Color.DARK_GRAY, Color.GRAY);  // Define as cores do botão
    }

    @Override
    public void onClick() {
        // Para a música de fundo antes de iniciar a cutscene
        MainMenu.stopMusic();
        
        // Quando o botão é clicado, inicia a cutscene de áudio
        Greenfoot.setWorld(new Cutscene());  // Altera para a classe Cutscene
    }
}
