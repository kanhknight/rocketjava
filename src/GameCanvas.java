import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameCanvas extends JPanel {


    BufferedImage playerImage;
    BufferedImage backBuffered;
    Graphics graphics;

    List<Star> stars;
    private Random random = new Random();

//    public int positionXStar = 400;
//    public int positionYStar = 300;

    List<Enemy> enemies;

    Player players;


//    public int positionXPlayer = 400;
//    public int positionYPlayer = 200;

    public  int countStar;

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
        this.setupStar();
//        this.enemyImage = this.loadImage("resources/images/circle.png");
        this.setupEnemy();
//        this.playerImage = this.loadImage("resources/images/circle.png");

        this.setupPlayer();

    }

    private void setupStar(){
//        this.star = new Star(3,5);
//        this.star.image = this.loadImage( "resources/images/star.png");
//        this.star.x = 1024;
//        this.star.y = 300;
//        this.star.width = 5;
//        this.star.height = 5;
//        this.star.velocityx = 3;
//        this.star.velocityy= -3;
        this.stars = new ArrayList<>();
    }

    private void setupEnemy(){
        this.enemies = new ArrayList<>();
        for (int i =0; i<4; i++){
            createEnemy();
        }
    }

    private void setupPlayer(){
        createPlayer();
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

//        this.star.render(this.graphics);
        this.stars.forEach(star -> star.render(graphics)); //Vong lap foreach

//        this.graphics.drawImage(this.enemyImage, this.positionXEnemy, this.positionYEnemy, 10, 10, null);
        this.enemies.forEach(enemies -> enemies.render(graphics));

//        this.graphics.drawImage(this.playerImage, this.positionXPlayer, this.positionYPlayer, null);
        this.repaint();
    }

    private void renderBackground(){
        this.graphics.setColor(Color.BLACK);
        this.graphics.fillRect(0, 0, 1024, 600);
    }

    public void runAll(){
        this.createStar();
        this.stars.forEach(star -> star.run());

//        this.createEnemy();
        this.enemies.forEach(enemy -> enemy.run());

        this.createPlayer();
    }

    public void createStar(){
        if (this.countStar == 5) {
            Star star = new Star(
                    this.loadImage("resources/images/star.png"),
                    1024,
                    this.random.nextInt(600),
                    3,
                    3,
                    -(this.random.nextInt(3) + 1),
                    0
            );
            this.stars.add(star);
            this.countStar = 0;
        } else {
            this.countStar +=1;
        }
    }

    public void createEnemy(){
        Enemy enemy = new Enemy(
                this.loadImage("resources/images/circle.png"),
                this.random.nextInt(1024),
                this.random.nextInt(600),
                20,
                20,
                this.random.nextInt(3),
                this.random.nextInt(2)
        );
        this.enemies.add(enemy);
    }

    public void  createPlayer(){
        Player player = new Player(
                this.loadImage("resources/images/circle.png"),
                this.random.nextInt(1024),
                this.random.nextInt(600),
                20,
                20,
                30,
                30
        );
        this.players = player;
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
