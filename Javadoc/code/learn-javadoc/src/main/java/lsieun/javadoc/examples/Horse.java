package lsieun.javadoc.examples;

import java.awt.Color;

import static java.lang.System.out;

/**
 * Equine.
 */
public class Horse extends MammalWithHair implements Herbivorous, Viviparous {
    private final Color hairColor = null;

    /**
     * @param plant Plant to be eaten by this horse.
     */
    @Override
    public void eat(final Plant plant) {
    }

    /**
     *
     */
    @Override
    public void verballyCommunicate() {
        out.println("Neigh");
    }

    /**
     * @param numberColts Number of colts to be born to horse.
     */
    @Override
    public void giveBirth(int numberColts) {
    }

    @Override
    public Color getHairColor() {
        return hairColor;
    }
}
