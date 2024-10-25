import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class MedicKit extends Actor {
    public MedicKit() {
        // Carrega e ajusta o tamanho da imagem do kit médico
        GreenfootImage image = new GreenfootImage("medic_kit.png");
        image.scale(50, 50); 
        setImage(image);
    }

    public void act() {
        checkForPlayer();
    }

    private void checkForPlayer() {
        // Verifica se o Player1 está em cima do Medic Kit
        Player1 player1 = (Player1) getOneIntersectingObject(Player1.class);
        Player2 player2 = (Player2) getOneIntersectingObject(Player2.class);

        boolean shouldRemove = false; // Variável para controlar a remoção do MedicKit

        if (player1 != null && player1.getHealth() < player1.getMaxHealth()) {
            player1.regenerateHealth();  // Regenera a vida de Player1
            shouldRemove = true;  // Marca para remover o MedicKit
        }

        // Verifica se o Player2 está em cima do Medic Kit
        if (player2 != null && player2.getHealth() < player2.getMaxHealth()) {
            player2.regenerateHealth();  // Regenera a vida de Player2
            shouldRemove = true;  // Marca para remover o MedicKit
        }

        // Remove o MedicKit e toca o som se foi utilizado por um dos jogadores
        if (shouldRemove) {
            Greenfoot.playSound("regeneratehealth.wav");  // Toca o som de regeneração
            getWorld().removeObject(this);  // Remove o MedicKit do mundo
        }
    }
}
