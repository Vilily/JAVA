/*
判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。

数字 1-9 在每一行只能出现一次。
数字 1-9 在每一列只能出现一次。
数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/valid-sudoku
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class EffectiveSudoku {
	 public boolean isValidSudoku(char[][] board) {
		 int []sign = new int[9];
		 boolean is_effective = true;
		 
		 for(int i=0;i<9;i++)
		 {
			 for(int j=0;j<9;j++)
				 if(board[i][j]!='.')
				 {
					 sign[board[i][j]-'1']+=1;
					 if(sign[board[i][j]-'1']>1)
					 {
						 is_effective = false;
						 if(!is_effective)
							 return false;
					 }
				 }
			 for(int k=0;k<9;k++)
				 sign[k] = 0;
		 }
		 for(int i=0;i<9;i++)
			 sign[i] = 0;
		 for(int j=0;j<9;j++)
		 {
			 for(int i=0;i<9;i++)
				 if(board[i][j]!='.')
				 {
					 sign[board[i][j]-'1']+=1;
					 if(sign[board[i][j]-'1']>1)
					 {
						 is_effective = false;
						 if(!is_effective)
							 return false;
					 }
				 }
			 for(int k=0;k<9;k++)
				 sign[k] = 0;
		 }
		 for(int i=0;i<3;i++)
		 {
			 for(int j=0;j<3;j++)
			 {
				 int ii = 3*i;
				 int jj = 3*j;
				 for(int iii=0;iii<3;iii++)
				 {
					 for(int jjj=0;jjj<3;jjj++)
					 {
						 if(board[i][j]!='.')
						 {
							 sign[board[ii+iii][jj+jjj]-'1']+=1;
							 if(sign[board[ii+iii][jj+jjj]-'1']>1)
							 {
								 is_effective = false;
								 if(!is_effective)
									 return false;
							 }
						 }
					 }
				 }
				 for(int k=0;k<9;k++)
					 sign[k] = 0;
				 
			 }
		 }
		 return true;
	    }

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		char [][] board = {
				{'8','3','.','.','7','.','.','.','.'},
				{'6','.','.','1','9','5','.','.','.'},
				{'.','9','8','.','.','.','.','6','.'},
				{'8','.','.','.','6','.','.','.','3'},
				{'4','.','.','8','.','3','.','.','1'},
				{'7','.','.','.','2','.','.','.','6'},
				{'.','6','.','.','.','.','2','8','.'},
				{'.','.','.','4','1','9','.','.','5'},
				{'.','.','.','.','8','.','.','7','9'}
		};

		EffectiveSudoku a = new EffectiveSudoku();
		System.out.println(a.isValidSudoku(board));
	}

}

