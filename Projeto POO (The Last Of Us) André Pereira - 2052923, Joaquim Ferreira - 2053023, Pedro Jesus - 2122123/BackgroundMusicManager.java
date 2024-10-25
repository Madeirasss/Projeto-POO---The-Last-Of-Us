import greenfoot.*;

public class BackgroundMusicManager {
    private static GreenfootSound backgroundMusic = new GreenfootSound("background_music.mp3");

    // Método para tocar a música de fundo
    public static void playMusic() {
        if (!backgroundMusic.isPlaying()) {
            backgroundMusic.playLoop();  // Toca a música em loop
        }
    }

    // Método para parar a música de fundo
    public static void stopMusic() {
        if (backgroundMusic.isPlaying()) {
            backgroundMusic.stop();
        }
    }
}
