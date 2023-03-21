package stinky.monkey.Chess;

import stinky.monkey.Chess.Pieces.*;

public class Position {
    private int x;
    private int y;
    private Piece currentPiece;

    public Position(int x, int y, Piece piece) {
        this.x = x;
        this.y = y;
        currentPiece = piece;
    }

    public Position(int x, int y) {
        this(x, y, null);
    }

    public void set(Piece piece) {
        currentPiece = piece;
        currentPiece.setPosition(this);
    }

    public Piece getCurrentPiece() {
        return currentPiece;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
