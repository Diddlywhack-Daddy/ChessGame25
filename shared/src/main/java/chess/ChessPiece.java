package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPiece {

    private ChessGame.TeamColor color;
    private PieceType pieceType;

    public ChessPiece(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type) {
        color = pieceColor;
        pieceType = type;
    }

    /**
     * The various different chess piece options
     */
    public enum PieceType {
        KING,
        QUEEN,
        BISHOP,
        KNIGHT,
        ROOK,
        PAWN
    }

    /**
     * @return Which team this chess piece belongs to
     */
    public ChessGame.TeamColor getTeamColor() {
        return color;
    }

    /**
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType() {
        return pieceType;
    }

    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        return switch (this.pieceType) {
            case KING -> getKingMoves(board, myPosition);
            case QUEEN -> getQueenMoves(board, myPosition);
            case BISHOP -> getBishopMoves(board, myPosition);
            case KNIGHT -> getKnightMoves(board, myPosition);
            case ROOK -> getRookMoves(board, myPosition);
            case PAWN -> getPawnMoves(board, myPosition);
        };

    }

    private Collection<ChessMove> getRookMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<chess.ChessMove> rookMoves = new ArrayList<>();
        int currentRow = myPosition.getRow();
        int currentCol = myPosition.getColumn();
        ChessGame.TeamColor myColor = board.getPiece(myPosition).getTeamColor();
        if (currentRow + 1 < 9) {
            for (int r = myPosition.getRow() + 1; r < 9; r++) {
                ChessPosition newPosition = new ChessPosition(r, currentCol);
                if (board.getPiece(newPosition) == null) {
                    rookMoves.add(new ChessMove(myPosition, newPosition, null));
                } else if (board.getPiece(newPosition).getTeamColor() != myColor) {
                    rookMoves.add(new ChessMove(myPosition, newPosition, null));
                    break;
                } else {
                    break;
                }
            }
        }

        if (currentRow - 1 > 0) {
            for (int r = myPosition.getRow(); r > 0; r--) {
                ChessPosition newPosition = new ChessPosition(r, currentCol);
                if (board.getPiece(newPosition) == null) {
                    rookMoves.add(new ChessMove(myPosition, newPosition, null));
                } else if (board.getPiece(newPosition).getTeamColor() != myColor) {
                    rookMoves.add(new ChessMove(myPosition, newPosition, null));
                    break;
                } else {
                    break;
                }
            }
        }

        if (currentCol + 1 > 9) {
            for (int c = myPosition.getColumn(); c < 9; c++) {
                ChessPosition newPosition = new ChessPosition(currentRow, c);
                if (board.getPiece(newPosition) == null) {
                    rookMoves.add(new ChessMove(myPosition, newPosition, null));
                } else if (board.getPiece(newPosition).getTeamColor() != myColor) {
                    rookMoves.add(new ChessMove(myPosition, newPosition, null));
                    break;
                } else {
                    break;
                }
            }
        }

        if (currentCol - 1 > 0) {
            for (int c = myPosition.getColumn(); c > 0; c--) {
                ChessPosition newPosition = new ChessPosition(currentRow, c);
                if (board.getPiece(newPosition) == null) {
                    rookMoves.add(new ChessMove(myPosition, newPosition, null));
                } else if (board.getPiece(newPosition).getTeamColor() != myColor) {
                    rookMoves.add(new ChessMove(myPosition, newPosition, null));
                    break;
                } else {
                    break;
                }
            }
        }


        return rookMoves;
    }

    private Collection<ChessMove> getKnightMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<chess.ChessMove> knightMoves = new ArrayList<>();

        return knightMoves;
    }

    private Collection<ChessMove> getBishopMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<chess.ChessMove> bishopMoves = new ArrayList<>();

        return bishopMoves;
    }

    private Collection<ChessMove> getQueenMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<chess.ChessMove> queenMoves = new ArrayList<>();

        return queenMoves;
    }

    private Collection<ChessMove> getKingMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<chess.ChessMove> kingMoves = new ArrayList<>();

        return kingMoves;
    }

    private Collection<ChessMove> getPawnMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<chess.ChessMove> pawnMoves = new ArrayList<>();

        return pawnMoves;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChessPiece that = (ChessPiece) o;
        return color == that.color && pieceType == that.pieceType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, pieceType);
    }

    @Override
    public String toString() {
        return "ChessPiece{" +
                "color=" + color +
                ", pieceType=" + pieceType +
                '}';
    }
}
