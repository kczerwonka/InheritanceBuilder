package com.kadam.builder;

public class Rectangle extends Shape {
    protected final double height;
    protected final double width;

    protected Rectangle(Init<?> init) {
        super(init);
        this.height = init.height;
        this.width = init.width;
    }

    protected static abstract class Init<T extends Init<T>> extends Shape.Init<T> {
        private double height;
        private double width;

        public T height(double height) {
            this.height = height;
            return self();
//            return (T)this;
        }

        public T width(double width) {
            this.width = width;
            return self();
        }

        public Rectangle build() {
            return new Rectangle(this);
        }
    }

    public static class Builder extends Init<Builder> {
        @Override
        protected Builder self() {
            return this;
        }
    }

}
