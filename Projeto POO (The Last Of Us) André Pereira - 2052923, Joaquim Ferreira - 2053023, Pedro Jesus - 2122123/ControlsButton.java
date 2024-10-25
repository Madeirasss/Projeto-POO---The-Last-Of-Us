import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class ControlsButton extends Button
{
    public ControlsButton()
    {
        super("Controls", Color.DARK_GRAY, Color.GRAY);  // Define as cores do botão
    }
    
    @Override
    public void onClick()
    {
        // Ao clicar no botão Controls, abre a janela de controles no mundo
        getWorld().addObject(new ControlsWindow(), getWorld().getWidth() / 2, getWorld().getHeight() / 2);
    }
}
