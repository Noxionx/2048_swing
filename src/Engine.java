public class Engine {
	// Test game (if necessary)
	//_________________________
	//|  2  |  2  |  4  |  4  |
	//|_____|_____|_____|_____|
	//|  8  |  16 |  4  |  4  |
	//|_____|_____|_____|_____|
	//|  0  |  2  |  8  |  8  |
	//|_____|_____|_____|_____|
	//|  0  |  2  |  4  |  2  |
	//|_____|_____|_____|_____|
	
	static int[][] game = {	{2, 2,4,4},
							{8,16,4,4},
							{0, 2,8,8},
							{0, 2,4,2}};
	
	public static void main(String[] args){
		new EngineIDE("2048");
	}
	

	
}
