package com.example.study.structure.dfs;

public class MaxAreaOfIsland {

    public int maxAreaOfIsland(int[][] grid) {
        int result = 0;
        int rowLength = grid.length;
        int colLength = grid[0].length;
        int[][] markArr = new int[rowLength][colLength];

        for(int i=0; i<rowLength; i++) {
            for(int j=0; j<colLength; j++) {
                int count = exploreIsland(markArr, grid, i, j, 0);
                result = Math.max(result, count);

            }
        }

        return result;
    }

    public int exploreIsland(int[][] markArr, int[][] grid, int r, int c, int num) {
        if(r >= grid.length || c >= grid[0].length || r < 0 || c < 0 || markArr[r][c] == 1 || grid[r][c] == 0)
            return num;

        markArr[r][c] = 1;

        num++;

        num = exploreIsland(markArr, grid, r+1, c, num);
        num = exploreIsland(markArr, grid, r-1, c, num);
        num = exploreIsland(markArr, grid, r, c+1, num);
        num = exploreIsland(markArr, grid, r, c-1, num);

        return num;
    }
}
