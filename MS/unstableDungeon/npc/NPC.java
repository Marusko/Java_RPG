package MS.unstableDungeon.npc;

import MS.unstableDungeon.mapa.Mapa;
import MS.unstableDungeon.hraHrac.Hrac;
import MS.unstableDungeon.hraHrac.HraciaPlocha;
import MS.unstableDungeon.prevzate.Obrazok;

import javax.swing.JOptionPane;

/**
 * Predok pre nepriateľov a NPCčka
 *
 * @author Matúš Suský
 * @version r2022ver02.2
 */
public class NPC {
    private static final int STRANA_OBRAZKA = 20;
    private final Obrazok obrazok;

    public NPC(int posX, int posY, String cestaKuObrazku) {
        this.obrazok = new Obrazok(cestaKuObrazku);
        this.obrazok.zmenPolohu(posX - 5, posY - 5);
        this.obrazok.zmenVelkost(NPC.STRANA_OBRAZKA, NPC.STRANA_OBRAZKA);
        this.obrazok.skry();
    }

    public Obrazok getObrazok() {
        return this.obrazok;
    }

    public int getX() {
        return this.obrazok.getPolohaX();
    }
    public int getY() {
        return this.obrazok.getPolohaY();
    }

    public void zobraz() {
        this.obrazok.zobraz();
    }

    public void skry() {
        this.obrazok.skry();
    }

    /**
     * Metóda ktorá sa vykoná vždy pri interakcii s NPCčkom
     * @return vracia hodnotu toho či hráč ešte žije
     */
    public boolean akcia(int hracX, int hracY, Mapa mapa, Hrac hrac, HraciaPlocha hraciaPlocha) {
        Object[] options = {"Nechaj to spať"};
        JOptionPane.showOptionDialog(null, "*divné zvuky spánku*\nOno vlasne ani netuším že čo to je\na čo to robí...", "Spiace voľačo?", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        return false;
    }
}
