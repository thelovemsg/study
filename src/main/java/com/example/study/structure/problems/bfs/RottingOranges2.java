package com.example.study.structure.problems.bfs;

import java.util.LinkedList;
import java.util.Queue;

// Rotting Oranges
public class RottingOranges2 {
    public int orangesRotting(int[][] grid) {
        int[][] directions = {{1,0}, {0,1}, {-1,0}, {0,-1}};

        Queue<int[]> queue = new LinkedList<>();
        int freshCount = 0;

        for(int i=0; i<grid.length; i++) {
            for(int j=0; j<grid[0].length; j++) {
                if(grid[i][j] == 2) {
                    queue.add(new int[]{i, j, 0});
                } else if(grid[i][j] == 2) {
                    freshCount++;
                }
            }
        }

        if(freshCount == 0) return 0;

        int maxTime = 0;

        while(!queue.isEmpty()) {
            int[] current = queue.poll();

            int x = current[0];
            int y = current[1];
            int time = current[2];

            maxTime = Math.max(maxTime, time);

            for(int[] direction : directions) {
                int newX = x + direction[0];  // 올바른 좌표 계산
                int newY = y + direction[1];  // 올바른 좌표 계산

                // 경계 체크 및 신선한 오렌지인지 확인
                if(newX >= 0 && newY >= 0 &&
                        newX < grid.length && newY < grid[0].length &&
                        grid[newX][newY] == 1) {  // 신선한 오렌지만

                    grid[newX][newY] = 2;  // 썩은 오렌지로 변경
                    freshCount--;  // 신선한 오렌지 개수 감소
                    queue.add(new int[]{newX, newY, time + 1});
                }
            }

        }

        return freshCount == 0 ? maxTime : -1;
    }
}
