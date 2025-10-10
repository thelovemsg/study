package com.example.study.practicalprogramming.chapter03;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Rectangle {
    long width;
    long height;

    public long calculateArea() {
        return width * height;
    }
}
