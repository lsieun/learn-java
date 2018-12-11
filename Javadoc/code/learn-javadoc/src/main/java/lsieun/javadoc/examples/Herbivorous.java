package lsieun.javadoc.examples;

/**
 * Marks animals that eat plants.
 */
public interface Herbivorous {
    /**
     * Eat the provided plant.
     *
     * @param plantToBeEaten Plant that will be eaten.
     */
    void eat(Plant plantToBeEaten);
}
