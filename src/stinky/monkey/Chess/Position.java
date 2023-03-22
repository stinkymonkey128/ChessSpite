package stinky.monkey.Chess;

import stinky.monkey.Chess.Pieces.*;

public class Position {
    private Vec2 xy;
    private Piece currentPiece;

    public Position(Vec2 xy) {
        this(xy.x, xy.y);
    }

    public Position(Vec2 xy, Piece piece) {
        this(xy.x, xy.y, piece);
    }

    public Position(int x, int y, Piece piece) {
        xy.x = x;
        xy.y = y;
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
        return xy.x;
    }

    public int getY() {
        return xy.y;
    }

    public Vec2 getVec() {
        return xy;
    }
}
