package stinky.monkey.Chess.Pieces;

import stinky.monkey.Chess.Board;
import stinky.monkey.Chess.Position;

import java.util.*;

public class Pawn extends Piece {
    private boolean firstMove;

    public Pawn(TEAM team, Position position) {
        super(team, position);
        firstMove = true;
        currentMoves = new ArrayList<Move>();
    }

    @Override
    public Move.State move(Position position) throws IncorrectMove {
        firstMove = false;
        return super.move(position);
    }

    @Override
    public ArrayList<Move> getAvailableMoves(Board board) {
        currentMoves.clear();
        int modifier = this.team == Piece.TEAM.BLACK ? -1 : 1;

        Position jump = board.atPosition(position.getX(), position.getY() + 2 * modifier);
        if (firstMove && jump != null && jump.getCurrentPiece() == null) {
            currentMoves.add(new Move(jump, Move.State.MOVE));
        }

        Position takeL = board.atPosition(position.getX() - 1, position.getY() + 1 * modifier);
        Position takeR = board.atPosition(position.getY() + 1, position.getY() + 1 * modifier);


        // Checking left diagonal
        if (takeL != null &&
                takeL.getCurrentPiece() != null &&
                takeL.getCurrentPiece().getTeam() != team) {
            Piece selPiece = takeL.getCurrentPiece();
            if (!selPiece.getClass().equals(King.class))
                currentMoves.add(new Move(takeL, Move.State.TAKE));
            else {
                ((King) selPiece).setChecked();
                currentMoves.add(new Move(takeL, Move.State.THREAT));
            }
        }

        // Checking right diagonal
        if (takeR != null &&
                takeR.getCurrentPiece() != null &&
                takeL.getCurrentPiece().getTeam() != team) {
            Piece selPiece = takeR.getCurrentPiece();
            if (!selPiece.getClass().equals(King.class))
                currentMoves.add(new Move(takeR, Move.State.TAKE));
            else {
                ((King) selPiece).setChecked();
                currentMoves.add(new Move(takeR, Move.State.THREAT));
            }
        }

        Position forward = board.atPosition(position.getX(), position.getY() + 1 * modifier);
        if (forward != null && forward.getCurrentPiece() == null)
            currentMoves.add(new Move(forward, Move.State.MOVE));

        return currentMoves;
    }
}
