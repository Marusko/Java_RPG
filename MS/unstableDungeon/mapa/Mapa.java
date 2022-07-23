package MS.unstableDungeon.mapa;

import MS.unstableDungeon.npc.NPC;
import MS.unstableDungeon.npc.Nepriatel;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JProgressBar;

/**
 * Objekt na uloženie a prepínanie medzi jednotlivými časťami mapy
 * 
 * @author Matúš Suský 
 * @version r2022ver05.2
 */

public class Mapa extends JFrame {
    private CastMapy[][] castiMapy;
    private CastMapy aktivnaCast;
    private final ArrayList<NPC> npccka;
    private int riadok;
    private int stlpec;
    private final JProgressBar progressBar;
    
    public Mapa() {
        this.progressBar = new JProgressBar(0, 9);
        this.progressBar.setBounds(40, 40, 160, 30);
        this.progressBar.setValue(0);
        this.progressBar.setStringPainted(true);
        this.add(this.progressBar);
        this.setSize(250, 150);
        this.setLayout(null);

        try {
            this.nacitaj();
        } catch (IOException e) {
            System.out.println("Chyba pri načítavaní mapových dát");
        }
        this.aktivnaCast = null;
        this.riadok = 0;
        this.stlpec = 0;
        this.nastav();
        this.zaciatok();
        this.npccka = new ArrayList<>();
        this.setKluc();
    }

    /**
     * Metóda načíta všetky časti mapy a uloží ich podľa súboru
     */
    private void nacitaj() throws IOException {
        File subor = new File("Data/CastiMapy/mapa.txt");
        Scanner citac = new Scanner(subor);
        this.castiMapy = new CastMapy[citac.nextInt()][citac.nextInt()];
        for (int i = 0; i < this.castiMapy.length; i++) {
            for (int j = 0; j < this.castiMapy[i].length; j++) {
                int cast = citac.nextInt();
                if (cast != 0) {
                    this.castiMapy[i][j] = new CastMapy(cast);
                }
            }
        }
    }
    
    /**
     * Metóda nastaví všetky časti mapy podľa indexov v konštruktore, zobrazí a aktualizuje načítavací riadok 
     */
    private void nastav() {
        int nacitavanaCast = 1;      
        this.setVisible(true);
        this.setAlwaysOnTop(true);
        for (CastMapy[] riadky : this.castiMapy) {
            for (CastMapy cast : riadky) {
                if (cast != null) {
                    try {
                        this.progressBar.setValue(nacitavanaCast);
                        cast.nastavCast();
                        nacitavanaCast++;
                    } catch (IOException e) {
                        System.out.println("Chyba pri načítavaní časti mapy {" + nacitavanaCast + "}");
                    }
                }
            }
        }
        this.setVisible(false);
    }
    
    /**
     * Metóda zobrazí prvú časť mapy, definovanú v konštruktore
     */
    private void zaciatok() {
        this.castiMapy[this.riadok][this.stlpec].zobrazenieCasti();
        this.aktivnaCast = this.castiMapy[this.riadok][this.stlpec];
    }
    
    /**
     * Metóda zmení časť mapy
     * @param riadok určuje riadok z dvojrozmerného poľa častí mapy
     * @param stlpec určuje stĺpec z dvojrozmerného poľa častí mapy
     */
    private void zmenCast(int riadok, int stlpec) {
        if (riadok < this.castiMapy.length) {
            if (stlpec < this.castiMapy[riadok].length) {
                if (this.castiMapy[riadok][stlpec] != null) {
                    this.riadok = riadok;
                    this.stlpec = stlpec;
                    this.aktivnaCast.skrytieCasti();
                    this.castiMapy[this.riadok][this.stlpec].zobrazenieCasti();
                    this.aktivnaCast = this.castiMapy[this.riadok][this.stlpec];
                }
            }
        }
    }
    
    //Ovládanie časti mapy
    /**
     * Metóda vráti dvojrozmerné pole určujúce akciu na tom políčku v aktívnej časti
     */
    public int[][] getAkciaPodlaPolicka() {
        return this.aktivnaCast.getTypyPolicok();
    }

    /**
     * Metóda vráti NPC podľa súradníc
     * @param x x-ová súradnica NPC
     * @param y y-ová súradnica NPC
     * @return NPC
     */
    public NPC getNPC(int x, int y) {
        return this.aktivnaCast.getNPC(x, y);
    }
    
    public int getIndexAktivnejCasti() {
        return this.aktivnaCast.getIndex();
    }
    
    /**
     * Metóda vytvorí zoznam všetkých nepriateľov na mape a náhodne vyberie jedného, ktorému priradí kľúč
     */
    private void setKluc() {
        Random r = new Random();
        for (CastMapy[] riadky : this.castiMapy) {
            for (CastMapy castMapy : riadky) {
                if (castMapy != null) {
                    if (castMapy.getNpccka() != null) {
                        this.npccka.addAll(castMapy.getNpccka());
                    }
                }
            }
        }
        while (true) {
            NPC npc = this.npccka.get(r.nextInt(this.npccka.size() - 1));
            if (npc instanceof Nepriatel) {
                ((Nepriatel)npc).setMaKluc(true);
                break;
            }
        }
    }
    
    /**
     * Metóda odstráni nepriateľa zo zoznamu všetkých nepriateľov ale aj zo zoznamu nepriateľov v aktivnej časti mapy
     */
    public void zrusNepriatela(NPC n) {
        this.aktivnaCast.zrusNepriatela(n);
        this.npccka.remove(n);
    }
    
    /**
     * Metóda zistí či môže zmeniť časť mapy a ak môže zmení časť mapy na tú čo sa nachádza "pod" aktívnou
     */
    public boolean posunDole() {
        this.riadok++;
        if (this.riadok < this.castiMapy.length && this.castiMapy[this.riadok][this.stlpec] != null) {
            this.zmenCast(this.riadok, this.stlpec);
            return true;
        } else {
            this.riadok--;
            return false;
        }
    }
    
    /**
     * Metóda zistí či môže zmeniť časť mapy a ak môže zmení časť mapy na tú čo sa nachádza "nad" aktívnou
     */
    public boolean posunHore() {
        this.riadok--;
        if (this.riadok > -1 && this.castiMapy[this.riadok][this.stlpec] != null) {
            this.zmenCast(this.riadok, this.stlpec);
            return true;
        } else {
            this.riadok++;
            return false;
        }
    }
    
    /**
     * Metóda zistí či môže zmeniť časť mapy a ak môže zmení časť mapy na tú čo sa nachádza "vpravo" od aktívnej
     */
    public boolean posunVpravo() {
        this.stlpec++;
        if (this.stlpec < this.castiMapy[this.riadok].length && this.castiMapy[this.riadok][this.stlpec] != null) {
            this.zmenCast(this.riadok, this.stlpec);
            return true;
        } else {
            this.stlpec--;
            return false;
        }
    }
    
    /**
     * Metóda zistí či môže zmeniť časť mapy a ak môže zmení časť mapy na tú čo sa nachádza "vľavo" od aktívnej
     */
    public boolean posunVlavo() {
        this.stlpec--;
        if (this.stlpec > -1 && this.castiMapy[this.riadok][this.stlpec] != null) {
            this.zmenCast(this.riadok, this.stlpec);
            return true;
        } else {
            this.stlpec++;
            return false;
        }
    }
}
