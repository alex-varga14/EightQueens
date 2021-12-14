/**
 * @author Alexander Varga
 * @version 1.2
 * @since 1.0
 * @date: february 5th 2020
 */

public class EightQueens implements Cloneable {//BEGINNING OF CLASS DECLARATION

	public static void main(String[] args) throws CloneNotSupportedException{
		var board = new EightQueens();
		board.setQueens(8);
		board.printboard();
	}
	
	private char[][] board = new char[8][8];
	
	public EightQueens() {//Constructor to generate chess board
		for(int i = 0; i < this.board.length; i++)
		{
			for(int j = 0; j < this.board[i].length; j++)
			{
				this.board[i][j] = 'o';
			}
		} 
	}
	public char[][] getBoard(){//getboard method
		return this.board;
	}
	public void printboard(){ //printboard method
		for(int i = 0; i < this.board.length; i++)
		{
			System.out.println();
			for(int j = 0; j < this.board[i].length; j++)
			{
				System.out.print(" " + this.board[i][j] + " ");
			}
		}
		System.out.println();
	}
	
	public void setQueen(int row, int column) {//setQueen Method
		this.board[row][column]= 'Q';
	}
	
	public void emptySquare(int row, int column) {//emptySquare Method
		this.board[row][column]= 'o';
	}
	
	public EightQueens clone() throws CloneNotSupportedException{//CLONE METHOD
    	char[][] cloned = new char[board.length][];//creates new idependent object
    	for(int i = 0; i < this.board.length; i++)
    	{
    		cloned[i] = board[i].clone();
    	}
		EightQueens temp = (EightQueens)super.clone();
		temp.board = cloned;
    	return temp;
    }
	
	public boolean setQueens(int queensRemaining)//setQueens Method
	{
		boolean flag= false;
		if(queensRemaining > 9 || queensRemaining < 0) {//checks for illegal arg
			throw new IllegalArgumentException("ERROR INVALID ENTRY: Cannot Place more Than 8 Queens. ");
		}
		if(queensRemaining == 0) {//base case for recursiom
			return true;
		}
		for(int i = 0; i < 8; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				if(this.board[i][j] == 'o' && this.checkThreatened(i, j)) {//check for empty space and no threats
					this.setQueen(i, j);
					if(setQueens(queensRemaining-1)) {//RECURSION 
						flag = true;
						break;
					}
					else {
						this.emptySquare(i,j);
					}
				}
			}
			if(flag == true)
			{
				break;
			}
		}
		
		return flag;
	}
	
	public boolean checkThreatened(int r, int c)//CHECK IF QUEEN IS THREATENED
	{
		for(int i = 0; i < 8; i++) 
		{
			for(int j = 0; j < 8; j++) 
			{
				if(board[i][j]== 'Q' && i == r)
				{
					return false;
				}
				if(board[i][j]== 'Q' && j == c)
				{
					return false;
				}
				if((Math.abs(i-r)==Math.abs(j-c)) && board[i][j]== 'Q')
				{
				
					return false;
				}
			}
		}
		return true;
	}
	public void clean() {//simple method to clean bored
		for(int i = 0; i < this.board.length; i++)
		{
			for(int j = 0; j < this.board[i].length; j++)
			{
				this.board[i][j] = 'o';
			}
		} 
	}

}//END OF CLASS DECLARATION