package stinky.monkey.Chess.Pieces;

import stinky.monkey.Chess.*;

public class Moves {
    public static enum State {
        TAKE,
        MOVE
    }

    public Position position;
    public State state;

    public Moves(Position position, State state) {
        this.position = position;
        this.state = state;
    }
}
