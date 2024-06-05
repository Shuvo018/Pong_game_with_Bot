import javax.swing.*;

public class GameFrame extends JFrame {
    GamePanel panel;
    GameFrame(){
        panel = new GamePanel();
        this.add(panel);

        this.setTitle("pang game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
