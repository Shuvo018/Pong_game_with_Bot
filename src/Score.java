import java.awt.*;

public class Score extends Rectangle {
    int Game_width, Game_height;
    int player1=0;
    int player2=0;

    Score(int width, int height){
        this.Game_width = width;
        this.Game_height = height;
    }

    public void draw(Graphics g){
        g.setColor(Color.black);
        g.setFont(new Font("consolas", Font.BOLD, 45));
        g.drawString(String.valueOf("Human: ")+String.valueOf(player1), (Game_width/2)-230, 50);
        g.drawString(String.valueOf(" Bot: ")+String.valueOf(player2), (Game_width/2)+00, 50);
    }
}

