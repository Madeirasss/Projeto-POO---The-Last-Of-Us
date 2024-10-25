import greenfoot.*;

public class ExitConfirmButton extends Button
{
    public ExitConfirmButton()
    {
        super("Sair", Color.RED, Color.DARK_GRAY);  // Define o texto e as cores do botão
    }

    @Override
    public void onClick()
    {
        Greenfoot.stop();  // Para o jogo e simula o "sair"
    }
}
