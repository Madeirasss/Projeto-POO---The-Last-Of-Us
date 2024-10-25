import greenfoot.*;  // (World, Actor, GreenfootImage, GreenfootSound, and MouseInfo)

public class GrenadeCounter extends Actor
{
    private int grenades;  // Quantidade de granadas
    private GreenfootImage icon;  // Ícone da granada
    private GreenfootImage text;  // Imagem de texto para o contador

    // Construtor que define o número inicial de granadas
    public GrenadeCounter(int initialGrenades) {
        grenades = initialGrenades;

        // Carrega a imagem da granada para o ícone
        icon = new GreenfootImage("granada.png");
        icon.scale(40, 40);  // Ajusta o tamanho do ícone

        // Atualiza o contador para exibir o número inicial de granadas
        updateCounter();
    }

    // Atualiza o contador exibindo o ícone e o número de granadas
    public void updateCounter() {
        GreenfootImage counterImage = new GreenfootImage(80, 40);  // Define o tamanho da área do contador (ícone + texto)

        // Desenha o ícone da granada no contador
        counterImage.drawImage(icon, 0, 0);

        // Atualiza o texto com o número de granadas
        text = new GreenfootImage("x " + grenades, 20, Color.WHITE, new Color(0, 0, 0, 0));  // Texto branco com fundo transparente
        counterImage.drawImage(text, 45, 10);  // Desenha o texto ao lado do ícone

        // Define a imagem atualizada como a imagem do ator
        setImage(counterImage);
    }

    // Método para adicionar uma granada
    public void addGrenade() {
        grenades++;  // Incrementa o número de granadas
        updateCounter();  // Atualiza a imagem do contador
    }

    // Método para usar (diminuir) uma granada
    public void useGrenade() {
        if (grenades > 0) {
            grenades--;  // Decrementa o número de granadas
            updateCounter();  // Atualiza a imagem do contador
        }
    }

    // Método para obter a quantidade de granadas
    public int getGrenades() {
        return grenades;  // Retorna o número atual de granadas
    }
}
