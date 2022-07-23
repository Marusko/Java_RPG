package MS.unstableDungeon.npc;

import MS.unstableDungeon.hraHrac.Hrac;
import MS.unstableDungeon.hraHrac.HraciaPlocha;
import MS.unstableDungeon.mapa.Mapa;

import javax.swing.JOptionPane;

/**
 * Objekt na uloženie informácií o zadávateľovi úloh.
 *
 * @author Matúš Suský
 * @version r2022ver03.2
 */
public class Zadavatel extends NPC {

    public Zadavatel(int posX, int posY) {
        super(posX, posY, "Data/Tiles/Vybrane/Postavicky/NPC/zadavatel.png");
    }

    /**
     * Metóda zobrazí "rozhovor" medzi hráčom a zadáveteľom úloh a potom ak to hráč príjme tak zobrazí dostupné úlohy
     * a ak už úlohy nemá tak zobrazí iný "rozhovor"
     */
    public boolean akcia(int hracX, int hracY, Mapa mapa, Hrac hrac, HraciaPlocha hraciaPlocha) {
        if (hraciaPlocha.getSpravcaUloh().vsetkyZobrane()) {
            Object[] options = {"Ukáž čo máš", "Nie nechcem"};
            int tmp = JOptionPane.showOptionDialog(null, "Psst\nHej tu dole...\nHALÓ\nMám tu pre teba niečo", "ČO TO ..... JE?", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            if (tmp == 0) {
                hraciaPlocha.vypisUlohy();
            }
        } else {
            Object[] options = {"......"};
            JOptionPane.showOptionDialog(null, "Psst\nHej tu dole...\nHALÓ\nNič nemám\nAHAHAHAHA...", "ČO TO ..... JE?", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        }
        return false;
    }
}
