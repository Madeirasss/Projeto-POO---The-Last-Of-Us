import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

public class MovingPlatform extends Actor
{
    private int startX, startY;  // Posições iniciais da plataforma
    private int targetY;         // Posição final para onde a plataforma vai se mover
    private int speed = 2;       // Velocidade da plataforma

    public MovingPlatform(String imageName, int startX, int startY, int targetY) {
        // Carrega e define a imagem da plataforma móvel
        GreenfootImage platformImage = new GreenfootImage(imageName);
        setImage(platformImage);

        // Armazena a posição inicial e final
        this.startX = startX;
        this.startY = startY;
        this.targetY = targetY;

        // Define a posição inicial da plataforma
        setLocation(startX, startY);
    }

    public void act()
    {
        // Verifica se algum botão que controla essa plataforma está pressionado
        if (isAnyButtonPressed()) {
            // Levanta a plataforma até o targetY
            liftPlatform();
        } else {
            // Volta a plataforma para a posição inicial
            lowerPlatform();
        }
    }

    public void liftPlatform() {
        if (getY() > targetY) {
            setLocation(getX(), getY() - speed);
        }
    }

    public void lowerPlatform() {
        if (getY() < startY) {
            setLocation(getX(), getY() + speed);
        }
    }

    // Método que verifica se algum botão que controla essa plataforma está pressionado
    private boolean isAnyButtonPressed() {
        List<ButtonPlayer> buttons = getWorld().getObjects(ButtonPlayer.class);  // Obtém todos os botões
        for (ButtonPlayer button : buttons) {
            if (button.isControllingPlatform(this) && button.isPressed()) {
                return true;  // Se algum botão que controla esta plataforma estiver pressionado, retorna true
            }
        }
        return false;  // Se nenhum botão estiver pressionado, retorna false
    }
}
