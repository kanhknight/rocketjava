import java.awt.*;
import java.awt.image.BufferedImage;

public class Player {
    public BufferedImage image;
    public int x;
    public int y;
    public int width;
    public int height;
    public int velocityx;
    public int velocityy;


    public Player(BufferedImage image, int x, int y, int width, int height, int velocityx, int velocityy) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.velocityx = velocityx;
        this.velocityy = velocityy;
    }

    public void run(){
        this.x += this.velocityx;
        this.y += this.velocityy;
    }

    public  void render(Graphics graphics){
        graphics.drawImage(
                this.image,
                this.x,
                this.y,
                this.width,
                this.height,
                null
        );
    }
}
