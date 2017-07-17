/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dxball;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.sound.sampled.*;
/*
//    Title: DxBall
//    Author Md. Abdullah Al Awal
//    Course: CSE 150
//    Reg: 2012331055  
//    Email: abdullah@student.sust.edu
//    Session: 2012-13
*/
public class DxBall implements Runnable,KeyListener,MouseListener,MouseMotionListener 
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        DxBall b = new DxBall();
        b.d_h();
        b.run();  
    }
    Graphics2D g;
    String p[] = {"1st ","2nd","3rd","4th","5th"};
    String h[] = {"*Press left & right arrow key to move "," your paddle.","*Press space to pause your game.","*Press space to play again.","*Press esc to exit."};
    boolean MouseInPlay = false;
    boolean ekbar = false;
    boolean MouseInHigh = false;
    boolean MouseInInst = false;
    boolean gameStart = false;
    boolean gamePause = false;
    boolean gameOver = false;
    boolean playAgain = false;
    boolean showHelp = false;
    boolean Highscore=false,Instructions=false;
    boolean youMakeHighScore = false;
    boolean MouseInHelpBack = false;
    boolean MouseInBack = false;
    boolean showHighScores = false;
    private int paddleSpeed = 30, paddleX = 350,paddleY = 600;
    private int ballSpeed = 6, ballX=0, ballY=0,t=0;
    private int  direction = -1;
    private int deltaX=1300, deltaY = 600;
    private int highScores = 0;
    private int level_score = 10;
    private int level = 1;
    private int h_s,i;
    private int x[] = new int[5];
    private int a1=0,a2=0,a3=0,b1=0;
    String ss= "0";
    //
    ScreenManager  s = new ScreenManager();
    Image bg,bg_l,paddle,ball,flame1,flame2,flame3,flame4,flame5,flame6,flame7,scores,l_i,hi,back;
    boolean gameRunning = true;
    boolean paddleMoveR=false, paddleMoveL=false;
    boolean downL=true, downR = false, upR = false, upL = false;
    


    private static final DisplayMode modes1[] = {new DisplayMode(1366,768,16,0)};
    /**
     * Loading all the Images.
     */
    

    /**
     * Initializing all the values to play again.
     */
    public void initialize()
    {
        Random r = new Random();
        ballX=r.nextInt(300); ballY=0;
        a1=a2=a3=b1=0;
        showHelp = false;
        MouseInPlay = true;
        MouseInHigh = false;
        MouseInInst = false;
        MouseInBack = false;
        level = 1;
        ekbar = false;
        MouseInHelpBack = false;
        gameStart = true;
        gamePause = false;
        gameOver = false;
        showHighScores = false;
        playAgain = false;
        Highscore=false;
        Instructions=false;
        youMakeHighScore = false;
        paddleSpeed = 30; paddleX = 350;paddleY = 600;
        ballSpeed = 6; t=0;
        direction = -1;
        deltaX=1300; deltaY = 600;
        highScores = 0;
        ss= "0";
        gameRunning = true;
        paddleMoveR=false; paddleMoveL=false;
        downL=true; downR = false; upR = false; upL = false;
        bg_l= new ImageIcon("menu4.jpg").getImage();
         
         bg = new ImageIcon("image1.jpg").getImage();
         paddle = new ImageIcon("bluepaddle1.png").getImage();
         ball = new ImageIcon("blue_ball.png").getImage();
         
         
         
         flame1 = new ImageIcon("m.gif").getImage();
         flame2 = new ImageIcon("m.gif").getImage();
         flame3 = new ImageIcon("m.gif").getImage();
         flame4 = new ImageIcon("m.gif").getImage();
         flame5 = new ImageIcon("m.gif").getImage();
         flame6 = new ImageIcon("m.gif").getImage();
         flame7 = new ImageIcon("m.gif").getImage();
         scores = new ImageIcon("high_bg.jpg").getImage();//
         hi  = new ImageIcon("high.png").getImage();
         back  = new ImageIcon("back.png").getImage();
         d_h();
    }
    public void init()
    {
        
         bg_l= new ImageIcon("menu4.jpg").getImage();
         
         bg = new ImageIcon("image1.jpg").getImage();
         paddle = new ImageIcon("bluepaddle1.png").getImage();
         ball = new ImageIcon("blue_ball.png").getImage();
         
         
         
         flame1 = new ImageIcon("m.gif").getImage();
         flame2 = new ImageIcon("m.gif").getImage();
         flame3 = new ImageIcon("m.gif").getImage();
         flame4 = new ImageIcon("m.gif").getImage();
         flame5 = new ImageIcon("m.gif").getImage();
         flame6 = new ImageIcon("m.gif").getImage();
         flame7 = new ImageIcon("m.gif").getImage();
         scores = new ImageIcon("high_bg.jpg").getImage();
         hi  = new ImageIcon("high.png").getImage();
         back  = new ImageIcon("back.png").getImage();
    }
    void init1()
    {
        Random r = new Random();
        ballX=r.nextInt(300); ballY=0;
        
    }
    
    /**
     * Starting the game.
     * Storing FullScreenWindow.
     * Adding KeyListener,MouseListener,MouseMotionListener to the window.
     * Calling init() & initialize() method.
     * Calling restoreScreen() method.
     * Updating game.
     */
    public void d_h()
    {
        try{
                                    File f = new File("high_scores.txt");
                                    Scanner scanner = new Scanner(f);               
                                    i = 0;
                                    while (scanner.hasNext())
                                    { 
                                        x[i++] = scanner.nextInt();                            
                                    }
                                    h_s = x[4];
                                    scanner.close();
                                    //f.close();
                                } 
                                catch (Exception ex) {
                                    System.out.println("ERROR: Level data not found " + ex.getMessage());
                                }
    }
    public void run()
    {
        
        try{
            DisplayMode dm = s.findFirstCompatibleMode(modes1);
            //System.out.println("x="+dm.getWidth()+"y="+dm.getHeight());
            s.setFullScreen(dm);
            Window w = s.getFullScreenWindow();
            w.addKeyListener(this);
            w.addMouseListener(this);
            w.addMouseMotionListener(this);
            init();


            while (gameRunning == true)
            {
                try
                {
                    Thread.sleep(25);
                }
                catch(Exception e)
                {}
                
                g = s.getGraphics();
                if(playAgain==true)
                {
                    initialize();
                }
                //g.drawImage(bg,0,0,null);
                if(gameStart==false)
                {
                    paintOpenning(g);
                    g.dispose();
                    s.update();
                }
                
                if(gameStart==true && gameOver==false && gameRunning==true)
                {
                    g = s.getGraphics();
                    
                    try{
                        paintPlay(g);
                    }
                    catch(Exception e)
                    {
                        
                    }
                    
                    Font p1 = new Font("Monotype Corsiva", Font.BOLD,40);
                    g.setFont(p1);
                    g.setColor(Color.BLACK);
                    if(level==3)
                        g.setColor(Color.WHITE);
                    ss = Integer.toString(h_s);
                    g.drawString("HighScore: "+ss,50,100);
                    ss = Integer.toString(level);
                    g.drawString("Level: "+ss,550,50);
                    ss = Integer.toString(highScores);
                    g.drawString("YourScore: "+ss,950,100);
                    
                    g.dispose();
                    s.update();
                }
                while(gamePause==true && gameStart==true && gameRunning == true)
                {
                    g = s.getGraphics();
                    Font p = new Font("Monotype Corsiva", Font.BOLD,40);
                    g.setFont(p);
                    g.setColor(Color.BLACK);
                    g.drawString("PAUSE",570,350);
                    g.drawImage(ball,ballX,ballY,null);
                    g.dispose();
                    s.update();
                }
                while(gameOver==true && playAgain==false && gameRunning == true)
                {
                    //initialize();
                    g = s.getGraphics();
                    Font p1 = new Font("Monotype Corsiva", Font.BOLD,60);
                    g.setFont(p1);
                    g.setColor(Color.RED);
                    g.drawString("GAME OVER!", 480, 350);
                    if(youMakeHighScore==true)
                    g.drawString("You make high score",200,400);
                    g.drawImage(ball,ballX,ballY,null);
                    g.dispose();
                    s.update();
                }
                while(showHighScores==true && gameRunning == true)
                {
                    g = s.getGraphics();
                    paintHighScores(g);
                    g.dispose();
                    s.update();
                }
                while(showHelp==true && gameRunning == true)
                {
                    g = s.getGraphics();
                    paintHelp(g);
                    g.dispose();
                    s.update();
                }
                
            }

        }


        finally
        {
            s.restoreScreen();
        }
    }
    
    /**
     * Controlling the user input.
     * Controlling paddle movement.
     * Drawing the Flame image.
     * Playing the audio file when ball hit the paddle.
     * Controlling the direction of the ball.
     * Calculating Scores.
     * Saving of the highScores.
     * @param g  : An Instance/Object of Graphics2D
     */
    public void paintPlay(Graphics2D g) throws InterruptedException
    {
        g.drawImage(bg,0,0,null);
        g.drawImage(flame1,0,675, null);
        g.drawImage(flame2,200,675, null);
        g.drawImage(flame3,400,675, null);
        g.drawImage(flame4,600,675, null);
        g.drawImage(flame5,800,675, null);
        g.drawImage(flame6,1000,675, null); 
        g.drawImage(flame7,1200,675, null);
        if(paddleMoveL==false && paddleMoveR==false)
        {
            g.drawImage(paddle,paddleX,paddleY, null);
        }
        else if(paddleMoveL==true && paddleMoveR==false)
        {
            if((paddleX-paddleSpeed)>=0)
                paddleX-=paddleSpeed;
            g.drawImage(paddle, paddleX, paddleY, null);
            paddleMoveL=false;
            paddleMoveR=false;
        }
        else if(paddleMoveL==false && paddleMoveR==true)
        {
            if((paddleX+paddleSpeed)<=1050)
                paddleX+=paddleSpeed;
            g.drawImage(paddle,paddleX,paddleY, null);
            paddleMoveL=false;
            paddleMoveR=false;
        }
        //g.drawImage(paddle,paddleX,paddleY, null);
        if(direction == -1)
        {
            if(downL==true)
            {
                ballX+=ballSpeed;
                ballY+=ballSpeed;
                if(ballX>=deltaX && (ballY>=0 && ballY<=deltaY))
                {
                    downL=false;
                    downR=true;
                    upR=false;
                    upL=false;
                    //System.out.println("A");
                }
                else if(ballY>=deltaY && (ballX>=0 && ballX<=deltaX))
                {
                    direction*=-1;
                    upL=true;
                    upR=false;
                    downL=false;
                    downR=false;
                    //System.out.println("B");
                }
            }
            else if(downR==true)
            {   
                ballX-=ballSpeed;
                ballY+=ballSpeed;
                if(ballX<=0 && (ballY>=0 && ballY<=deltaY))
                {
                    downL=true;
                    downR=false;
                    //System.out.println("c");
                }
                else if(ballY>=deltaY && (ballX>=0 && ballX<=deltaX))
                {
                    direction*=-1;
                    upR=true;
                    upL=false;
                    downL=false;
                    downR=false;
                    //System.out.println("d  "+direction);
                }
            }
        }
        else if(direction==1)
        {
            if(upL==true)
            {
               ballX+=ballSpeed;
               ballY-=ballSpeed;
               if(ballX>=deltaX && (ballY>=0 && ballY<=deltaY))
               {
                   upR=true;
                   upL=false;
                   //System.out.println("e");
               }
               else if(ballY<=0 && (ballX>=0 && ballX<=deltaX))
               {
                   direction*=-1;
                   downL=true;
                   upL=false;
                   upR=false;
                   downR=false;
                   //System.out.println("f");
               }
            }
            else if(upR==true)
            {
                ballX-=ballSpeed;
                ballY-=ballSpeed;
                if(ballX<=0 && (ballY>=0 && ballY<=deltaY))
                {
                    upR=false;
                    upL=true;
                    downL=false;
                    downR=false;
                    //System.out.println("g");
                }
                else if(ballY<=0 && (ballX>=0&&ballX<=deltaX))
                {
                    direction*=-1;
                    downR=true;
                    downL=false;
                    upL=false;
                    upR=false;
                    //System.out.println("h");
                }
            }
        }
        if(direction == -1)
        {
            if((((ballX+30)>=paddleX) && (ballX<=(paddleX+220))) && (ballY+64>=paddleY))
            {
                ++highScores;
                if(highScores==11)
                {
                    level_score += 10;
                    level = 2;
                    ballSpeed +=2;//;;;;;
                    
                    g = s.getGraphics();
                   Font p1 = new Font("Monotype Corsiva", Font.BOLD,40);
                    g.setFont(p1);
                    g.setColor(Color.BLACK);
                    //ss = Integer.toString(h_s);
                    //g.drawString("HighScore: "+ss,50,100);
                    //ss = Integer.toString(highScores);
                    //g.drawString("YourScore: "+ss,550,100);
                    init1();
                    Thread.sleep(300);
                    
                    l_i = new ImageIcon("l_i.png").getImage();
                    
                    g.drawImage(l_i,0,0,null);
                    
                    s.update();
                    Thread.sleep(3000);
                    bg = new ImageIcon("image2.jpg").getImage();
                    paddle = new ImageIcon("redpaddle.png").getImage();
                    ball = new ImageIcon("redball.png").getImage();
         
                }
                else if(highScores%5 == 0 && highScores>=25)
                {
                    level = 3;
                    if(ekbar==false)
                    {
                        init1();
                        ekbar = true;
                        l_i = new ImageIcon("l_i1.png").getImage();
                    g = s.getGraphics();
                    g.drawImage(l_i,0,0,null);
                    s.update();
                    Thread.sleep(3000);
                    bg = new ImageIcon("image3.jpg").getImage();
                    paddle = new ImageIcon("greenpaddle1.png").getImage();
                    ball = new ImageIcon("greenball1.png").getImage();
                    }
                    level_score = 100;
                    ballSpeed++;
                    
         
                }
                try {
                            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("bounce.wav").getAbsoluteFile());
                            Clip clip = AudioSystem.getClip();
                            clip.open(audioInputStream);
                            clip.start();
                        }
                        catch(Exception ex){}
                direction*=-1;
                if(downL==true)
                {
                    upL=true;
                    downL=false;
                }
                else if(downR==true)
                {
                    downR=false;
                    upR=true;
                }
            }
            else if((ballX<paddleX || ballX>(paddleX+220)) && ((ballY+45)>paddleY))
            {
                
                gameOver = true;
                try {
                            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("burn.wav").getAbsoluteFile());
                            Clip clip = AudioSystem.getClip();
                            clip.open(audioInputStream);
                            clip.start();
                        }
                        catch(Exception ex){}
                
                try{
                                    File f = new File("high_scores.txt");
                                    Scanner scanner = new Scanner(f);               
                                    int i = 0;
                                    while (scanner.hasNext())
                                    { 
                                        x[i++] = scanner.nextInt();                            
                                    }
                                    scanner.close();
                                    //f.close();
                                } 
                                catch (Exception ex) {
                                    System.out.println("ERROR: Level data not found " + ex.getMessage());
                                }
                Arrays.sort(x);
                                if (highScores>x[0]){
                                    x[0]=highScores;
                                    youMakeHighScore = true;
                                }
                                Arrays.sort(x);
                                
                                BufferedWriter outputWriter = null;
                                try{
                                    outputWriter = new BufferedWriter(new FileWriter("high_scores.txt"));
                                    for (int i = 0; i < x.length; i++) {
                                        // Maybe:
                                        //outputWriter.write(x[i]+"");
                                        // Or:
                                        outputWriter.write(Integer.toString(x[i]));
                                        outputWriter.newLine();
                                    }
                                    outputWriter.flush();  
                                    outputWriter.close();  
                                }
                                catch(Exception e){}
            }
        }
            
        
        
        g.drawImage(ball,ballX,ballY,null);
    }
    
    /**
     * Showing How to play the game.
     * @param g : An Instance/Object of Graphics2D
     */
    public void paintHelp(Graphics2D g)
    {
        
        g.drawImage(scores,0,0,null);
        Font p1 = new Font("Monotype Corsiva", Font.BOLD,60);
                    g.setFont(p1);
                    g.setColor(Color.BLUE);
                    //g.drawString("PONG HIGHSCORES!", 150, 80);
                    
                    g.drawRect(680, 610 , 110, 70);
                    g.drawImage(back, 680 , 610, null);
        
        p1 = new Font("Monotype Corsiva", Font.BOLD,40);
                    g.setFont(p1);
                    g.setColor(Color.MAGENTA);
                    g.drawString("Instructions",300,40);
                    g.drawString(h[0], 50, 100);
                    g.drawString(h[1], 50, 200);
                    g.drawString(h[2], 50, 300);
                    g.drawString(h[3], 50, 400);
                    g.drawString(h[4], 50, 500);
                    g.drawRect(680, 610 , 110, 70);
                    //Font p2 = new Font("Monotype Corsiva", Font.BOLD,50);
                    //g.setFont(p2);
                    //g.setColor(Color.BLACK);
                    //g.drawString("Back", 350, 555);
                    if(MouseInHelpBack == true)
                    {
                        g.setColor(Color.white);
                        g.drawRect( 680, 610,110,70);
                        if(b1==0)
                        {
                            try {
                            AudioInputStream audio5 = AudioSystem.getAudioInputStream(new File("button.wav").getAbsoluteFile());
                            Clip clip = AudioSystem.getClip();
                            clip.open(audio5);
                            clip.start();
                        }
                        catch(Exception ex){}
                            ++b1;
                        }
                    }
                    else
                    {
                        b1=0;
                    }
    }
    
    /**
     * Showing the highScores in descending order.
     * 
     * @param g : An Instance/Object of Graphics2D
     */
    public void paintHighScores (Graphics2D g)
    {
        
        g.drawImage(scores,0,0, null);
        g.drawImage(hi,350,0,null);
                    Font p1 = new Font("Monotype Corsiva", Font.BOLD,60);
                    g.setFont(p1);
                    g.setColor(Color.BLUE);
                    //g.drawString("PONG HIGHSCORES!", 150, 80);
                    
                    g.drawRect(680, 610 , 110, 70);
                    g.drawImage(back, 680 , 610, null);
                    Font p2 = new Font("Monotype Corsiva", Font.BOLD,50);
                    g.setFont(p2);
                    g.setColor(Color.BLUE);
                    //g.drawString("Back", 350, 555);
                    if(MouseInBack == true)
                    {
                        g.setColor(Color.white);
                        g.drawRect( 680, 610,110,70);
                        if(b1==0)
                        {
                            try {
                            AudioInputStream audio4 = AudioSystem.getAudioInputStream(new File("button.wav").getAbsoluteFile());
                            Clip clip = AudioSystem.getClip();
                            clip.open(audio4);
                            clip.start();
                            
                        }
                        catch(Exception ex){}
                            
                            ++b1;
                        }
                    }
                    else
                    {
                        b1=0;
                    }
                    
                    
                    int z = 275;
                    for(int j = 4,k=0;j>-1;--j,++k)
                    {
                        //p = Integer.toString(k);
                        ss = Integer.toString(x[j]);
                        g.setColor(Color.MAGENTA);
                        g.drawString(p[k]+"-> "+ss, 620,z);
                        z+=75;
                    }
    }
    
    /**
     * This method is invoked when game is start.
     * @param g : An Instance/Object of Graphics2D
     */
    public void paintOpenning (Graphics2D g)   {
        g = s.getGraphics();
        g.drawImage(bg, 0, 0, null);
        //g.drawImage(bg_1,-20,-20,null);
        g.drawImage(bg_l,0,0,null);
        
        Font f1 = new Font("Monotype Corsiva", Font.BOLD,40);
        g.setFont(f1);
        g.setColor(Color.white);
        //g.drawString("PONG GAME", 500, 100);
        //g.setColor(Color.BLUE);
        Font f = new Font("Monotype Corsiva",Font.BOLD,25);
        g.setFont(f);
        g.drawRect(1100,550,180,40);
        g.drawString("Play", 1120, 575);
        g.drawRect(1100,600,180,40);
        g.drawString("Highscores", 1120, 625);
        g.drawRect(1100,650,180,40);
        g.drawString("Help", 1120, 675);
        //g.setColor(Color.red);
        if(MouseInPlay==true)
        {
            g.setColor(Color.blue);
            g.drawString("Play", 1120, 575);
            if(a1==0)
            try {
                            AudioInputStream audio1 = AudioSystem.getAudioInputStream(new File("button.wav").getAbsoluteFile());
                            Clip clip = AudioSystem.getClip();
                            clip.open(audio1);
                            clip.start();
                            ++a1;
                            a2=0;
                            a3=0;
                        }
                        catch(Exception ex){}
        }
        else
        {
            a1=0;
            g.setColor(Color.white);
            g.drawString("Play",1120,575);
            
        }
        if(MouseInHigh==true)
        {
            g.setColor(Color.blue);
            //g.drawString("Highscores", 670, 525);
            g.drawString("Highscores", 1120, 625);
            if(a2==0)
            try {
                            AudioInputStream audio2 = AudioSystem.getAudioInputStream(new File("button.wav").getAbsoluteFile());
                            Clip clip = AudioSystem.getClip();
                            clip.open(audio2);
                            clip.start();
                            ++a2;
                            a1=0;
                            a3=0;
                        }
                        catch(Exception ex){}
        }
        else
        {
            a2=0;
            g.setColor(Color.white);
            g.drawString("Highscores", 1120, 625);
            
        }    
        if(MouseInInst==true)
        {
            g.setColor(Color.blue);
            g.drawString("Help", 1120, 675);
            if(a3==0)
            try {
                            AudioInputStream audio3 = AudioSystem.getAudioInputStream(new File("button.wav").getAbsoluteFile());
                            Clip clip = AudioSystem.getClip();
                            clip.open(audio3);
                            clip.start();
                            ++a3;
                            a1=0;
                            a2=0;
                        }
                        catch(Exception ex){}
        }
        else
        {
            a3=0;
            g.setColor(Color.white);
            g.drawString("Help", 1120, 675);
            
        }
    }
//
    public void keyTyped(KeyEvent e) 
    {
        
    }

    /**
     * This method is invoked when user press the keys.
     * @param e: An instance/object of KeyEvent.
     */
    public void keyPressed(KeyEvent e) 
    {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            gameRunning =  false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_SPACE)
        {
            if(gameOver==true)
            {
                gameRunning = true;
                playAgain = true;
                //gameOver = false;
                //initialize();
                //highScores = 0;
                //ballX=0;
                //ballY=0;
            }
            else if(gamePause==false && gameOver == false && gameRunning == true)
            {
                gamePause = true;
                t=ballSpeed;
                ballSpeed=0;
            }
            else if(gamePause==true && gameOver != true)
            {
                gamePause = false;
                ballSpeed = t;
                t=0;
            }

        }
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            paddleMoveR=true;
            paddleMoveL=false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT){
            paddleMoveR=false;
            paddleMoveL=true;
        }
        else if (e.getKeyCode() == KeyEvent.VK_ENTER)
        {
            
        }
    }


    /**
     * This method is invoked when user released the keys.
     * @param e: An instance/object of KeyEvent. 
     */
    public void keyReleased(KeyEvent e) 
    {

    }
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseDragged(MouseEvent e) {}

    /**
     * This method is invoked when user click the mouse.
     * @param e: An instance/object of MouseEvent.
     */
    @Override
    public void mouseClicked(MouseEvent e) 
    {
        if(MouseInPlay==true)
        {
            gameStart = true;
        }
        if(MouseInHigh==true)
        {
            showHighScores = true;
        }
        if(MouseInInst==true)
        {
            showHelp = true;
        }
        if(MouseInBack==true)
        {
            showHighScores = false;
        }
        if(MouseInHelpBack ==true)
        {
            showHelp = false;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        }

    /**
     * Handles mouse movement.
     * @param e: Takes MouseEvent.
     */
    public void mouseMoved(MouseEvent e) 
    {
        /*
        
        g.drawRect(1100,550,180,40);
        g.drawString("Play", 1120, 575);
        g.drawRect(1100,600,180,40);
        g.drawString("Highscores", 1120, 625);
        g.drawRect(1100,650,180,40);
        g.drawString("Help", 1120, 675);
        
        
        */
        if(e.getX()>1100 && e.getX()<1280 && e.getY()>550 && e.getY()<590)
        {
            MouseInPlay = true;
        }
        else
        {
            MouseInPlay = false;
        }
        if(e.getX()>1100 && e.getX()<1280 && e.getY()>600 && e.getY()<640)
        {
            MouseInHigh = true;
        }
        else
        {
            MouseInHigh = false;
        }    
            
        if(e.getX()>1100 && e.getX()<1280 && e.getY()>650 && e.getY()<690)
        {
            MouseInInst = true;
        }
        else
        {
            MouseInInst = false;
        }
        if(e.getX()>680 && e.getX()<790 && e.getY()>610 && e.getY()<680)
        {
            MouseInBack = true;
        }
        else
        {
            MouseInBack = false;
        }
        if(e.getX()>680 && e.getX()<790 && e.getY()>610 && e.getY()<680)
        {
            MouseInHelpBack = true;
        }
        else
        {
            MouseInHelpBack = false;
        }
    }
}
