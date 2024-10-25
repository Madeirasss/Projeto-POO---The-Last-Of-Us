import greenfoot.*;

public class ResumeButton extends Button
{
    private World previousWorld;

    public ResumeButton(World previousWorld)
    {
        super("Resume", Color.GRAY, Color.DARK_GRAY);
        this.previousWorld = previousWorld;
    }

    @Override
    public void onClick()
    {
        Greenfoot.setWorld(previousWorld);  // Retorna ao mundo anterior
    }
}
