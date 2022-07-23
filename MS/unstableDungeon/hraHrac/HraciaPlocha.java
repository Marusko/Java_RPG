package MS.unstableDungeon.hraHrac;

import MS.unstableDungeon.mapa.Mapa;
import MS.unstableDungeon.npc.NPC;
import MS.unstableDungeon.prevzate.Manazer;
import MS.unstableDungeon.ulohy.SpravcaUloh;
import MS.unstableDungeon.ulohy.IUlohy;
import MS.unstableDungeon.ulohy.ZabijanieUloha;

import javax.swing.JOptionPane;
import java.util.Random;
import java.util.TreeMap;

/**
 * Objekt na uloženie a prepínanie medzi jednotlivými časťami mapy
 *
 * @author Matúš Suský
 * @version r2022ver07.2
 */
public class HraciaPlocha {
    
    private final Hrac hrac;
    private int hracX;
    private int hracY;
    private final Mapa mapa;
    private final SpravcaUloh spravcaUloh;
    private int pocitadloNorm;
    private int pocitadloBoss;


    public HraciaPlocha() {
        this.mapa = new Mapa();
        this.hrac = new Hrac();
        this.spravcaUloh = new SpravcaUloh();
        this.hracX = ((this.hrac.getX()) / 20);
        this.hracY = ((this.hrac.getY()) / 20);
        Manazer manazer = new Manazer();
        manazer.spravujObjekt(this);
        this.pocitadloNorm = 0;
        this.pocitadloBoss = 0;
    }

    /**
     * Metóda ukončí hru
     */
    public void zrus() {
        Random r = new Random();
        Object[] options = {"Zostaň", "Koniec"};
        int tmp = JOptionPane.showOptionDialog(null, "Naozaj chceš odísť?????", "Ukončiť hru", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        if (tmp == 1) {
            int opak = r.nextInt(5);
            for (int i = 0; i < opak; i++) {
                Object[] options2 = {"Už chcem ísť preč!!!"};
                JOptionPane.showOptionDialog(null, "Ale ja nechcem aby si odišiel!!!", "Ukončiť hru", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options2, options2[0]);
            }
            System.exit(0);
        }
    }

    /**
     * Metóda po zavolaní z manažéra zistí či sa hráč nachádza na konkrétnom políčku konkrétnej časti, ak áno vykoná sa určitá akcia, ak hráč hru vyhral zobrazí sa okno o tom že vyhral
     */
    public void aktivuj() {
        if (this.mapa.getIndexAktivnejCasti() == 1 && this.hracX == 2 && this.hracY == 7) {
            if (!this.hrac.getVeciVInv().isEmpty()) {
                for (String vec : this.hrac.getVeciVInv()) {
                    if (!(vec.equals("mec")) && !(vec.equals("sekera"))) {
                        this.hrac.ulozVec(1);
                        this.hrac.setPoskodenie(2);
                    }
                }
            } else {
                this.hrac.ulozVec(1);
                this.hrac.setPoskodenie(2);
            }
            if (this.kontrolaUlohy()) {
                this.vypisAUlohy();
            }
        } else if (this.mapa.getIndexAktivnejCasti() == 4 && this.hracX == 11 && this.hracY == 7) {
            if (this.spravcaUloh.getPocetUloh() == this.spravcaUloh.getPocetHotovychUloh()) {
                if (!this.hrac.getVeciVInv().isEmpty()) {
                    for (String vec : this.hrac.getVeciVInv()) {
                        if (vec.equals("lod")) {
                            Object[] options = {"Yaaay"};
                            JOptionPane.showOptionDialog(null, "Vieš bojovať veľmi dobre,\na vojaci z Backwoodu neboli ťažký nepriatelia.\nZískal si informácie, loď a vyplavil si sa za slobodou.", "Ušiel si", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                            System.exit(0);
                        }
                    }
                }
            } else {
                Object[] options = {"Tak poďme na to"};
                int kolko = this.spravcaUloh.getPocetUloh() - this.spravcaUloh.getPocetHotovychUloh();
                if (kolko == 1) {
                    JOptionPane.showOptionDialog(null, "Ešte si nespravil všetky úlohy\nChýba ti ešte " + kolko + " úloha", "Úlohy", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);
                } else if (kolko >= 2 && kolko <= 4) {
                    JOptionPane.showOptionDialog(null, "Ešte si nespravil všetky úlohy\nChýbajú ti ešte " + kolko + " úlohy", "Úlohy", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);
                } else if (kolko >= 5) {
                    JOptionPane.showOptionDialog(null, "Ešte si nespravil všetky úlohy\nChýba ti ešte " + kolko + " úloh", "Úlohy", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);
                }

            }
            if (this.kontrolaUlohy()) {
                this.vypisAUlohy();
            }
        } else if (this.mapa.getIndexAktivnejCasti() == 6 && this.hracX == 11 && this.hracY == 12) {
            if (!this.hrac.getVeciVInv().isEmpty()) {
                for (String vec : this.hrac.getVeciVInv()) {
                    if (vec.equals("kluc")) {
                        this.hrac.odoberVec("kluc");
                        this.hrac.ulozVec(3);
                    }
                }
            }
            if (this.kontrolaUlohy()) {
                this.vypisAUlohy();
            }
        }
    }

    /**
     * Metóda posunie hráča dole, zistí či sa má vykonať nejaká akcia na políčku, ak áno tak ju vykoná
     */
    public void posunDole() {
        this.hrac.posunDole();
        this.hracY = ((this.hrac.getY()) / 20);
        
        if (this.hracY > 14) {
            if (this.mapa.posunDole()) {
                this.hrac.setPoziciaY(0);
                this.hrac.zobraz();
            } else {
                this.hrac.posunHore();
            }
        }
        this.hracY = ((this.hrac.getY()) / 20);
        
        if (this.mapa.getAkciaPodlaPolicka()[this.hracY][this.hracX] == 1) {
            this.hrac.posunHore();
            this.hracY = ((this.hrac.getY()) / 20);
        } else if (this.mapa.getAkciaPodlaPolicka()[this.hracY][this.hracX] == 2) {
            this.akcia(this.hracX, this.hracY);
        }
    }

    /**
     * Metóda posunie hráča hore, zistí či sa má vykonať nejaká akcia na políčku, ak áno tak ju vykoná
     */
    public void posunHore() {
        this.hrac.posunHore();
        this.hracY = ((this.hrac.getY()) / 20);
        
        if (this.hracY < 0) {
            if (this.mapa.posunHore()) {
                this.hrac.setPoziciaY(280);
                this.hrac.zobraz();
            } else {
                this.hrac.posunDole();
            }
        }
        this.hracY = ((this.hrac.getY()) / 20);
        
        if (this.mapa.getAkciaPodlaPolicka()[this.hracY][this.hracX] == 1) {
            this.hrac.posunDole();
            this.hracY = ((this.hrac.getY()) / 20);
        } else if (this.mapa.getAkciaPodlaPolicka()[this.hracY][this.hracX] == 2) {
            this.akcia(this.hracX, this.hracY);
        }
    }

    /**
     * Metóda posunie hráča vpravo, zistí či sa má vykonať nejaká akcia na políčku, ak áno tak ju vykoná
     */
    public void posunVpravo() {
        this.hrac.posunVpravo();
        this.hracX = ((this.hrac.getX()) / 20);
        
        if (this.hracX > 14) {
            if (this.mapa.posunVpravo()) {
                this.hrac.setPoziciaX(0);
                this.hrac.zobraz();
            } else {
                this.hrac.posunVlavo();
            }
        }
        this.hracX = ((this.hrac.getX()) / 20);
        
        if (this.mapa.getAkciaPodlaPolicka()[this.hracY][this.hracX] == 1) {
            this.hrac.posunVlavo();
            this.hracX = ((this.hrac.getX()) / 20);
        } else if (this.mapa.getAkciaPodlaPolicka()[this.hracY][this.hracX] == 2) {
            this.akcia(this.hracX, this.hracY);
        }
    }

    /**
     * Metóda posunie hráča vľavo, zistí či sa má vykonať nejaká akcia na políčku, ak áno tak ju vykoná
     */
    public void posunVlavo() {
        this.hrac.posunVlavo();
        this.hracX = ((this.hrac.getX()) / 20);
        
        if (this.hracX < 0) {
            if (this.mapa.posunVlavo()) {
                this.hrac.setPoziciaX(280);
                this.hrac.zobraz();
            } else {
                this.hrac.posunVpravo();
            }
        }
        this.hracX = ((this.hrac.getX()) / 20);
        
        if (this.mapa.getAkciaPodlaPolicka()[this.hracY][this.hracX] == 1) {
            this.hrac.posunVpravo();
            this.hracX = ((this.hrac.getX()) / 20);
        } else if (this.mapa.getAkciaPodlaPolicka()[this.hracY][this.hracX] == 2) {
            this.akcia(this.hracX, this.hracY);
        }
    }
    
    /**
     * Metóda vykoná útok, vždy útočí najprv hráč a až po ňom nepriateľ, ak hráč umrie zobrazí sa okno o tom že umrel
     * @param hracX x pozícia políčka, na ktorom sa hráč nachádza
     * @param hracY y pozícia políčka, na ktorom sa hráč nachádza
     */
    private void akcia(int hracX, int hracY) {
        NPC vybrany = this.mapa.getNPC(hracX * 20, hracY * 20);
        if (vybrany.akcia(hracX, hracY, this.mapa, this.hrac, this) && this.hrac.getZivot() == 0) {
            Object[] options = {"Škoda"};
            JOptionPane.showOptionDialog(null, "Bohužiaľ si sa neubránil moc dobre.\nAvšak aj toto je určitý typ slobody.", "Umrel si", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);
            System.exit(0);
        }
        this.hracX = ((this.hrac.getX()) / 20);
        this.hracY = ((this.hrac.getY()) / 20);
        if (this.kontrolaUlohy()) {
            this.vypisAUlohy();
        }
    }

    /**
     * Metóda na počítanie zabitých nepriateľov
     */
    public void pridajNorm() {
        this.pocitadloNorm++;
    }

    /**
     * Metóda na počítanie zabitých bossov
     */
    public void pridajBoss() {
        this.pocitadloBoss++;
    }

    public SpravcaUloh getSpravcaUloh() {
        return this.spravcaUloh;
    }

    /**
     * Metóda na výpis aktívnych úloh
     */
    public void vypisAUlohy() {
        this.kontrolaUlohy();
        this.spravcaUloh.vypisAUlohy();
    }

    /**
     * Metóda na výpis všetkých úloh
     */
    public void vypisUlohy() {
        this.spravcaUloh.vypisUlohy();
    }

    /**
     * Metóda kontroluje či sú úlohy splnené
     *
     * @return true ak je nejaká úloha splnená
     */
    public boolean kontrolaUlohy() {
        TreeMap<Integer, IUlohy> ulohy = this.spravcaUloh.getUlohy();

        for (int i = 0; i < ulohy.size(); i++) {
            if (ulohy.get(i).getAktivny() && ulohy.get(i).getTypUlohy().equals("zber")) {
                for (String p : this.hrac.getVeciVInv()) {
                    if (ulohy.get(i).getPoziadavka().equals(p) && !ulohy.get(i).getSkryty()) {
                        ulohy.get(i).setHotovy(true);
                        return true;
                    }
                }
            }
        }
        for (int i = 0; i < ulohy.size(); i++) {
            if (ulohy.get(i).getAktivny() && ulohy.get(i).getTypUlohy().equals("zab")) {
                ZabijanieUloha uloha = (ZabijanieUloha)ulohy.get(i);
                if (uloha.getTypEnemy().equals("norm") && this.pocitadloNorm == Integer.parseInt(uloha.getPoziadavka())) {
                    uloha.setHotovy(true);
                    this.pocitadloNorm = 0;
                    return true;
                } else if (uloha.getTypEnemy().equals("boss") && this.pocitadloBoss == Integer.parseInt(uloha.getPoziadavka())) {
                    uloha.setHotovy(true);
                    this.pocitadloBoss = 0;
                    return true;
                }
            }
        }
        return false;
    }
}
