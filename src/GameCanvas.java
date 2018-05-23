import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameCanvas extends JPanel {

    BufferedImage starImage;
    BufferedImage enemyImage;
    BufferedImage playerImage;
    BufferedImage backBuffered;
    Graphics graphics;

    public int positionXStar = 400;
    public int positionYStar = 300;

    public int positionXEnemy = 500;
    public int positionYEnemy = 0;

    public int positionXPlayer = 400;
    public int positionYPlayer = 200;

    public GameCanvas() {
        this.setSize(1024, 600);


        // load image
        this.setupCharacter();
        this.setupBackbuffered();
        this.setVisible(true);
    }

    private void setupBackbuffered(){
        this.backBuffered = new BufferedImage(1024, 600, BufferedImage.TYPE_4BYTE_ABGR);
        this.graphics = this.backBuffered.getGraphics();
    }

    private  void  setupCharacter() {
        this.starImage = this.loadImage("resources/images/star.png");

        this.enemyImage = this.loadImage("resources/images/circle.png");

        this.playerImage = this.loadImage("resources/images/circle.png");
    }

    @Override
    protected void paintComponent(Graphics g) {
        // lat backbuffered
        g.drawImage(this.backBuffered, 0, 0, null);
//        g.setColor(Color.BLACK);
//        g.fillRect(0, 0, 1024, 600);
//
//        g.drawImage(this.starImage, this.positionXStar, this.positionYStar, 5, 5, null);
//        g.drawImage(this.enemyImage, this.positionXEnemy, this.positionYEnemy, 10, 10, null);
//        g.drawImage(this.playerImage, this.positionXPlayer, this.positionYPlayer, null);
    }

    public void renderAll() {
        this.renderBackground();
        this.graphics.drawImage(this.starImage, this.positionXStar, this.positionYStar, 5, 5, null);
        this.graphics.drawImage(this.enemyImage, this.positionXEnemy, this.positionYEnemy, 10, 10, null);
        this.graphics.drawImage(this.playerImage, this.positionXPlayer, this.positionYPlayer, null);
        this.repaint();
    }

    private void renderBackground(){
        this.graphics.setColor(Color.BLUE);
        this.graphics.fillRect(0, 0, 1024, 600);
    }

    public void runAll(){
        this.positionXStar -=3;
        this.positionYEnemy +=2;
    }

    private BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
