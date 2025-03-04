package fr.citedesiles.plugincite.holograms;

public class HologramsManager {

    public void initAll() {
        MainRankingHologram.init();
        HeadRankingHologram.init();
        SPRankingHologram.init();
        AngelRankingHologram.init();
        ArrowShootOnPlayerRankingHologram.init();
        BlueAxolotlKilledRankingHologram.init();
        DeathRankingHologram.init();
        DigRankingHologram.init();
        DirtRankingHologram.init();
        DragonSlayerRankingHologram.init();
        EnderpearlRankingHologram.init();
        ApiculteurRankingHologram.init();
        KillRankingHologram.init();
        ToolBrokenRankingHologram.init();
        NetheriteHoeRankingHologram.init();
        TotemOfUndyingRankingHologram.init();
        WardenKilledRankingHologram.init();
    }

    public void refreshAll() {
        MainRankingHologram.refresh();
        HeadRankingHologram.refresh();
        SPRankingHologram.refresh();
        AngelRankingHologram.refresh();
        ArrowShootOnPlayerRankingHologram.refresh();
        KillRankingHologram.refresh();
        WardenKilledRankingHologram.refresh();
        BlueAxolotlKilledRankingHologram.refresh();
        DeathRankingHologram.refresh();
        DigRankingHologram.refresh();
        DirtRankingHologram.refresh();
        DragonSlayerRankingHologram.refresh();
        EnderpearlRankingHologram.refresh();
        ToolBrokenRankingHologram.refresh();
        NetheriteHoeRankingHologram.refresh();
        ApiculteurRankingHologram.refresh();
        TotemOfUndyingRankingHologram.refresh();
    }
}
