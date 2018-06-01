package com.kadam.builder;

public class ColorableRectangle extends Rectangle {

    protected String color;

    protected static abstract class Init<T extends Init<T>> extends Rectangle.Init<T> {
        private String color;

        public T color(String color) {
            this.color = color;
            return self();
//            return (T)this;
        }

        public ColorableRectangle build() {
            return new ColorableRectangle(this);
        }
    }

    public static class Builder extends Init<Builder> {
        @Override
        protected Builder self() {
            return this;
        }
    }

    protected ColorableRectangle(Init<?> init) {
        super(init);
        this.color = init.color;
    }

    @Override
    public String toString() {
        return "ColorableRectangle{" +
                "color='" + color + '\'' +
                ", height=" + height +
                ", width=" + width +
                ", opacity=" + opacity +
                '}';
    }
}
