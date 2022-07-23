package MS.unstableDungeon.mapa;

import MS.unstableDungeon.prevzate.Obrazok;

/**
 * Objekt na uloženie a zobrazenie jednej časti protredia.
 * 
 * @author Matúš Suský 
 * @version r2022ver03.2
 */

public class CastProstredia {
    private static final int STRANA_OBRAZKA = 20;
    private final Obrazok obrazok;
    private boolean jeStena;
    
    
    /**
     * Pri vytvorení sa nastaví, aký obrázok sa má zobraziť a kde.
     */
    public CastProstredia(int typ, int posX, int posY) {
        this.jeStena = false;
        this.obrazok = new Obrazok(this.urciCast(typ));
        this.obrazok.zmenPolohu(posX - 5, posY - 5);
        this.obrazok.zmenVelkost(CastProstredia.STRANA_OBRAZKA, CastProstredia.STRANA_OBRAZKA);
        this.obrazok.skry();
    }
    
    public void zobraz() {
        this.obrazok.zobraz();
    }
    
    public void skry() {
        this.obrazok.skry();
    }
    
    /**
     * Vráti hodnotu či konkrétne políčko je alebo nie je stena
     */
    public boolean getJeStena() {
        return this.jeStena;
    }
    
    /**
     * Metóda určujúca aké políčko sa má zobraziť
     * @param typ určuje, ktoré políčko sa má zobraziť
     */
    private String urciCast(int typ) {
        switch (typ) {
            case 1:this.jeStena = true;
                    return "Data/Tiles/Vybrane/Prostredie/Steny/L_D_roh.png";
            case 2:this.jeStena = true;
                    return "Data/Tiles/Vybrane/Prostredie/Steny/L_S_kraj.png";
            case 3:this.jeStena = true;
                    return "Data/Tiles/Vybrane/Prostredie/Steny/L_H_roh.png";
            case 4:this.jeStena = true;
                    return "Data/Tiles/Vybrane/Prostredie/Steny/R_D_roh.png";
            case 5:this.jeStena = true;
                    return "Data/Tiles/Vybrane/Prostredie/Steny/R_S_kraj.png";
            case 6:this.jeStena = true;
                    return "Data/Tiles/Vybrane/Prostredie/Steny/R_H_roh.png";
            case 7:this.jeStena = true;
                    return "Data/Tiles/Vybrane/Prostredie/Steny/S_H_D_kraj.png";
            case 8:this.jeStena = false;
                    return "Data/Tiles/Vybrane/Prostredie/Steny/S_S_stred.png";
            case 9:this.jeStena = false;
                    return "Data/Tiles/Vybrane/Prostredie/Steny/S_D_dvere.png";
            case 10:this.jeStena = false;
                    return "Data/Tiles/Vybrane/Prostredie/Steny/S_H_dvere.png";
            case 11:this.jeStena = false;
                    return "Data/Tiles/Vybrane/Prostredie/Priroda/palma.png";
            case 12:this.jeStena = false;
                    return "Data/Tiles/Vybrane/Prostredie/Priroda/strk.png";
            case 13:this.jeStena = false;
                    return "Data/Tiles/Vybrane/Prostredie/Priroda/trava.png";
            case 14:this.jeStena = false;
                    return "Data/Tiles/Vybrane/Prostredie/Priroda/ihlicnan.png";
            case 15:this.jeStena = false;
                    return "Data/Tiles/Vybrane/Prostredie/Priroda/listnaty.png";
            case 16:this.jeStena = false;
                    return "Data/Tiles/Vybrane/Prostredie/Vnutro/stol.png";
            case 17:this.jeStena = false;
                    return "Data/Tiles/Vybrane/Prostredie/Vnutro/stolicka.png";
            case 18:this.jeStena = false;
                    return "Data/Tiles/Vybrane/Prostredie/Vnutro/postel.png";
            case 19:this.jeStena = false;
                    return "Data/Tiles/Vybrane/Prostredie/Vnutro/truhlica.png";
            case 20:this.jeStena = false;
                    return "Data/Tiles/Vybrane/Prostredie/Vonkajsok/hrob_1.png";
            case 21:this.jeStena = false;
                    return "Data/Tiles/Vybrane/Prostredie/Vonkajsok/hrob_2.png";
            case 22:this.jeStena = false;
                    return "Data/Tiles/Vybrane/Prostredie/Vonkajsok/ohnisko.png";
            case 23:this.jeStena = false;
                    return "Data/Tiles/Vybrane/Prostredie/Vonkajsok/posta.png";
            case 24:this.jeStena = false;
                    return "Data/Tiles/Vybrane/Prostredie/Vonkajsok/tabula.png";
            case 25:this.jeStena = false;
                    return "Data/Tiles/Vybrane/Prostredie/Vonkajsok/vlajka.png";
            case 26:this.jeStena = true;
                    return "Data/Tiles/Vybrane/Prostredie/Voda/L_D_roh.png";
            case 27:this.jeStena = true;
                    return "Data/Tiles/Vybrane/Prostredie/Voda/L_S_kraj.png";
            case 28:this.jeStena = true;
                    return "Data/Tiles/Vybrane/Prostredie/Voda/L_H_roh.png";
            case 29:this.jeStena = true;
                    return "Data/Tiles/Vybrane/Prostredie/Voda/R_D_roh.png";
            case 30:this.jeStena = true;
                    return "Data/Tiles/Vybrane/Prostredie/Voda/R_S_kraj.png";
            case 31:this.jeStena = true;
                    return "Data/Tiles/Vybrane/Prostredie/Voda/R_H_roh.png";
            case 32:this.jeStena = true;
                    return "Data/Tiles/Vybrane/Prostredie/Voda/S_D_kraj.png";
            case 33:this.jeStena = true;
                    return "Data/Tiles/Vybrane/Prostredie/Voda/S_S_stred.png";
            case 34:this.jeStena = true;
                    return "Data/Tiles/Vybrane/Prostredie/Voda/S_H_kraj.png";
            default: return null;
        }
    }
}
