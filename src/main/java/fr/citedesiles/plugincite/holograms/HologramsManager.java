package fr.citedesiles.plugincite.holograms;

public class HologramsManager {

    public void initAll() {
        MainRankingHologram.init();
        HeadRankingHologram.init();
        SPRankingHologram.init();
        AngelRankingHologram.init();
        ArrowShootOnPlayerRankingHologram.init();
        KillRankingHologram.init();
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
    }
}
