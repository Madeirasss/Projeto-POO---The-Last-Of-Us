import greenfoot.*;

// Classe que representa o portal no jogo
public class Portal extends Actor {
    private GreenfootImage[] portalImages = new GreenfootImage[9]; // Array para guardar as imagens do portal
    private int imageIndex = 0; // Índice atual da imagem de animação
    private int animationSpeed = 5; // Velocidade da animação
    private int animationCounter = 0; // Contador para controlar o avanço da animação

    // Construtor do Portal
    public Portal() {
        // Carrega as imagens do portal e ajusta o tamanho
        for (int i = 0; i < portalImages.length; i++) {
            portalImages[i] = new GreenfootImage("portal" + i + ".png");
            portalImages[i].scale(100, 100); // Ajusta o tamanho da imagem
        }
        setImage(portalImages[0]); // Define a imagem inicial do portal
    }

    // Método principal de acção executado em cada frame
    public void act() {
        animatePortal(); // Controla a animação do portal
        checkPlayersOnPortal(); // Verifica se os jogadores estão no portal
    }

    // Animação do portal, alternando as imagens em sequência
    private void animatePortal() {
        animationCounter++;
        if (animationCounter >= animationSpeed) {
            animationCounter = 0; // Reinicia o contador de animação
            imageIndex = (imageIndex + 1) % portalImages.length; // Avança para a próxima imagem e faz loop
            setImage(portalImages[imageIndex]); // Define a imagem atual
        }
    }

    // Verifica se ambos os jogadores estão no portal
    private void checkPlayersOnPortal() {
        Player1 player1 = (Player1) getOneIntersectingObject(Player1.class);
        Player2 player2 = (Player2) getOneIntersectingObject(Player2.class);

        // Se ambos os jogadores estão no portal, inicia a transição para a Credit Screen
        if (player1 != null && player2 != null) {
            // Para a música de fundo ao passar pelo portal
            BackgroundMusicManager.stopMusic();

            // Obtém o mundo atual e a pontuação total
            Boss_Room bossWorld = (Boss_Room) getWorld();
            int totalScore = bossWorld.getScoreCounter().getScore();

            // Transita para a Credit Screen, passando a pontuação total
            Greenfoot.setWorld(new CreditScreen(totalScore));
        }
    }
}
