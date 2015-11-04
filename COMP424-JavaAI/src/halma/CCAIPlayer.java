package halma;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

import boardgame.Board;
import boardgame.Move;
import boardgame.Player;

/**
 *A random Halma player.
 */
public class CCAIPlayer extends Player {
    private boolean verbose = false;
    public Point goal = getGoal(super.playerID);
    Random rand = new Random();
    
    /** Provide a default public constructor */
    public CCAIPlayer() { super("random"); }
    public CCAIPlayer(String s) { super(s); }
    
    public Board createBoard() { return new CCBoard(); }

    /** Implement a very stupid way of picking moves */
    public Move chooseMove(Board theboard) 
    {
    	//Initialization
        CCBoard board = (CCBoard) theboard;
        ArrayList<CCMove> moves = board.getLegalMoves();
        ArrayList<CCMove> myMoves = getMyMoves(super.playerID, moves);
        CCMove bestMove = myMoves.get(0);
        int bestDistance = 0;
        ArrayList<CCMove> nextMoves = new ArrayList<CCMove>();
        CCGameState gameState = new CCGameState(super.playerID, (ArrayList<Point>) board.getPieces(playerID), board.bases[super.playerID], 0,(CCBoard) board, nextMoves);
        //Beginning Game
        if(board.getTurnsPlayed() < 15)
        {	        
	        return maxMove(gameState).bestMoves.get(0);        	
        }
        
        //Mid-Game
        if(board.getTurnsPlayed() >= 15 && board.getTurnsPlayed() <= 50)
        {	        
	        for(CCMove move : myMoves){
	        	try{
		        	int change = findDistance(move.getFrom(), goal) - findDistance(move.getTo(), goal);
		        	if(change > bestDistance){
		        		bestMove = move;
		        		bestDistance = change;
		        	}
	        	}
	        	catch(NullPointerException e)
	        	{
	        		System.out.println("whoops");
	        	}
	        }
	        return bestMove;
        }

        //End Game
        else
        {	        
        	//find the current distance to goal state
        	int distanceToGoal = 100;
	        for(CCMove move : myMoves){
	        	try{
		        	int change = findDistance(move.getFrom(), goal) - findDistance(move.getTo(), goal);
		        	if(change > bestDistance){
		        		bestMove = move;
		        		bestDistance = change;
		        	}
	        	}
	        	catch(NullPointerException e)
	        	{
	        		System.out.println("whoops");
	        	}
	        }
	        return bestMove;
        }
    
    }
    
	public CCGameState minMove(CCGameState gameState){    	
    	/*id, from, to*/
    	CCMove bestMove = new CCMove(gameState.playerID, new Point(0,0), new Point(0,0));
    	CCGameState bestGameState = gameState.clone();

    	ArrayList<CCMove> possibleMoves = getMyMoves(gameState.playerID, gameState.board.getLegalMoves());
    	for(CCMove move : possibleMoves){
    		//make move
        	CCGameState newGameState = gameState.clone();
    		newGameState.playerPieces.remove(move.getFrom());
    		newGameState.playerPieces.add(move.getTo());
    		newGameState.bestMoves.add(move);
    		newGameState.depth = newGameState.depth + 1;
    		//recursive call
            if(gameState.depth < 3){
                newGameState = maxMove(newGameState);
            }
            if(findEndgameDistance(newGameState) < findEndgameDistance(bestGameState)){
    			bestGameState = newGameState;
    		}
    	}
		return bestGameState;
    }
    
	public CCGameState maxMove(CCGameState gameState){
    	/*id, from, to*/
		CCGameState bestGameState = gameState;
    	ArrayList<CCMove> possibleMoves = getMyMoves(gameState.playerID, gameState.board.getLegalMoves());
    	for(CCMove move : possibleMoves){
    		//make move
        	CCGameState newGameState = gameState.clone();
    		newGameState.playerPieces.remove(move.getFrom());
    		newGameState.playerPieces.add(move.getTo());
    		newGameState.bestMoves.add(move);
    		newGameState.depth = newGameState.depth + 1;
    		//recursive call
            if(gameState.depth < 3){
                newGameState = minMove(newGameState);
            }
            System.out.println("passed recursive call");
            int newDistance = findEndgameDistance(newGameState);
            int bestDistance = findEndgameDistance(bestGameState);
            System.out.println("newDistance="+newDistance);
            System.out.println("bestDistance="+bestDistance);

    		if(newDistance < bestDistance ){
    			bestGameState = newGameState;
    			System.out.println(bestGameState.depth);
    		}
    	}
		return bestGameState;
	}

    public int findDistance(Point start, Point goal){
    	int x = Math.abs(start.x - goal.x);
    	int y = Math.abs(start.y - goal.y);
    	return x + y;
    }
    
    //calculates the distance from the current state to the goal state
    public int findEndgameDistance(CCGameState gameState){
    	int possibleDistance = 1000;
    	int bestDistance = 1000;
    	int combinedDistance = 0;
    	//for each piece in my pieces, find the nearest point in the goal state
    	for(Point piece : gameState.playerPieces){
			bestDistance = 1000;
    		for(Point goalSquare : gameState.goalState){
    			possibleDistance = findDistance(piece, goalSquare);
    			if(possibleDistance < bestDistance){
    				bestDistance = possibleDistance;
    			}
    		}
    		combinedDistance += bestDistance;
    	}
    	return combinedDistance;
    }
    
    
    public ArrayList<CCMove> getMyMoves(int playerID, ArrayList<CCMove> moves){
    	ArrayList<CCMove> myMoves = new ArrayList<CCMove>();
    	for( CCMove move : moves){
    		if(move.getPlayerID() == playerID)
    		{
    			myMoves.add(move);
    		}
    	}
    	return myMoves;
    }
    
    public Point getGoal(int playerID)
    {
    	if(playerID == 0)
    	{
    		return new Point(15,15);
    	}
    	if(playerID == 1)
    	{
    		return new Point(0,15);
    	}
    	if(playerID == 2)
    	{
    		return new Point(15,0);
    	}
    	if(playerID == 3)
    	{
    		return new Point(0,0);
    	}
    	return new Point(0,0);
    }
    
    public int valuePosition(int playerID, CCBoard board, Point goal){
    	ArrayList<Point> pieces = board.getPieces(playerID);
    	int combinedDistance = 0;  
    	for(Point piece : pieces){
    		combinedDistance += findDistance(piece, goal);
    	}
    	
    	return combinedDistance;
    }
} // End class
