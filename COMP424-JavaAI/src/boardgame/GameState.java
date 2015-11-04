package boardgame;

import java.util.ArrayList;
import java.awt.Point;

import halma.CCBoard;
import halma.CCGameState;

abstract public class GameState implements Cloneable {
	abstract public CCGameState CCGameState(int playerID, ArrayList<Point> playerPieces, ArrayList<Point> goalState, int depth, CCBoard board);
	abstract public CCGameState clone();
	public int playerID;
	public ArrayList<Point> playerPieces;
	public ArrayList<Point> goalState;  
	public int depth;
	public CCBoard board;
}