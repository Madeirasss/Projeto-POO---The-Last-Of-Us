import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

// Classe que representa o Boss no jogo
public class Boss extends Enemys {
    // Arrays de imagens para diferentes animações
    private GreenfootImage[] walkImages = new GreenfootImage[12];
    private GreenfootImage[] specialImages = new GreenfootImage[34];
    private GreenfootImage[] deathImages = new GreenfootImage[15];
    private GreenfootImage[] meleeImages = new GreenfootImage[14];
    
    // Controla a animação e estado do Boss
    private int currentImage = 0;
    private int animationSpeed = 6;
    private int animationCounter = 0;
    private int speed = 1;
    private boolean facingRight = true;
    private int health = 20;
    private BossHB bossHB; // Barra de vida do Boss

    private boolean isSpecial = false;
    private boolean isDying = false;
    private boolean isDead = false;
    private boolean isMeleeAttacking = false; 
    
    private int meleeAnimationCounter = 0;
    private int meleeAnimationFrames = 14;  
    private int specialAnimationFrames = 34;
    private int specialAnimationCounter = 0;
    
    private int invulnerabilityTime = 60;
    private int invulnerableCounter = 0;
    
    private int ShockWave_speed = 5;
    private Boss_Room bossRoom;
    
    private boolean hasPlayedDeathSound = false; 

    // Construtor que recebe o mundo do Boss (Boss_Room)
    public Boss(Boss_Room world) {
        this.bossRoom = world;
        // Carrega as imagens de caminhada, especial, morte e ataque corpo-a-corpo
        loadImages(walkImages, "Boss_move", 12);
        loadImages(specialImages, "Boss_special", 34);
        loadImages(deathImages, "Boss_death", 15);
        loadImages(meleeImages, "Boss_melee", 14); 
        
        setImage(walkImages[0]);
    }
    
    // Define a barra de vida do Boss
    public void setHealthBar(BossHB hb) {
        bossHB = hb;
    }

    public void act() {
        // Impede que o Boss faça algo se estiver morto
        if (isDead) {
            return; 
        }

        handleInvulnerability();  // Controla o tempo de invulnerabilidade
        checkHit(); // Verifica se foi atingido por balas ou granadas
        
        // Verifica se o Boss deve fazer um ataque corpo-a-corpo
        if (!isMeleeAttacking) {
            checkProximityAndMeleeAttack();
        }
        
        checkPlayerCollision();  // Verifica colisão com o jogador apenas se não estiver a atacar

        if (isDying) {
            animateZombie(deathImages, deathImages.length, true); // Animação de morte
        } else if (isSpecial) {
            animateSpecialZombie(); // Animação especial
        } else if (isMeleeAttacking) {
            animateMeleeAttack(); // Animação de ataque corpo-a-corpo
        } else {
            moveZombie(); // Movimento normal
        }
    }
    
    // Verifica se o Boss está morto
    private void checkDeath() {
        if (health <= 0) {
            isDying = true;
            currentImage = 0;  // Reinicia o índice da animação de morte

            // Adiciona 3000 pontos ao jogador e remove o Boss do mundo
            World world = getWorld();
            if (world instanceof Boss_Room) {
                Boss_Room bossRoom = (Boss_Room) world;
                bossRoom.getScoreCounter().addScore(3000);  // Adiciona 3000 pontos
            }

            getWorld().removeObject(this);  // Remove o Boss do mundo
        }
    }

    // Verifica a proximidade de jogadores e inicia ataque corpo-a-corpo se estiverem próximos
    private void checkProximityAndMeleeAttack() {
        Player1 player1 = (Player1) getOneIntersectingObject(Player1.class);
        Player2 player2 = (Player2) getOneIntersectingObject(Player2.class);
        
        if (player1 != null && !isMeleeAttacking) {
            triggerMeleeAttack();  // Inicia ataque corpo-a-corpo se o jogador estiver perto
        }
        
        if (player2 != null && !isMeleeAttacking) {
            triggerMeleeAttack();
        }
    }

    // Método consolidado para animações
    private void animateZombie(GreenfootImage[] images, int frameCount, boolean triggerDeath) {
        animationCounter++;
        if (animationCounter >= animationSpeed) {
            animationCounter = 0;

            if (triggerDeath) {
                // Animação de morte, não faz loop
                if (!hasPlayedDeathSound) {
                    Greenfoot.playSound("BossDeath.wav");
                    hasPlayedDeathSound = true;  // Marca o som como tocado
                }

                if (currentImage < images.length - 1) {
                    currentImage++;
                } else {
                    getWorld().removeObject(this);  // Remove o Boss ao final da animação de morte
                    isDead = true;
                    return;  
                }
            } else {
                // Loop para animações que não são de morte
                currentImage = (currentImage + 1) % images.length;
            }

            setImage(images[currentImage]);
            flipDirection();
        }
    }
    
    // Animação de ataque corpo-a-corpo
    private void animateMeleeAttack() {
        animationCounter++;
        if (animationCounter >= animationSpeed) {
            animationCounter = 0;
            currentImage = (currentImage + 1) % meleeImages.length;
            setImage(meleeImages[currentImage]);
            flipDirection();

            // Termina a animação de ataque corpo-a-corpo após seus frames
            meleeAnimationCounter++;
            if (meleeAnimationCounter >= meleeAnimationFrames) {
                isMeleeAttacking = false;
                meleeAnimationCounter = 0;
            }
        }
    }

    // Verifica a colisão com jogadores e reduz a vida se atingidos pela ShockWave
    private void checkPlayerCollision() {
        Player1 player1 = (Player1) getOneIntersectingObject(Player1.class);
        Player2 player2 = (Player2) getOneIntersectingObject(Player2.class);
    
        if (player1 != null) {
            player1.reduceHealth(1);
        }

        if (player2 != null) {
            player2.reduceHealth(1);
        }
    }

    // Inicia o ataque corpo-a-corpo
    public void triggerMeleeAttack() {
        isMeleeAttacking = true;
        currentImage = 0;  // Reinicia a animação de ataque corpo-a-corpo
    }

    // Animação especial do Boss
    private void animateSpecialZombie() {
        animationCounter++;
        if (animationCounter >= animationSpeed) {
            animationCounter = 0;
            currentImage = (currentImage + 1) % specialImages.length;
            setImage(specialImages[currentImage]);
            flipDirection();

            // Dispara uma ShockWave num frame específico
            if (currentImage == 15) {
                SW();  // Dispara ShockWave no frame 15
            }

            // Controla o fim da animação especial
            specialAnimationCounter++;
            if (specialAnimationCounter >= specialAnimationFrames) {
                isSpecial = false;
                specialAnimationCounter = 0;
            }
        }
    }

    // Movimento normal do Boss
    private void moveZombie() {
        animateZombie(walkImages, walkImages.length, false);

        if (atWorldEdge() || atPlatformEdge()) {
            speed = -speed;
            facingRight = !facingRight;
        }
        move(speed);
    }

    // Carrega imagens para animações
    private void loadImages(GreenfootImage[] imageArray, String baseName, int count) {
        for (int i = 0; i < count; i++) {
            imageArray[i] = new GreenfootImage(baseName + i + ".png");
            imageArray[i].scale(100, 130);
        }
    }

    // Verifica se o Boss está na borda do mundo
    private boolean atWorldEdge() {
        return getX() <= 10 || getX() >= getWorld().getWidth() - 10;
    }

    // Verifica se o Boss está na borda de uma plataforma
    private boolean atPlatformEdge() {
        Actor below = getOneObjectAtOffset(0, getImage().getHeight() / 2, Platform.class);
        Actor edge = getOneObjectAtOffset((int)Math.signum(speed) * getImage().getWidth() / 2, getImage().getHeight() / 2 + 10, Platform.class);
        return below == null || edge == null;
    }

    // Inverte a direção do Boss
    private void flipDirection() {
        if (!facingRight) {
            GreenfootImage mirroredImage = new GreenfootImage(getImage());
            mirroredImage.mirrorHorizontally();
            setImage(mirroredImage);
        }
    }

    // Verifica se o Boss foi atingido por balas ou granadas
    private void checkHit() {
        // Colisão com balas
        Actor bullet = getOneIntersectingObject(Bullet.class);
        if (bullet != null && !isInvulnerable() && this.bossHB != null) {
            getWorld().removeObject(bullet);
            health--;
            bossHB.updateHB(health);  
            invulnerableCounter = invulnerabilityTime;

            if (health == 15 || health == 10 || health == 5) {
                isSpecial = true;
                specialAnimationCounter = 0;
                currentImage = 0;
            } else if (health <= 0) {
                World world = getWorld();
                if (world instanceof Boss_Room) {
                    Boss_Room bossWorld = (Boss_Room) world;
                    bossWorld.getScoreCounter().addScore(3000); 
                    bossWorld.bossDefeated();
                }
                isDying = true;
                currentImage = 0;
            }
        }

        // Colisão com granadas
        Actor grenade = getOneIntersectingObject(Grenade.class);
        if (grenade != null && !isInvulnerable()) {
            getWorld().removeObject(grenade);
            health -= 5;
            bossHB.updateHB(health);
            invulnerableCounter = invulnerabilityTime;

            getWorld().addObject(new Explosion(), getX(), getY());

            if (health == 15 || health == 10 || health == 5) {
                isSpecial = true;
                specialAnimationCounter = 0;
                currentImage = 0;
            } else if (health <= 0) {
                World world = getWorld();
                if (world instanceof Boss_Room) {
                    Boss_Room bossWorld = (Boss_Room) world;
                    bossWorld.getScoreCounter().addScore(3000);
                    bossWorld.spawnPortal();
                }

                isDying = true;
                currentImage = 0;
            }
        }
    }

    // Controla o tempo de invulnerabilidade do Boss após ser atingido
    private void handleInvulnerability() {
        if (invulnerableCounter > 0) {
            invulnerableCounter--;
        }
    }

    // Verifica se o Boss está invulnerável
    private boolean isInvulnerable() {
        return invulnerableCounter > 0;
    }
    
    // Cria shockwave durante o ataque especial
    private void SW() {
        getWorld().addObject(new ShockWaveL(-ShockWave_speed), getX(), 450);  // Onda para a esquerda
        getWorld().addObject(new ShockWave(ShockWave_speed), getX(), 450);  // Onda para a direita
    }
}
