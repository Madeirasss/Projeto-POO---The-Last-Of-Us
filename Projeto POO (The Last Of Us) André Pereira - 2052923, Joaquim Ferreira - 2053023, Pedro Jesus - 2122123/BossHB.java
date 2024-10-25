import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class BossHB extends HealthBar {
    private int desiredWidth = 600; 
    private int desiredHeight = 50;
    private int maxHealth;  // Vida máxima do boss

    public BossHB(int maxHealth) {
        this.maxHealth = maxHealth;

        // Carrega e Redimensiona as imagens da healthbar
        for (int i = 0; i < 6; i++) {
            healthImages[i] = new GreenfootImage("BossHB" + i + ".png");
            healthImages[i].scale(desiredWidth, desiredHeight);  // Redimensiona as imagens
        }
        setImage(healthImages[0]);  // vida cheia
    }

    /**
     * Update the health bar based on the current health of the boss.
     */
    public void updateHB(int currentHealth) {
        // Calcula a percentagem da vida
        int healthPercentage = (int) ((currentHealth * 100.0) / maxHealth);
        
        // Atualiza a healthbar com as imagens
        if (healthPercentage >= 80) {
            setImage(healthImages[0]);  // 100%
        } else if (healthPercentage >= 60) {
            setImage(healthImages[1]);  // 80% 
        } else if (healthPercentage >= 40) {
            setImage(healthImages[2]);  // 60% 
        } else if (healthPercentage >= 20) {
            setImage(healthImages[3]);  // 40% 
        } else if (healthPercentage > 0) {
            setImage(healthImages[4]);  // 20% 
        } else {
            setImage(healthImages[5]);  // boss morre
        }
    }
}
