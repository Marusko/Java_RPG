package MS.unstableDungeon.npc;

import MS.unstableDungeon.hraHrac.Hrac;
import MS.unstableDungeon.hraHrac.HraciaPlocha;
import MS.unstableDungeon.mapa.Mapa;

import javax.swing.JOptionPane;

/**
 * Objekt na uloženie informácií o obchodníkovi.
 *
 * @author Matúš Suský
 * @version r2022ver02.2
 */

public class Obchodnik extends NPC {
    public Obchodnik(int posX, int posY) {
        super(posX, posY, "Data/Tiles/Vybrane/Postavicky/NPC/obchodnik.png");
    }

    /**
     * Metóda zobrazí "rozhovor" medzi hráčom a obchodníkom, ak hráč súhlasí a má v inventári
     * meč tak vymení meč za sekeru
     */
    public boolean akcia(int hracX, int hracY, Mapa mapa, Hrac hrac, HraciaPlocha hraciaPlocha) {
        if (hrac.getVeciVInv().contains("mec")) {
            Object[] options = {"Rád vymením", "Nie nechcem"};
            int tmp = JOptionPane.showOptionDialog(null, "Psst\nHej...\nChceš vymeniť?", "Čo si zač?", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            if (tmp == 0) {
                hrac.odoberVec("mec");
                hrac.ulozVec(4);
                hrac.setPoskodenie(3);
            }
        } else {
            Object[] options = {"Nechaj ma tak"};
            JOptionPane.showOptionDialog(null, "Čo tu robíš?\nVypadni!!!!", "Čo si zač?", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        }
        return false;
    }
}
