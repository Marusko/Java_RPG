package MS.unstableDungeon.npc;

import MS.unstableDungeon.hraHrac.Hrac;
import MS.unstableDungeon.hraHrac.HraciaPlocha;
import MS.unstableDungeon.mapa.Mapa;

/**
 * Objekt na uloženie informácií o bossovi.
 *
 * @author Matúš Suský
 * @version r2022ver03.2
 */

public class Boss extends NPC {

    private static final int POSKODENIE = 1;
    private int zivot;

    public Boss(int posX, int posY) {
        super(posX, posY, "Data/Tiles/Vybrane/Postavicky/NPC/boss.png");
        this.zivot = 6;
    }

    /**
     * Metóda odobrie bossovi život
     * @param odobranyZivot určuje koľko života sa má odobrať
     */
    public void odoberZivot(int odobranyZivot) {
        this.zivot -= odobranyZivot;
    }

    /**
     * Metóda útoku medzi hráčom a bossom
     * Tiež zisťuje či je boss stále nažive a ak nie tak zvýši počet zabitých nepriateľov tohto typu
     * @return vracia hodnotu toho či hráč ešte žije
     */
    public boolean akcia(int hracX, int hracY, Mapa mapa, Hrac hrac, HraciaPlocha hraciaPlocha) {
        this.odoberZivot(hrac.getPoskodenie());
        if (this.zivot <= 0) {
            this.skry();
            mapa.getAkciaPodlaPolicka()[hracY][hracX] = 0;
            mapa.zrusNepriatela(this);
            hraciaPlocha.pridajBoss();
            hraciaPlocha.kontrolaUlohy();
            return false;
        } else {
            hrac.odoberZivot(Boss.POSKODENIE);
            return true;
        }
    }
}
