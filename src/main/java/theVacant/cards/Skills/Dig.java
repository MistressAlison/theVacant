package theVacant.cards.Skills;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theVacant.VacantMod;
import theVacant.actions.VacantMillAction;
import theVacant.cards.AbstractDynamicCard;
import theVacant.characters.TheVacant;
import theVacant.vfx.DigVFX;

import static theVacant.VacantMod.makeCardPath;

public class Dig extends AbstractDynamicCard
{

    public static final String ID = VacantMod.makeID(Dig.class.getSimpleName());
    public static final String IMG = makeCardPath("Dig.png");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheVacant.Enums.COLOR_GOLD;

    private static final int COST = 1;
    private static final int BLOCK = 6;
    private static final int UPGRADE_PLUS_BLOCK = 2;

    public Dig()
    {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 3;
        this.block = this.baseBlock = BLOCK;
        this.getBonusMillToMagic = true;
    }

    @Override
    public void use(AbstractPlayer player, AbstractMonster m)
    {
        addToBot(new VFXAction(new DigVFX(), DigVFX.DURATION*3/4F));
        AbstractDungeon.actionManager.addToBottom(new VacantMillAction(this.magicNumber));
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(player, player, this.block));
    }

    @Override
    public void upgrade()
    {
        if (!upgraded)
        {
            upgradeName();
            upgradeMagicNumber(2);
            upgradeBlock(UPGRADE_PLUS_BLOCK);
            initializeDescription();
        }
    }
}