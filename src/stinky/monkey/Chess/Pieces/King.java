package stinky.monkey.Chess.Pieces;

import stinky.monkey.Chess.Board;
import stinky.monkey.Chess.Position;
import stinky.monkey.Chess.Vec2;

import java.util.ArrayList;

public class King extends Piece {
    private boolean checked;
    private boolean castleAble;
    // SPAGHETTI
    private Board board;

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

    /*
    This shit some spaghetti code CLEAN LATER zzz
     */
    @Override
    public Move.State move(Position position) throws IncorrectMove {
        checked = false;
        castleAble = false;

        for (Move move : currentMoves) {
            System.out.println(move.position.getX() + " " + move.position.getY());
            if (move.position == position) {
                if (move.state != Move.State.THREAT) {
                    if (move.state == Move.State.CASTLE) {
                        int posY = team == TEAM.WHITE ? 0 : 7;
                        Position rook;

                        if (move.position.getX() == 0) {
                            rook = board.atPosition(new Vec2(0, posY));
                            rook.getCurrentPiece().setPosition(board.atPosition(new Vec2(2, posY)));
                            rook.set(null);
                        } else {
                            rook = board.atPosition(new Vec2(7, posY));
                            rook.getCurrentPiece().setPosition(board.atPosition(new Vec2(5, posY)));
                            rook.set(null);
                        }

                    }
                    move.position.set(this);
                    this.position = move.position;
                    return move.state;
                }
                return Move.State.THREAT;
            }
        }

        throw new IncorrectMove();
    }

    /**
     * Always call king availmoves last!!
     * @param board
     * @return positions of available moves
     */
    @Override
    public ArrayList<Move> getAvailableMoves(Board board) {
        this.board = board;
        currentMoves.clear();
        Move.State heatMap[][] = board.getOppHeatMap(team);

        if (castleAble) {
            int posY = team == TEAM.WHITE ? 0 : 7;
            if (board.atPosition(new Vec2(1, posY)).getCurrentPiece() == null && board.atPosition(new Vec2(2, posY)).getCurrentPiece() == null)
                currentMoves.add(new Move(board.atPosition(new Vec2(1, posY)), Move.State.CASTLE));
            if (board.atPosition(new Vec2(4, posY)).getCurrentPiece() == null && board.atPosition(new Vec2(5, posY)).getCurrentPiece() == null && board.atPosition(new Vec2(6, posY)).getCurrentPiece() == null)
                currentMoves.add(new Move(board.atPosition(new Vec2(6, posY)), Move.State.CASTLE));
        }

        for (int y = -1 + position.getY(); y < 2; y++) {
            for (int x = -1 + position.getX(); x < 2; x++) {
                if (y > 7 || y < 0 || x > 6 || x < 0)
                    continue;
                if (y != position.getY() && x != position.getX()) {
                    if (heatMap[y][x] == null)
                        currentMoves.add(new Move(board.atPosition(x, y), Move.State.MOVE));
                }
            }
        }

        if (checked && currentMoves.isEmpty())
            currentMoves.add(new Move(position, Move.State.CHECKMATE));

        return currentMoves;
    }
}
