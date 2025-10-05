class Solution {
    private int m;
    private int n;
    private int directions[][] = {{0,1},{0,-1},{-1,0},{1,0}};
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        m = heights.length;
        n = heights[0].length;

        boolean paci[][] = new boolean[m][n];
        boolean atla[][] = new boolean[m][n];

        for(int j=0; j<n; j++){
            dfs(paci,0,j,heights);
            dfs(atla,m-1,j,heights);
        }
        for(int i=0; i<m; i++){
            dfs(paci,i,0,heights);
            dfs(atla,i,n-1,heights);
        }
        List<List<Integer>> result = new ArrayList<>();
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(paci[i][j] && atla[i][j]){
                    result.add(Arrays.asList(i,j));
                }
            }
        }
        return result;
    }

    public void dfs(boolean vis[][], int row, int col, int heights[][]){
        if(vis[row][col]){
            return;
        }
        vis[row][col] = true;
        for(int dir[]:directions){
            int newR = row + dir[0]; int newC = col + dir[1];
            if(newR<0 || newC<0 || newR>=m || newC>=n) continue;
            if(heights[newR][newC] < heights[row][col]) continue;
            dfs(vis, newR, newC, heights);
        }
    }
}
