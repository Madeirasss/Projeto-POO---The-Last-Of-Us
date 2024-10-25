import greenfoot.*;  // (World, Actor, GreenfootImage, GreenfootSound, and MouseInfo)

public class Cutscene extends World
{
    private GreenfootSound cutsceneAudio;  // Variável para o som da cutscene
    private int audioDuration;  // Duração do áudio (em frames)
    private int framesPlayed = 0;  // Contador de frames

    public Cutscene()
    {    
        super(1300, 600, 1);  // Define o tamanho do mundo

        // Carrega o fundo preto
        GreenfootImage blackBackground = new GreenfootImage(getWidth(), getHeight());
        blackBackground.setColor(Color.BLACK);
        blackBackground.fill();
        setBackground(blackBackground);

        // Carrega e inicia o áudio da cutscene
        cutsceneAudio = new GreenfootSound("cutscene_audio.mp3");  // Substitua pelo nome do seu arquivo de áudio
        cutsceneAudio.play();

        // Defina a duração do áudio em frames (22 segundos * 60 frames por segundo)
        audioDuration = 22 * 60;  // 22 segundos
    }

    public void act()
    {
        framesPlayed++;
        
        // Atualiza as legendas de acordo com o momento da cutscene
        showSubtitles();
        
        // Exibe a dica para pular a cutscene
        showSkipHint();
        
        // Verifica se o jogador pressiona 'Space' para pular a cutscene
        if (Greenfoot.isKeyDown("space")) {
            cutsceneAudio.stop();  // Para o áudio
            Greenfoot.setWorld(new FirstLevel(6, 6, 0));  // Pula para o primeiro nível com o terceiro argumento
        }

        // Quando o áudio terminar ou a duração tiver sido alcançada, vá para o primeiro nível
        if (!cutsceneAudio.isPlaying() || framesPlayed >= audioDuration) {
            cutsceneAudio.stop();  // Para o áudio se ainda estiver tocando
            Greenfoot.setWorld(new FirstLevel(6, 6, 0));  // Inicia o primeiro nível com o terceiro argumento
        }
    }

    // Exibe as legendas baseadas no contador de frames
    private void showSubtitles() {
        if (framesPlayed < 60) {
            showText("", getWidth() / 2, getHeight() - 50);  // Nenhuma legenda no começo
        }
        else if (framesPlayed < 180) {
            showText("Joel: That was too damn close.", getWidth() / 2, getHeight() - 50);  // 1 a 3 segundos
        }
        else if (framesPlayed < 230) {
        showText("", getWidth() / 2, getHeight() - 50);  // Nenhuma legenda no começo
        }
        else if (framesPlayed < 470) {
            showText("Ellie: To the edge of the universe and back. Endure and survive.", getWidth() / 2, getHeight() - 50);  // 3 a 5 segundos
        }
        else if (framesPlayed < 600) {
            showText("Joel: Excuse me?", getWidth() / 2, getHeight() - 50);  // 5 a 8 segundos
        }
        else if (framesPlayed < 725) {
            showText("Ellie: Savage Starlight.", getWidth() / 2, getHeight() - 50);  // 8 a 10 segundos
        }
        else if (framesPlayed < 925) {
            showText("That comic I've been reading? It's what the hero says after a big battle.", getWidth() / 2, getHeight() - 50);  // 8 a 10 segundos
        }
        else if (framesPlayed < 1100) {
            showText("Joel: Endure and survive?", getWidth() / 2, getHeight() - 50);  // 8 a 10 segundos
        }
        else if (framesPlayed < 1120) {
            showText("Ellie: Yeah", getWidth() / 2, getHeight() - 50);  // 8 a 10 segundos
        }
        else if (framesPlayed < 1180) {
        showText("", getWidth() / 2, getHeight() - 50);  // Nenhuma legenda no começo
        }
        else if (framesPlayed < 1280) {
        showText("Joel: Okay", getWidth() / 2, getHeight() - 50);  // 8 a 10 segundos
        }
        else {
            showText("", getWidth() / 2, getHeight() - 50);  // Nenhuma legenda após o fim
        }
    }
    
        // Exibe o texto de dica para pular a cutscene no canto superior direito
        private void showSkipHint()
        {
        GreenfootImage skipHint = new GreenfootImage("Pressione 'Space' para pular", 24, Color.WHITE, new Color(0, 0, 0, 0));  // Texto branco com fundo transparente
        getBackground().drawImage(skipHint, getWidth() - skipHint.getWidth() - 10, 10);  // Desenha o texto no canto superior direito
    }
}
