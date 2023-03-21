package stinky.monkey.Chess.Pieces;

import stinky.monkey.Chess.Board;
import stinky.monkey.Chess.Position;

import java.util.*;

public class Pawn extends Piece {
    private boolean firstMove;

    public Pawn(TEAM team, Position position) {
        super(team, position);
        firstMove = true;
        currentMoves = new ArrayList<Moves>();
    }

    @Override
    public ArrayList<Moves> getAvailableMoves(Board board) {
        ArrayList<Moves> out = new ArrayList<Moves>();
        int modifier = this.team == Piece.TEAM.BLACK ? -1 : 1;

        Position jump = board.atPosition(position.getX(), position.getY() + 2 * modifier);
        if (firstMove && jump.getCurrentPiece() == null) {
            out.add(new Moves(jump, Moves.State.MOVE));
        }

        Position takeL = board.atPosition(position.getX() - 1, position.getY() + 1 * modifier);
        Position takeR = board.atPosition(position.getY() + 1, position.getY() + 1 * modifier);


        // Checking left diagonal
        if (takeL != null &&
                takeL.getCurrentPiece() != null) {
            Piece selPiece = takeL.getCurrentPiece();
            if (!selPiece.getClass().equals(King.class))
                out.add(new Moves(takeL, Moves.State.TAKE));
            else {
                ((King) selPiece).setChecked();
                out.add(new Moves(takeL, Moves.State.THREAT));
            }
        }

        // Checking right diagonal
        if (takeR != null &&
                takeR.getCurrentPiece() != null) {
            Piece selPiece = takeR.getCurrentPiece();
            if (!selPiece.getClass().equals(King.class))
                out.add(new Moves(takeR, Moves.State.TAKE));
            else {
                ((King) selPiece).setChecked();
                out.add(new Moves(takeR, Moves.State.THREAT));
            }
        }

        Position forward = board.atPosition(position.getX(), position.getY() + 1 * modifier);
        if (forward != null && forward.getCurrentPiece() == null)
            out.add(new Moves(forward, Moves.State.MOVE));

        currentMoves = out;
        return out;
    }

    @Override
    public Moves.State move(Position position) {
        return null;
    }
}
