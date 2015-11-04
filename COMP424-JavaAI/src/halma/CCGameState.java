package halma;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;

public class CCGameState {
	public int playerID;
	public ArrayList<Point> playerPieces;
	public HashSet<Point> goalState;  
	public int depth;
	public CCBoard board;
	public ArrayList<CCMove> bestMoves;
	public CCGameState(int pPlayerID, ArrayList<Point> pPlayerPieces, HashSet<Point> pGoalState, int depth, CCBoard board, ArrayList<CCMove> bestMoves){
		this.playerID = pPlayerID;
		this.playerPieces = (ArrayList<Point>) pPlayerPieces.clone();
		this.goalState = (HashSet<Point>) pGoalState.clone();
		this.depth = depth;
		this.board = (CCBoard) board.clone();
		this.bestMoves = bestMoves;
	}

	public CCGameState clone()
	{
		return new CCGameState(this.playerID, (ArrayList<Point>)this.playerPieces.clone(), (HashSet<Point>) this.goalState.clone(), this.depth, (CCBoard) this.board.clone(), (ArrayList<CCMove>) this.bestMoves.clone());
	}
}