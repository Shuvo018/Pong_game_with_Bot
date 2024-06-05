import java.awt.*;
import java.util.Random;

public class Ball extends Rectangle {
    int initialSpeed = 5;
    int xVelocity, yVelocity;
    Random random;
    Ball(int x, int y, int width, int height){
        super(x, y, width, height);

        random = new Random();
//        int randomXDirection = random.nextInt(2);
        int randomXDirection = 0;
        if(randomXDirection == 0){
            randomXDirection--;
        }
        setXDirection(randomXDirection*initialSpeed);

//        int randomYDirection = random.nextInt(2);
        int randomYDirection = 1;
        if(randomYDirection == 0){
            randomYDirection--;
        }
        setYDirection(randomYDirection*initialSpeed);
    }

    public void setYDirection(int yDirection) {
        yVelocity = yDirection;
//        System.out.println("--------------");

    }

    public void setXDirection(int xDirection) {
        xVelocity = xDirection;
    }
    public void move(){
        x += xVelocity;
        y += yVelocity;
//        System.out.println(y);

    }
    public void draw(Graphics g){
        g.setColor(Color.black);
        g.fillOval(x,y,width,height);
    }


}
