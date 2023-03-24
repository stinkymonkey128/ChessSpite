package stinky.monkey.Chess.Pieces;

import stinky.monkey.Chess.Board;
import stinky.monkey.Chess.Position;

import java.util.ArrayList;

public class Knight extends Piece {
    private static int knightPattern[][] = {{-1, 2}, {-1, -2}, {1, 2}, {1, -2}, {-2, 1}, {-2, -1}, {2, 1}, {2, -1}};

    public Knight(TEAM team, Position position) {
        super(team, position);
    }

    @Override
    public ArrayList<Move> getAvailableMoves(Board board) {
        currentMoves.clear();
        
        for (int[] pattern : knightPattern) {
            Position selectPos = board.atPosition(position.getX() + pattern[0], position.getY() + pattern[1]);
            if (selectPos != null) {
                Piece selectPie = selectPos.getCurrentPiece();
                
                if (selectPie == null)
                    currentMoves.add(new Move(selectPos, Move.State.MOVE));
                else if (selectPie.getTeam() != team) {
                    if (selectPie.getClass().equals(King.class)) {
                        currentMoves.add(new Move(selectPos, Move.State.THREAT));
                        ((King) selectPie).setChecked();
                    }
                    else 
                        currentMoves.add(new Move(selectPos, Move.State.TAKE));
                }
            }
        }
        
        return currentMoves;
    }
}
