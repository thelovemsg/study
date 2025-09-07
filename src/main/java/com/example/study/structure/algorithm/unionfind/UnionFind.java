package com.example.study.structure.algorithm.unionfind;

public class UnionFind {
    private int[] parent;
    private int[] rank;

    /**
     * 각각의 성분들은 처음에는 자기 자신을 부모로 가짐.
     * 그리고 처음에는 혼자이므로 트리 높이는 0이다.
     * @param n 배열의 길이 혹은 숫자의 갯수
     */
    public UnionFind(int n) {
        parent = new int[n+1];
        rank = new int[n+1];
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }

        return parent[x];
    }

    public boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) {
            return false;
        }

        if(rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else if(rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else {
            parent[rootY] = rootX;
            rank[rootX] += 1;
        }

        return true;
    }
}
