import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public abstract class Button extends Actor {
    private String label;
    private Color backgroundColor;
    private Color hoverColor;
    private boolean isHovered = false;

    public Button(String label, Color backgroundColor, Color hoverColor) {
        this.label = label;
        this.backgroundColor = backgroundColor;
        this.hoverColor = hoverColor;
        updateImage(backgroundColor);  // Define a imagem inicial do botão
    }

    public void act() {
        // Verifica se o rato está sobre o botão
        if (Greenfoot.mouseMoved(this) && !isHovered) {
            isHovered = true;
            updateImage(hoverColor);  // Muda a cor do botão quando o rato está sobre ele
        } else if (Greenfoot.mouseMoved(null) && isHovered) {
            isHovered = false;
            updateImage(backgroundColor);  // Volta à cor original quando o rato sai
        }

        // Verifica se o botão foi clicado
        if (Greenfoot.mouseClicked(this)) {
            Greenfoot.playSound("buttonclick.wav");  // Toca o som do clique
            onClick();  // Ação a ser executada quando o botão é clicado
        }
    }

    // Atualiza a imagem do botão com as cores e o texto
    private void updateImage(Color color) {
        GreenfootImage image = new GreenfootImage(200, 60);  // Define o tamanho do botão
        image.setColor(color);  // Cor de fundo
        image.fill();  // Preenche o fundo

        // Desenha a borda do botão
        image.setColor(Color.WHITE);  // Cor da borda
        image.drawRect(0, 0, 199, 59);  // Borda em torno do botão

        // Define o texto no centro do botão
        GreenfootImage text = new GreenfootImage(label, 30, Color.WHITE, new Color(0, 0, 0, 0));  // Texto branco sem fundo
        image.drawImage(text, (image.getWidth() - text.getWidth()) / 2, (image.getHeight() - text.getHeight()) / 2);

        setImage(image);  // Define a nova imagem como o botão
    }

    // Método abstrato para definir o comportamento ao clicar
    public abstract void onClick();
}
