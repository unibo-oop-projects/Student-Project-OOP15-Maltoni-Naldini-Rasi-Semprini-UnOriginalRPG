package it.unibo.unori.model.character;

import java.util.Map;

/**
 * Implementation of Interface Foe.
 */
public class FoeImpl extends CharacterImpl implements Foe {

    /**
     * 
     */
    private static final long serialVersionUID = -1168567801329410379L;
    private final int ia;

    /**
     * Standard constructor for a Foe.
     * @param intelligence the IA of the Foe.
     * @param name the name of the Foe.
     * @param battleFrame path of frame to set for the battle interface
     * @param map the Statistics of the Foe.
     */
    public FoeImpl(final int intelligence, final String name,
            final String battleFrame, final Map<Statistics, Integer> map) {
        super(name, battleFrame, map);
        this.ia = intelligence;
    }

    @Override
    public int getIA() {
        return this.ia;
    }

}
