import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class HealthBar extends Actor
{
    public GreenfootImage[] healthImages = new GreenfootImage[6];  // Array para armazenar as 6 imagens da barra de vida
    private int desiredWidth = 200;  // tamanho desejado da barra (largura)
    private int desiredHeight = 40;  // tamanho desejado da barra (altura)

    public HealthBar()
    {
        // Carrega as 6 imagens da barra de vida e redimensiona
        for (int i = 0; i < 6; i++) {
            healthImages[i] = new GreenfootImage("healthbar" + i + ".png");
            healthImages[i].scale(desiredWidth, desiredHeight);  // Redimensiona as imagens para o tamanho desejado
        }
        setImage(healthImages[5]);  // Define a barra cheia inicialmente
    }

    /**
     * Atualiza a barra de vida de acordo com o valor atual da vida
     */
    public void updateHealth(int health)
    {
        if (health >= 1 && health <= 6) {
            setImage(healthImages[health - 1]);  // Atualiza a imagem conforme o valor de vida
        }
    }
}
