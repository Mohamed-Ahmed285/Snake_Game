import javax.swing.*;


public class Main {
    public static void main(String[] args) throws Exception {

        int boardW = 600;
        int boardH = 600;
        JFrame frame = new JFrame("Snake");
        frame.setVisible(true);
        frame.setSize(boardW,boardH);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//      sound sound = new sound();
        snake snakeg = new snake(boardW,boardH);
        frame.add(snakeg);
        frame.pack();
        snakeg.requestFocus();

    }
}