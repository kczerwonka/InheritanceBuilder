package com.kadam.builder;

public class BuilderDemo {

    public static void main(String[] args) {

        ColorableRectangle colorableRectangle = new ColorableRectangle.Builder()
                .height(12)
                .width(14)
                .opacity(16)
                .color("RED")
                .build();

        System.out.println(colorableRectangle);
    }

}
