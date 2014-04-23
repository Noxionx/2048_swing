	
public class Grid {
	private int score = 0;
	static final int probaFour = 15; // Probability of 4 (instead of 2) -> %
	private int[][] grid = new int[4][4];
	
	public Grid(){
		//Put 0 in all cells
		for(int i = 0; i<4; i++){
			for(int j = 0; j<4; j++){
				grid[i][j]=0;
			}
		}
		//Get a random cell and put 2 or 4
		int x = (int) (Math.random() * 4);
		int y = (int) (Math.random() * 4);
		grid[x][y]=2;
		//Get a 2nd random cell and do the same
		x = (int) (Math.random() * 4);
		y = (int) (Math.random() * 4);
		//Check if 2nd cell is different
		if(grid[x][y]==0){
			grid[x][y]=twoOrFour();
		}
		else{
			grid[x][(y+1)%4]=twoOrFour();
		}
	}
	public Grid (int[][] game){
		grid = game;
	}
	
	public int getCell(int x,int y){
		return grid[x][y];
	}
	public int getScore(){
		return score;
	}
	
	public void newCase(){
		boolean done = false;
		int a;
		int b;
		do{
			a = (int) (Math.random() * 4);
			b = (int) (Math.random() * 4);
			if(grid[a][b]==0){
				grid[a][b]=twoOrFour();
				done=true;
			}
		}while(!done);
		if(!canMove()){
			looseFunc();
		}
	}
	public int twoOrFour(){
		int randomized = (int) (Math.random()*100);
		if(randomized<probaFour){
			return 4;
		}
		else{
			return 2;
		}
	}
	
	public boolean canMove(){
		return okMove("LEFT")||okMove("RIGHT")||okMove("UP")||okMove("DOWN");
	}
	public boolean okMove(String direction){
		boolean okMove=false;
		switch (direction){
		case "UP":
				for(int i=0;i<4;i++){
					Line l= new Line(grid[0][i],grid[1][i],grid[2][i],grid[3][i]);
					l.process();
					for(int j=0;j<4;j++){
						if(grid[j][i]!=l.getLine()[j]){
							okMove=true;
						}
					}
				}
				break;
		case "DOWN":
			for(int i=0;i<4;i++){
				Line l= new Line(grid[3][i],grid[2][i],grid[1][i],grid[0][i]);
				l.process();
				for(int j=0;j<4;j++){
					if(grid[3-j][i]!=l.getLine()[j]){
						okMove=true;
					}
				}
			}
			break;
		case "LEFT":
			for(int i=0;i<4;i++){
				Line l= new Line(grid[i][0],grid[i][1],grid[i][2],grid[i][3]);
				l.process();
				for(int j=0;j<4;j++){
					if(grid[i][j]!=l.getLine()[j]){
						okMove=true;
					}
				}
			}
			break;
		
		case "RIGHT":
			for(int i=0;i<4;i++){
				Line l= new Line(grid[i][3],grid[i][2],grid[i][1],grid[i][0]);
				l.process();
				for(int j=0;j<4;j++){
					if(grid[i][3-j]!=l.getLine()[j]){
						okMove=true;
					}
				}
			}            
			break;
		default : break;
		}
		return okMove;
	}
	public void move(String direction){
		boolean newCaseTODO = okMove(direction);
		switch (direction){
		case "UP":
				for(int i=0;i<4;i++){
					Line l= new Line(grid[0][i],grid[1][i],grid[2][i],grid[3][i]);
					score += l.process();
					for(int j=0;j<4;j++){
						grid[j][i]=l.getLine()[j];
					}
				}
				break;
		case "DOWN":
			for(int i=0;i<4;i++){
				Line l= new Line(grid[3][i],grid[2][i],grid[1][i],grid[0][i]);
				score += l.process();
				for(int j=0;j<4;j++){
					grid[3-j][i]=l.getLine()[j];
				}
			}
			break;
		case "LEFT":
			for(int i=0;i<4;i++){
				Line l= new Line(grid[i][0],grid[i][1],grid[i][2],grid[i][3]);
				score += l.process();
				for(int j=0;j<4;j++){
					grid[i][j]=l.getLine()[j];
				}
			}
			break;
		
		case "RIGHT":
			for(int i=0;i<4;i++){
				Line l= new Line(grid[i][3],grid[i][2],grid[i][1],grid[i][0]);
				score += l.process();
				for(int j=0;j<4;j++){
					grid[i][3-j]=l.getLine()[j];
				}
			}            
			break;
		default : break;
		}
		System.out.println("score : "+score);
		if(win()){
			winFunc();
		}
		if(newCaseTODO){
			newCase();
		}	
	}
	
	public boolean win(){
		boolean win = false;
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				if(grid[i][j]==2048){
					win=true;
				}
			}
		}
		return win;
	}
	public void winFunc(){
		System.out.println("c'est bien tu es un boss !");
	}
	public void looseFunc(){
		System.out.println("tu t'es bien fait baiser !");
	}
	
	public void print(){
		System.out.println(" _______________________________________________________________");
		System.out.println("|               |               |               |               |");
		System.out.println("|\t"+grid[0][0]+"\t|\t"+grid[0][1]+"\t|\t"+grid[0][2]+"\t|\t"+grid[0][3]+"\t|");
		System.out.println("|_______________|_______________|_______________|_______________|");
		System.out.println("|               |               |               |               |");
		System.out.println("|\t"+grid[1][0]+"\t|\t"+grid[1][1]+"\t|\t"+grid[1][2]+"\t|\t"+grid[1][3]+"\t|");
		System.out.println("|_______________|_______________|_______________|_______________|");
		System.out.println("|               |               |               |               |");
		System.out.println("|\t"+grid[2][0]+"\t|\t"+grid[2][1]+"\t|\t"+grid[2][2]+"\t|\t"+grid[2][3]+"\t|");
		System.out.println("|_______________|_______________|_______________|_______________|");
		System.out.println("|               |               |               |               |");
		System.out.println("|\t"+grid[3][0]+"\t|\t"+grid[3][1]+"\t|\t"+grid[3][2]+"\t|\t"+grid[3][3]+"\t|");
		System.out.println("|_______________|_______________|_______________|_______________|");

	}
}
