package com.example.study.structure.dfs;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

class NumberOfIslandsTest {

    @Test
    @DisplayName("노드가 set 에서 잘 작동하는지")
    public void node_check() {
        Set<Node> set = new HashSet<>();

        set.add(new Node(1,2));
        set.add(new Node(1,2));

        System.out.println(set.size());

        char[][] grid = {{1,0,0,0,0}, {1,1,0,0,0} ,{0,0,1,0,0}, {0,0,0,1,1}};

        NumberOfIslands numberOfIslands = new NumberOfIslands();
        int i = numberOfIslands.numIslands(grid);
        System.out.println("i = " + i);
    }
}