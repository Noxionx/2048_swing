import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EngineIDE extends JFrame implements WindowListener{

		/**
		 * 
		 */
	
		private JLabel[][] textGrid = new JLabel[4][4];
		private JPanel[][] colorGrid = new JPanel[4][4];
		private JLabel labelScore;
		private JLabel labelMaxScore;
		private long maxScore = 0;
        private FileInputStream  fis = null;
        private FileOutputStream fos = null;
		private Grid grid = new Grid();
		private static final long serialVersionUID = 1L;
		
		public EngineIDE(String name){
			super(name);
		    this.setTitle("2048");
		    this.setSize(615, 700);
		    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    this.addKeyListener(new KeyboardListener());
		    this.addWindowListener(this);
		    JPanel pan = new JPanel();
		    pan.setSize(600,700);
		    pan.setLayout(null);
		    
		    this.setContentPane(pan);               
		    this.setLocationRelativeTo(null);
		    
		    JPanel topView = new JPanel();
		    topView.setBounds(0, 0, 600, 60);
		    pan.add(topView);
		    
		    Font myScoreFont = new Font("Arial",Font.BOLD,20);
		    Font scoreFont = new Font("Arial",Font.BOLD,35);
		    
		    JLabel labelMyScore = new JLabel("Your Score :");
		    labelMyScore.setFont(myScoreFont);
		    labelScore = new JLabel("0");
		    labelScore.setFont(scoreFont);
		    
		    JLabel labelMyMaxScore = new JLabel("Best Score :");
		    labelMyMaxScore.setFont(myScoreFont);
		    labelMaxScore = new JLabel("0");
		    labelMaxScore.setFont(scoreFont);
		    
		    topView.add(labelMyScore);
		    topView.add(labelScore);
		    topView.add(labelMyMaxScore);
		    topView.add(labelMaxScore);
		    
		    JPanel gameView = new JPanel();
		    gameView.setBounds(0, 60, 600, 600);
		    pan.add(gameView);
		    gameView.setLayout(new GridLayout(4, 4,5,5));
		    gameView.setBackground(Color.gray);  
		    
		    //Creation of a "JGrid" 
		    for(int i=0;i<4;i++){
		    	for(int j=0;j<4;j++){
		    		
		    		JPanel boxPan = new JPanel();
		    		boxPan.setLayout(new BorderLayout());
		    		JLabel box = new JLabel(Integer.toString(grid.getCell(i, j)));
		    		box.setHorizontalAlignment(JLabel.CENTER);
		    		boxPan.add(box, BorderLayout.CENTER);
		    		boxPan.setBackground(Color.white);
		    		Font f = new Font("Arial",Font.BOLD,50);
		    		box.setFont(f);
		    		colorGrid[i][j]=boxPan;
		    		textGrid[i][j]=box;
		    		gameView.add(boxPan);
		    	}
		    }
		    maxScore = getMaxScore();
		    
		    reload();
		    this.setVisible(true);
		} 
		
		public void reload(){
			labelScore.setText(Long.toString(grid.getScore()));
			if(grid.getScore()>=maxScore){
				maxScore=grid.getScore();	
			}
			labelMaxScore.setText(Long.toString(maxScore));
			for(int i=0;i<4;i++){
		    	for(int j=0;j<4;j++){
		    		if(grid.getCell(i, j)==0){
		    			textGrid[i][j].setText("");
		    		}
		    		else{
		    			textGrid[i][j].setText(Integer.toString(grid.getCell(i, j)));
		    		}
		    		colorGrid[i][j].setBackground(getColorNb(grid.getCell(i, j)));
		    	}
			}
		}
		
		public Color getColorNb(int nb){
			switch(nb){
    		case 0 : return (Color.white);
    		case 2 : return (new Color(120,228,255));
    		case 4 : return (new Color(96,116,255));
    		case 8 : return (new Color(96,108,159));
    		case 16 : return (new Color(72,132,111));
    		case 32 : return (new Color(120,188,95));
    		case 64 : return (new Color(160,255,39));
    		case 128 : return (new Color(160,159,87));
    		case 256 : return (new Color(232,159,87));
    		case 512 : return (new Color(232,95,143));
    		case 1024 : return (new Color(232,55,47));
    		case 2048 : return (new Color(112,0,0));
    		case 4096 : return (new Color(48,48,48));
    		case 8192 : return (new Color(16,16,16));
    		default : return Color.white;
    		}
		}
		public long getMaxScore(){
			long s = 0;
			try {
				fis = new FileInputStream(new File("scoreMax.txt"));
				BufferedReader br=new BufferedReader(new InputStreamReader(fis));
				s = Long.parseLong(br.readLine());	
				System.out.println(s);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				
			} catch (IOException e) {
				
			}
			finally{
				try {
		            if (fis != null)
		               fis.close();
		         } catch (IOException e) {
		            e.printStackTrace();
		         }
			}
			return s;
		}
		public void setMaxScore(long mScore){
			if(mScore>=maxScore){
				try {
					fos = new FileOutputStream(new File("scoreMax.txt"));
					fos.write(Long.toString(mScore).getBytes());
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				finally{
					try {
			            if (fos != null)
			               fos.close();
			         } catch (IOException e) {
			            e.printStackTrace();
			         }
				}
			}
		}
		class KeyboardListener implements KeyListener{
		    public void keyPressed(KeyEvent event) {
		      switch(event.getKeyCode()){
		      case 37 :grid.move("LEFT");
		      	break;
		      case 38 :grid.move("UP");
    			break;
		      case 39 :grid.move("RIGHT");
    			break;
		      case 40 :grid.move("DOWN");
    			break;
		      }
		      reload();
		    }
		    public void keyReleased(KeyEvent event) {
                
		    }
		    public void keyTyped(KeyEvent event) {

		    }       
		  }

		@Override
		public void windowActivated(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosed(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosing(WindowEvent arg0) {
			// TODO Auto-generated method stub
			System.out.println("allo");
			setMaxScore(grid.getScore());
		}

		@Override
		public void windowDeactivated(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeiconified(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowIconified(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowOpened(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}   		
	}