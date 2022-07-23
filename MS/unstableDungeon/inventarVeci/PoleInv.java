package MS.unstableDungeon.inventarVeci;

import MS.unstableDungeon.prevzate.Obrazok;

/**
 * Objekt na uloženie a zobrazenie polí invetára. Taktiež môže zobrazovať veci úložené v poliach.
 * 
 * @author Matúš Suský 
 * @version r2021ver03.2
 */
public class PoleInv {
    private static final int STRANA_POLA = 20;
    private final Obrazok obrazok;
    private final int indexPola;
    private boolean plne;
    private Vec aktualnaVec;
    private final Vec[] veci;
    
    public PoleInv(int index) {
        this.indexPola = index;
        this.plne = false;
        this.veci = new Vec[4];
        this.nacitajVeci();
        this.obrazok = new Obrazok("Data/Tiles/Vybrane/Veci/poleInv.png");
        this.obrazok.zmenPolohu(5 + PoleInv.STRANA_POLA * indexPola, 5);
        this.obrazok.zmenVelkost(PoleInv.STRANA_POLA, PoleInv.STRANA_POLA);
        this.obrazok.zobraz();
    }
    
    public int getStrana() {
        return PoleInv.STRANA_POLA;
    }
    
    public int getIndex() {
        return this.indexPola;
    }
    
    public boolean getJePlne() {
        return this.plne;
    }

    /**
     * Metóda načíta všetky veci.
     */
    private void nacitajVeci() {
        for (int i = 0; i < this.veci.length; i++) {
            this.veci[i] = new Vec(i + 1, this);
        }
    }
    
    /**
     * Metóda zobrazí konkrétnu vec
     * @param indexVeci určuje aká vec sa má zobraziť
     */
    public void zobrazVec(int indexVeci) {
        this.veci[indexVeci - 1].zobraz();
        this.aktualnaVec = this.veci[indexVeci - 1];
        this.plne = true;
    }
    
    /**
     * Metóda skryje aktuálnu vec v poli
     */
    public void skryVec() {
        this.aktualnaVec.skry();
        this.plne = false;
    }
    
    /**
     * Pri zmene časti mapy treba znova nakresliť pole aby ho bolo vidno.
     */
    public void zobraz() {
        this.obrazok.zobraz();
        if (this.aktualnaVec != null) {
            this.aktualnaVec.zobraz();
        }
    }
    
    //Ovládanie veci
    /**
     * Metóda vráti typ aktuálnej veci nachádzajúcej sa v poli
     */
    public String getTypVeci() {
        if (this.aktualnaVec != null && this.plne) {
            return this.aktualnaVec.getTypVeci();
        } else {
            return null;
        }
    }
}
