package it.unibo.unori.model.maps.cell;

import it.unibo.unori.model.items.Item;
import it.unibo.unori.model.maps.exceptions.NoObjectFoundException;

/**
 * Extends SimpleCellImpl, handle a object on the map. 
 *
 */
public class ObjectCellImpl extends SimpleCellImpl {

    /**
     * 
     */
    private static final long serialVersionUID = 1077983786031256996L;
    private final Item obj;

    /**
     * Constructor.
     * @param obj
     *              object to set in the cell
     */
    public ObjectCellImpl(final Item obj) {
        super("res/sprites/map/item.png", CellState.BLOCKED);
        this.obj = obj;
    }

    @Override
    public Item getObject() throws NoObjectFoundException {
        return this.obj;
    }


}
