package stinky.monkey.Chess.Pieces;

import stinky.monkey.Chess.Board;
import stinky.monkey.Chess.Position;

import java.util.ArrayList;

public class Pawn extends Piece {
    private boolean firstMove;

    public Pawn(TEAM team, Position position) {
        super(team, position);
        firstMove = true;
    }

    @Override
    public ArrayList<Moves> getAvailableMoves(Board board) {
        ArrayList<Moves> out = new ArrayList<Moves>();
        int modifier = this.team == Piece.TEAM.BLACK ? -1 : 1;

        if (firstMove && board.atPosition(position.getX(), position.getY() + 2 * modifier) == null) {

        }

        return null;
    }


}
