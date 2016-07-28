package it.unibo.unori.model.character;

import java.io.Serializable;
import java.util.List;

import it.unibo.unori.model.character.exceptions.MaxFoesException;

/**
 * An interface to model a team of Foes to be presented in Batlle Mode.
 *
 */
public interface FoeSquad extends Serializable {
    
    /**
     * Method to add a foe to the Squad.
     * @param toAdd the Foe to be added.
     * @throws MaxFoesException if the FoeSquad is full.
     */
    void addFoe(final Foe toAdd) throws MaxFoesException;
    
    /**
     * Method to remove a foe from the Squad.
     * @param toRemove the foe to be removed.
     * @throws IllegalArgumentException if the FoeSquad is empty or if the Foe to be
     * removed is not present in the Squad.
     */
    void removeFoe(final Foe toRemove) throws IllegalArgumentException;
    
    /**
     * A getter method that returns the whole Squad of Foes.
     * @return the whole Squad.
     * @throws IllegalStateException if the list of Foes is empty.
     */
    List<Foe> getAllFoes() throws IllegalStateException;
    
    /**
     * A getter method that returns the list of the Foes whose status is not DEAD.
     * @return the list of alive Foes
     * @throws IllegalStateException if the list of Foes is empty.
     */
    List<Foe> getAliveFoes() throws IllegalStateException;
    
    /**
     * A getter method that returns the first Foe who's allowed to move in Battle.
     * @return the first Foe of the Squad.
     * @throws IllegalStateException if the list of Foes is empty.
     */
    Foe getFirstFoeOnTurn() throws IllegalStateException;
}