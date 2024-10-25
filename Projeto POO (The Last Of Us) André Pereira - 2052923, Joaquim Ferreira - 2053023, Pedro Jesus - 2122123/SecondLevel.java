import greenfoot.*;

// Classe que representa o segundo nível do jogo
public class SecondLevel extends World {
    private boolean doorActive = false; // Controle para verificar se a porta está ativa
    private Player1 player1;
    private Player2 player2;
    private ScoreCounter scoreCounter; // Contador de pontos para os jogadores

    // Construtor do segundo nível
    public SecondLevel(int player1Health, int player2Health, int initialScore) {    
        super(1300, 600, 1); // Define o tamanho do mundo

        // Configura a imagem de fundo do nível
        GreenfootImage background = new GreenfootImage("level2.png");
        background.scale(getWidth(), getHeight());
        setBackground(background);
        
        // Toca a música de fundo do nível
        BackgroundMusicManager.playMusic();

        // Adiciona uma porta que permite avançar de nível quando ativa
        Door door = new Door();
        addObject(door, 1280, 52);

        // Adiciona o contador de pontos no centro da tela
        scoreCounter = new ScoreCounter(initialScore);
        addObject(scoreCounter, 670, 10);

        // Inicializa e posiciona os jogadores
        player1 = new Player1(player1Health);
        addObject(player1, 100, 500);

        player2 = new Player2(player2Health);
        addObject(player2, 1200, 500);

        // Cria e adiciona as healthbars dos jogadores
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
    }

    // Método que adiciona plataformas no nível em locais específicos
    private void addPlatforms() {
        addObject(new Platform("1.png"), 647, 95);
        addObject(new Platform("1.png"), 697, 95);
        addObject(new Platform("6.png"), 1180, 95);
        addObject(new Platform("4.png"), 243, 95);
        addObject(new Platform("5.png"), 820, 480);
        addObject(new Platform("5.png"), 120, 380);
        addObject(new Platform("5.png"), 10, 240);
        addObject(new Platform("5.png"), 1170, 380);
        addObject(new Platform("7.png"), 760, 280);
        addObject(new Platform("7.png"), 560, 380);
        addObject(new Platform("5.png"), 520, 480);
        addObject(new Platform("elevador.png"), 600, 580);
        addObject(new Platform("elevador.png"), 730, 580);

        // Adiciona paredes e setas indicando direção
        addObject(new Wall("9.png"), 670, 600);
        addObject(new Wall("9.png"), 670, 400);
        addObject(new StaticImage("seta.png", 50, 50), 825, 250);

        // Plataformas móveis no nível
        MovingPlatform leftPlatform = new MovingPlatform("elevador.png", 428, 280, 95);
        MovingPlatform rightPlatform = new MovingPlatform("elevador.png", 925, 280, 95);

        addObject(leftPlatform, 428, 280);
        addObject(rightPlatform, 925, 280);

        // Botões para controlar as plataformas móveis
        addObject(new ButtonPlayer(leftPlatform), 700, 265);
        addObject(new ButtonPlayer(rightPlatform), 580, 85);
        addObject(new ButtonPlayer(rightPlatform), 1105, 85);
    }

    // Método que adiciona zombies em locais específicos do nível
    private void addZombies() {
        addObject(new Zombie_basic(), 520, 445);
        addObject(new Zombie_basic(), 120, 345);
        addObject(new Zombie_basic(), 243, 55);
        addObject(new Zombie_basic(), 820, 445);
        addObject(new Zombie_basic(), 1170, 345);
        addObject(new Zombie_basic(), 760, 245);
        addObject(new Zombie_basic(), 1180, 55);
    }

    // Método que adiciona medic kits no nível
    private void addMedicKit() {
        MedicKit medicKit1 = new MedicKit();
        MedicKit medicKit2 = new MedicKit();
        addObject(medicKit1, 630, 555);
        addObject(medicKit2, 700, 555);
    }

    // Método de atualização executado a cada frame do jogo
    public void act() {
        // Verifica se a tecla 'esc' foi pressionada para abrir o menu de pausa
        if (Greenfoot.isKeyDown("escape")) {
            Greenfoot.setWorld(new PauseMenu(this)); // Pausa o jogo e abre o menu de pausa
        }

        // Verifica se todos os zombies foram derrotados
        checkForZombies();
    }

    // Verifica se todos os zombies foram derrotados e ativa a porta se não houver mais zombies
    public void checkForZombies() {
        if (getObjects(Zombie_basic.class).isEmpty()) {
            doorActive = true; // Ativa a porta se todos os zombies forem derrotados
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
