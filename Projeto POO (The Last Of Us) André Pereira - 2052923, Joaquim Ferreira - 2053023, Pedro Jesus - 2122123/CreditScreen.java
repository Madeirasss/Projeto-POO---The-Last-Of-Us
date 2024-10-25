import greenfoot.*;

public class CreditScreen extends World {
    private int scrollSpeed = 1;  // Velocidade de rolagem do texto
    private int startY = 600;  // Ponto de partida do texto (fora da tela)
    private GreenfootImage background;
    private int totalScore;  // Para armazenar o total de pontos
    private boolean showEndMessage = false;  // Flag para mostrar a mensagem final
    private GreenfootSound endCreditsMusic;  // Música de fundo para a tela de créditos

    public CreditScreen(int totalScore) {    
        super(1300, 600, 1);  // Define o tamanho do mundo
        this.totalScore = totalScore;  // Recebe o total de pontos
        
        // Configura a tela preta de fundo
        background = new GreenfootImage(getWidth(), getHeight());
        background.setColor(Color.BLACK);
        background.fill();
        setBackground(background);

        // Inicia a música de fundo para os créditos
        endCreditsMusic = new GreenfootSound("endcredits.mp3");
        endCreditsMusic.playLoop();  // Toca a música em loop

        // Cria o texto inicial fora da tela (na parte inferior)
        showCredits();
    }

    public void act() {
        // Faz o texto rolar para cima
        if (startY + 600 > 0) {
            startY -= scrollSpeed;
            showCredits();  // Atualiza a posição do texto
        } else if (!showEndMessage) {
            // Quando o texto parar de rolar, mostra a mensagem para voltar ao menu
            showEndMessage = true;
            showPressEnterMessage();
        } else if (Greenfoot.isKeyDown("enter")) {
            // Volta ao menu principal quando "Enter" é pressionado
            Greenfoot.setWorld(new MainMenu()); 
        }
    }

    // Método chamado automaticamente quando o mundo é pausado ou fechado
    @Override
    public void stopped() {
        endCreditsMusic.stop();  // Para a música quando sair do CreditScreen
    }

    private void showCredits() {
        GreenfootImage creditText = new GreenfootImage(getWidth(), getHeight() * 2);  // Duplica a altura para que o texto caiba
        creditText.setColor(Color.WHITE);  // Cor do texto
        creditText.setFont(new Font(30));  // Define o tamanho da fonte

        // Lista de créditos
        String[] credits = {
            "Você Concluiu o Jogo!",
            "CRÉDITOS",
            "Trabalho Realizado por",
            "André Pereira",
            "Joaquim Ferreira",
            "Pedro Jesus",
            "Obrigado por jogar o nosso jogo",
            "Total Score: " + totalScore  // Adiciona a pontuação total ao final da lista
        };

        // Defina as posições `x` e `y` manualmente para cada linha de texto
        int[] positionsX = {525, 595, 520, 590, 565, 595, 470, 570};  // Exemplo de posições X
        int[] positionsY = {100, 200, 300, 400, 500, 600, 700, 800};  // Exemplo de posições Y

        // Desenha o texto nas posições desejadas
        for (int i = 0; i < credits.length; i++) {
            creditText.drawString(credits[i], positionsX[i], positionsY[i]);
        }

        // Copia o texto para a posição adequada e desenha na tela
        GreenfootImage image = new GreenfootImage(background);
        image.drawImage(creditText, 0, startY);
        setBackground(image);
    }

    // Método para exibir a mensagem "Pressione 'Enter' para voltar para o Main Menu"
    private void showPressEnterMessage() {
        GreenfootImage image = new GreenfootImage(getBackground());
        image.setColor(Color.WHITE);
        image.setFont(new Font(40));  // Tamanho da fonte maior para destaque
        image.drawString("Pressione 'Enter' para voltar para o Main Menu", 250, 300);  // Posição centralizada
        setBackground(image);
    }
}
