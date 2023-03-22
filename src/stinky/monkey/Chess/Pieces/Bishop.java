package stinky.monkey.Chess.Pieces;

import stinky.monkey.Chess.Board;
import stinky.monkey.Chess.Position;

import java.util.ArrayList;

public class Bishop extends Piece {
    public Bishop(TEAM team, Position position) {
        super(team, position);
    }

    @Override
    public ArrayList<Move> getAvailableMoves(Board board) {
        currentMoves.clear();
        selectRadius(board, 1, 1, position);
        selectRadius(board, 1, -1, position);
        selectRadius(board, -1, 1, position);
        selectRadius(board, -1, -1, position);
        return currentMoves;
    }
}
