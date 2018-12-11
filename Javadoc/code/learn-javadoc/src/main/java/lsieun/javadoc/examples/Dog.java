package lsieun.javadoc.examples;

import java.awt.Color;

import static java.lang.System.out;

/**
 * Canine and man's best friend.
 */
public class Dog extends MammalWithHair implements Omnivorous, Viviparous {
    private final Color hairColor = null;

    /**
     * {@inheritDoc}
     *
     * @param otherAnimal Tasty treat.
     */
    @Override
    public void eat(final Animal otherAnimal) {
    }

    /**
     * {@inheritDoc}
     *
     * @param plantToBeEaten Plant that this dog will eat.
     */
    @Override
    public void eat(final Plant plantToBeEaten) {
    }

    /**
     * {@inheritDoc}
     * Bark.
     */
    public void verballyCommunicate() {
        out.println("Woof!");
    }

    /**
     * {@inheritDoc}
     *
     * @param numberPuppies Number of puppies being born.
     */
    @Override
    public void giveBirth(final int numberPuppies) {
    }

    /**
     * Provide the color of the dog's hair.
     *
     * @return Color of the dog's fur.
     */
    @Override
    public Color getHairColor() {
        return hairColor;
    }
}
