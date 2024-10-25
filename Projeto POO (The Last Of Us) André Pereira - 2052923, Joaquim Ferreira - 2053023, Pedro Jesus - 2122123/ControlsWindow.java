import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class ControlsWindow extends Actor
{
    public ControlsWindow()
    {
        // Define o tamanho e cria a imagem de fundo da janela
        GreenfootImage window = new GreenfootImage(450, 350);  // Define o tamanho da janela
        window.setColor(new Color(220, 220, 220));  // Cor de fundo da janela
        window.fill();  // Preenche o fundo
        
        // Desenha uma borda dupla para melhorar o design
        window.setColor(Color.DARK_GRAY);
        window.drawRect(0, 0, window.getWidth() - 1, window.getHeight() - 1);
        window.drawRect(1, 1, window.getWidth() - 3, window.getHeight() - 3);
        
        // Adiciona o título "Controls"
        GreenfootImage title = new GreenfootImage("Game Controls", 36, Color.BLACK, new Color(0, 0, 0, 0));
        window.drawImage(title, (window.getWidth() - title.getWidth()) / 2, 10);
        
        // Adiciona subtítulos para cada jogador
        GreenfootImage player1Title = new GreenfootImage("Player 1", 28, Color.BLUE, new Color(0, 0, 0, 0));
        GreenfootImage player2Title = new GreenfootImage("Player 2", 28, Color.RED, new Color(0, 0, 0, 0));
        
        // Posições para os títulos
        window.drawImage(player1Title, 85, 60);
        window.drawImage(player2Title, 285, 60);
        
        // Adiciona controles para Player 1
        GreenfootImage player1Controls = new GreenfootImage(
            "W: Jump\nD: Move Right\nA: Move Left\nQ: Shoot\nG: Throw Grenade", 
            22, Color.BLACK, new Color(0, 0, 0, 0)
        );
        window.drawImage(player1Controls, 50, 100);
        
        // Adiciona controles para Player 2
        GreenfootImage player2Controls = new GreenfootImage(
            "↑: Jump\n→: Move Right\n←: Move Left\nEnter: Shoot\nP: Throw Grenade", 
            22, Color.BLACK, new Color(0, 0, 0, 0)
        );
        window.drawImage(player2Controls, 250, 100);
        
        // Define a imagem da janela de controle como sendo a do ator
        setImage(window);
    }
    
    // Permite fechar a janela ao clicar fora dela (opcional)
    public void act()
    {
        if (Greenfoot.mouseClicked(null)) {
            getWorld().removeObject(this);  // Fecha a janela ao clicar fora dela
        }
    }
}
