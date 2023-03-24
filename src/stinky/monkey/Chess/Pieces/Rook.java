package stinky.monkey.Chess.Pieces;

import stinky.monkey.Chess.Board;
import stinky.monkey.Chess.Position;

import java.util.ArrayList;

public class Rook extends Piece {
    private boolean castleAble;

    public Rook(TEAM team, Position position) {
        super(team, position);
        castleAble = true;
    }

    @Override
    public Move.State move(Position position) throws IncorrectMove {
        castleAble = false;
        return super.move(position);
    }

    public boolean canCastle() {return castleAble;};

    @Override
    public ArrayList<Move> getAvailableMoves(Board board) {
        currentMoves.clear();
        selectRadius(board, 0, -1, position);
        selectRadius(board, 0, 1, position);
        selectRadius(board, -1, 0, position);
        selectRadius(board, 1, 0, position);
        return currentMoves;
    }
}
