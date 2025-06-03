package com.example.study.structure.dfs;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class NumberOfIslands {
    int result = 0;
    public int numIslands(char[][] grid) {
        Set<Node> set = new HashSet<>();
        return dfs(grid, 0, 0, set);
    }

    public int dfs(char[][] grid, int r, int c, Set<Node> set) {
        int count = 0;
        int rowLength = grid.length;
        int colLength = grid[0].length;

        // 범위 확정
        if(r >= rowLength || c >= colLength || r < 0 || c < 0) return 0;
        if(set.contains(new Node(r,c))) return 0;
        boolean isLand = grid[r][c] == 1;

        set.add(new Node(r,c));

        if(isLand) {
            count += dfs(grid, r+1, c, set);
            count += dfs(grid, r-1, c, set);
            count += dfs(grid, r, c+1, set);
            count += dfs(grid, r, c-1, set);
        } else {
            return 1;
        }

        if(count == 0) {
            result++;
        }

        return count;
    }

}


class Node {
    int row;
    int col;

    public Node(int row, int col) {
        this.row = row;
        this.col = col;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Node node = (Node) obj;
        return row == node.row && col == node.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}
