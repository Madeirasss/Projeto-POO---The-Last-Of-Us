import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Bullet extends Actor
{
    public int speed = 5;

    public Bullet(int bulletSpeed)
    {
        speed = bulletSpeed; 
    }

    public void act()
    {
        move(speed);
        if (!checkWorldEdge()) {  // Apenas continua se a bala não for removida no limite do mundo
            checkWallCollision();  // Verifica se a bala colidiu com uma parede
        }
    }

    private boolean checkWorldEdge()
    {
        // Remove a bala se ela atingir o limite do mundo
        if (isAtEdge()) {
            getWorld().removeObject(this);
            return true;  // Indica que a bala foi removida
        }
        return false;  // A bala não foi removida
    }

    private void checkWallCollision()
    {
        // Verifica se a bala colidiu com uma parede (classe Wall)
        Actor wall = getOneIntersectingObject(Wall.class);
        if (wall != null) {
            // Remove a bala ao colidir com a parede
            getWorld().removeObject(this);
        }
    }
}