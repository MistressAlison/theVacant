package theVacant.orbs;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.vfx.combat.OrbFlareEffect;
import theVacant.VacantMod;
import theVacant.actions.VacantMillAction;
import theVacant.powers.VoidPower;

import static theVacant.VacantMod.makeOrbPath;

public class EmeraldOrb extends AbstractGemOrb
{
    public static final String ORB_ID = VacantMod.makeID(EmeraldOrb.class.getSimpleName());
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESCRIPTIONS = orbString.DESCRIPTION;
    private static boolean TURN_START_ORB = true;

    public EmeraldOrb(int size)
    {
        super(ORB_ID, orbString.NAME, size, TURN_START_ORB, makeOrbPath("EmeraldOrb.png"));
    }

    @Override
    public void TriggerPassive()
    {
        AbstractDungeon.actionManager.addToBottom(// 2.This orb will have a flare effect
                new VFXAction(new OrbFlareEffect(this, OrbFlareEffect.OrbFlareColor.LIGHTNING), 0.1f));
        AbstractDungeon.actionManager.addToBottom(new SFXAction("CARD_OBTAIN"));
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(1));
    }

    @Override
    public void EvokeGem()
    {
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(evokeAmount));
        AbstractDungeon.actionManager.addToBottom(new SFXAction("CARD_OBTAIN"));
    }

    @Override
    public void updateDescription()
    {
        applyFocus();
        description = DESCRIPTIONS[0] + evokeAmount + (evokeAmount==1?DESCRIPTIONS[1]:DESCRIPTIONS[2]);
    }

    @Override
    public AbstractOrb makeCopy()
    {
        return new AmethystOrb(passiveAmount);
    }
}