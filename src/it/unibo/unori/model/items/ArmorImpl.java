package it.unibo.unori.model.items;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import it.unibo.unori.model.character.Statistics;
import it.unibo.unori.model.character.Status;



/**
 * Describe an armor implementations.
 * It has simply some parameters and their getter.
 */
public class ArmorImpl implements Armor {

    /**
     * 
     */
    private static final long serialVersionUID = 6023683727427402144L;
    private final String name;
    private final ArmorPieces piece;
    private final String desc;
    private final Map<Statistics, Integer> stats;
    private final Status immunity;
    private static final String STDNAME = "Nudo";
    private static final String STDDESC = "La semplice pelle";


    /**
     * Basic equipment for a character without any piece of that part of armor.
     */
    public static final ArmorImpl NAKED = new ArmorImpl();

    private Map<Statistics, Integer> generateStdStats() {
        final Map<Statistics, Integer> stats = new HashMap<>();
        stats.put(Statistics.PHYISICDEF, 0);
        stats.put(Statistics.FIREDEF, 0);
        stats.put(Statistics.ICEDEF, 0);
        stats.put(Statistics.THUNDERDEF, 0);
        return stats;
    }

    private boolean isNakedConstruction(final String name, final ArmorPieces piece, final String desc,
                final Map<Statistics, Integer> stats, final Status immunity) {
        return STDNAME.equals(name) && piece.equals(ArmorPieces.NONE)
                && stats.isEmpty() && immunity.equals(Status.NONE)
                && STDDESC.equals(desc);
    }

    private boolean hasLegitStats(final Set<Statistics> s) {
        return s.containsAll(Arrays.asList(Statistics.FIREATK, Statistics.ICEDEF, 
                Statistics.THUNDERDEF, Statistics.PHYISICDEF))
                && s.size() == 4;
    }


    private ArmorImpl() {
        this(STDNAME, ArmorPieces.NONE, STDDESC, new HashMap<>(), 
                   Status.NONE);
       }

    /**
     * Standard constructor.
     * @param name
     *             name of the armor.
     * @param piece
     *              part of the armor.
     * @param desc
     *              description of the armor.
     * @param stats
     *              statistics of the armor.
     * @param immunity
     *              immunity to status of the armor.
     * @throws IllegalArgumentException if stats does not contains all the parameters
     *  required.
     */
    public ArmorImpl(final String name, final ArmorPieces piece, final String desc,
                      final Map<Statistics, Integer> stats, final Status immunity) 
                       throws IllegalArgumentException {
        this.name = name;
        this.desc = desc;
        this.piece = piece;
        this.immunity = immunity;
        if (this.isNakedConstruction(name, piece, desc, stats, immunity)) {
            this.stats = this.generateStdStats();
        } else {
            if (this.hasLegitStats(stats.keySet())) {
                this.stats = stats;
            } else {
                throw new IllegalArgumentException();
            }
        }
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.desc;
    }

    @Override
    public int getPhysicalRes() {
        return this.stats.get(Statistics.PHYISICDEF);
    }

    @Override
    public int getFireDef() {
        return this.stats.get(Statistics.FIREDEF);
    }

    @Override
    public int getThunderDefense() {
        return this.stats.get(Statistics.THUNDERDEF);
    }

    @Override
    public int getIceDefense() {
        return this.stats.get(Statistics.ICEDEF);
    }

    @Override
    public Status getImmunity() {
        return this.immunity;
    }

    @Override
    public ArmorPieces getArmorClass() {
        return this.piece;
    }

}