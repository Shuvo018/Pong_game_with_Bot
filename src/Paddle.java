import java.awt.*;
import java.awt.event.KeyEvent;

public class Paddle extends Rectangle {
    int id;
    int yVelocity;
    int speed = 10;
    Paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id){
        super(x,y,PADDLE_WIDTH,PADDLE_HEIGHT);
        this.id = id;
    }

    public void draw(Graphics g){
        if(id == 1){
            g.setColor(Color.blue);
        }else {
            g.setColor(Color.red);
        }
        g.fillRect(x,y, width, height);

    }
    public void keyPressed(KeyEvent e){
        if(id == 1){
            if(e.getKeyCode() == KeyEvent.VK_UP){
                setYDirection(-speed);
            }else if(e.getKeyCode() == KeyEvent.VK_DOWN){
                setYDirection(speed);
            }
        }else if(id == 2){
            if(e.getKeyCode() == KeyEvent.VK_A){
                setYDirection(-speed);
            }else if(e.getKeyCode() == KeyEvent.VK_F){
                setYDirection(speed);
            }
        }
    }

    public void setYDirection(int yDirection) {
        yVelocity = yDirection;
    }
//    move() for smoth paddle
    public void move(){
        if(id == 1){
            y += yVelocity;
        }else if(id == 2){
            y = yVelocity;
        }
    }

    public void keyReleased(KeyEvent e) {
        if(id == 1){
            if(e.getKeyCode() == KeyEvent.VK_UP){
                setYDirection(0);
            }else if(e.getKeyCode() == KeyEvent.VK_DOWN){
                setYDirection(0);
            }
        }
    }
}
