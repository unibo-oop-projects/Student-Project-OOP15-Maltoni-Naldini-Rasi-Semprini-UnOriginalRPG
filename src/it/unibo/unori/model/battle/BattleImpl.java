package it.unibo.unori.model.battle;

import java.util.List;

import it.unibo.unori.model.battle.exceptions.BarNotFullException;
import it.unibo.unori.model.battle.exceptions.CantEscapeException;
import it.unibo.unori.model.battle.exceptions.NotDefendableException;
import it.unibo.unori.model.battle.exceptions.NotEnoughMPExcpetion;
import it.unibo.unori.model.battle.utility.BattleLogics;
import it.unibo.unori.model.character.Foe;
import it.unibo.unori.model.character.FoeSquad;
import it.unibo.unori.model.character.FoeSquadImpl;
import it.unibo.unori.model.character.Hero;
import it.unibo.unori.model.character.HeroTeam;
import it.unibo.unori.model.character.HeroTeamImpl;
import it.unibo.unori.model.character.Status;
import it.unibo.unori.model.character.exceptions.NoWeaponException;
import it.unibo.unori.model.character.Character;
import it.unibo.unori.model.items.Bag;
import it.unibo.unori.model.items.BagImpl;
import it.unibo.unori.model.items.Potion;
import it.unibo.unori.model.items.exceptions.ItemNotFoundException;

/**
 * The principal Class modeling the Battle Mode.
 * It implements Battle Interface.
 */
public class BattleImpl implements Battle {

    private final HeroTeam squad;
    private final FoeSquad enemies;
    private Foe foeOnTurn;
    private Hero heroOnTurn;
    private boolean over;
    private final Bag itemBag;
    
    /**
     * Standard constructor for Class BattleImpl.
     * @param team my team.
     * @param en a List of Enemies.
     * @param bag the Item Bag.
     */
    public BattleImpl(final HeroTeam team, final FoeSquad en, final Bag bag) {
        this.squad = team;
        this.enemies = en;
        this.itemBag = bag;
        this.heroOnTurn = this.squad.getFirstHeroOnTurn();
        this.over = false;
    }
    
    private Character defeated(final Character ch) {
        if (ch instanceof Foe) {
            final Foe toRemove = (Foe) ch;
            this.enemies.removeFoe(toRemove);
            if (this.enemies.getAliveFoes().size() > 0) {
                this.over = false;
            } else {
                this.over = true;
            }
        } else if (ch instanceof Hero) {
            final Hero toRemove = (Hero) ch;
            this.squad.removeHero(toRemove);
        }
        return ch;
    }
    
    private boolean isDefeated(final Character ch) {
        return ch.getRemainingHP() == 0;
    }
    
    private int getMediumLevelFoes() {
        int mediumLevel = 0;
        for (final Foe h : this.enemies.getAllFoes()) {
            mediumLevel += h.getLevel();
        }
        mediumLevel /= this.enemies.getAllFoes().size();
        return mediumLevel;
    }
    
    @Override
    public String runAway(final Foe enemy, 
            final Hero my) throws CantEscapeException {
        if (BattleLogics.canEscape(my.getLevel(), enemy.getLevel())) {
            this.over = true;
            return "Sei riuscito a fuggire!";
        } else {
            throw new CantEscapeException();
        }
    }

    @Override
    public int attack(final Foe enemy, final Hero my) throws NoWeaponException {
        final int atkTot = my.getAttack() + (my.getWeapon().getPhysicalAtk());
        final int damage = 
                BattleLogics.getStandardDamage(my.getLevel(), atkTot);
        enemy.takeDamage(damage);
        if (this.isDefeated(enemy)) {
            this.defeated(enemy);
        } else {
            if (enemy.getStatus().equals(Status.NONE)) {
                enemy.setStatus(BattleLogics.causingStatus(my, enemy));
            }
        }
        return damage;
    }

    @Override
    public String defend(final Hero friend) throws NotDefendableException {
        if (friend.getStatus() == Status.NOT_DEFENDABLE) {
            throw new NotDefendableException();
        }
        if (friend.isDefended()) {
            return friend.getName() + " e' gia'  difeso! Peccato...";
        } else {
            friend.setDefended();
            return "Hai difeso " + friend.getName();
        }
    }

    @Override
    public String usePotionHP(final Hero my, final Potion toUse) 
            throws ItemNotFoundException {
        if (this.itemBag.contains(toUse)) {
            final int restore = toUse.getRestore();
            switch(toUse.getStatisticToRestore()) {
            case TOTALHP: my.restoreDamage(restore);
                break;
            case TOTALMP: my.restoreMP(restore);
                break;
            default:
                break;
            }
            return "Hai usato " + toUse.getName() + " su " + my.getName() + "!";
        } else {
            throw new ItemNotFoundException();
        }
    }

    @Override
    public String specialAttack(final Hero my) throws BarNotFullException {
        if (my.getCurrentBar() == my.getTotBar()) {
            my.resetSpecialBar();
            final int damage = 
                    BattleLogics.specialAttackCalc(my.getLevel(), my.getAttack());
            this.enemies.getAllFoes().forEach(e -> {
                e.takeDamage(damage);
                if (this.isDefeated(e)) {
                    this.defeated(e);
                }
            });
            return my.getName() + "ha usato l'attacco speciale!";
        } else {
            throw new BarNotFullException();
        }
    }

    @Override
    public int useMagicAttack(final MagicAttack m, final Hero my, final Foe enemy)
            throws NotEnoughMPExcpetion {
        if (my.getCurrentMP() > m.getMPRequired()) {
            my.consumeMP(m.getMPRequired());
        } else {
            throw new NotEnoughMPExcpetion();
        }
        //TODO A lot of things.
        return 0;
    }

    @Override
    public boolean isOver() {
        return this.over;
    }

    @Override
    public void acquireExp() {
        final List<Integer> points =
                BattleLogics.expAcquired(this.squad, this.getMediumLevelFoes(),
                        this.squad.getAliveHeroes().size());
        int index = 0;
        for (final Hero h : this.squad.getAliveHeroes()) {
            h.addExp(points.get(index));
            index++;
        }
    }

    @Override
    public FoeSquadImpl getEnemies() {
        return new FoeSquadImpl(this.enemies.getAllFoes());
    }

    @Override
    public HeroTeam getSquad() {
        return new HeroTeamImpl(this.squad.getAllHeroes());
    }

    @Override
    public Bag getItemBag() {
        return new BagImpl(this.itemBag);
    }
    
    @Override
    public Foe getFoeOnTurn() {
        return this.foeOnTurn;
    }
    
    @Override
    public Hero getHeroOnTurn() {
        return this.heroOnTurn;
    }
    
    @Override
    public String setHeroOnTUrn(final Hero h) {
        this.heroOnTurn = h;
        return "E' il turno di " + h.getName();
    }
    
    @Override
    public String setFoeOnTurn(final Foe en) {
        this.foeOnTurn = en;
        return "E' il turno di " + en.getName(); 
    }
}
