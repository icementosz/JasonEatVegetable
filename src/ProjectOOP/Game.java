import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.util.ArrayList;
import java.lang.Thread;  

class lifea{
    public static int life=10;
}

class Scorea{
    private int scorea=0;

    public int getscorea(){
        return scorea;
    }
}

class MenuPanel extends JPanel{
	JButton btplay = new JButton("");
	JButton bthelp = new JButton("");
	JButton btexit = new JButton("");
	
	ImageIcon bgmenu = new ImageIcon(getClass().getResource("backgroundmenu.png"));

	ImageIcon imgbtplay = new ImageIcon(getClass().getResource("playbutton.png")); 
	ImageIcon imgbthelp = new ImageIcon(getClass().getResource("helpbutton.png"));
	ImageIcon imgbtexit = new ImageIcon(getClass().getResource("exitbutton.png"));

	JPanel framemenu = new JPanel();
	
	MenuPanel(){
		
		framemenu.setLayout(new BoxLayout(framemenu,BoxLayout.Y_AXIS));
		add(framemenu);
		
		btplay.setIcon(imgbtplay); 
		bthelp.setIcon(imgbthelp);
		btexit.setIcon(imgbtexit);
		
		framemenu.add(btplay);
		framemenu.add(bthelp);
		framemenu.add(btexit);
				
		btplay.addActionListener(new Click());
		bthelp.addActionListener(new Click());
		btexit.addActionListener(new Click());
	}
	
	class Click implements ActionListener{
	
		public void actionPerformed(ActionEvent e){
			if(e.getSource() == btplay){
				Game.cl.show(Game.cards, "GamePanel");
                lifea.life=10;           
			}
			if(e.getSource() == bthelp){
				Game.cl.show(Game.cards, "HelpPanel"); 
			}	
			if(e.getSource() == btexit){
				System.exit(0);  
			}
		}
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		setFocusable(true);
		g.drawImage(bgmenu.getImage(), 0,0, null);
		repaint();
	}
}

class HelpPanel extends JPanel{
	ImageIcon bghelp = new ImageIcon(getClass().getResource("backgroundhelp.png"));
	JButton btback;
	HelpPanel(){
        setLayout(null);
		setFocusable(true);
        btback = new JButton("Back");
        btback.setBounds(454, 650, 80, 50);
		add(btback);			
		
		btback.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Game.cl.show(Game.cards, "MenuPanel");
			}	
		  });
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(bghelp.getImage(), 0,0, null);
		repaint();
	}
}

class GamePanel extends JPanel{ 
	ImageIcon bggame = new ImageIcon(getClass().getResource("backgroundgame.png"));
    ImageIcon heart = new ImageIcon(getClass().getResource("heart.png"));
    // Image heart1 = heart.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
    


    Jason Jason;
    ArrayList<ObjectFall> Vegetablearray;
    Bomb Bomb;
	Random rand = new Random();

    Thread time1;
    Thread time2;

    private Scorea scorea;
    int score = 0;
    int detectbomb = 0;
	boolean gameOver = false;
	GamePanel(){
		setLayout(null);
		setFocusable(true);
        this.scorea = new Scorea();
        score = scorea.getscorea();
        Jason = new Jason();
        Vegetablearray = new ArrayList<>();
        Vegetablearray.add(new Vegetable(100,100));
        Vegetablearray.add(new Vegetable(100,100));
        Vegetablearray.add(new Vegetable(100,100));
        Bomb = new Bomb();
        
        time1 = new Thread(new Runnable(){
            public void run(){
                while(true){
                    try{
                        FallVegetable();
                        repaint();
                        Thread.sleep(8);
                    }catch(InterruptedException e){

                    }
                }
            }
        });
        
        time1.start();

        time2 = new Thread(new Runnable(){
            public void run(){
                while(true){
                    try{
                        FallBomb();
                        repaint();
                        Thread.sleep(1);
                    }catch(InterruptedException e){

                    }
                }
            }
        });
        
        time2.start();

		addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent k){
				
				if(k.getKeyCode() == KeyEvent.VK_LEFT && Jason.getx_jason() >= -30){
					Jason.setx_jason(Jason.getx_jason()-50);
                    System.out.println("Move Left");
				}
				if(k.getKeyCode() == KeyEvent.VK_RIGHT && Jason.getx_jason()<=849){
					Jason.setx_jason(Jason.getx_jason()+50);
                    System.out.println("Move Right");
				}
			}
		});	
	}
	
    public void FallVegetable(){
        for(int i=0;i<Vegetablearray.size();i++){
            if(Vegetablearray.get(i).gety_vegetable() >= 650){
                lifea.life = lifea.life - 1;
                Vegetablearray.remove(i);
                Vegetablearray.add(new Vegetable());
            }
            else{
                Vegetablearray.get(i).sety_vegetable(Vegetablearray.get(i).gety_vegetable()+2);
            }
        }
    }

    public  void FallBomb(){
        if(Bomb.gety_bomb() >= 650){
            Bomb = new Bomb();
        }
        else{
            Bomb.sety_bomb(Bomb.gety_bomb()+1);
        }
    }

	public void detectVegetableCollision(){
		Rectangle JasonRect = new Rectangle(Jason.getx_jason(),Jason.gety_jason(),200,100); 
		for(int i=0;i<Vegetablearray.size();i++){
            if(Vegetablearray.get(i).VegetableRect.intersects(JasonRect)){
                score = score + 5;
                Vegetablearray.remove(i);
                Vegetablearray.add(new Vegetable());
            }
        }
	}

    public void detectBombCollision(){
		Rectangle JasonRect = new Rectangle(Jason.getx_jason(),Jason.gety_jason(),200,100); 
        if(Bomb.BombRect.intersects(JasonRect)){
            System.out.println("Die");
            detectbomb = detectbomb+1;
        }
	}
	
	public void checkGameOver(){
		if(lifea.life <= 0 || detectbomb > 0)
		{
            gameOver = true;
		}
	}
	
    JButton btexit = new JButton("Exit");
	public void paintComponent(Graphics g){
        var width = 50;
        var height = 50;
		super.paintComponent(g);
		checkGameOver();
		
		if(gameOver == false){
			setFocusable(true);
			grabFocus();
			detectVegetableCollision();
            detectBombCollision();
            checkGameOver();
            g.drawImage(bggame.getImage(), 0, 0, null);
            g.setFont(new Font("2005_iannnnnTKO",Font.CENTER_BASELINE,30));
            // g.drawImage(heart1.getImage(),700,10,null);
            g.drawImage(heart.getImage(),700,10,width,height,this);
            g.setColor(Color.white);
            g.drawString("SCORE : "+score, 30, 40);
            g.drawString("Heart : "+lifea.life,770,45); 
            Jason.draw(g);
            for(int i=0;i<Vegetablearray.size();i++){
                Vegetablearray.get(i).draw(g);
            }
            if(score >= 150){
                Bomb.draw(g);
            }
		}

        else if(gameOver == true){
            g.setFont(new Font("2005_iannnnnTKO",Font.CENTER_BASELINE,50));
            g.setColor(Color.red);
            g.drawString("Game Over !", 370, 300);
            g.setFont(new Font("2005_iannnnnTKO",Font.CENTER_BASELINE,30));
            g.setColor(Color.black);
            g.drawString("Your Score is "+score,400,350);
            btexit.setBounds(464, 420, 80, 50);
            add(btexit);
            btexit.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    System.exit(0);  
                }	
              });
        }
		
	}
}

public class Game extends JFrame{
	
	static MenuPanel MenuPanel = new MenuPanel();
	static HelpPanel HelpPanel = new HelpPanel();
	static GamePanel GamePanel = new GamePanel();
	
	static CardLayout cl = new CardLayout();
	static JPanel cards = new JPanel(); 
	Game(){
		
		cards.setLayout(cl);
		cards.add(MenuPanel, "MenuPanel");
		cards.add(HelpPanel, "HelpPanel");
		cards.add(GamePanel, "GamePanel");
		cl.show(cards, "MenuPanel");
		add(cards);
		
		setTitle("Jason Eat Vegetable Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1024, 768);
        setResizable(false);
		setVisible(true); 
	}
	
	public static void main(String args[]){
		new Game();
	}
}