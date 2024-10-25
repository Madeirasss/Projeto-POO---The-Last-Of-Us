import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Explosion extends Actor
{
    private GreenfootImage[] explosionImages = new GreenfootImage[36];  // 36 imagens para a animação de explosão
    private int currentImage = 0;  // Índice da imagem atual
    private int animationSpeed = 2;  // Controla a velocidade da animação (ajuste conforme necessário)
    private int animationCounter = 0;

    public Explosion() {
        // Carrega as imagens da explosão
        for (int i = 0; i < 36; i++) {
            explosionImages[i] = new GreenfootImage("explosao" + i + ".png");
            explosionImages[i].scale(100, 100);  // Ajusta o tamanho da explosão (modifique conforme necessário)
        }
        setImage(explosionImages[0]);  // Define a primeira imagem da explosão
    }

    public void act() {
        animateExplosion();  // Executa a animação da explosão
    }

    private void animateExplosion() {
        animationCounter++;
        if (animationCounter % animationSpeed == 0) {
            setImage(explosionImages[currentImage]);
            currentImage++;
            if (currentImage >= explosionImages.length) {
                getWorld().removeObject(this);  // Remove a explosão quando a animação terminar
            }
        }
    }
}
