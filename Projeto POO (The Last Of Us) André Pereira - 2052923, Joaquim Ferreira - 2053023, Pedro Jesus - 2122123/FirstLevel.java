import greenfoot.*;

// Classe que representa o primeiro nível do jogo
public class FirstLevel extends World {
    private boolean doorActive = false; // Indica se a porta está ativa para avançar de nível
    private Player1 player1;
    private Player2 player2;
    private ScoreCounter scoreCounter;  // Contador de pontos para os jogadores

    // Construtor do primeiro nível
    public FirstLevel(int player1Health, int player2Health, int initialScore) {    
        super(1300, 600, 1); // Define o tamanho do mundo

        GreenfootImage background = new GreenfootImage("level1_1.jpg");
        background.scale(getWidth(), getHeight());
        setBackground(background);

        BackgroundMusicManager.playMusic();

        Door door = new Door();
        addObject(door, 1280, 52);

        player1 = new Player1(player1Health);
        addObject(player1, 100, 500);

        player2 = new Player2(player2Health);
        addObject(player2, 1200, 500);

        HealthBar healthBar1 = new HealthBar();
        addObject(healthBar1, 80, 15);
        player1.setHealthBar(healthBar1);
        healthBar1.updateHealth(player1Health);

        HealthBar healthBar2 = new HealthBar();
        addObject(healthBar2, 235, 15);
        player2.setHealthBar(healthBar2);
        healthBar2.updateHealth(player2Health);

        addPlatforms();
        addZombies();
        addMedicKit();

        scoreCounter = new ScoreCounter(initialScore);
        addObject(scoreCounter, 670, 10);
    }

    private void addPlatforms() {
        addObject(new Platform("1.png"), 1144, 95);
        addObject(new Platform("8.png"), 670, 200);
        addObject(new Platform("1_invertido.png"), 144, 95);
        addObject(new Platform("1.png"), 147, 380);
        addObject(new Platform("1.png"), 670, 380);
        addObject(new Platform("7.png"), 400, 480);
        addObject(new Platform("7.png"), 920, 480);
        addObject(new Platform("1.png"), 640, 380);
        addObject(new Platform("1.png"), 1144, 380);
        addObject(new Platform("elevador.png"), 320, 280);
        addObject(new Platform("elevador.png"), 1020, 280);
        addObject(new Platform("elevador.png"), 670, 550);
    }
    
    private void addZombies() {
        addObject(new Zombie_basic(), 670, 350);
        addObject(new Zombie_basic(), 670, 155);
        addObject(new Zombie_basic(), 147, 350);
        addObject(new Zombie_basic(), 1144, 350);
        addObject(new Zombie_basic(), 980, 155);
        addObject(new Zombie_basic(), 380, 155);
        addObject(new Zombie_basic(), 144, 55);
        addObject(new Zombie_basic(), 1144, 55);
        addObject(new Zombie_basic(), 400, 445);
        addObject(new Zombie_basic(), 920, 445);
    }

    private void addMedicKit() {
        MedicKit medicKit = new MedicKit();
        addObject(medicKit, 670, 532);
    }

    public void act() {
        // Verifica se a tecla 'esc' foi pressionada para abrir o menu de pausa
        if (Greenfoot.isKeyDown("escape")) {
            BackgroundMusicManager.stopMusic();  // Para a música ao pausar o jogo
            Greenfoot.setWorld(new PauseMenu(this));  // Pausa o jogo e abre o menu de pausa
        }

        checkForZombies();
    }

    public void checkForZombies() {
        if (getObjects(Zombie_basic.class).isEmpty()) {
            doorActive = true;
        }
    }

    public boolean isDoorActive() {
        return doorActive;
    }

    public ScoreCounter getScoreCounter() {
        return scoreCounter;
    }
}
