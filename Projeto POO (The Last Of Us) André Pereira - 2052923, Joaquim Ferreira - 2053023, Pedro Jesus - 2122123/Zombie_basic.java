import greenfoot.*;

// Classe que representa o zombie básico no jogo
public class Zombie_basic extends Enemys {
    private int health = 4; // Vida do zombie
    private GreenfootImage[] walkImages = new GreenfootImage[8]; // Array de imagens para a animação de caminhada
    private int currentImage = 0;
    private int animationSpeed = 5; // Velocidade da animação
    private int animationCounter = 0; 
    private int speed = 2; // Velocidade de movimento do zombie
    private boolean facingRight = true; // Controla a direção em que o zombie está a olhar

    // Construtor do zombie básico
    public Zombie_basic() {
        // Carrega as imagens de animação de caminhada e ajusta o tamanho
        for (int i = 0; i < 8; i++) {
            walkImages[i] = new GreenfootImage("zombie" + i + ".png");
            walkImages[i].scale(40, 60);
        }
        setImage(walkImages[0]); // Define a primeira imagem como inicial
    }

    // Método principal de acção executado em cada frame
    public void act() {
        animateZombie(); // Controla a animação de caminhada
        moveZombie();    // Controla o movimento
        checkHit();      // Verifica se o zombie foi atingido
    }
    
    // Verifica se o zombie foi atingido por uma bala
private void checkHit() {
    Actor bullet = getOneIntersectingObject(Bullet.class);
    if (bullet != null) {
        getWorld().removeObject(bullet);  // Remove a bala ao colidir com o zombie
        health--;  // Reduz a vida do zombie

        if (health <= 0) {
            // Toca o som de morte antes de remover o zombie
            Greenfoot.playSound("zombiedie.wav");

            World world = getWorld();
            if (world != null) {  // Verifica se o zumbi ainda está no mundo
                // Adiciona pontuação dependendo do nível
                if (world instanceof FirstLevel) {
                    FirstLevel firstWorld = (FirstLevel) world;
                    firstWorld.getScoreCounter().addScore(300);  // Adiciona 300 pontos
                } else if (world instanceof SecondLevel) {
                    SecondLevel secondWorld = (SecondLevel) world;
                    secondWorld.getScoreCounter().addScore(300);
                } else if (world instanceof Boss_Room) {
                    Boss_Room bossWorld = (Boss_Room) world;
                    bossWorld.getScoreCounter().addScore(300);
                }

                world.removeObject(this);  // Remove o zombie do mundo
            }
        }
    }
}


    // Animação de caminhada do zombie
    private void animateZombie() {
        animationCounter++;
        if (animationCounter >= animationSpeed) {
            animationCounter = 0;
            currentImage = (currentImage + 1) % walkImages.length; // Avança para a próxima imagem
            setImage(walkImages[currentImage]);
            flipDirection(); // Ajusta a direção da imagem
        }
    }

    // Movimento do zombie com mudança de direção em obstáculos
    private void moveZombie() {
        if (atWorldEdge() || atPlatformEdge() || atWall()) {
            speed = -speed; // Inverte a velocidade para mudar de direção
            facingRight = !facingRight; // Altera a direção para a qual o zombie está a olhar
        }
        move(speed); // Move o zombie de acordo com a velocidade
    }

    // Verifica se o zombie está a colidir com uma parede
    private boolean atWall() {
        Actor wall = getOneObjectAtOffset((int)Math.signum(speed) * getImage().getWidth() / 2, 0, Wall.class);
        return wall != null;
    }

    // Verifica se o zombie está na borda do mundo
    private boolean atWorldEdge() {
        return getX() <= 10 || getX() >= getWorld().getWidth() - 10;
    }

    // Verifica se o zombie está na borda de uma plataforma
    private boolean atPlatformEdge() {
        Actor below = getOneObjectAtOffset(0, getImage().getHeight() / 2, Platform.class);
        Actor edge = getOneObjectAtOffset((int)Math.signum(speed) * getImage().getWidth() / 2, getImage().getHeight() / 2 + 10, Platform.class);
        return below == null || edge == null;
    }

    // Altera a direção da imagem para corresponder à direção de movimento
    private void flipDirection() {
        if (facingRight) {
            setImage(walkImages[currentImage]); // Mostra a imagem normal se a direção for direita
        } else {
            GreenfootImage mirroredImage = new GreenfootImage(walkImages[currentImage]);
            mirroredImage.mirrorHorizontally(); // Espelha a imagem horizontalmente para virar à esquerda
            setImage(mirroredImage);
        }
    }
}
