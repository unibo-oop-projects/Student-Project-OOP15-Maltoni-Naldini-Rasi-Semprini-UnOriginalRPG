package it.unibo.unori.model.maps.exceptions;

/**
 * Exception to rise when the party try to walk on a cell whose status in "BLOCKED".
 *
 */
public class BlockedPathException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 7282142092547170919L;

    /**
     * Send a message when the Exception is thrown.
     * 
     * @return the message to show
     */
    public String toString() {
        return "Error, Path is Blocked";
    }

}
