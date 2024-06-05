import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel implements Runnable{
    static final int GAME_WIDTH = 1000;
    static final int GAME_HEIGHT =  (int)(GAME_WIDTH * 0.55);
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    static final int BALL_DIAMETER = 20;
    static final int PADDLE_WIDTH = 25;
    static final int PADDLE_HEIGHT = 100;
    Paddle paddle1, paddle2;
    Ball ball;
    Score score;
    Image image;
    Graphics graphics;
    Thread gameThread;
    GamePanel(){
        newPaddles();
        this.setFocusable(true);
        this.setPreferredSize(SCREEN_SIZE);
        this.addKeyListener(new AL());
        score = new Score(GAME_WIDTH, GAME_HEIGHT);

        gameThread = new Thread(this);
        gameThread.start();
    }

    private void newPaddles() {
        paddle1 = new Paddle(0, (GAME_HEIGHT/2), PADDLE_WIDTH, PADDLE_HEIGHT, 1);
        paddle2 = new Paddle(GAME_WIDTH-PADDLE_WIDTH, (GAME_HEIGHT/2), PADDLE_WIDTH, PADDLE_HEIGHT, 2);
        ball = new Ball((GAME_WIDTH/2), (GAME_HEIGHT/2), BALL_DIAMETER, BALL_DIAMETER);
    }

    public void paint(Graphics g){
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image,0,0,this);

    }
    public void draw(Graphics g){
        g.drawLine(GAME_WIDTH/2, 0,GAME_WIDTH/2, GAME_HEIGHT);
        paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);
        score.draw(g);

    }
    void move(){
        ball.move();
        paddle1.move();
        paddle2.move();
    }
    void checkCollision(){
//        bounce ball of the top and bottom window edge
        if(ball.y <= 0){
            ball.setYDirection(-ball.yVelocity);
        }
        if(ball.y >= GAME_HEIGHT- ball.height){
            ball.setYDirection(-ball.yVelocity);
        }

//        bounce ball of paddles 1 (left)
        if(ball.intersects(paddle1)){
            ball.xVelocity = Math.abs(ball.xVelocity);

//            This line of code for enhancing yVelocity
//            if(ball.yVelocity > 0){
//                ball.yVelocity+=5;
//            }else{
//                ball.yVelocity -= 5;
//            }
            ball.setXDirection(ball.xVelocity);
//            ball.setYDirection(ball.yVelocity);
        }else if(ball.intersects(paddle2)){
            ball.setXDirection(-ball.xVelocity);
        }
//        wins
        if(ball.x<0){
            score.player2++;
            newPaddles();
        }else if(ball.x>GAME_WIDTH){
            score.player1++;
            newPaddles();
        }

//        For auto paddle right side
        if(ball.x >= GAME_WIDTH/2){
            paddle2.setYDirection(ball.y);
        }


    }

    @Override
    public void run(){
        //game loop
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000/ amountOfTicks;
        double delta = 0;
        while(true){
            long now = System.nanoTime();
            delta += (now - lastTime)/ns;
            lastTime  = now;
            if(delta >= 1){
                move();
                checkCollision();
                repaint();
                delta--;
            }
        }
    }

    public class AL extends KeyAdapter{
        public void keyPressed(KeyEvent e) {
            if(paddle1.y<=0){
                paddle1.y = 0;
            }else if(paddle1.y > GAME_HEIGHT-PADDLE_HEIGHT){
                paddle1.y = GAME_HEIGHT-PADDLE_HEIGHT;
            }

            paddle1.keyPressed(e);
//            move();
        }

        public void keyReleased(KeyEvent e) {
            paddle1.keyReleased(e);
        }
    }
}
