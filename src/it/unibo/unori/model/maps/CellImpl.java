package it.unibo.unori.model.maps;

import java.util.Optional;

import it.unibo.unori.model.maps.exceptions.NoMapFoundException;

/**
 * Implementation of the Cell interface.
 * 
 *
 */
public class CellImpl implements Cell {

    /**
     * 
     */
    private static final long serialVersionUID = -8981471810277191248L;
    private Object frame;
    private CellState state;
    private Optional<Map> maptolink = Optional.empty();

    /**
     * Basic Constructor for CellImpl.
     * 
     * @param frame
     *            the frame to associate
     * @param state
     *            the state to associate
     */
    public CellImpl(final Object frame, final CellState state) {
        this.frame = frame;
        this.state = state;
    }

    /**
     * Constructor for the Linked state, include the map to associate to the cell.
     * @param frame
     *              the frame to associate
     * @param state
     *              the state to associate
     * @param map
     *              the map to associate
     */
    public CellImpl(final Object frame, final CellState state, final Map map) {
       this(frame, state);
       this.maptolink = Optional.of(map);
    }


    @Override
    public void setState(final CellState state) {
        this.state = state;
    }

    @Override
    public CellState getState() {
        return this.state;
    }

    @Override
    public void setLinkedMap(final Map map) {
       this.maptolink = Optional.of(map);
    }

    @Override
    public void setFrame(final Object frame) {
        this.frame = frame;
    }

    @Override
    public Map getCellMap() throws NoMapFoundException {
        if (!this.maptolink.isPresent()) {
            throw new NoMapFoundException();
        }
        return this.maptolink.get();
    }

    @Override
    public Object getFrame() {
        return this.frame;
    }

}
