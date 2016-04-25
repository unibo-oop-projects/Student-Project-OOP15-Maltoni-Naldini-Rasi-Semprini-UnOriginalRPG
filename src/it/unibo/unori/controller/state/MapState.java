package it.unibo.unori.controller.state;

import it.unibo.unori.model.maps.GameMap;
import it.unibo.unori.model.maps.Party;
import it.unibo.unori.model.maps.SingletonParty;
import it.unibo.unori.view.View;

/**
 * This GameState models the state of exploring a map (world, town or dungeon room).
 */
public class MapState implements GameState {
    private final Party party;

    /**
     * Default constructor.
     * 
     * @param map
     *            the map to start from
     */
    public MapState(final GameMap map) {
        party = SingletonParty.getParty();
        party.setCurrentMap(map);
        // TODO
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final double elapsedTime) {
        // TODO Auto-generated method stub
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render() {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onEnter() {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onExit() {
        // TODO Auto-generated method stub

    }

    @Override
    public View getView() {
        // TODO Auto-generated method stub
        return null;
    }

}
