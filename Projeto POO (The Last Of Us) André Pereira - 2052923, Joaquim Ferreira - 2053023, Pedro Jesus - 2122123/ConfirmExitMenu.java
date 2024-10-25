import greenfoot.*;

public class ConfirmExitMenu extends World
{
    private World previousWorld;  // Referência ao mundo anterior

    public ConfirmExitMenu(World previousWorld)
    {
        super(1300, 600, 1);  // Define o tamanho do mundo     
        this.previousWorld = previousWorld;  // Armazena o mundo anterior

        // Define o fundo como branco
        GreenfootImage background = new GreenfootImage("level2.png");
        background.scale(getWidth(), getHeight());
        setBackground(background);

        // Adiciona os botões "Sair" e "Não"
        addObject(new ExitConfirmButton(), 670, 250);  // Botão para sair do jogo
        addObject(new CancelExitButton(previousWorld), 670, 350);  // Botão para cancelar a saída
    }
}
