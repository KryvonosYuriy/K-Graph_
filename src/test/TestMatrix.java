package test;

public class TestMatrix {
	
	//matrix 3 x 3 zusammenhängend
	public int[][] generate3Mal3Matrix(){
		int[][] array = {
				{0, 1, 0},
				{1, 0, 1},
				{0, 1, 0}
		};
		return array;
	}
	
	//matrix 3 x 3 NICHT zusammenhängend
	public int[][] generate3x3Matrix(){
		int[][] array = {
				{0, 0, 0},
				{0, 0, 1},
				{0, 1, 0}
		};
		return array;
	}
	//matrix 4 x 4 zusammenhängend
	public int[][] generate4Mal4Matrix(){
		int[][] array = {
				{0, 0, 0, 1},
				{0, 0, 1, 0},
				{0, 1, 0, 1},
				{1, 0, 1, 0}
		};
		return array;
	}
	//matrix 4 x 4 NICHT zusammenhängend
	public int[][] generate4x4Matrix(){
		int[][] array = {
				{0, 0, 0, 1},
				{0, 0, 0, 0},
				{0, 0, 0, 1},
				{1, 0, 1, 0}
		};
		return array;
	}
	//matrix 5 x 5 zusammenhängend
	public int[][] generate5Mal5Matrix(){
		int[][] array = {
				{0, 0, 0, 1, 0},
				{0, 0, 1, 0, 1},
				{0, 1, 0, 1, 0},
				{1, 0, 1, 0, 0},
				{0, 1, 0, 0, 0}
		};
		return array;
	}
	//matrix 5 x 5 NICHT zusammenhängend
	public int[][] generate5x5Matrix(){
		int[][] array = {
				{0, 0, 0, 0, 0},
				{0, 0, 1, 0, 0},
				{0, 1, 0, 1, 0},
				{0, 0, 1, 0, 0},
				{0, 0, 0, 0, 0}
		};
		return array;
	}
	//matrix 6 x 6 zusammenhängend
	public int[][] generate6Mal6Matrix(){
		int[][] array = {
				{0, 1, 0, 0, 0, 1},
				{1, 0, 1, 0, 0, 0},
				{0, 1, 0, 1, 0, 1},
				{0, 0, 1, 0, 1, 0},
				{0, 0, 0, 1, 0, 1},
				{1, 0, 1, 0, 1, 0}
		};
		return array;
	}
	//matrix 6 x 6 NICHT zusammenhängend
	public int[][] generate6x6Matrix(){
		int[][] array = {
				{0, 1, 0, 0, 0, 0},
				{1, 0, 0, 0, 0, 0},
				{0, 0, 0, 1, 0, 1},
				{0, 0, 1, 0, 1, 0},
				{0, 0, 0, 1, 0, 1},
				{0, 0, 1, 0, 1, 0}
		};
		return array;
	}
	//matrix 7 x 7 zusammenhängend
	public int[][] generate7Mal7Matrix(){
		int[][] array = {
				{0, 1, 0, 0, 1, 1, 1},
				{1, 0, 1, 0, 0, 0, 0},
				{0, 1, 0, 1, 0, 0, 0},
				{0, 0, 1, 0, 1, 0, 0},
				{1, 0, 0, 1, 0, 0, 0},
				{1, 0, 0, 0, 0, 0, 1},
				{1, 0, 0, 0, 0, 1, 0}
		};
		return array;
	}
	//matrix 7 x 7 NICHT zusammenhängend
	public int[][] generate7x7Matrix(){
		int[][] array = {
				{0, 1, 0, 0, 1, 1, 1},
				{1, 0, 1, 0, 0, 0, 0},
				{0, 1, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0},
				{1, 0, 0, 0, 0, 0, 0},
				{1, 0, 0, 0, 0, 0, 1},
				{1, 0, 0, 0, 0, 1, 0}
		};
		return array;
	}
	//matrix 8 x 8 zusammenhängend
	public int[][] generate8Mal8Matrix(){
		int[][] array = {
				{0, 1, 0, 0, 0, 1, 0, 1},
				{1, 0, 1, 1, 0, 0, 0, 0},
				{0, 1, 0, 1, 0, 0, 0, 0},
				{0, 1, 1, 0, 1, 0, 0, 0},
				{0, 0, 0, 1, 0, 0, 0, 0},
				{1, 0, 0, 0, 0, 0, 1, 0},
				{0, 0, 0, 0, 0, 1, 0, 1},
				{1, 0, 0, 0, 0, 0, 1, 0}
		};
		return array;
	}
	//matrix 8 x 8 NICHT zusammenhängend
	public int[][] generate8x8Matrix(){
		int[][] array = {
				{0, 0, 0, 0, 0, 1, 0, 1},
				{0, 0, 1, 0, 0, 0, 0, 0},
				{0, 1, 0, 1, 0, 0, 0, 0},
				{0, 0, 1, 0, 1, 0, 0, 0},
				{0, 0, 0, 1, 0, 0, 0, 0},
				{1, 0, 0, 0, 0, 0, 1, 0},
				{0, 0, 0, 0, 0, 1, 0, 1},
				{1, 0, 0, 0, 0, 0, 1, 0}
		};
		return array;
	}
	//matrix 13 x 13 zusammenhängend
	public int[][] generate13Mal13Matrix(){
		int[][] array = {
				{0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1}, //1
				{1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0}, //2
				{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //3
				{0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0}, //4
				{0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0}, //5
				{0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0}, //6
				{0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0}, //7
				{0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0}, //8 
				{0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0}, //9
				{0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 0}, //10
				{0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0}, //11
				{1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1}, //12
				{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0}  //13
		};
		return array;
	}
}
