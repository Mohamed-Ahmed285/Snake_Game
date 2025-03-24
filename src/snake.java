import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
import java.util.concurrent.TimeUnit;


public class snake extends JPanel implements ActionListener,KeyListener
{




    @Override
    public void keyPressed(KeyEvent e) {

    if (e.getKeyCode() == KeyEvent.VK_UP &&veloy != 1)
        {

                velox =0;
                veloy =-1;


        } else if (e.getKeyCode() == KeyEvent.VK_DOWN &&veloy != -1)
            {

                    velox =0;
                    veloy =1;



            }else if (e.getKeyCode() == KeyEvent.VK_LEFT && velox != 1)
            {


                    velox =-1;
                    veloy =0;


            }else if (e.getKeyCode() == KeyEvent.VK_RIGHT && velox != -1){



                    velox =1;
                    veloy =0;



                }}

    @Override
    public void keyReleased(KeyEvent e) {

    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    private class Tile{

        int x;
        int y;

        Tile(int x, int y ){
            this.x = x;
            this.y = y;
        }
    }

    int boardW;
    int boardH;
    int tiles = 25;
    //snake
    Tile snakehead; ArrayList<Tile> snakebody;boolean gameover = false   ;
    //food
    Tile food;Random random;

    // game
      Timer gameloop;int velox, veloy;
    snake(int boardW, int boardH){

        this.boardH=boardH;
        this.boardW=boardW;
        setPreferredSize(new Dimension(this.boardH,this.boardW));
        setBackground(Color.black);
        addKeyListener(this);
        setFocusable(true);

        snakehead = new Tile(5,5);
        snakebody = new ArrayList<Tile>();
        food = new Tile(10,10);

        random = new Random();
        placefood();
        velox = 0 ;
        veloy = 1;
        gameloop = new Timer(100,this);
        gameloop.start();

    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }
    public  void draw(Graphics g){


        //grid
//        for(int i =0 ; i < (boardW/tiles); i++ ){
//
//            g.setColor(Color.DARK_GRAY);
//            g.drawLine(i*tiles, 0 , i*tiles,boardH);
//            g.drawLine(0, i*tiles, boardW, i*tiles);
//
//        }
            //FOOD
        g.setColor(Color.RED);
        g.fillRect(food.x*tiles, food.y*tiles,tiles,tiles);
        g.fill3DRect(food.x*tiles, food.y*tiles,tiles,tiles,true);

        //snake
        g.setColor(Color.green);
        g.fillRect(snakehead.x * tiles,snakehead.y * tiles,tiles,tiles);
        g.fill3DRect(snakehead.x * tiles,snakehead.y * tiles,tiles,tiles,true);

        // snakebody
        for (int i = 0; i < snakebody.size(); i++) {
            Tile snakePart = snakebody.get(i);
            g.fillRect(snakePart.x*tiles, snakePart.y*tiles, tiles,tiles);
            g.fill3DRect(snakePart.x*tiles, snakePart.y*tiles, tiles,tiles,true);
        }
//score

        if(gameover){
            g.setFont( new Font("Arial",Font.PLAIN,34));
            g.setColor(Color.red);
            g.drawString("Game Over: " + String.valueOf(snakebody.size()),tiles + 172,tiles +280);
        }else {
            g.setFont( new Font("Arial",Font.PLAIN,18));
            g.drawString("Score: " + String.valueOf(snakebody.size()),tiles-14,tiles);
        }

    }
    public void placefood(){
        food.x = random.nextInt(boardW/tiles); //24
        food.y = random.nextInt(boardH/tiles); // 24
    }
    public void move(){

        if(coll(snakehead,food)){
            snakebody.add(new Tile(food.x, food.y));
            placefood();
        }
        for (int i = snakebody.size()-1; i >=0 ; i--) {
            Tile snakePart = snakebody.get(i);
            if (i ==0){
                snakePart.x = snakehead.x;
                snakePart.y = snakehead.y;
            }else{
                Tile prevpart = snakebody.get(i-1);
                snakePart.x = prevpart.x;
                snakePart.y = prevpart.y;
            }
        }
        snakehead.x += velox;
        snakehead.y += veloy;

        for (int i = 0; i < snakebody.size() ; i++) {

            Tile snakepart = snakebody.get(i);
        if (coll(snakehead,snakepart)){
            gameover = true;

        }
        }
        if (snakehead.x*tiles < 0 || snakehead.x*tiles > boardW || snakehead.y*tiles < 0 || snakehead.y*tiles > boardH){
    gameover= true;
        }
    }
    public boolean coll(Tile tile1, Tile tile2){
        return tile1.x == tile2.x && tile1.y == tile2.y;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
        if (gameover){
            gameloop.stop();
        }
    }
}
