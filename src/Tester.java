import stinky.monkey.Chess.*;
import stinky.monkey.Chess.Pieces.*;

import java.util.*;

public class Tester {
    private static void printHeatMap(Move.State[][] states) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (states[j][i] == null) {
                    System.out.print(" ");
                } else {
                    switch (states[j][i]) {
                        case MOVE:
                            System.out.print("1");
                            break;
                        case THREAT:
                            System.out.print("2");
                            break;
                        case TAKE:
                            System.out.print("3");
                            break;
                    }
                }
            }
            System.out.println();
        }
    }

    public static void printAvailMoves(List<Move> moves) {
        System.out.println("AVAIL MOVES");
        for (Move move : moves) {
            Position pos = move.position;
            System.out.println(pos.getX() + " " + pos.getY());
        }
    }

    public static void main(String[] args) {
        Board board = new Board();
        Piece.TEAM checkmated = null;
        Scanner cin = new Scanner(System.in);

        while (checkmated == null) {
            int fx = 0, fy = 0, tx = 0, ty = 0;
            System.out.println(board.whosTurn() == Piece.TEAM.WHITE ? "WHITE" : "BLACK");
            System.out.println(board);
            System.out.println("FROM");
            System.out.print("x: ");
            fx = cin.nextInt();
            System.out.print("y: ");
            fy = cin.nextInt();
            System.out.println("TO");
            System.out.print("x: ");
            tx = cin.nextInt();
            System.out.print("y: ");
            ty = cin.nextInt();
            System.out.println(board.nextMove(new Vec2(fx, fy), new Vec2(tx, ty)));


            checkmated = board.checkCheckmate();
        }
        /*
        //printHeatMap(board.getOppHeatMap(Piece.TEAM.BLACK));
        printAvailMoves(board.atPosition(new Vec2(1, 1)).getCurrentPiece().getAvailableMoves(board));
        System.out.println(board.nextMove(new Vec2(2, 1), new Vec2(2, 3)));
        System.out.println(board);
        printAvailMoves(board.atPosition(new Vec2(1, 6)).getCurrentPiece().getAvailableMoves(board));
        //printHeatMap(board.getOppHeatMap(Piece.TEAM.WHITE));
        System.out.println(board.nextMove(new Vec2(1, 6), new Vec2(1, 4)));
        System.out.println(board);
        printAvailMoves(board.atPosition(new Vec2(2, 3)).getCurrentPiece().getAvailableMoves(board));
        System.out.println(board.nextMove(new Vec2(2, 3), new Vec2(1, 4)));
        System.out.println(board);*/
    }
}
