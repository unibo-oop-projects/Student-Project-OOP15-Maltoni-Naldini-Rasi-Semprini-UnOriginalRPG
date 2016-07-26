package it.unibo.unori.model.maps.cell;

import it.unibo.unori.model.items.Bag;
import it.unibo.unori.model.items.Item;
import it.unibo.unori.model.items.ItemImpl;
import it.unibo.unori.model.items.ObjectItem;
import it.unibo.unori.model.items.exceptions.ItemNotFoundException;
import it.unibo.unori.model.maps.exceptions.NoKeyFoundException;
import it.unibo.unori.model.maps.exceptions.NoObjectFoundException;

/**
 * Class which design a chest in the game, which has a object inside and it need
 * a key to open.
 *
 */
public class ChestCellImpl extends SimpleCellImpl {

    /**
     * 
     */
    private static final long serialVersionUID = -7296644040007976176L;
    private final Item o;

    /**
     * Constructor for the cell.
     * @param path
     *              the path of frame to set in the cell
     * @param o
     *              object to put in the cell
     */
    public ChestCellImpl(final String path, final ObjectItem o) {
        super(path, CellState.BLOCKED);
        this.o = o;
    }

    /**
     * Method to open the chest and get the item inside.
     * @param b
     *          the bag of the party
     * @return
     *          the item in the chest
     * @throws NoObjectFoundException if the chest is empty.
     * @throws NoKeyFoundException if the bag does not contain a key
     * @throws ItemNotFoundException if there's an error in the process of 
     *  removing the key
     * 
     */
    public Item openChest(final Bag b) throws NoObjectFoundException, 
                                              NoKeyFoundException,
                                              ItemNotFoundException {
        if (!b.containsKey()) {
            throw new NoKeyFoundException();
        }
        b.removeItem(ItemImpl.KEY);
        return o;
    }

}
