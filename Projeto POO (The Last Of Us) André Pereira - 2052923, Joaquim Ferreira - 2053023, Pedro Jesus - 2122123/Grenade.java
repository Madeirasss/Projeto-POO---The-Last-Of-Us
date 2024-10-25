import greenfoot.*;

public class Grenade extends Actor
{
    public int speed = 10;

    public Grenade(int grenadeSpeed)
    {
        speed = grenadeSpeed;
        // Carrega a imagem da granada
        GreenfootImage image = new GreenfootImage("granada.png");
        
        // Redimensiona a imagem
        image.scale(40, 40); 
        
        // Define a imagem redimensionada para a granada
        setImage(image);
    }

    public void act() {
        move(speed);
        checkCollision();
    }

    private void checkCollision() {
        // Verifica colisão com zombies ou paredes
        Actor zombie = getOneIntersectingObject(Zombie_basic.class);
        if (zombie != null) {
            explode();
        }

        if (isAtEdge()) {
            explode();
        }
    }

    private void explode() {
        // Adiciona a explosão e remove a granada
        Explosion explosion = new Explosion();
        getWorld().addObject(explosion, getX(), getY());
        getWorld().removeObject(this);
    }
    
    private void addGrenades() {
        
    }
}
