package com.example.study.structure.dfs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MaxAreaOfIslandTest {

    @Test
    public void test() {
        int[][] grid1 = {
                {0,0,1,0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,1,1,0,1,0,0,0,0,0,0,0,0},
                {0,1,0,0,1,1,0,0,1,0,1,0,0},
                {0,1,0,0,1,1,0,0,1,1,1,0,0},
                {0,0,0,0,0,0,0,0,0,0,1,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,0,0,0,0,0,0,1,1,0,0,0,0}
        };

        MaxAreaOfIsland maxAreaOfIsland = new MaxAreaOfIsland();
        int result1 = maxAreaOfIsland.maxAreaOfIsland(grid1);
        System.out.println("result1 = " + result1);

        int[][] grid2 = {
                {1,1,0,0,0},
                {1,1,0,0,0},
                {0,0,0,1,1},
                {0,0,0,1,1}

        };

        int result2 = maxAreaOfIsland.maxAreaOfIsland(grid2);
        System.out.println("result2 = " + result2);

    }



}