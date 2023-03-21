package stinky.monkey.Chess;

import stinky.monkey.Chess.Pieces.*;

public class Board {
    private Position board[][];

    public Board() {
        init();
    }

    private void init() {
        board = new Position[8][8];
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                board[i][j] = new Position(i, j);

        for (int i = 0; i < 8; i++) {
            board[6][i].set(new Pawn(Piece.TEAM.BLACK, board[6][i]));
            board[1][i].set(new Pawn(Piece.TEAM.WHITE, board[1][i]));
        }

        board[7][7].set(new Rook(Piece.TEAM.BLACK, board[7][7]));
        board[7][0].set(new Rook(Piece.TEAM.BLACK, board[7][0]));
        board[0][0].set(new Rook(Piece.TEAM.WHITE, board[0][0]));
        board[0][7].set(new Rook(Piece.TEAM.WHITE, board[0][7]));

        board[7][6].set(new Knight(Piece.TEAM.BLACK, board[7][6]));
        board[7][1].set(new Knight(Piece.TEAM.BLACK, board[7][1]));
        board[0][6].set(new Knight(Piece.TEAM.WHITE, board[0][6]));
        board[0][1].set(new Knight(Piece.TEAM.WHITE, board[0][1]));

        board[7][5].set(new Bishop(Piece.TEAM.BLACK, board[7][5]));
        board[7][2].set(new Bishop(Piece.TEAM.BLACK, board[7][2]));
        board[0][5].set(new Bishop(Piece.TEAM.WHITE, board[0][5]));
        board[0][2].set(new Bishop(Piece.TEAM.WHITE, board[0][2]));

        board[7][3].set(new Queen(Piece.TEAM.BLACK, board[7][3]));
        board[0][3].set(new Queen(Piece.TEAM.WHITE, board[0][3]));

        board[7][4].set(new King(Piece.TEAM.BLACK, board[7][4]));
        board[0][4].set(new King(Piece.TEAM.WHITE, board[0][4]));
    }

    public Position atPosition(int x, int y) {
        if (x > 7 || x < 0 || y > 7 || y < 0)
            return null;
        return board[x][y];
    }
}
