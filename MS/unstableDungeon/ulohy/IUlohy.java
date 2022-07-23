package MS.unstableDungeon.ulohy;

/**
 * Interface pre úlohy
 *
 * @author Matúš Suský
 * @version r2022ver03.2
 */

public interface IUlohy {

    /**
     * Metóda vráti názov úlohy
     */
    String getNazov();
    /**
     * Metóda vráti popis úlohy
     */
    String getPopis();
    /**
     * Metóda vráti či je úloha spravená
     */
    boolean getHotovy();
    /**
     * Metóda nastaví premennú na potrebnú hodnotu
     */
    void setHotovy(boolean hotovy);
    /**
     * Metóda vráti či je úloha aktívna
     */
    boolean getAktivny();
    /**
     * Metóda nastaví premennú na potrebnú hodnotu
     */
    void setAktivny(boolean aktitvny);
    /**
     * Metóda nastaví premennú na potrebnú hodnotu
     */
    void setSkryty(boolean skryty);
    /**
     * Metóda vráti boolean či je úloha skrytá
     */
    boolean getSkryty();

    /**
     * Metóda vráti požiadavku na splnenie úlohy
     */
    String getPoziadavka();

    /**
     * Metóda vráti typ úlohy
     */
    String getTypUlohy();

}
