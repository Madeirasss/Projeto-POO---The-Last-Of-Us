import greenfoot.*;

public class PauseMenu extends World
{
    private World previousWorld;

    public PauseMenu(World previousWorld)
    {
        super(previousWorld.getWidth(), previousWorld.getHeight(), 1);
        this.previousWorld = previousWorld;

        // Usa o fundo do mundo anterior
        GreenfootImage background = new GreenfootImage(previousWorld.getBackground());
        setBackground(background);

        // Adiciona os botões no menu de pausa
        addObject(new ResumeButton(previousWorld), getWidth() / 2, 200);
        addObject(new MainMenuButton(), getWidth() / 2, 300);
        addObject(new ExitButton(this), getWidth() / 2, 400);
    }
}
