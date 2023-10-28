package lk.ijse.dep.service;

import lk.ijse.dep.controller.BoardController;

import java.util.Arrays;

public class BoardImpl implements Board {
    private final Piece[][] pieces;
    private final BoardUI boardUI;

    public BoardImpl(BoardUI boardUI) {
        this.boardUI = boardUI;
        pieces=new Piece[6][5];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                pieces[i][j] = Piece.EMPTY;
            }
        }
    }
    @Override
    public BoardUI getBoardUI() {
        return boardUI;
    }

    @Override
    public int findNextAvailableSpot(int col) {

        for (int i = 0; i < 5; i++) {
            if (pieces[col][i].equals(Piece.EMPTY)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean isLegalMove(int col) {

        int index = findNextAvailableSpot(col);

        if (index == -1) {
            return false;
        }
        return true;
    }

    @Override
    public boolean existLegalMoves() {
        for (Piece[] pieces1 : pieces) {
            for (Piece pieces2 : pieces1) {
                if (pieces2.equals(Piece.EMPTY)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void updateMove(int col, Piece move) {

        for (int i = 0; i < 5; i++) {
            if (pieces[col][i].equals(Piece.EMPTY)) {
                pieces[col][i] = move;
                break;
            }
        }

    }

    @Override
    public void updateMove(int col, int row, Piece move) {
        pieces[col][row]=move;
    }

    @Override
    public Winner findWinner() {

        L1:for (int i=0; i<NUM_OF_COLS; i++){
            for (int j=0; j<NUM_OF_ROWS; j++) {
                if (pieces[i][1].equals(Piece.BLUE)) {
                    if ((pieces[i][1].equals(pieces[i][2]) && pieces[i][2].equals(pieces[i][3])) && (pieces[i][0].equals(pieces[i][1]))) {
                        return new Winner(Piece.BLUE, i, 0, i, 3);
                    } else if ((pieces[i][1].equals(pieces[i][2]) && (pieces[i][2].equals(pieces[i][3])) && (pieces[i][3].equals(pieces[i][4])))) {
                        return new Winner(Piece.BLUE, i, 1, i, 4);
                    }
                }
                if (pieces[i][1].equals(Piece.GREEN)) {
                    if ((pieces[i][1].equals(pieces[i][2]) && pieces[i][2].equals(pieces[i][3])) && (pieces[i][0].equals(pieces[i][1]))) {
                        return new Winner(Piece.GREEN, i, 0, i, 3);
                    } else if ((pieces[i][1].equals(pieces[i][2]) && (pieces[i][2].equals(pieces[i][3])) && (pieces[i][3].equals(pieces[i][4])))) {
                        return new Winner(Piece.GREEN, i, 1, i, 4);
                    }
                }
                if (pieces[2][j].equals(Piece.BLUE)){
                    if ((pieces[2][j].equals(pieces[3][j]))&&((pieces[1][j].equals(pieces[2][j]))&&(pieces[0][j].equals(pieces[1][j])))){
                        return new Winner(Piece.BLUE,0,j,3,j);
                    }else if ((pieces[2][j].equals(pieces[3][j]))&&((pieces[1][j].equals(pieces[2][j]))&&(pieces[4][j].equals(pieces[3][j])))){
                        return new Winner(Piece.BLUE,1,j,4,j);
                    }else if ((pieces[2][j].equals(pieces[3][j]))&&((pieces[4][j].equals(pieces[3][j]))&&(pieces[5][j].equals(pieces[4][j])))){
                        return new Winner(Piece.BLUE,2,j,5,j);
                    }
                }
                if (pieces[2][j].equals(Piece.GREEN)){
                    if ((pieces[2][j].equals(pieces[3][j]))&&((pieces[1][j].equals(pieces[2][j]))&&(pieces[0][j].equals(pieces[1][j])))){
                        return new Winner(Piece.GREEN,0,j,3,j);
                    }else if ((pieces[2][j].equals(pieces[3][j]))&&((pieces[1][j].equals(pieces[2][j]))&&(pieces[4][j].equals(pieces[3][j])))){
                        return new Winner(Piece.GREEN,1,j,4,j);
                    }else if ((pieces[2][j].equals(pieces[3][j]))&&((pieces[4][j].equals(pieces[3][j]))&&(pieces[5][j].equals(pieces[4][j])))){
                        return new Winner(Piece.GREEN,2,j,5,j);
                    }
                }

            }
        }
        return new Winner(Piece.EMPTY);
    }
}



