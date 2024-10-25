import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class ButtonPlayer extends Actor
{
    private GreenfootImage redButton;
    private GreenfootImage greenButton;
    private boolean isPressed = false;
    private MovingPlatform controlledPlatform;  // A plataforma que este botão vai controlar

    public ButtonPlayer(MovingPlatform platform) {
        // Carrega as imagens do botão
        redButton = new GreenfootImage("redbutton.png");
        greenButton = new GreenfootImage("greenbutton.png");

        // Define o tamanho do botão
        redButton.scale(80, 80);
        greenButton.scale(80, 80);

        setImage(redButton);  // Começa com o botão vermelho

        // Armazena a plataforma que este botão vai controlar
        this.controlledPlatform = platform;
    }

    public void act()
    {
        // Verifica se Player1 ou Player2 estão em cima do botão
        if (getOneIntersectingObject(Player1.class) != null || getOneIntersectingObject(Player2.class) != null) {
            setImage(greenButton);
            isPressed = true;  // O botão foi pressionado
        } else {
            setImage(redButton);
            isPressed = false;
        }
    }

    // Método para verificar se o botão está pressionado
    public boolean isPressed() {
        return isPressed;
    }

    // Método para verificar se este botão controla a plataforma passada
    public boolean isControllingPlatform(MovingPlatform platform) {
        return controlledPlatform == platform;
    }
}
