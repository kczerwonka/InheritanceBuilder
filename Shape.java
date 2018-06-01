package com.kadam.builder;

 /* https://community.oracle.com/blogs/emcmanus/2010/10/24/using-builder-pattern-subclasses#preferred */


public class Shape {
    protected final double opacity;


    /*
        java.lang Class Enum<E extends Enum<E>>

The idea is that instead of hardwriting opacity() to return the type of the Builder that defines it, we introduce
a type parameter T and we return T. The self-referential definition Init<T extends Init<T>> is what allows us
to make the type of the inherited opacity() inRectangle.Builder be Rectangle.Builder rather than Shape.Builder.

We can no longer simply return this from opacity(), since at the point where it is defined,this is an Init, not a T.
So instead of this, we return self(), and we arrange for self() to be overridden so that it returns the appropriate this.
This is pure ceremony to keep the compiler happy: all of the Builder classes have identical definitions of self().
(This is what Angelika Langer calls the getThis() trick [http://www.angelikalanger.com/GenericsFAQ/FAQSections/ProgrammingIdioms.html#FAQ206],
citing Maurice Naftalin and Philip Wadler for the name and Heinz Kabutz for the first publication.)

Why do we need to split our previous Builder class into separate Init and Builder classes?
Because we still want the caller to be able to write:
Rectangle r = new Rectangle.Builder().opacity(0.5).height(250).build();

     */

    protected static abstract class Init<T extends Init<T>> {
        private double opacity;

        protected abstract T self();

        public T opacity(double opacity) {
            this.opacity = opacity;
            return self();
//            return (T)this;
        }

        public Shape build() {
            return new Shape(this);
        }
    }

    public static class Builder extends Init<Builder> {
        @Override
        protected Builder self() {
            return this;
        }
    }

    protected Shape(Init<?> init) {
        this.opacity = init.opacity;
    }
}