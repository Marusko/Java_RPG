package MS.unstableDungeon.npc;

import MS.unstableDungeon.hraHrac.Hrac;
import MS.unstableDungeon.hraHrac.HraciaPlocha;
import MS.unstableDungeon.mapa.Mapa;


/**
 * Objekt na uloženie informácií o nepriateľovi. Uchováva informáciu či má nepriateľ kľúč.
 * 
 * @author Matúš Suský
 * @version r2022ver04.2
 */

public class Nepriatel extends NPC {
    private static final int POSKODENIE = 1;
    private boolean maKluc;
    private int zivot;


    public Nepriatel(int posX, int posY) {
        super(posX, posY, "Data/Tiles/Vybrane/Postavicky/NPC/nepriatel.png");
        this.maKluc = false;
        this.zivot = 2;
    }

    /**
     * Metóda nastaví či nepriateľ bude mať kľúč
     * @param maKluc určuje či ten kľúč bude mať alebo nie
     */
    public void setMaKluc(boolean maKluc) {
        this.maKluc = maKluc;
    }
    
    /**
     * Metóda odobrie nepriateľovi život
     * @param odobranyZivot určuje koľko života sa má odobrať
     */
    public void odoberZivot(int odobranyZivot) {
        this.zivot -= odobranyZivot;
    }

    /**
     * Metóda útoku medzi hráčom a nepriateľom
     * Tiež zisťuje či je nepriateľ stále nažive a ak nie tak zvýši počet zabitých nepriateľov tohto typu
     * @return vracia hodnotu toho či hráč ešte žije
     */
    public boolean akcia(int hracX, int hracY, Mapa mapa, Hrac hrac, HraciaPlocha hraciaPlocha) {
        this.odoberZivot(hrac.getPoskodenie());
        if (this.zivot <= 0) {
            this.skry();
            mapa.getAkciaPodlaPolicka()[hracY][hracX] = 0;
            mapa.zrusNepriatela(this);
            hraciaPlocha.pridajNorm();
            hraciaPlocha.kontrolaUlohy();
            if (this.maKluc) {
                hrac.ulozVec(2);
            }
            return false;
        } else {
            hrac.odoberZivot(Nepriatel.POSKODENIE);
            return true;
        }
    }
}
