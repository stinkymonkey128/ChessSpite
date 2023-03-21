package stinky.monkey.Chess.Pieces;

import stinky.monkey.Chess.Board;
import stinky.monkey.Chess.Position;

import java.util.ArrayList;

public class Knight extends Piece {
    public Knight(TEAM team, Position position) {
        super(team, position);
    }

    @Override
    public ArrayList<Moves> getAvailableMoves(Board board) {
        return null;
    }
}
