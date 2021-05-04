package Maze;

//Name: Ruohuan Xu ID:10453903

import java.util.*;
/**
 * Class that solves maze problems with backtracking.
 * @author Koffman and Wolfgang
 **/
public class Maze implements GridColors {

    /** The maze */
	
    private TwoDimGrid maze;

    public Maze(TwoDimGrid m) {
    	
        maze = m;
    }

    /** Wrapper method. */
    
    public boolean findMazePath() {
    	
        return findMazePath(0, 0); // (0, 0) is the start point.
    }

    /**
     * Attempts to find a path through point (x, y).
     * @pre Possible path cells are in BACKGROUND color;
     *      barrier cells are in ABNORMAL color.
     * @post If a path is found, all cells on it are set to the
     *       PATH color; all cells that were visited but are
     *       not on the path are in the TEMPORARY color.
     * @param x The x-coordinate of current point
     * @param y The y-coordinate of current point
     * @return If a path through (x, y) is found, true;
     *         otherwise, false
     */
    
    //PROBLEM 1
    public boolean findMazePath(int x, int y) {
    	
    	//the current cell should be inside the grid and have non_background
    	//exit cell: painted color path 
    	int col = maze.getNCols();
    	int row = maze.getNRows();
    	if (x >=0 && y >=0 && x < col && y < row && maze.getColor(x, y) == NON_BACKGROUND)
    	{
    		//current cell is the exit cell
    		if (x == col-1 && y == row-1) {
    			maze.recolor(x, y, PATH);
    			return true;   			
    		}
    	//current cell is the part of the path
    	else {
    		//painted color path 
    		maze.recolor(x, y, PATH);
    		
    		//explore neighboring cells using recursive calls
    		boolean neighbor = ( findMazePath( x+1, y) || findMazePath( x, y+1) || findMazePath( x, y-1) || findMazePath( x-1, y));
    		if(neighbor)
    		{
    			return true;
    		}
    	//current cell is not the part of the path
    	else {
    		//marked it with color temporary
    		
    		maze.recolor(x, y, TEMPORARY);
    		
    		return false;
    		
    	}   		
    	}
    	}
    	
    	//not find the path
    	else {
    		return false;    		
    	}

    }

    //PROBLEM 2
    public ArrayList<ArrayList<PairInt>> findAllMazePaths(int x,int y){
    	
    	ArrayList<ArrayList<PairInt>> result = new ArrayList<>();
    	Stack < PairInt > trace = new Stack < >();
    	findMazePathStackBased(0,0,result,trace);
    	return result;
    	
    }
    
    //helper method
    public void findMazePathStackBased(int x, int y, ArrayList<ArrayList<PairInt>> result, Stack<PairInt> trace) {
    	
    	//check current cell in the maze
    	int col = maze.getNCols();
    	int row = maze.getNRows();
    	if(x <0 || y< 0 || x >col-1 || y >row-1 || maze.getColor(x, y) != NON_BACKGROUND) {
    		
    		return;
    	}
    	//copy the contents into a list when exit reached
    	if( x == col-1 && y == row-1) {
    		
    		PairInt p = new PairInt(x,y);
    		ArrayList<PairInt> list = new ArrayList<PairInt>();
    		maze.recolor(x, y, PATH);
    		trace.push(p);
    		list.addAll(trace);
    		result.add(list);
    		trace.pop();
    		
    	}   
    		//mark cell before call helper recursively on neighboring cell
    		maze.recolor(x, y, PATH);
    		trace.push(new PairInt(x,y));
    		findMazePathStackBased(x+1, y, result, trace);
    		findMazePathStackBased(x-1, y, result, trace);
    		findMazePathStackBased(x, y+1, result, trace);
    		findMazePathStackBased(x, y-1, result, trace);
    		//remove mark after visiting neighbors
    		maze.recolor(x, y, NON_BACKGROUND);
    		trace.pop();
    		
 
    	
    }
    
    //PROBLEM 3
    //return shortest path in the list of paths
    public ArrayList<PairInt> findMazePathMin(int x, int y){
    	
    	//while the path is not exist
    	if ( findMazePath(x,y) == false) {
    		return null;
    	}
    	
    	//while the path exist
    	  //restore all path
    	maze.recolor(PATH, NON_BACKGROUND);
    	maze.recolor(TEMPORARY, NON_BACKGROUND);
    	
    	  //find all path
    	ArrayList<ArrayList<PairInt>> paths = findAllMazePaths(x,y);
    	if( paths.size() !=0) {
    	ArrayList<PairInt> minpath = paths.get(0);
    	int minsize = minpath.size();
    	
    	 //compare to find min path
    	int i = 1;
    	while(i < paths.size()) {
    		
    		int tmpsize = paths.get(i).size();
    		if( tmpsize <= minsize) {
    			minsize = tmpsize;
    			minpath = paths.get(i);   			
    		}
    		i++;
    	}
    	
    	return minpath; 
    	}
    	else {
    		return new ArrayList<PairInt>();
    	}
    }
    

    /*<exercise chapter="5" section="6" type="programming" number="2">*/
    public void resetTemp() {
        maze.recolor(TEMPORARY, BACKGROUND);
    }
    /*</exercise>*/

    /*<exercise chapter="5" section="6" type="programming" number="3">*/
    public void restore() {
        resetTemp();
        maze.recolor(PATH, BACKGROUND);
        maze.recolor(NON_BACKGROUND, BACKGROUND);
    }
    /*</exercise>*/
}
/*</listing>*/
