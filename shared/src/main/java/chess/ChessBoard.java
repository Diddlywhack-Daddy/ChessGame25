package chess;

import java.util.Arrays;

/**
 * A chessboard that can hold and rearrange chess pieces.
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessBoard {

    private ChessPiece [][] board;
    public ChessBoard() {
        resetBoard();
    }

    /**
     * Adds a chess piece to the chessboard
     *
     * @param position where to add the piece to
     * @param piece    the piece to add
     */
    public void addPiece(ChessPosition position, ChessPiece piece)
    {
        board[position.getRow()-1][position.getColumn()-1] = piece;
    }

    /**
     * Gets a chess piece on the chessboard
     *
     * @param position The position to get the piece from
     * @return Either the piece at the position, or null if no piece is at that
     * position
     */
    public ChessPiece getPiece(ChessPosition position) {
        //modifiers are to account for zero indexing and counting row upwards instead of downwards
        return board[position.getRow()-1][position.getColumn()-1];

    }

    /**
     * Sets the board to the default starting board
     * (How the game of chess normally starts)
     */
    public void resetBoard() {
        board = new ChessPiece[8][8];
        addPawns();
        addBackRank(ChessGame.TeamColor.WHITE);
        addBackRank(ChessGame.TeamColor.BLACK);


    }
    // A helper function for resetBoard, this adds the back rank of a particular color to the board.
    private void addBackRank(ChessGame.TeamColor color){
        int row;
        if(color == ChessGame.TeamColor.WHITE){row = 1;}
        else{row = 8;}

        //Adds Rooks
        addPiece(new ChessPosition(row,1),new ChessPiece(color, ChessPiece.PieceType.ROOK));
        addPiece(new ChessPosition(row,8),new ChessPiece(color, ChessPiece.PieceType.ROOK));

        //Adds knights
        addPiece(new ChessPosition(row,2),new ChessPiece(color, ChessPiece.PieceType.KNIGHT));
        addPiece(new ChessPosition(row,7),new ChessPiece(color, ChessPiece.PieceType.KNIGHT));

        //Adds bishops
        addPiece(new ChessPosition(row,3),new ChessPiece(color, ChessPiece.PieceType.BISHOP));
        addPiece(new ChessPosition(row,6),new ChessPiece(color, ChessPiece.PieceType.BISHOP));

        //adds Royalty
        addPiece(new ChessPosition(row,4),new ChessPiece(color, ChessPiece.PieceType.QUEEN));
        addPiece(new ChessPosition(row,5),new ChessPiece(color, ChessPiece.PieceType.KING));

    }

    // Another helper function for resetBoard that adds all pawns of both colors to the board.
    private void addPawns(){
        for(int col=1; col<9; col++) {
            addPiece(new ChessPosition(2, col), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN));
            addPiece(new ChessPosition(7, col), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChessBoard that = (ChessBoard) o;
        return Arrays.deepEquals(board, that.board);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(board);
    }

    @Override
    public String toString() {
        return "ChessBoard{" +
                "board=" + Arrays.deepToString(board) +
                '}';
    }
}
