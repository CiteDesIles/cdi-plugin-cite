package fr.citedesiles.plugincite.holograms;

public class HologramsManager {

    public void initAll() {
        MainHologram.init();
        HeadHologram.init();
    }

    public void refreshAll() {
        MainHologram.refresh();
        HeadHologram.refresh();
    }
}
