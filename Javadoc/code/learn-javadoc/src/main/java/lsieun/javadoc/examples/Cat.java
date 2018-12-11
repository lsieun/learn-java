package lsieun.javadoc.examples;

import java.awt.Color;

import static java.lang.System.out;

/**
 * Feline.
 */
public class Cat extends MammalWithHair implements Carnivorous, Viviparous {
    private final Color hairColor = null;

    /**
     * {@inheritDoc}
     */
    @Override
    public void eat(final Animal otherAnimal) {
    }

    @Override
    public void verballyCommunicate() {
        out.println("Meow");
    }

    @Override
    public void giveBirth(int numberKittens) {
    }

    @Override
    public Color getHairColor() {
        return hairColor;
    }
}
