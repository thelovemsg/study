package com.example.study.structure.problems.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 1091. Shortest Path in Binary Matrix
 *
 * Given an n x n binary matrix grid, return the length of the shortest clear path in the matrix. If there is no clear path, return -1.
 *
 * A clear path in a binary matrix is a path from the top-left cell (i.e., (0, 0)) to the bottom-right cell (i.e., (n - 1, n - 1)) such that:
 *
 * All the visited cells of the path are 0.
 * All the adjacent cells of the path are 8-directionally connected (i.e., they are different and they share an edge or a corner).
 * The length of a clear path is the number of visited cells of this path.
 */
public class ShortestPathInBinaryMatrix {
    public int shortestPathBinaryMatrix(
        int[][] grid) {int[][] directions = {{1,0}, {0,1}, {-1,0}, {0,-1}, {-1,-1}, {1,1}, {-1,1},{1,-1}};

        if(grid[0][0] == 1) return -1;

        Queue<int[]> queue = new LinkedList<>();

        int xLength = grid.length;
        int yLength = grid[0].length;
        int[][] mark = new int[xLength][yLength];

        int lastX = grid.length-1;
        int lastY = grid[0].length-1;

        // x, y, length
        int[] startPoint = {0,0,1};
        mark[0][0] = 1;
        queue.add(startPoint);

        while(!queue.isEmpty()) {
            int[] target = queue.poll();

            int x = target[0];
            int y = target[1];
            int length = target[2];

            if(lastX == x && lastY == y) {
                return length;
            }

            for (int[] direction : directions) {
                int newX = x + direction[0];  // 새 변수 사용
                int newY = y + direction[1];  // 새 변수 사용

                if(newX < 0 || newY < 0 || xLength <= newX || yLength <= newY || grid[newX][newY] == 1 || mark[newX][newY] == 1){
                    continue;
                }

                int[] newXY = {newX, newY, length+1};
                mark[newX][newY] = 1;

                queue.add(newXY);
            }
        }

        return -1;
    }
}
