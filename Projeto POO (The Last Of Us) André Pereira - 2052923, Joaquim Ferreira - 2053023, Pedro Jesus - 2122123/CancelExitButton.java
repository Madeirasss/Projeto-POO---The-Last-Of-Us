import greenfoot.*;

public class CancelExitButton extends Button
{
    private World previousWorld;  // Referência ao mundo anterior

    public CancelExitButton(World previousWorld)
    {
        super("Não", Color.GRAY, Color.DARK_GRAY);  // Define o texto e as cores do botão
        this.previousWorld = previousWorld;  // Armazena o mundo anterior
    }

    @Override
    public void onClick()
    {
        Greenfoot.setWorld(previousWorld);  // Volta para o mundo anterior
    }
}
