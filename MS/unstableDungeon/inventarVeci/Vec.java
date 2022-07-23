package MS.unstableDungeon.inventarVeci;

import MS.unstableDungeon.prevzate.Obrazok;

/**
 * Objekt na uloženie a zobrazenie jednej veci, ktorú môže hráč získať.
 * 
 * @author Matúš Suský 
 * @version r2021ver03.2
 */
public class Vec {
    private static final int STRANA_OBRAZKA = 14;
    private final Obrazok obrazok;
    private String typVeci;

    public Vec(int typ, PoleInv pole) {
        this.typVeci = null;
        int posX = 12 + pole.getIndex() * pole.getStrana();
        this.obrazok = new Obrazok(this.urciVec(typ));
        int posY = 8;
        this.obrazok.zmenPolohu(posX - 5, posY);
        this.obrazok.zmenVelkost(Vec.STRANA_OBRAZKA, Vec.STRANA_OBRAZKA);
        this.obrazok.skry();
    }
    
    public void zobraz() {
        this.obrazok.zobraz();
    }
    
    public void skry() {
        this.obrazok.skry();
    }
    
    public String getTypVeci() {
        return this.typVeci;
    }    
    
    /**
     * Metóda určujúca aká vec sa má zobraziť
     * @param typ určuje, ktorá vec sa má zobraziť
     */
    private String urciVec(int typ) {
        switch (typ) {
            case 1:this.typVeci = "mec";
                    return "Data/Tiles/Vybrane/Veci/mec.png";
            case 2:this.typVeci = "kluc";
                    return "Data/Tiles/Vybrane/Veci/kluc.png";
            case 3:this.typVeci = "lod";
                    return "Data/Tiles/Vybrane/Veci/mala_lod.png";
            case 4:this.typVeci = "sekera";
                    return "Data/Tiles/Vybrane/Veci/sekera.png";
            default: return null;
        }
    }
}
