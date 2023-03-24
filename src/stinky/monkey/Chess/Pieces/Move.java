package stinky.monkey.Chess.Pieces;

import stinky.monkey.Chess.*;

public class Move {
    public static enum State {
        TAKE,
        MOVE,
        THREAT,
        CHECKMATE
    }

    public Position position;
    public State state;

    public Move(Position position, State state) {
        this.position = position;
        this.state = state;
    }
}
