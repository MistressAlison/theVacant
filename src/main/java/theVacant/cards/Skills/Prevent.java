package theVacant.cards.Skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theVacant.VacantMod;
import theVacant.cards.AbstractDynamicCard;
import theVacant.characters.TheVacant;
import theVacant.powers.HollowRune;
import theVacant.powers.VoidPower;
import theVacant.powers.WardRune;

import static theVacant.VacantMod.makeCardPath;

public class Prevent extends AbstractDynamicCard
{

    public static final String ID = VacantMod.makeID(Prevent.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheVacant.Enums.COLOR_GOLD;

    private static final int COST = 2;
    private static final int BLOCK = 4;
    private static final int UPGRADE_PLUS_BLOCK = 6;

    public Prevent()
    {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.block = this.baseBlock = BLOCK;
    }

    @Override
    public void use(AbstractPlayer player, AbstractMonster monster)
    {
        if(player.hasPower(VoidPower.POWER_ID))
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(player, player, new DexterityPower(player, player.getPower(VoidPower.POWER_ID).amount), player.getPower(VoidPower.POWER_ID).amount));
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(player, player, this.block));
    }

    @Override
    public void upgrade()
    {
        if (!upgraded)
        {
            upgradeName();
            upgradeBlock(UPGRADE_PLUS_BLOCK);
            initializeDescription();
        }
    }
}