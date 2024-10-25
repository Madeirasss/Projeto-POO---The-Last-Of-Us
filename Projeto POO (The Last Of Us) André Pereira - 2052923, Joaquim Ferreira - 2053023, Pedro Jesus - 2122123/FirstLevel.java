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

        // Configura a imagem de fundo
        GreenfootImage background = new GreenfootImage("level1_1.jpg");
        background.scale(getWidth(), getHeight());
        setBackground(background);

        // Inicia a música de fundo do nível
        BackgroundMusicManager.playMusic();

        // Adiciona uma porta que permite avançar de nível quando ativa
        Door door = new Door();
        addObject(door, 1280, 52);

        // Inicializa e posiciona os jogadores
        player1 = new Player1(player1Health);
        addObject(player1, 100, 500);

        player2 = new Player2(player2Health);
        addObject(player2, 1200, 500);

        // Cria e adiciona as Healthbars dos jogadores
        HealthBar healthBar1 = new HealthBar();
        addObject(healthBar1, 80, 15);
        player1.setHealthBar(healthBar1);
        healthBar1.updateHealth(player1Health);

        HealthBar healthBar2 = new HealthBar();
        addObject(healthBar2, 235, 15);
        player2.setHealthBar(healthBar2);
        healthBar2.updateHealth(player2Health);

        // Adiciona plataformas, zombies e medic kits ao nível
        addPlatforms();
        addZombies();
        addMedicKit();

        // Adiciona o contador de pontos no centro da tela
        scoreCounter = new ScoreCounter(initialScore);
        addObject(scoreCounter, 670, 10);
    }

    // Método que adiciona plataformas no nível em locais específicos
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
    
    // Método que adiciona zombies em locais específicos do nível
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

    // Método que adiciona um medic kit no nível
    private void addMedicKit() {
        MedicKit medicKit = new MedicKit();
        addObject(medicKit, 670, 532);  // Coloca o medic kit na posição desejada
    }

    // Método de atualização executado a cada frame do jogo
    public void act() {
        // Verifica se a tecla 'esc' foi pressionada para abrir o menu de pausa
        if (Greenfoot.isKeyDown("escape")) {
            Greenfoot.setWorld(new PauseMenu(this));  // Pausa o jogo e abre o menu de pausa
        }

        // Verifica se todos os zombies foram derrotados
        checkForZombies();
    }

    // Verifica se todos os zombies foram derrotados; ativa a porta se não houver mais zombies
    public void checkForZombies() {
        if (getObjects(Zombie_basic.class).isEmpty()) {
            doorActive = true; // Ativa a porta se todos os zumbis forem derrotados
        }
    }

    // Retorna se a porta está ativa (ou seja, se os jogadores podem avançar)
    public boolean isDoorActive() {
        return doorActive;
    }

    // Retorna o contador de pontos para atualização de pontuação em outras classes
    public ScoreCounter getScoreCounter() {
        return scoreCounter;
    }
}
