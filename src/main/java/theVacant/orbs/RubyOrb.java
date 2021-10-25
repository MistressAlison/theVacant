package theVacant.orbs;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import com.megacrit.cardcrawl.vfx.combat.OrbFlareEffect;
import theVacant.VacantMod;

import static theVacant.VacantMod.makeOrbPath;

public class RubyOrb extends AbstractGemOrb
{
    public static final String ORB_ID = VacantMod.makeID(RubyOrb.class.getSimpleName());
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESCRIPTIONS = orbString.DESCRIPTION;
    private static boolean TURN_START_ORB = true;

    public RubyOrb(int size)
    {
        super(ORB_ID, orbString.NAME, size, TURN_START_ORB, makeOrbPath("RubyOrb.png"));
    }

    @Override
    public void TriggerPassive()
    {
        AbstractDungeon.actionManager.addToBottom(// 2.This orb will have a flare effect
                new VFXAction(new OrbFlareEffect(this, OrbFlareEffect.OrbFlareColor.DARK), 0.1f));
        AbstractDungeon.actionManager.addToBottom(new SFXAction("TINGSHA"));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                new VigorPower(AbstractDungeon.player, passiveAmount), passiveAmount));
    }

    @Override
    public void EvokeGem()
    {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                new StrengthPower(AbstractDungeon.player, evokeAmount), evokeAmount));
        AbstractDungeon.actionManager.addToBottom(new SFXAction("TINGSHA"));
    }

    @Override
    public void updateDescription()
    {
        applyFocus();
        description = DESCRIPTIONS[0] + passiveAmount + DESCRIPTIONS[1] + evokeAmount + DESCRIPTIONS[2];
    }

    @Override
    public AbstractOrb makeCopy()
    {
        return new RubyOrb(passiveAmount);
    }
}