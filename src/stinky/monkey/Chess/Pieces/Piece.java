package stinky.monkey.Chess.Pieces;

import stinky.monkey.Chess.*;

import java.util.*;

public abstract class Piece {
    public static enum TEAM {
        BLACK,
        WHITE
    }

    protected TEAM team;
    protected Position position;
    protected ArrayList<Move> currentMoves;

    public Piece(TEAM team, Position position) {
        this.team = team;
        this.position = position;
        this.position.set(this);
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public TEAM getTeam() {
        return team;
    }

    /**
     * Gets available positions for piece includes moves, takes, and threats.
     * Also updates the private arraylist for the move call.
     */
    public abstract ArrayList<Move> getAvailableMoves(Board board);

    public Move.State move(Position position) {
        for (Move move : currentMoves) {
            if (move.position == position) {
                if (move.state != Move.State.THREAT) {
                    move.position.set(this);
                    this.position = move.position;
                    return move.state;
                }
                return Move.State.THREAT;
            }
        }
        throw new IncorrectMove();
    }

    protected void selectRadius(Board board, int xDirec, int yDirec, Position current) {
        Position nextPos = board.atPosition(current.getX() + xDirec, current.getY() + yDirec);
        if (nextPos == null)
            return;
        Piece selectPiece = nextPos.getCurrentPiece();
        if (selectPiece != null && selectPiece.getTeam() != team) {
            if (selectPiece.getClass().equals(King.class))
                currentMoves.add(new Move(nextPos, Move.State.THREAT));
            else
                currentMoves.add(new Move(nextPos, Move.State.TAKE));
        }
        if (selectPiece == null) {
            currentMoves.add(new Move(nextPos, Move.State.MOVE));
            selectRadius(board, xDirec, yDirec, nextPos);
        }
    }

    public class IncorrectMove extends RuntimeException {
        public IncorrectMove() {
            super("Move is not part of the available MOVE or TAKE list");
        }
    }
}
