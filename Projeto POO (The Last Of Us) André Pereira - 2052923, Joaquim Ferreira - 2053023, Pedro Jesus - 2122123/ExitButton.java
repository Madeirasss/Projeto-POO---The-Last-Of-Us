import greenfoot.*;

public class ExitButton extends Button
{
    private World currentWorld;

    public ExitButton(World world)
    {
        super("Exit Game", Color.RED, Color.DARK_GRAY);  // Define o texto e as cores do botão
        this.currentWorld = world;  // Armazena o mundo atual
    }

    @Override
    public void onClick()
    {
        // Passa o mundo atual para o ConfirmExitMenu para permitir voltar a ele
        Greenfoot.setWorld(new ConfirmExitMenu(currentWorld));  
    }
}
