class Solution {
    public boolean exist(char[][] board, String word) {
        int m=board.length;
        int n=board[0].length;

        if(word.length()>m*n){
            return false;
        }

        int[] boardFrequency=new int[128];
        for(int r=0;r<m;r++){
            for(int c=0;c<n;c++){
                boardFrequency[board[r][c]]++;
            }
        }

        int[] wordFrequency=new int[128];
        for(char ch : word.toCharArray()){
            wordFrequency[ch]++;
            if(wordFrequency[ch] > boardFrequency[ch]){
                return false;
            }
        }

        if(boardFrequency[word.charAt(0)]>boardFrequency[word.charAt(word.length()-1)]){
            word = new StringBuilder(word).reverse().toString();
        }

        for(int r=0;r<m; r++){
            for(int c=0;c<n;c++){
                if(board[r][c]==word.charAt(0)){
                    if(dfs(board,word,r,c,0)){
                        return true;
                    }
                }
            }
        }
        return false;

    }

    private boolean dfs(char[][] board,String word,int r, int c, int index){
        if(index==word.length()){
            return true;
        }

        if(r<0||r>=board.length||c<0||c>=board[0].length||board[r][c]!=word.charAt(index)){
            return false;
        }

        char temp=board[r][c];
        board[r][c]='#';

        boolean found=dfs(board,word,r+1,c,index+1)||dfs(board,word,r-1,c,index+1)||dfs(board,word,r,c+1,index+1)||dfs(board,word,r,c-1,index+1);

        board[r][c]=temp;

        return found;

    }
}