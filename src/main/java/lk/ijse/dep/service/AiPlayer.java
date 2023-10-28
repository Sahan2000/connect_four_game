package lk.ijse.dep.service;

import java.util.Scanner;

public class AiPlayer extends Player{
    private Board newBoard;

    public AiPlayer(Board newBoard) {
        super();
        this.newBoard = newBoard;
    }

    @Override
    public void movePiece(int col) {
        col = this.findBestMove();
        this.newBoard.updateMove(col, Piece.GREEN);
        this.newBoard.getBoardUI().update(col, false);
        Winner winner = this.newBoard.findWinner();
        if (winner.getWinningPiece() != Piece.EMPTY) {
            this.newBoard.getBoardUI().notifyWinner(winner);
        } else if (!this.newBoard.existLegalMoves()) {
            this.newBoard.getBoardUI().notifyWinner(new Winner(Piece.EMPTY));
        }

    }
    private int minimax(int depth,boolean maximizingPlayer){
        if (this.newBoard.findWinner().getWinningPiece().equals(Piece.GREEN)){
            return 1;
        }
        if (this.newBoard.findWinner().getWinningPiece().equals(Piece.BLUE)){
            return -1;
        }
        if (depth==2 || (!this.newBoard.existLegalMoves())){
            return 0;
        }
        if (this.newBoard.existLegalMoves()) {
            if (maximizingPlayer) {
                for (int i = 0; i <this.newBoard.NUM_OF_COLS; i++) {
                    if (this.newBoard.isLegalMove(i)) {
                        int row=this.newBoard.findNextAvailableSpot(i);
                        this.newBoard.updateMove(i,Piece.GREEN);
                        int heuristicVal=minimax(depth+1,false);
                        this.newBoard.updateMove(i,row,Piece.EMPTY);
                        if (heuristicVal==1){
                            return heuristicVal;
                        }
                    }
                }
            }else {
                for (int i = 0; i <this.newBoard.NUM_OF_COLS; i++) {
                    if (this.newBoard.isLegalMove(i)) {
                        int row= this.newBoard.findNextAvailableSpot(i);
                        this.newBoard.updateMove(i,Piece.BLUE);
                        int heuristicVal=minimax(depth+1,true);
                        this.newBoard.updateMove(i,row,Piece.EMPTY);
                        if (heuristicVal==-1){
                            return heuristicVal;
                        }
                    }
                }
            }
        }
        return 0;
    }
    public int findBestMove(){
        boolean userWinningState=false;
        int tiedColumn=0;
        for (int i = 0; i <this.newBoard.NUM_OF_COLS; i++) {
            if ((this.newBoard.isLegalMove(i))&&(this.newBoard.existLegalMoves())){
                int row=this.newBoard.findNextAvailableSpot(i);
                this.newBoard.updateMove(i,Piece.GREEN);
                int score=minimax(0,false);
                this.newBoard.updateMove(i,row,Piece.EMPTY);
                if (score==1){
                    return i;
                }
                if (score==-1){
                    userWinningState=true;
                }else {
                    tiedColumn=i;
                }
            }
        }
        if ((userWinningState)&&(this.newBoard.isLegalMove(tiedColumn))){
            return tiedColumn;
        }

        int col=0;
        do {
            col=(int)(Math.random()*6);
        }while (!this.newBoard.isLegalMove(col));

        return col;
    }
}
