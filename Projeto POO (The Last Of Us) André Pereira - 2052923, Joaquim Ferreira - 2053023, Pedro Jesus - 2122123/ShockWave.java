import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class ShockWave extends Actor {
    private int speed;  // Verlocidade da ShockWave
    public int currentImage = 0;
    public int animationSpeed = 6;
    public int animationCounter = 0;
    
    public GreenfootImage[] SWImages = new GreenfootImage[6];

    public ShockWave(int speed) {
        this.speed = speed; 
        loadImages(SWImages, "ShockWave", 6);  // Carrega as imagens para as animações
    }

    public void act() {
        if (getWorld() == null) {
            return;
        }

        move(speed);  // Move o projetil baseado na velocidade
        checkPlayerCollision();  // Verifica se tem colisao com o player
        checkWorldEdge();  // Verifica se o projetil chegou ao fim do mapa
        animateSW();  // Anima a ShockWave
    }

    // Remove a shockwave se chegar ao fim do mapa
    public void checkWorldEdge() {
        if (getWorld() == null) {
            return;  
        }
        if (getX() <= 0 || getX() >= getWorld().getWidth() - 1) {
            removeShockWave();  
        }
    }

    public void removeShockWave() {
        if (getWorld() != null) {
            getWorld().removeObject(this);  // Remove o objeto do mapa
        }
    }

    public void checkPlayerCollision() {
        if (getWorld() == null) {
            return; 
        }

        Player1 player1 = (Player1) getOneIntersectingObject(Player1.class);
        Player2 player2 = (Player2) getOneIntersectingObject(Player2.class);
        
        // Reduz a vida quando a shockwave bate no player
        if (player1 != null) {
            player1.reduceHealth(2);  // Reduz a vida do jogador (-2) quando a shockwave acerta no player
        }
        
        if (player2 != null) {
            player2.reduceHealth(2);  // Reduz a vida do jogador (-2) quando a shockwave acerta no player
        }
    }
    
    public void loadImages(GreenfootImage[] imageArray, String baseName, int count) {
        for (int i = 0; i < count; i++) {
            imageArray[i] = new GreenfootImage(baseName + i + ".png");
            imageArray[i].scale(100, 130);
        }
    }
    
    public void animateSW() {
        if (getWorld() == null) {
            return; 
        }
        
        animationCounter++;
        if (animationCounter >= animationSpeed) {
            animationCounter = 0;
            currentImage = (currentImage + 1) % SWImages.length;
            setImage(SWImages[currentImage]);
        }
    }
}