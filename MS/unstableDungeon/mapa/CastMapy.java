package MS.unstableDungeon.mapa;

import MS.unstableDungeon.npc.Boss;
import MS.unstableDungeon.npc.Nepriatel;
import MS.unstableDungeon.npc.NPC;
import MS.unstableDungeon.npc.Obchodnik;
import MS.unstableDungeon.npc.Tulak;
import MS.unstableDungeon.npc.Zadavatel;

import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Zoznam prostredí v takom poradí aby vytvorili mapu. Sú rozdelené na 9 častí, medzi ktorými sa prepína
 * 
 * @author Matúš Suský 
 * @version r2022ver04.2
 */

public class CastMapy {
    private final ArrayList<CastProstredia> castiProstredia;
    private final int[][] typyPolicka;
    private final ArrayList<NPC> npccka;
    private final int indexCasti;
    
    public CastMapy(int cast) {
        this.indexCasti = cast;
        this.castiProstredia = new ArrayList<>();
        this.typyPolicka = new int[15][15];
        this.npccka = new ArrayList<>();
    }
    
    /**
     * Metóda na základe infromácií zo súboru načíta a skryje všetky políčka na časti mapy
     */
    public void nastavCast() throws IOException {    
        int strana = 20;  //Musi mat stranu priamo z castProstredia
        int stlpec = 0;
        int riadok = 0;
        
        File subor = new File("Data/CastiMapy/cast" + this.indexCasti + ".txt");
        Scanner citac = new Scanner(subor);
        
        while (citac.hasNextLine()) {
            int typ = citac.nextInt();
            if (typ == 88) {
                CastProstredia castNepriatela = new CastProstredia(8, strana * stlpec, strana * riadok);
                this.castiProstredia.add(castNepriatela);
                Nepriatel nepriatel = new Nepriatel(strana * stlpec, strana * riadok);
                this.npccka.add(nepriatel);
                stlpec++;
            } else if (typ == 89) {
                CastProstredia castBoss = new CastProstredia(8, strana * stlpec, strana * riadok);
                this.castiProstredia.add(castBoss);
                Boss boss = new Boss(strana * stlpec, strana * riadok);
                this.npccka.add(boss);
                stlpec++;
            }  else if (typ == 90) {
                CastProstredia castTulaka = new CastProstredia(8, strana * stlpec, strana * riadok);
                this.castiProstredia.add(castTulaka);
                Tulak tulak = new Tulak(strana * stlpec, strana * riadok);
                this.npccka.add(tulak);
                stlpec++;
            } else if (typ == 91) {
                CastProstredia castZadavatela = new CastProstredia(8, strana * stlpec, strana * riadok);
                this.castiProstredia.add(castZadavatela);
                Zadavatel zadavatel = new Zadavatel(strana * stlpec, strana * riadok);
                this.npccka.add(zadavatel);
                stlpec++;
            } else if (typ == 92) {
                CastProstredia castObchodnika = new CastProstredia(8, strana * stlpec, strana * riadok);
                this.castiProstredia.add(castObchodnika);
                Obchodnik obchodnik = new Obchodnik(strana * stlpec, strana * riadok);
                this.npccka.add(obchodnik);
                stlpec++;
            } else {
                CastProstredia cast = new CastProstredia(typ, strana * stlpec, strana * riadok);
                this.castiProstredia.add(cast);
                stlpec++;
            }
            if (stlpec >= 15) {
                riadok++;
                stlpec = 0;
            }
        }
        
        citac.close();
    }
    
    /**
     * Metóda zobrazí časť mapy a ak sú tam nepriatelia tak aj tých
     */
    public void zobrazenieCasti() {
        for (CastProstredia prostredie : this.castiProstredia) {
            prostredie.zobraz();
        }
        
        if (!this.npccka.isEmpty()) {
            for (NPC npc : this.npccka) {
                npc.zobraz();
            }
        }
    }
    
    /**
     * Metóda skyje časť mapy a ak sú tam aj nepriatelia tak aj tých
     */
    public void skrytieCasti() {
        for (CastProstredia prostredie : this.castiProstredia) {
            prostredie.skry();
        }
        
        if (!this.npccka.isEmpty()) {
            for (NPC npc : this.npccka) {
                npc.skry();
            }
        }
    }
    
    /**
     * Metóda vráti dvojrozmerné pole, kde jednotlivé polia reprezentujú políčka na mape a hodnota polí reprezentuje či je to stena, NPC alebo voľné priestranstvo
     */
    public int[][] getTypyPolicok() {
        int riadok = 0;
        int stlpec = 0;
        for (CastProstredia prostredie : this.castiProstredia) {
            if (prostredie.getJeStena()) {
                this.typyPolicka[riadok][stlpec] = 1;
            } else {
                this.typyPolicka[riadok][stlpec] = 0;
            }
            stlpec++;
            if (stlpec >= 15) {
                riadok++;
                stlpec = 0;
            }
        }
        if (!this.npccka.isEmpty()) {
            for (NPC npc : this.npccka) {
                this.typyPolicka[(npc.getY()) / 20][(npc.getX()) / 20] = 2;
                stlpec++;
                if (stlpec >= 15) {
                    riadok++;
                    stlpec = 0;
                }
            } 
        }
        return this.typyPolicka;
    }
    
    /**
     * Metóda vráti zoznam všetky NPC na mape, ak nie sú tak vráti null
     */
    public ArrayList<NPC> getNpccka() {
        if (!this.npccka.isEmpty()) {
            return this.npccka;
        } else {
            return null;
        }
    }
    
    /**
     * Metóda vráti konkrétne NPC
     * @param x určuje x-ovú pozíciu
     * @param y určuje y-ovú pozíciu
     */
    public NPC getNPC(int x, int y) {
        NPC vybrany = null;
        if (!this.npccka.isEmpty()) {
            for (NPC npc : this.npccka) {
                if (x == npc.getX() && y == npc.getY()) {
                    vybrany = npc;
                }
            }
        }
        return vybrany;
    }
    
    /**
     * Metóda odstráni konkrétneho nepriateľa zo zoznamu keď bol zabitý
     */
    public void zrusNepriatela(NPC n) {
        if (!this.npccka.isEmpty()) {
            this.npccka.remove(n);
        }
    }
    
    public int getIndex() {
        return this.indexCasti;
    }
}
