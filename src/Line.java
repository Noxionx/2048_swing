
public class Line {
	private int[] line = new int[4];

	
	public Line(int a,int b,int c,int d){
		this.line[0] = a;
		this.line[1] = b;
		this.line[2] = c;
		this.line[3] = d;
	}
	
	private void slide(){
		for(int passe=0; passe<3; passe++){
			for(int i=0; i<4; i++){
				if(line[i]==0 && i<3){
					line[i]=line[i+1];
					line[i+1]=0;
				}
			}
		}	
	}
	private int merge(){
		int score = 0;
		for(int i=0 ; i<3; i++){
			if(line[i]==line[i+1]){
				line[i]=line[i]*2;
				score = score + line[i];
				line[i+1]=0;
				
			}
		}
		return score;
	}
	public int process(){
		int score = 0;
		slide();
		score = merge();
		slide();
		return score;
	}
	
	public int[] getLine(){
		return line;
	}
	public void setLine(int a, int b, int c, int d){
		line[0]=a;
		line[1]=b;
		line[2]=c;
		line[3]=d;
	}
	
	public static void main(String[] args){
		Line l = new Line(4,4,8,8);
		System.out.println("Line before : | "+l.getLine()[0]+" | "+l.getLine()[1]+" | "+l.getLine()[2]+" | "+l.getLine()[3]+" | ");
		l.process();
		System.out.println("Line after : | "+l.getLine()[0]+" | "+l.getLine()[1]+" | "+l.getLine()[2]+" | "+l.getLine()[3]+" | ");
	}
}
