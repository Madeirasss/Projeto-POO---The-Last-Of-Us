import greenfoot.*;

public class MainMenuButton extends Button
{
    public MainMenuButton()
    {
        super("Main Menu", Color.GRAY, Color.DARK_GRAY);
    }

    @Override
    public void onClick()
    {
        Greenfoot.setWorld(new MainMenu());  // Vai para o menu principal
    }
}
