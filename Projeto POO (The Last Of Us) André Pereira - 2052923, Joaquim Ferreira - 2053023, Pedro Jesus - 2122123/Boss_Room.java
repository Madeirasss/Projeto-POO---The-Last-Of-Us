import greenfoot.*;

// Classe que representa a sala do Boss no jogo
public class Boss_Room extends World {
    // Contadores de granadas e pontuação
    private GrenadeCounter grenadeCounter1;
    private GrenadeCounter grenadeCounter2;
    private ScoreCounter scoreCounter;
    private boolean bossDefeated = false; // Controle para verificar se o Boss foi derrotado

    // Construtor da sala do Boss
    public Boss_Room(int player1Health, int player2Health, int initialScore) {
        super(1300, 600, 1); // Define o tamanho do mundo

        // Configura a imagem de fundo
        GreenfootImage background = new GreenfootImage("level1.jpg");
        background.scale(getWidth(), getHeight());
        setBackground(background);

        // Inicia a música de fundo
        BackgroundMusicManager.playMusic();

        // Cria e adiciona o contador de pontuação na tela
        scoreCounter = new ScoreCounter(initialScore);
        addObject(scoreCounter, 670, 10);

        // Cria e adiciona os jogadores na posição inicial
        Player1 player1 = new Player1(player1Health);
        addObject(player1, 100, 435);

        Player2 player2 = new Player2(player2Health);
        addObject(player2, 1200, 435);

        // Cria e adiciona o Boss na posição inicial
        Boss boss = new Boss(this);
        addObject(boss, 649, 435);

        // Cria e adiciona as healthbars dos jogadores e do Boss
        HealthBar healthBar1 = new HealthBar();
        addObject(healthBar1, 120, 20);
        player1.setHealthBar(healthBar1);

        HealthBar healthBar2 = new HealthBar();
        addObject(healthBar2, 1180, 20);
        player2.setHealthBar(healthBar2);

        BossHB bossHB = new BossHB(20); // HealthBar do boss com 20 pontos de vida
        addObject(bossHB, 650, 560);
        boss.setHealthBar(bossHB);

        prepare(); // Configura elementos adicionais da sala
    }

    // Método para criar e adicionar um portal na sala quando o boss é derrotado
    public void spawnPortal() {
        Portal portal = new Portal();
        addObject(portal, 650, 210);
    }

    // Método que prepara plataformas, medic kits, granadas e zombies
    private void prepare() {
        // Cria e adiciona as plataformas em posições específicas
        Platform plataforma1 = new Platform("8.png");
        addObject(plataforma1, 1000, 510);
        Platform plataforma2 = new Platform("8.png");
        addObject(plataforma2, 499, 510);
        Platform plataforma3 = new Platform("1_invertido.png");
        addObject(plataforma3, 147, 503);
        Platform plataforma4 = new Platform("1_invertido.png");
        addObject(plataforma4, 0, 390);
        Platform plataforma5 = new Platform("1.png");
        addObject(plataforma5, 1300, 390);
        Platform plataforma6 = new Platform("8.png");
        addObject(plataforma6, 650, 150);
        Platform plataforma7 = new Platform("1_invertido.png");
        addObject(plataforma7, 300, 270);
        Platform plataforma8 = new Platform("1_invertido.png");
        addObject(plataforma8, 1000, 270);
        Platform plataforma9 = new Platform("elevador.png");
        addObject(plataforma9, 650, 270);
        Platform plataforma10 = new Platform("elevador.png");
        addObject(plataforma10, 510, 330);
        Platform plataforma11 = new Platform("elevador.png");
        addObject(plataforma11, 790, 330);

        // Adiciona medic kits em locais específicos
        MedicKit medicKit1 = new MedicKit();
        addObject(medicKit1, 1250, 365);
        MedicKit medicKit2 = new MedicKit();
        addObject(medicKit2, 50, 365);
        MedicKit medicKit3 = new MedicKit();
        addObject(medicKit3, 510, 305);
        MedicKit medicKit4 = new MedicKit();
        addObject(medicKit4, 790, 305);

        // Adiciona granadas em locais específicos
        GrenadePickup grenade1 = new GrenadePickup();
        addObject(grenade1, 500, 122);
        GrenadePickup grenade2 = new GrenadePickup();
        addObject(grenade2, 670, 122);
        GrenadePickup grenade3 = new GrenadePickup();
        addObject(grenade3, 840, 122);

        // Inicializa e adiciona contadores de granadas para os jogadores
        grenadeCounter1 = new GrenadeCounter(0);
        addObject(grenadeCounter1, 80, 60);
        grenadeCounter2 = new GrenadeCounter(0);
        addObject(grenadeCounter2, 1220, 60);

        // Adiciona zombies na sala em locais específicos
        addZombies();
    }

    // Método que adiciona zombies na sala em locais definidos
    private void addZombies() {
        addObject(new Zombie_basic(), 650, 110);
        addObject(new Zombie_basic(), 360, 110);
        addObject(new Zombie_basic(), 950, 110);
        addObject(new Zombie_basic(), 300, 235);
        addObject(new Zombie_basic(), 1000, 235);
    }

    // Métodos para obter os contadores de granadas e pontuação para os jogadores
    public GrenadeCounter getGrenadeCounter1() {
        return grenadeCounter1;
    }

    public GrenadeCounter getGrenadeCounter2() {
        return grenadeCounter2;
    }

    public ScoreCounter getScoreCounter() {
        return scoreCounter;
    }

    // Método que é chamado quando o boss é derrotado, cria um portal apenas uma vez
    public void bossDefeated() {
        if (!bossDefeated) {
            spawnPortal();
            bossDefeated = true; // Marca que o boss foi derrotado
        }
    }
}
