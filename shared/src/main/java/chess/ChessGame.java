package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * For a class that can manage a chess game, making moves on a board
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessGame {
    private TeamColor teamTurn = TeamColor.WHITE;
    private ChessBoard board;

    public ChessGame() {
        board = new ChessBoard();
        board.resetBoard();
    }

    /**
     * @return Which team's turn it is
     */
    public TeamColor getTeamTurn() {
        return this.teamTurn;
    }

    /**
     * Set's which teams turn it is
     *
     * @param team the team whose turn it is
     */
    public void setTeamTurn(TeamColor team) {
        this.teamTurn = team;
    }

    private void switchTeams(TeamColor currentTurn) {
        if (currentTurn == TeamColor.WHITE) {
            teamTurn = TeamColor.BLACK;
        } else {
            teamTurn = TeamColor.WHITE;
        }
    }

    /**
     * Enum identifying the 2 possible teams in a chess game
     */
    public enum TeamColor {
        WHITE,
        BLACK
    }

    /**
     * Gets a valid moves for a piece at the given location
     *
     * @param startPosition the piece to get valid moves for
     * @return Set of valid moves for requested piece, or null if no piece at
     * startPosition
     */
    public Collection<ChessMove> validMoves(ChessPosition startPosition) {
        //account for teamTurn in make move
        Collection<ChessMove> possibleMoves = board.getPiece(startPosition).pieceMoves(board, startPosition);
        ChessPiece piece = board.getPiece(startPosition);
        Collection<ChessMove> validMoves = new ArrayList<>();


        //implement some more checks to determine if the move is actually valid

        for (ChessMove move : possibleMoves) {
            if (!movePutsInCheck(piece.getTeamColor(), move)) {
                validMoves.add(move);
            }
        }
        return validMoves;
    }

    public Collection<ChessMove> possibleMoves(TeamColor color) {
        Collection<ChessMove> possibleMoves = new ArrayList<>();
        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                ChessPosition boardPosition = new ChessPosition(i, j);
                //safety check for a piece on the square
                if (board.getPiece(boardPosition) != null) {
                    //if the piece is my color, save all their possible moves
                    if (board.getPiece(boardPosition).getTeamColor() == color) {
                        possibleMoves.addAll(board.getPiece(boardPosition).pieceMoves(board, boardPosition));
                    }
                }
            }
        }
        return possibleMoves;
    }

    private void unsafeMove(ChessMove move) {
        ChessPosition start = move.getStartPosition();
        if (board.getPiece(start) != null) {
            ChessPiece myPiece = board.getPiece(start);
            TeamColor color = myPiece.getTeamColor();
            if (myPiece.getPieceType() == ChessPiece.PieceType.PAWN) {
                if (myPiece.getTeamColor() == TeamColor.WHITE && move.getEndPosition().getRow() == 8 ||
                        myPiece.getTeamColor() == TeamColor.BLACK && move.getEndPosition().getRow() == 1) {
                    board.addPiece(start, null);
                    board.addPiece(move.getEndPosition(), new ChessPiece(color, move.getPromotionPiece()));
                }

            } else {
                board.addPiece(move.getEndPosition(), new ChessPiece(color, myPiece.getPieceType()));
                board.addPiece(start, null);
            }
        }
    }


    /**
     * Makes a move in a chess game
     *
     * @param move chess move to preform
     * @throws InvalidMoveException if move is invalid
     */
    public void makeMove(ChessMove move) throws InvalidMoveException {
        throw new RuntimeException("Not implemented");
    }

    /**
     * Determines if the given team is in check
     *
     * @param teamColor which team to check for check
     * @return True if the specified team is in check
     */
    public boolean isInCheck(TeamColor teamColor) {
        throw new RuntimeException("Not implemented");
    }

    /**
     * Determines if the given team is in checkmate
     *
     * @param teamColor which team to check for checkmate
     * @return True if the specified team is in checkmate
     */
    public boolean isInCheckmate(TeamColor teamColor) {
        Collection<ChessMove> possibleMoves = possibleMoves(teamColor);
        possibleMoves.removeIf(move -> movePutsInCheck(teamColor, move));
        return possibleMoves.isEmpty() && isInCheck(teamColor);
    }

    /**
     * Determines if the given team is in stalemate, which here is defined as having
     * no valid moves
     *
     * @param teamColor which team to check for stalemate
     * @return True if the specified team is in stalemate, otherwise false
     */
    public boolean isInStalemate(TeamColor teamColor) {
        throw new RuntimeException("Not implemented");
    }

    /**
     * Sets this game's chessboard with a given board
     *
     * @param board the new board to use
     */
    public void setBoard(ChessBoard board) {
        this.board = board;
    }

    /**
     * Gets the current chessboard
     *
     * @return the chessboard
     */
    public ChessBoard getBoard() {
        return this.board;
    }

    private boolean movePutsInCheck(TeamColor teamColor, ChessMove move) {
        ChessPiece destPiece = board.getPiece(move.getEndPosition());
        unsafeMove(move);
        ChessMove undo = new ChessMove(move.getEndPosition(), move.getStartPosition(), move.getPromotionPiece());
        if (isInCheck(teamColor)) {
            unsafeMove(undo);
            board.addPiece(move.getEndPosition(), destPiece);
            return true;
        } else {
            unsafeMove(undo);
            board.addPiece(move.getEndPosition(), destPiece);
            return false;
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
        ChessGame chessGame = (ChessGame) o;
        return teamTurn == chessGame.teamTurn && Objects.equals(board, chessGame.board);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamTurn, board);
    }
}


