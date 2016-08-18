package it.unibo.unori.model.menu;

import it.unibo.unori.model.battle.Battle;
import it.unibo.unori.model.battle.MagicAttack;
import it.unibo.unori.model.battle.exceptions.BarNotFullException;
import it.unibo.unori.model.battle.exceptions.NotDefendableException;
import it.unibo.unori.model.battle.exceptions.NotEnoughMPExcpetion;
import it.unibo.unori.model.character.Foe;
import it.unibo.unori.model.character.Hero;
import it.unibo.unori.model.character.exceptions.MagicNotFoundException;
import it.unibo.unori.model.character.exceptions.NoWeaponException;

/**
 * Class that implements FightInterface, to model a fight menu in Battle.
 *
 */
public class FightMenu extends BattleMenu implements FightInterface {
    
   /**
    * Standard constructor for a Fight Menu.
    * @param battle the Battle from which generate the FightMenu.
    */
   public FightMenu(final Battle battle) {
       super(battle);
   }
   
    @Override
    public DialogueInterface attack(final boolean whoAttacks) throws NoWeaponException {
        return this.getBattle().attack(whoAttacks);
    }

    @Override
    public DialogueInterface magic(final MagicAttack m,
            final Foe enemy, final boolean whosFirst) 
                    throws NotEnoughMPExcpetion, MagicNotFoundException {
        return this.getBattle().useMagicAttack(m, enemy, whosFirst);
    }

    @Override
    public DialogueInterface specialAtk() throws BarNotFullException {
        return this.getBattle().specialAttack();
    }

    @Override
    public DialogueInterface defend(final Hero toDefend) 
            throws NotDefendableException {
        return this.getBattle().defend(toDefend);
    }

}
