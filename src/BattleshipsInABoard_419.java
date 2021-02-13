
/*
* Given an 2D board, count how many battleships are in it.
* The battleships are represented with 'X's, empty slots are represented with '.'s.
* You may assume the following rules:
* You receive a valid board, made of only battleships or empty slots.
* Battleships can only be placed horizontally or vertically.
* In other words, they can only be made of the shape 1xN (1 row, N columns)
* or Nx1 (N rows, 1 column), where N can be of any size.
* At least one horizontal or vertical cell separates between two battleships -
* there are no adjacent battleships.

Example:
X..X
...X
...X
In the above board there are 2 battleships.

Invalid Example:
...X
XXXX
...X

This is an invalid board that you will not receive -
as battleships will always have a cell separating between them.

Follow up:
Could you do it in one-pass, using only O(1) extra memory and without modifying
the value of the board?
* */
public class BattleshipsInABoard_419 {
    public int countBattleships(char[][] board) {
        int count=0;
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                if(board[i][j]=='X'){
                    //若当前为X，才会考虑是否需要count
                    //查看它的左边或上面是否也为X，任意一个为X，就说明这不是战舰头，不count
                    //注意要i和j都>0的情况下判断，否则超出index
                    //唯一的漏洞时i=0&&j=0，但是头一个如果为X，直接count，肯定是舰头
                    if((i>0 && board[i-1][j]=='X') || (j>0 && board[i][j-1]=='X'))
                        continue;
                    count++;
                }
            }
        }
        return count;
    }
}

