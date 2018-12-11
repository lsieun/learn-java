package lsieun.javadoc.examples;

/**
 * Marks an Animal that eats other animals.
 */
public interface Carnivorous {
    /**
     * Eat the provided animal.
     *
     * @param animalBeingEaten Animal that will be eaten.
     */
    void eat(Animal animalBeingEaten);
}
