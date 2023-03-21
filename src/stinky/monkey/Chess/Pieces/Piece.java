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

    public abstract ArrayList<Moves> getAvailableMoves(Board board);
}
