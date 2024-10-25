import greenfoot.*;

public class Player1 extends Actor
{
    private int vSpeed = 0;
    private int acceleration = 1;
    private int jumpHeight = -17;
    public int bulletSpeed = 5;
    public boolean Shooting = false;

    private int health = 6;
    private final int maxHealth = 6;
    private HealthBar healthBar;
    private int invulnerabilityTime = 60;
    private int invulnerableCounter = 0;
    private boolean isDead = false;
    private boolean facingRight = true;

    // Animação
    private GreenfootImage[] walkingRightImages = new GreenfootImage[10];
    private GreenfootImage[] walkingLeftImages = new GreenfootImage[10];
    private GreenfootImage shootingRightImage;
    private GreenfootImage shootingLeftImage;
    private GreenfootImage idleRightImage;
    private GreenfootImage idleLeftImage;
    private int imageIndex = 0;
    private int frameCounter = 0;
    private int animationSpeed = 5;
    private int animationCounter = 0;
    private int grenadeSpeed = 10;

    // Adicionando uma variável de cooldown para o lançamento de granada
    private int grenadeCooldown = 0;  // Tempo de recarga entre lançamentos de granadas
    private final int grenadeCooldownTime = 30;  // Tempo de cooldown
    private boolean isMarkedForRemoval = false;  // Flag para marcar o jogador para remoção

    public Player1(int health)
    {
        this.health = health;
        prepareImages();
        setImage(idleRightImage);
    }

    private void prepareImages()
    {
        for (int i = 0; i < 10; i++) {
            walkingRightImages[i] = new GreenfootImage("EllyRunRight" + i + ".png");
            walkingLeftImages[i] = new GreenfootImage("EllyRunLeft" + i + ".png");
        }
        shootingRightImage = new GreenfootImage("EllyPewRight.png");
        shootingLeftImage = new GreenfootImage("EllyPewLeft.png");
        idleRightImage = new GreenfootImage("EllyIdleRight.png");
        idleLeftImage = new GreenfootImage("EllyIdleLeft.png");
    }

    public void setHealthBar(HealthBar hb)
    {
        healthBar = hb;
    }

public void act() {
    if (isMarkedForRemoval) {
        getWorld().removeObject(this);  // Remove o jogador quando marcado
        return;  // Impede qualquer outra ação após a remoção
    }

    if (!isDead) {
        movePlayer();
        checkFalling();
        handleInvulnerability();
        checkHitByZombie();
        checkGrenadePickup();  // Verifica se o jogador apanhou uma granada
        checkThrowGrenade();  // Verifica se o jogador quer atirar uma granada
        frameCounter++;
        animatePlayer();
    }

    // Reduz o cooldown da granada a cada frame
    if (grenadeCooldown > 0) {
        grenadeCooldown--;
    }
}

    private void checkThrowGrenade() {
        // Verifica se o cooldown terminou e se o jogador tem granadas
        if (Greenfoot.isKeyDown("p") && grenadeCooldown == 0) {  
            // Verifica se o mundo atual é uma instância de Boss_Room antes de fazer o casting
            if (getWorld() instanceof Boss_Room) {
                Boss_Room world = (Boss_Room) getWorld();
                GrenadeCounter grenadeCounter1 = world.getGrenadeCounter1();

                if (grenadeCounter1.getGrenades() > 0) {
                    throwGrenade();
                    grenadeCounter1.useGrenade();  // Remove uma granada do inventário
                    grenadeCooldown = grenadeCooldownTime;  // Reinicia o cooldown após lançar a granada
                }
            }
        }
    }

    private void throwGrenade() {
        // Adiciona uma nova granada no mundo, atirada na direção que o Player2 está olhando
        int grenadeSpeed = facingRight ? 5 : -5;  // Define a velocidade com base na direção que o jogador está olhando
        Grenade grenade = new Grenade(grenadeSpeed);  // Cria a granada com a velocidade definida
        getWorld().addObject(grenade, getX(), getY());  // Adiciona a granada na posição do Player2
    }

    public void movePlayer() {
        boolean isWalking = false;

        if (Greenfoot.isKeyDown("d")) {
            move(4);
            bulletSpeed = 5;
            facingRight = true;
            animateWalkingRight();  // Anima a caminhada para a direita
            isWalking = true;
        }
        if (Greenfoot.isKeyDown("a")) {
            move(-4);
            bulletSpeed = -5;
            facingRight = false;
            animateWalkingLeft();  // Anima a caminhada para a esquerda
            isWalking = true;
        }
        
        // Permitir pular tanto do chão quanto do bottom edge
        if (Greenfoot.isKeyDown("w") && (onGround() || atWorldEdgeBottom())) {
            vSpeed = jumpHeight;
            fall();
        }

        if (Greenfoot.isKeyDown("q") && !Shooting) {
            Greenfoot.playSound("Pew.mp3");
            if (facingRight) {
                setImage(shootingRightImage);
            } else {
                setImage(shootingLeftImage);
            }
            getWorld().addObject(new Bullet(bulletSpeed), getX(), getY());
            Shooting = true;
        }
        if (!Greenfoot.isKeyDown("q")) {
            Shooting = false;
        }

        if (!isWalking && !Shooting) {
            if (facingRight) {
                setImage(idleRightImage);
            } else {
                setImage(idleLeftImage);
            }
        }
    }

    private void animateWalkingRight() {
        if (frameCounter % animationSpeed == 0) {
            setImage(walkingRightImages[imageIndex]);
            imageIndex = (imageIndex + 1) % walkingRightImages.length;
        }
    }

    private void animateWalkingLeft() {
        if (frameCounter % animationSpeed == 0) {
            setImage(walkingLeftImages[imageIndex]);
            imageIndex = (imageIndex + 1) % walkingLeftImages.length;
        }
    }

    private void checkGrenadePickup() {
        GrenadePickup grenade = (GrenadePickup) getOneIntersectingObject(GrenadePickup.class);
        if (grenade != null) {
            getWorld().removeObject(grenade);
            Boss_Room world = (Boss_Room) getWorld();
            world.getGrenadeCounter1().addGrenade();  // Adiciona uma granada ao contador do Player2
        }
    }

    private void handleInvulnerability()
    {
        if (invulnerableCounter > 0) {
            invulnerableCounter--;
        }
    }

    private boolean isInvulnerable()
    {
        return invulnerableCounter > 0;
    }

    private void fall()
    {
        setLocation(getX(), getY() + vSpeed);
        if (vSpeed <= 12) {
            vSpeed = vSpeed + acceleration;
        }
    }

    public void checkFalling() {
        if (!onGround() && !atWorldEdgeBottom()) {
            fall();
        }
        checkPlatformCollisionAbove();  // Verifica se há colisão com a parte de baixo de uma plataforma
    }
    
    public boolean atWorldEdgeBottom() {
        if (getY() >= getWorld().getHeight() - getImage().getHeight() / 2) {
            // Define o jogador no "chão" quando atinge a borda inferior
            setLocation(getX(), getWorld().getHeight() - getImage().getHeight() / 2);
            vSpeed = 0;  // Interrompe a queda
            return true;
        }
        return false;
    }
    
        // Verifica se o jogador está colidindo com uma parede à direita ou à esquerda
    boolean atWall()
    {
        // Verifica se há uma parede à direita ou à esquerda
        Actor wallRight = getOneObjectAtOffset(getImage().getWidth() / 2, 0, Wall.class);
        Actor wallLeft = getOneObjectAtOffset(-getImage().getWidth() / 2, 0, Wall.class);

        return wallRight != null || wallLeft != null;  // Retorna true se colidiu com uma parede
    }

    // Modifica o movimento do jogador para lidar com colisões de parede
    public void move(int distance)
    {
        int originalX = getX();  // Guarda a posição X original

        // Move o jogador
        super.move(distance);

        // Se o jogador colidir com uma parede, retorna à posição original
        if (atWall())
        {
            setLocation(originalX, getY());  // Volta à posição original para evitar atravessar a parede
        }
    }
    
    // Verifica se o jogador colide com a parte inferior de uma plataforma enquanto está subindo
    private void checkPlatformCollisionAbove() {
        Actor platformAbove = getOneObjectAtOffset(0, -getImage().getHeight() / 2, Platform.class);
    
        // Se o jogador estiver colidindo com a parte inferior da plataforma enquanto sobe
        if (vSpeed < 0 && platformAbove != null) {
            vSpeed = 0;  // Para o movimento ascendente
            setLocation(getX(), getY() + 5);  // Move o jogador um pouco para baixo para evitar que ele fique preso
        }
    }  

    public boolean onGround() {
        // Verifica se há plataformas ou paredes sob o jogador
        Actor platformUnder = getOneObjectAtOffset(0, getImage().getHeight() / 2, Platform.class);
        Actor movingPlatformUnder = getOneObjectAtOffset(0, getImage().getHeight() / 2, MovingPlatform.class);
        Actor wallUnder = getOneObjectAtOffset(0, getImage().getHeight() / 2, Wall.class);

        // Verifica se o jogador está colidindo com a parte inferior de uma plataforma enquanto sobe
        Actor platformAbove = getOneObjectAtOffset(0, -getImage().getHeight() / 2, Platform.class);
        Actor movingPlatformAbove = getOneObjectAtOffset(0, -getImage().getHeight() / 2, MovingPlatform.class);

        // Se o jogador colidir com a parte inferior de uma plataforma enquanto sobe, impedir a passagem
        if (vSpeed < 0 && (platformAbove != null || movingPlatformAbove != null)) {
            // Se o jogador está subindo e colidiu com algo acima, pare o movimento de subida e empurre-o para baixo
            vSpeed = 0;
            setLocation(getX(), getY() + 5);  // Empurra levemente para baixo
        }

        // Se há uma plataforma sob o jogador, ajusta a posição e considera que está no chão
        if (platformUnder != null) {
            if (vSpeed >= 0) {  // Apenas ajusta se o jogador está caindo
                moveToGround(platformUnder);
                vSpeed = 0;  // Para de cair ao atingir a plataforma
            }
            return true;  // O jogador está no chão (sobre uma plataforma)
        } else if (movingPlatformUnder != null) {
            if (vSpeed >= 0) {  // Apenas ajusta se o jogador está caindo
                moveToGround(movingPlatformUnder);
                vSpeed = 0;  // Para de cair
            }
            return true;  // O jogador está no chão (sobre uma plataforma móvel)
        } else if (wallUnder != null) {
            if (vSpeed >= 0) {  // Apenas ajusta se o jogador está caindo
                moveToGround(wallUnder);
                vSpeed = 0;  // Para de cair
            }
            return true;  // O jogador está no chão (sobre uma parede)
        } else if (!atWorldEdgeBottom()) {
            return false;  // O jogador não está no chão, nem em uma plataforma ou parede
        } else {
            return true;  // Caso esteja no fundo do mundo
        }
    }

    public void moveToGround(Actor under)
    {
        int underHeight = under.getImage().getHeight();
        int newY = under.getY() - (underHeight + getImage().getHeight()) / 2;
        if (getY() != newY) {
            setLocation(getX(), newY);
        }
    }

private void checkHitByZombie() {
    Actor zombie = getOneIntersectingObject(Zombie_basic.class);
    
    // Verifica se o jogador colidiu com um zumbi e se ele ainda não está invulnerável
    if (zombie != null && !isInvulnerable()) {
        health--;
        
        // Atualiza a barra de vida do jogador se a barra estiver disponível
        if (healthBar != null) {
            healthBar.updateHealth(health);
        }
        
        // Ativa a invulnerabilidade por um tempo após ser atingido
        invulnerableCounter = invulnerabilityTime;
        
        // Se o jogador morrer (health <= 0)
        if (health <= 0 && !isMarkedForRemoval) {
            isMarkedForRemoval = true;  // Marca o jogador para remoção no próximo ciclo
            showGameOver();  // Chama a tela de Game Over
        }
    }
}

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void regenerateHealth() {
        health = maxHealth;
        if (healthBar != null) {
            healthBar.updateHealth(health);
        }
    }

    private void showGameOver()
    {
        World world = getWorld();
        GameOverScreen gameOver = new GameOverScreen();
        world.addObject(gameOver, world.getWidth() / 2, world.getHeight() / 2);
        Greenfoot.stop();
    }

    public void reduceHealth(int amount) {
        if (!isInvulnerable()) {
            health -= amount;
            if (healthBar != null) {
                healthBar.updateHealth(health);
            }
            invulnerableCounter = invulnerabilityTime;
            if (health <= 0) {
                isDead = true;
                showGameOver();
                getWorld().removeObject(this);
            }
        }
    }
    
    public void animatePlayer() {
        animationCounter++;

        if (Shooting) {
            // Se o player estiver atirando, mostrar a imagem de disparo
            if (facingRight) {
                setImage(shootingRightImage);
            } else {
                setImage(shootingLeftImage);
            }
        } else if (Greenfoot.isKeyDown("d")) {
            // Se o player está caminhando para a direita
            if (animationCounter % animationSpeed == 0) {
                setImage(walkingRightImages[imageIndex]);
                imageIndex = (imageIndex + 1) % walkingRightImages.length;
            }
        } else if (Greenfoot.isKeyDown("a")) {
            // Se o player está caminhando para a esquerda
            if (animationCounter % animationSpeed == 0) {
                setImage(walkingLeftImages[imageIndex]);
                imageIndex = (imageIndex + 1) % walkingLeftImages.length;
            }
        } else {
            // Se o player está parado, mostrar a imagem idle 
            if (facingRight) {
                setImage(idleRightImage);
            } else {
                setImage(idleLeftImage);
            }
        }
    }
}
