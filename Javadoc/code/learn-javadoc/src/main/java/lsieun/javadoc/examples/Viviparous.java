package lsieun.javadoc.examples;

/**
 * Mammals that give birth to young that develop within
 * the mother's body.
 * 胎生的
 */
public interface Viviparous {
    /**
     * Give birth to indicated number of offspring.
     *
     * @param numberOfOffspring Number of offspring being born.
     */
    void giveBirth(int numberOfOffspring);
}
