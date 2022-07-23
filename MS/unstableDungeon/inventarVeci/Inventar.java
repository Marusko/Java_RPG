package MS.unstableDungeon.inventarVeci;

import java.util.ArrayList;

/**
 * Objekt na spravovanie polí invetára a vecí v nich uložených.
 *
 * @author Matúš Suský
 * @version r2022ver03.2
 */
public class Inventar {
    
    private final PoleInv[] polia;
    private final ArrayList<String> veciVInv;

    public Inventar(int pocetPoli) {
        this.polia = new PoleInv[pocetPoli];
        this.veciVInv = new ArrayList<>();
        this.nacitajPolia();
    }
    
    /**
     * Metóda načíta a zobrazí vopred daný počet polí
     */
    private void nacitajPolia() {
        for (int i = 0; i < this.polia.length; i++) {
            this.polia[i] = new PoleInv(i);
        }
    }

    /**
     * Metóda zobrazí danú vec vo voľnom poli a pridá ju do zoznamu vecí v inventári
     *
     * @param typVeci určuje akú vec má zobraziť
     */
    public void ulozVec(int typVeci) {
        for (PoleInv pole : this.polia) {
            if (!pole.getJePlne()) {
                pole.zobrazVec(typVeci);
                this.veciVInv.add(pole.getTypVeci());
                return;
            }
        }
    }

    /**
     * Metóda skryje danú vec v poli a odobere ju zo zoznamu, ak je vec viackrát v inventári skryje a odobere vec, ktorú nájde ako prvú
     *
     * @param typVeci určuje akú vec treba odobrať
     */
    public void odoberVec(String typVeci) {
        for (PoleInv pole : this.polia) {
            if (pole.getJePlne() && typVeci.equals(pole.getTypVeci())) {
                pole.skryVec();
                this.veciVInv.remove(typVeci);
                return;
            }
        }
    }

    /**
     * Pri zmene časti mapy treba znova nakresliť inventár aby ho bolo vidno.
     */
    public void zobraz() {
        for (PoleInv poleInv : this.polia) {
            poleInv.zobraz();
        }
    }
    
    //Ovládanie pola
    /**
     * Vráti zoznam všetkých vecí v inventári
     */
    public ArrayList<String> getVeciVInv() {
        return this.veciVInv;
    }
}
