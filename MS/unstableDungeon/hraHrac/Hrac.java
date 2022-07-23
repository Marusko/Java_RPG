package MS.unstableDungeon.hraHrac;

import MS.unstableDungeon.inventarVeci.Inventar;
import MS.unstableDungeon.prevzate.Obrazok;

import java.util.ArrayList;
/**
 * Objekt na reprezentáciu a pohyb hráča. Tiež spravuje objekt Inventár. 
 * 
 * @author Matúš Suský 
 * @version r2022ver04.2
 */
public class Hrac {
    private static final int STRANA_OBRAZKA = 20;
    private final Obrazok obrazok;
    private final Obrazok zivot;
    private int pocetZivota;
    private int poskodenie;
    private final Inventar inventar;
    
    public Hrac() {
        this.obrazok = new Obrazok("Data/Tiles/Vybrane/Postavicky/Hrac/hrac.png");
        this.obrazok.zmenPolohu(35, 75);
        this.obrazok.zmenVelkost(Hrac.STRANA_OBRAZKA, Hrac.STRANA_OBRAZKA);
        this.obrazok.zobraz();
        this.zivot = new Obrazok("Data/Tiles/Vybrane/Postavicky/Hrac/Zivot/zivot2,2.png");
        this.zivot.zmenPolohu(265, 5);
        this.zivot.zmenVelkost(Hrac.STRANA_OBRAZKA, Hrac.STRANA_OBRAZKA);
        this.zivot.zobraz();
        this.pocetZivota = 2;
        this.poskodenie = 1;
        this.inventar = new Inventar(2);
    }
    
    public int getZivot() {
        return this.pocetZivota;
    }
    
    /**
     * Metóda odobrie hráčovi život a na základe toho koľko života mu ostalo zobrazí obrázok
     * @param odobranyZivot určuje koľko života sa má odobrať
     */
    public void odoberZivot(int odobranyZivot) {
        this.pocetZivota -= odobranyZivot;
        switch (this.pocetZivota) {
            case 0:
                this.zivot.zmenObrazok("Data/Tiles/Vybrane/Postavicky/Hrac/Zivot/zivot0,2.png");
                break;
            case 1:
                this.zivot.zmenObrazok("Data/Tiles/Vybrane/Postavicky/Hrac/Zivot/zivot1,2.png");
                break;
            case 2:
                this.zivot.zmenObrazok("Data/Tiles/Vybrane/Postavicky/Hrac/Zivot/zivot2,2.png");
                break;
        }
    }
    
    public int getPoskodenie() {
        return this.poskodenie;
    }
    
    /**
     * Metóda nastaví hráčovi požadované poškodenie(útočnú silu)
     * @param poskodenie určuje akú útočnú silu má hráč mať
     */
    public void setPoskodenie(int poskodenie) {
        this.poskodenie = poskodenie;
    }    
    
    public int getX() {
        return this.obrazok.getPolohaX();
    }
    
    public int getY() {
        return this.obrazok.getPolohaY();
    }
    
    /**
     * Metóda nastaví hráčovi požadovanú polohu X
     * @param x určuje polohu kam sa má hráč premiestniť
     */
    public void setPoziciaX(int x) {
        this.obrazok.posunVodorovne(x - this.obrazok.getPolohaX());
    }
    
    /**
     * Metóda nastaví hráčovi požadovanú polohu Y
     * @param y určuje polohu kam sa má hráč premiestniť
     */
    public void setPoziciaY(int y) {
        this.obrazok.posunZvisle(y - this.obrazok.getPolohaY());
    }
    
    /**
     * Metóda posunie hráča dole a zistí či je stále v medziach okna, ak nie posunie ho naspäť
     */
    public void posunDole() {
        this.obrazok.skry();
        this.obrazok.posunDole();
        if (!(this.obrazok.getPolohaY() < 315)) {
            this.obrazok.posunHore();
        }
        this.obrazok.zobraz();
    }
    
    /**
     * Metóda posunie hráča hore a zistí či je stále v medziach okna, ak nie posunie ho naspäť
     */
    public void posunHore() {
        this.obrazok.skry();
        this.obrazok.posunHore();
        if (!(this.obrazok.getPolohaY() > -25)) {
            this.obrazok.posunDole();
        }
        this.obrazok.zobraz();
    }
    
    /**
     * Metóda posunie hráča vpravo a zistí či je stále v medziach okna, ak nie posunie ho naspäť
     */
    public void posunVpravo() {
        this.obrazok.skry();
        this.obrazok.posunVpravo();
        if (!(this.obrazok.getPolohaX() < 315)) {
            this.obrazok.posunVlavo();
        }
        this.obrazok.zobraz();
    }
    
    /**
     * Metóda posunie hráča vľavo a zistí či je stále v medziach okna, ak nie posunie ho naspäť
     */
    public void posunVlavo() {
        this.obrazok.skry();
        this.obrazok.posunVlavo();
        if (!(this.obrazok.getPolohaX() > -25)) {
            this.obrazok.posunVpravo();
        }
        this.obrazok.zobraz();
    }
    
    /**
     * Pri zmene časti mapy treba znova nakresliť hráča, život a inventár aby ho bolo vidno.
     */
    public void zobraz() {
        this.obrazok.zobraz();
        this.zivot.zobraz();
        this.inventar.zobraz();
    }
    
    //Ovládanie inventára
    /**
     * Metóda pošle správu triede MS.unstableDungeon.inventarVeci.Inventar, že má uložiť vec
     * @param typVeci určuje akú vec má uložiť
     */
    public void ulozVec(int typVeci) {
        this.inventar.ulozVec(typVeci);
    }
    
    /**
     * Metóda pošle správu triede MS.unstableDungeon.inventarVeci.Inventar, že má odobrať vec
     * @param typVeci určuje akú vec má odobrať
     */
    public void odoberVec(String typVeci) {
        this.inventar.odoberVec(typVeci);
    }
    
    /**
     * Vráti zoznam všetkých vecí v inventári
     */
    public ArrayList<String> getVeciVInv() {
        return this.inventar.getVeciVInv();
    }
    
}
