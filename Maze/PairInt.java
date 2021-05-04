package Maze;

//Name: Ruohuan Xu ID:10453903

//encodes pairs of integers that represent coordinates
public class PairInt {
	
	private int x;
	private int y;
	
	public PairInt(int x,int y) {
		
		this.x = x;
		this.y = y;
		
	}
	
	public int getX() {
		
		return x;
	}
	
	public int getY() {
		
		return y;
	}
	
	//set coordinate
	public void setX(int x) {
		this.x =x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public boolean equals(Object p) {
		
		PairInt P = (PairInt)p;
		
		return (this.x == P.x && this.y == P.y);
		
	}
	
	//print output path
	public String toString() {
		
		
		StringBuilder respath = new StringBuilder();
		respath.append("("+String.valueOf(x)+","+String.valueOf(y)+")");
		
		return respath.toString();
	}
	
	//return a new copy of a pairInt
	public PairInt copy() {
		
		PairInt new_p = new PairInt(x,y);
		return new_p;
	}
	
}
