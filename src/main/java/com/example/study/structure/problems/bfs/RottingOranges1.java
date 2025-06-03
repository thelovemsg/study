package com.example.study.structure.problems.bfs;

import java.util.LinkedList;
import java.util.Queue;

// Rotting Oranges.
// (0,0) 에서만 고려함. 잘못된 코드
public class RottingOranges1 {
    public int orangesRotting(int[][] grid) {
        int[][] directions = {{1,0}, {0,1}, {-1,0}, {0,-1}};

        if(grid[0][0] == 0) return -1;

        Queue<int[]> queue = new LinkedList<>();

        int[] newTarget = {0,0,1};
        queue.add(newTarget);

        int[][] mark = new int[grid.length][grid[0].length];

        while(!queue.isEmpty()) {
            int[] target = queue.poll();

            int x = target[0];
            int y = target[1];
            int length = target[1];

            if(grid.length == x && grid[0].length == y) {
                return length;
            }

            for(int[] direction : directions) {
                int newX = direction[0];
                int newY = direction[1];
                if(newX < 0 || newY < 0 || grid.length <= newX || grid[0].length <= newY || grid[newX][newY] == 0 || mark[newX][newY] == 1
                        || mark[newX][newY] == 1) {
                    continue;
                }

                int[] addTarget = {newX, newY, length+1};
                mark[newX][newY] = 1;
                queue.add(addTarget);
            }
        }

        return -1;
    }
}
