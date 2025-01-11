package fr.citedesiles.plugincite.holograms;

public class HologramsManager {

    public void initAll() {
        MainRankingHologram.init();
        HeadRankingHologram.init();
        SPRankingHologram.init();
    }

    public void refreshAll() {
        MainRankingHologram.refresh();
        HeadRankingHologram.refresh();
        SPRankingHologram.refresh();
    }
}
