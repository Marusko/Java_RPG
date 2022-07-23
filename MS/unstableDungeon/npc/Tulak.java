package MS.unstableDungeon.npc;

import MS.unstableDungeon.hraHrac.Hrac;
import MS.unstableDungeon.hraHrac.HraciaPlocha;
import MS.unstableDungeon.mapa.Mapa;

import javax.swing.JOptionPane;

/**
 * Objekt na uloženie informácií o tulákovi.
 *
 * @author Matúš Suský
 * @version r2022ver02.2
 */

public class Tulak extends NPC {

    public Tulak(int posX, int posY) {
        super(posX, posY, "Data/Tiles/Vybrane/Postavicky/NPC/tulak.png");
    }

    /**
     * Metóda zobrazí okno s informáciam čo tulák robí, konkrétne je opitý
     */
    public boolean akcia(int hracX, int hracY, Mapa mapa, Hrac hrac, HraciaPlocha hraciaPlocha) {
        Object[] options = {"Ignoruj ho"};
        JOptionPane.showOptionDialog(null, "*divné zvuky ktoré neviem napodobniť*\nNa čo čumíš *hic*? sceš sa biť?", "Bezmenný tulák, asi zablúdil? Haha...", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        return false;
    }
}
