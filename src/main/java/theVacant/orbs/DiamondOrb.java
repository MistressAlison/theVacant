package theVacant.orbs;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.BufferPower;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import com.megacrit.cardcrawl.powers.MetallicizePower;
import com.megacrit.cardcrawl.vfx.combat.OrbFlareEffect;
import theVacant.VacantMod;
import theVacant.powers.TemperancePower;

import static theVacant.VacantMod.makeOrbPath;

public class DiamondOrb extends AbstractGemOrb
{
    public static final String ORB_ID = VacantMod.makeID(DiamondOrb.class.getSimpleName());
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESCRIPTIONS = orbString.DESCRIPTION;
    private static boolean TURN_START_ORB = true;

    public DiamondOrb(int size)
    {
        super(ORB_ID, orbString.NAME, size, TURN_START_ORB, makeOrbPath("DiamondOrb.png"));
    }

    @Override
    public void TriggerPassive()
    {
        AbstractDungeon.actionManager.addToBottom(
                new VFXAction(new OrbFlareEffect(this, OrbFlareEffect.OrbFlareColor.DARK), 0.1f));
        AbstractDungeon.actionManager.addToBottom(new SFXAction("TINGSHA"));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                new IntangiblePlayerPower(AbstractDungeon.player, 1), 1));
    }

    @Override
    public void EvokeGem()
    {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                new BufferPower(AbstractDungeon.player, evokeAmount), evokeAmount));
        AbstractDungeon.actionManager.addToBottom(new SFXAction("TINGSHA"));
    }

    @Override
    public void updateDescription()
    {
        applyFocus();
        description = DESCRIPTIONS[0] + evokeAmount + DESCRIPTIONS[1];
    }

    @Override
    public AbstractOrb makeCopy()
    {
        return new DiamondOrb(passiveAmount);
    }
}