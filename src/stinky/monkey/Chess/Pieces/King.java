package stinky.monkey.Chess.Pieces;

import stinky.monkey.Chess.Board;
import stinky.monkey.Chess.Position;

import java.util.ArrayList;

public class King extends Piece {
    private boolean checked;
    private boolean castleAble;

    public King(TEAM team, Position position) {
        super(team, position);
        checked = false;
        castleAble = true;
    }

    public boolean isChecked() {
        return checked;
    }

    public boolean canCastle() {return castleAble;}

    public void setChecked() {
        this.checked = true;
    }

    /**
     * Always call king availmoves last!!
     * @param board
     * @return positions of available moves
     */
    @Override
    public ArrayList<Move> getAvailableMoves(Board board) {
        currentMoves.clear();


        return currentMoves;
    }
}
