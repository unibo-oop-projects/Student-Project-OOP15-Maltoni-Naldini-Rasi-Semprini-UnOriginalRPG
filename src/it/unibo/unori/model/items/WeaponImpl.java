package it.unibo.unori.model.items;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import it.unibo.unori.model.character.Statistics;
import it.unibo.unori.model.character.Status;


/**
 * Implements the weapon interface.
 *
 */
public class WeaponImpl implements Weapon {

    /**
     * 
     */
    private static final long serialVersionUID = -1283066002516323821L;
    private final String name;
    private final String desc;
    private final Map<Statistics, Integer> stats;
    private final Status inflictedStatus;
    private static final String STDNAME = "Pugno";
    private static final String STDDESC = "Ma ti aspetta un pugno, ma è ovvio!";
    /**
     * Basic weapon for every Character.
     */
    public static final Weapon FISTS = new WeaponImpl();

    private WeaponImpl() {
        this(STDNAME, STDDESC, new HashMap<>(), Status.NONE);
    }

    private Map<Statistics, Integer> generateStdStats() {
        final Map<Statistics, Integer> stats = new HashMap<>();
        stats.put(Statistics.PHYSICATK, 0);
        stats.put(Statistics.FIREATK, 0);
        stats.put(Statistics.ICEATK, 0);
        stats.put(Statistics.THUNDERATK, 0);
        return stats;
    }

    private boolean isNakedConstruction(final String name, final String desc,
            final Map<Statistics, Integer> stats, final Status inflictedStatus) {
    return STDNAME.equals(name)
            && stats.isEmpty() && inflictedStatus.equals(Status.NONE)
            && STDDESC.equals(desc);
    }

    private boolean hasLegitStats(final Set<Statistics> s) {
        return s.containsAll(Arrays.asList(Statistics.FIREATK, Statistics.ICEATK, 
                Statistics.THUNDERATK, Statistics.PHYSICATK))
                && s.size() == 4;
    }

    /**
     * Standard constructor.
     * @param name
     *             name of the armor.
     * @param desc
     *              description of the armor.
     * @param stats
     *              statistics of the armor.
     * @param inflictedStatus
     *               status of the weapon.
     * @throws IllegalArgumentException if stats does not contains all the parameters
     *  required.
     */
    public WeaponImpl(final String name, final String desc,
            final Map<Statistics, Integer> stats, final Status inflictedStatus) 
                    throws IllegalArgumentException {
        this.name = name;
        this.desc = desc;
        this.inflictedStatus = inflictedStatus;
        if (this.isNakedConstruction(name, desc, stats, inflictedStatus)) {
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
    public int getPhysicalAtk() {
        return this.stats.get(Statistics.PHYSICATK);
    }

    @Override
    public int getFireAtk() {
        return this.stats.get(Statistics.FIREATK);
    }

    @Override
    public int getThunderAtk() {
        return this.stats.get(Statistics.THUNDERATK);
    }

    @Override
    public int getIceAtk() {
        return this.stats.get(Statistics.ICEATK);
    }

    @Override
    public Status getWeaponStatus() {
        return this.inflictedStatus;
    }

}