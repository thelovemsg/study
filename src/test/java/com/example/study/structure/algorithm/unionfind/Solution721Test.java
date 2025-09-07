package com.example.study.structure.algorithm.unionfind;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class Solution721Test {

    /**
     * i=0: ["John", a, b]
     * i=1: ["John", b, c]
     * i=2: ["Mary", d]
     *
     * 와 허벌나게 어렵네잉~
     */
    @Test
    void test721() {
        List<List<String>> accounts = new ArrayList<>();
        accounts.add(List.of("John", "a", "b"));
        accounts.add(List.of("John", "b", "c"));
        accounts.add(List.of("Mary", "d"));
        Solution721 solution721 = new Solution721();
        List<List<String>> lists = solution721.accountMerge(accounts);
        System.out.println("lists = " + lists);
    }
}