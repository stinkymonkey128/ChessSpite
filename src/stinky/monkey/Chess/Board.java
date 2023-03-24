package stinky.monkey.Chess;

import stinky.monkey.Chess.Pieces.*;
import java.util.*;

public class Board {
    private Position board[][];
    private Move.State currentBHeatMap[][];
    private Move.State currentWHeatMap[][];

    private Vec2 whiteKing;
    private Vec2 blackKing;
    private ArrayList<Piece> whitePieces;
    private ArrayList<Piece> blackPieces;

    private Piece.TEAM currentTeam;

    public Board() {
        whitePieces = new ArrayList<Piece>();
        blackPieces = new ArrayList<Piece>();

        currentWHeatMap = new Move.State[8][8];
        currentBHeatMap = new Move.State[8][8];

        board = new Position[8][8];
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                board[i][j] = new Position(j, i);
        initBoard();

        updateHeatmap();
    }

    //TODO implement chess cnvention board layout
    //public Board(Piece.TEAM startTeam, )

    public boolean nextMove(Vec2 from, Vec2 to) {
        return nextMove(atPosition(from), atPosition(to));
    }

    public boolean nextMove(Position from, Position to) {
        try {
            Piece piece = from.getCurrentPiece();
            if (piece == null || piece.getTeam() != currentTeam)
                return false;

            updateHeatmap();
            piece.move(to);
            currentTeam = currentTeam == Piece.TEAM.WHITE ? Piece.TEAM.BLACK : Piece.TEAM.WHITE;

            return true;
        } catch (Piece.IncorrectMove e) {
            e.printStackTrace();
            return false;
        }
    }

    private void updatePieces() {
        whitePieces.clear();
        blackPieces.clear();

        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
                if (board[i][j].getCurrentPiece() != null) {
                    Piece selectPiece = board[i][j].getCurrentPiece();
                    if (selectPiece.getTeam() == Piece.TEAM.WHITE)
                        whitePieces.add(selectPiece);
                    else
                        blackPieces.add(selectPiece);
                }
            }
    }

    private void updateHeatmap() {
        updatePieces();

        for (Piece wPiece : whitePieces) {
            if (wPiece.getClass().equals(King.class))
                whiteKing = wPiece.getPosition().getVec();
            else {
                for (Move move : wPiece.getAvailableMoves(this)) {
                    Position currentPos = move.position;
                    int x = currentPos.getX();
                    int y = currentPos.getY();

                    Move.State currentState = currentWHeatMap[x][y];
                    if (currentState == null )
                        currentWHeatMap[x][y] = move.state;
                    if (currentState != null && currentState != Move.State.THREAT)
                        currentWHeatMap[x][y] = move.state;
                }
            }
        }

        for (Piece bPiece : blackPieces) {
            if (bPiece.getClass().equals(King.class))
                blackKing = bPiece.getPosition().getVec();
            else {
                for (Move move : bPiece.getAvailableMoves(this)) {
                    Position currentPos = move.position;
                    int x = currentPos.getX();
                    int y = currentPos.getY();

                    Move.State currentState = currentBHeatMap[x][y];
                    if (currentState == null )
                        currentBHeatMap[x][y] = move.state;
                    if (currentState != null && currentState != Move.State.THREAT)
                        currentBHeatMap[x][y] = move.state;
                }
            }
        }


    }

    private void initBoard() {
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

        currentTeam = Piece.TEAM.WHITE;
    }

    public Piece.TEAM checkCheckmate() {
        King wKing = (King) atPosition(whiteKing).getCurrentPiece();
        King bKing = (King) atPosition(blackKing).getCurrentPiece();

        if (wKing.isChecked() && wKing.getAvailableMoves(this).isEmpty())
            return Piece.TEAM.BLACK;
        if (bKing.isChecked() && bKing.getAvailableMoves(this).isEmpty())
            return Piece.TEAM.WHITE;
        return null;
    }

    public Piece.TEAM whosTurn() {
        return currentTeam;
    }

    public Position atPosition(int x, int y) {
        if (x > 7 || x < 0 || y > 7 || y < 0)
            return null;
        return board[y][x];
    }

    public Position atPosition(Vec2 vec) {
        return atPosition(vec.x, vec.y);
    }

    public Move.State[][] getOppHeatMap(Piece.TEAM team) {
        return team == Piece.TEAM.WHITE ? currentBHeatMap : currentWHeatMap;
    }

    // 🖕🖕🖕
    @Override
    public String toString() {
        String out = "";

        for (int i = 7; i >= 0; i--) {
            Position row[] = board[i];
            for (Position pos : row) {
                Piece piece = pos.getCurrentPiece();
                if (piece == null)
                    out += " ";
                else {
                    switch (piece.getClass().toString()) {
                        case "class stinky.monkey.Chess.Pieces.King" -> {
                            if (piece.getTeam() == Piece.TEAM.BLACK)
                                out += "♔";
                            else
                                out += "♚";
                        }
                        case "class stinky.monkey.Chess.Pieces.Queen" -> {
                            if (piece.getTeam() == Piece.TEAM.BLACK)
                                out += "♕";
                            else
                                out += "♛";
                        }
                        case "class stinky.monkey.Chess.Pieces.Rook" -> {
                            if (piece.getTeam() == Piece.TEAM.BLACK)
                                out += "♖";
                            else
                                out += "♜";
                        }
                        case "class stinky.monkey.Chess.Pieces.Bishop" -> {
                            if (piece.getTeam() == Piece.TEAM.BLACK)
                                out += "♗";
                            else
                                out += "♝";
                        }
                        case "class stinky.monkey.Chess.Pieces.Knight" -> {
                            if (piece.getTeam() == Piece.TEAM.BLACK)
                                out += "♘";
                            else
                                out += "♞";
                        }
                        case "class stinky.monkey.Chess.Pieces.Pawn" -> {
                            if (piece.getTeam() == Piece.TEAM.BLACK)
                                out += "♙";
                            else
                                out += "♟";
                        }
                    }
                }
            }
            out += "\n";
        }

        return out;
    }
}
