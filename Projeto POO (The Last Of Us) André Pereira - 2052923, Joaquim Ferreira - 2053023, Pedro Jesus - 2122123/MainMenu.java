import greenfoot.*;

public class MainMenu extends World {
    private static GreenfootSound backgroundMusic;

    public MainMenu() {    
        // Cria o mundo e define o fundo
        super(1300, 600, 1);
        
        // Carrega a imagem de fundo
        GreenfootImage background = new GreenfootImage("mainmenu2.jpg");
        background.scale(getWidth(), getHeight());  // Escala o fundo para caber no mundo
        setBackground(background);

        // Inicializa e toca a música de fundo
        backgroundMusic = new GreenfootSound("mainmenu.mp3");
        backgroundMusic.playLoop();  // Toca a música em loop
        
        // Adiciona os botões ao mundo
        addObject(new StartButton(), 155, 200);  // Botão Start Game
        addObject(new ControlsButton(), 155, 300);  // Botão Controls
        addObject(new ExitButton(this), 155, 400);  // Botão Exit
    }

    // Método estático para parar a música de fundo
    public static void stopMusic() {
        if (backgroundMusic != null) {
            backgroundMusic.stop();
        }
    }

    @Override
    public void stopped() {
        // Para a música quando o mundo é interrompido
        backgroundMusic.stop();
    }
}
