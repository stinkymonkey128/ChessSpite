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
    public Move.State move(Position position) {
        castleAble = false;
        return super.move(position);
    }

    @Override
    public ArrayList<Move> getAvailableMoves(Board board) {
        Piece maybeKing = board.atPosition(4, team == Piece.TEAM.BLACK ? 7 : 0).getCurrentPiece();
        if (castleAble && maybeKing != null && maybeKing.getClass().equals(King.class) && ((King) maybeKing).canCastle()) {

        }
        return null;
    }
}
