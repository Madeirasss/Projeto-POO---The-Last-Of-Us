import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Door extends Actor
{
    public Door() {
        // Carrega a imagem da porta
        GreenfootImage image = new GreenfootImage("door.png");
        
        // Define o tamanho desejado para a porta (ajuste esses valores conforme necessário)
        int desiredWidth = 50;  // Largura desejada
        int desiredHeight = 80;  // Altura desejada
        
        // Redimensiona a imagem para o tamanho desejado
        image.scale(desiredWidth, desiredHeight);
        
        // Define a imagem redimensionada para a porta
        setImage(image);
    }

    public void act()
    {
        // Verifica se Player1 e Player2 estão ambos colidindo com a porta
        Player1 player1 = (Player1) getOneIntersectingObject(Player1.class);
        Player2 player2 = (Player2) getOneIntersectingObject(Player2.class);

        if (player1 != null && player2 != null) {  // Só continua se ambos os jogadores estiverem na porta
            World world = getWorld();

            // Verifica o estado da porta, seja no FirstLevel ou no SecondLevel
            if (world instanceof FirstLevel) {
                FirstLevel firstWorld = (FirstLevel) world;
                if (firstWorld.isDoorActive()) {
                    // Obtém a vida atual de cada jogador
                    int player1Health = player1.getHealth();
                    int player2Health = player2.getHealth();
                    
                    // Obtém a pontuação atual do FirstLevel
                    int score = firstWorld.getScoreCounter().getScore();
                    
                    // Transfere a pontuação e as vidas para o SecondLevel
                    Greenfoot.setWorld(new SecondLevel(player1Health, player2Health, score));
                }
            } else if (world instanceof SecondLevel) {
                SecondLevel secondWorld = (SecondLevel) world;
                if (secondWorld.isDoorActive()) {
                    // Obtém a vida atual de cada jogador
                    int player1Health = player1.getHealth();
                    int player2Health = player2.getHealth();

                    // Obtém a pontuação atual do SecondLevel
                    int score = secondWorld.getScoreCounter().getScore();

                    // Transfere a pontuação acumulada e a vida dos jogadores para o Boss_Room
                    Greenfoot.setWorld(new Boss_Room(player1Health, player2Health, score));
                }
            }
        }
    }
}
