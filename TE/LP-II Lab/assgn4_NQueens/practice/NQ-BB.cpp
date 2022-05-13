#include<bits/stdc++.h>
#define N 5
using namespace std;

int count = 0;

void printSolution(int board[N][N]){
    cout<<"printing solution"<<endl;

    for(int i=0; i<N; i++){
        for(int j=0; j<N; j++){
            cout<<" "<<board[i][j]<<" ";
        }
        cout<<endl;
    }

}


bool isSafe(int board[N][N], int cols[], int ndiag[], int rdiag[], int row, int col){

    if(cols[col] == 0 && ndiag[row+col] == 0 && rdiag[row-col+N-1] == 0){
        return true;
    }

    return false;

}


bool NQutil(int board[N][N], int row, int cols[], int ndiag[], int rdiag[]){


    if(row==N){
       return true;
    }

    for(int col=0; col<N; col++){


        if(isSafe(board, cols, ndiag, rdiag, row, col)){

            board[row][col] = 1;
            cols[col] = 1;
            ndiag[row+col] = 1;
            rdiag[row-col+N-1] = 1;

           if(NQutil(board, row+1, cols, ndiag, rdiag)) {
               return true;
           }


            board[row][col] = 0;
            cols[col] = 0;
            ndiag[row+col] = 0;
            rdiag[row-col+N-1] = 0;

        }
    }

    return false;

}


bool solveNQ(){

    int board[N][N] = {
        {0,0,0,0,0},
        {0,0,0,0,0},
        {0,0,0,0,0},
        {0,0,0,0,0},
        {0,0,0,0,0},
    };

    int cols[N] = {0};
    int ndiag[2*N-1] = {0};
    int rdiag[2*N-1] = {0};

    if(NQutil(board, 0, cols, ndiag, rdiag)){
        printSolution(board);
    }

    return true;

}



int main(){

    solveNQ();

    return 0;

}