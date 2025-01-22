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
        Collection<ChessMove> rookMoves = new ArrayList<>();
        ChessGame.TeamColor myColor = board.getPiece(myPosition).color;
        int currentRow = myPosition.getRow();
        int currentCol = myPosition.getColumn();
        int count = 1;
        boolean forward = true;
        boolean backward = true;
        boolean left = true;
        boolean right = true;

        while (count <=7) {

            //checks count squares forward
            if (currentRow + count <= 8) {
                if (forward) {
                    ChessPosition Forward = new ChessPosition(currentRow + count, currentCol);
                    if (board.getPiece(Forward) == null) {
                        rookMoves.add(new ChessMove(myPosition, Forward, null));
                    } else if (board.getPiece(Forward).getTeamColor() != myColor) {
                        rookMoves.add(new ChessMove(myPosition, Forward, null));
                        forward = false;
                    } else {
                        forward = false;
                    }
                }
            }
            else {forward = false;}

            //checks count squares backward

            if (currentRow - count >= 1) {
                if (backward) {
                    ChessPosition Backward = new ChessPosition(currentRow - count, currentCol);
                    if (board.getPiece(Backward) == null) {
                        rookMoves.add(new ChessMove(myPosition, Backward, null));
                    } else if (board.getPiece(Backward).getTeamColor() != myColor) {
                        rookMoves.add(new ChessMove(myPosition, Backward, null));
                        backward = false;
                    } else {
                        backward = false;
                    }
                }
            }
            else{backward = false;}

            //checks count squares to the right

            if (currentCol + count <= 8) {
                if (right) {
                    ChessPosition Right = new ChessPosition(currentRow, currentCol+count);
                    if (board.getPiece(Right) == null) {
                        rookMoves.add(new ChessMove(myPosition, Right, null));
                    } else if (board.getPiece(Right).getTeamColor() != myColor) {
                        rookMoves.add(new ChessMove(myPosition, Right, null));
                        right = false;
                    } else {
                        right = false;
                    }
                }
            }
            else{right = false;}

            //checks count squares to the left

            if (currentCol - count >= 1) {
                if (left) {
                    ChessPosition Left = new ChessPosition(currentRow, currentCol - count);
                    if (board.getPiece(Left) == null) {
                        rookMoves.add(new ChessMove(myPosition, Left, null));
                    } else if (board.getPiece(Left).getTeamColor() != myColor) {
                        rookMoves.add(new ChessMove(myPosition, Left, null));
                        left = false;
                    } else {
                        left = false;
                    }
                }
            }
            else{left = false;}
            count ++;
        }
        return rookMoves;
    }

    private Collection<ChessMove> getKnightMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> knightMoves = new ArrayList<>();
        int currentRow = myPosition.getRow();
        int currentCol = myPosition.getColumn();
        ChessGame.TeamColor myColor = board.getPiece(myPosition).color;

        //right 1 forward 2
        if (currentRow + 2 < 9 && currentCol + 1 < 9) {
            ChessPosition newPosition = new ChessPosition(currentRow + 2, currentCol + 1);
            if (board.getPiece(newPosition) == null || board.getPiece(newPosition).color != myColor) {
                knightMoves.add(new ChessMove(myPosition, newPosition, null));
            }
        }

        //left 1 forward 2
        if (currentRow + 2 < 9 && currentCol - 1 > 0) {
            ChessPosition newPosition = new ChessPosition(currentRow + 2, currentCol - 1);
            if (board.getPiece(newPosition) == null || board.getPiece(newPosition).color != myColor) {
                knightMoves.add(new ChessMove(myPosition, newPosition, null));
            }
        }

        //right 2 forward 1
        if (currentRow + 1 < 9 && currentCol + 2 < 9) {
            ChessPosition newPosition = new ChessPosition(currentRow + 1, currentCol + 2);
            if (board.getPiece(newPosition) == null || board.getPiece(newPosition).color != myColor) {
                knightMoves.add(new ChessMove(myPosition, newPosition, null));
            }
        }

        //left 2 forward 1
        if (currentCol - 2 > 0 && currentRow + 1 < 9) {
            ChessPosition newPosition = new ChessPosition(currentRow + 1, currentCol - 2);
            if (board.getPiece(newPosition) == null || board.getPiece(newPosition).color != myColor) {
                knightMoves.add(new ChessMove(myPosition, newPosition, null));
            }
        }

        //right 2 back 1
        if (currentCol + 2 < 9 && currentRow - 1 > 0) {
            ChessPosition newPosition = new ChessPosition(currentRow - 1, currentCol + 2);
            if (board.getPiece(newPosition) == null || board.getPiece(newPosition).color != myColor) {
                knightMoves.add(new ChessMove(myPosition, newPosition, null));
            }
        }

        //left 2 back 1
        if (currentRow - 1 > 0 && currentCol - 2 > 0) {
            ChessPosition newPosition = new ChessPosition(currentRow - 1, currentCol - 2);
            if (board.getPiece(newPosition) == null || board.getPiece(newPosition).color != myColor) {
                knightMoves.add(new ChessMove(myPosition, newPosition, null));
            }
        }

        //right 1 back 2
        if (currentRow - 2 > 0 && currentCol + 1 < 9) {
            ChessPosition newPosition = new ChessPosition(currentRow - 2, currentCol + 1);
            if (board.getPiece(newPosition) == null || board.getPiece(newPosition).color != myColor) {
                knightMoves.add(new ChessMove(myPosition, newPosition, null));
            }
        }

        //left 1 back 2
        if (currentRow - 2 > 0 && currentCol - 1 > 0) {
            ChessPosition newPosition = new ChessPosition(currentRow - 2, currentCol - 1);
            if (board.getPiece(newPosition) == null || board.getPiece(newPosition).color != myColor) {
                knightMoves.add(new ChessMove(myPosition, newPosition, null));
            }
        }
        return knightMoves;
    }

    private Collection<ChessMove> getBishopMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<chess.ChessMove> bishopMoves = new ArrayList<>();
        ChessGame.TeamColor myColor = board.getPiece(myPosition).color;
        int currentRow = myPosition.getRow();
        int currentCol = myPosition.getColumn();
        int count = 1;
        boolean northwest = true;
        boolean northeast = true;
        boolean southwest = true;
        boolean southeast = true;

        while (count <=7) {

            //checks count squares northwest
            if (currentRow + count <= 8 && currentCol -count >=1) {
                if (northwest) {
                    ChessPosition NorthWest = new ChessPosition(currentRow + count, currentCol-count);
                    if (board.getPiece(NorthWest) == null) {
                        bishopMoves.add(new ChessMove(myPosition, NorthWest, null));
                    } else if (board.getPiece(NorthWest).getTeamColor() != myColor) {
                        bishopMoves.add(new ChessMove(myPosition, NorthWest, null));
                        northwest = false;
                    } else {
                        northwest = false;
                    }
                }
            }
            else {northwest = false;}

            //checks count squares northeast

            if (currentRow + count <= 8 && currentCol +count <=8) {
                if (northeast) {
                    ChessPosition NorthEast = new ChessPosition(currentRow + count, currentCol + count);
                    if (board.getPiece(NorthEast) == null) {
                        bishopMoves.add(new ChessMove(myPosition, NorthEast, null));
                    } else if (board.getPiece(NorthEast).getTeamColor() != myColor) {
                        bishopMoves.add(new ChessMove(myPosition, NorthEast, null));
                        northeast = false;
                    } else {
                        northeast = false;
                    }
                }
            }
            else{northeast = false;}

            //checks count squares to the southwest

            if (currentRow - count >= 1 && currentCol -count >=1) {
                if (southwest) {
                    ChessPosition SouthWest = new ChessPosition(currentRow-count, currentCol-count);
                    if (board.getPiece(SouthWest) == null) {
                        bishopMoves.add(new ChessMove(myPosition, SouthWest, null));
                    } else if (board.getPiece(SouthWest).getTeamColor() != myColor) {
                        bishopMoves.add(new ChessMove(myPosition, SouthWest, null));
                        southwest = false;
                    } else {
                        southwest = false;
                    }
                }
            }
            else{southwest = false;}

            //checks count squares to the southeast

            if (currentRow - count >=1 && currentCol +count <=8) {
                if (southeast) {
                    ChessPosition SouthEast = new ChessPosition(currentRow, currentCol - count);
                    if (board.getPiece(SouthEast) == null) {
                        bishopMoves.add(new ChessMove(myPosition, SouthEast, null));
                    } else if (board.getPiece(SouthEast).getTeamColor() != myColor) {
                        bishopMoves.add(new ChessMove(myPosition, SouthEast, null));
                        southeast = false;
                    } else {
                        southeast = false;
                    }
                }
            }
            else{southeast = false;}
            count ++;
        }
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
