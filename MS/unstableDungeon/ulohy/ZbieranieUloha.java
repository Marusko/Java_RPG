package MS.unstableDungeon.ulohy;

/**
 * Trieda pre uchovávanie informácií o zbieracej úlohe
 *
 * @author Matúš Suský
 * @version r2022ver04.2
 */

public class ZbieranieUloha implements IUlohy {

    private final String nazov;
    private final String popis;
    private final String poziadavka;
    private boolean hotovy;
    private boolean aktivny;
    private boolean skryty;

    public ZbieranieUloha(String nazov, String popis, String poziadavka) {
        this.nazov = nazov;
        this.popis = popis;
        this.poziadavka = poziadavka;
        this.skryty = false;
    }

    @Override
    public String getNazov() {
        return this.nazov;
    }

    @Override
    public String getPopis() {
        return this.popis;
    }

    @Override
    public boolean getHotovy() {
        return this.hotovy;
    }

    @Override
    public void setHotovy(boolean hotovy) {
        this.hotovy = hotovy;
    }

    @Override
    public boolean getAktivny() {
        return this.aktivny;
    }

    @Override
    public void setAktivny(boolean aktivny) {
        this.aktivny = aktivny;
    }

    @Override
    public void setSkryty(boolean skryty) {
        this.skryty = skryty;
    }

    @Override
    public boolean getSkryty() {
        return this.skryty;
    }

    @Override
    public String getPoziadavka() {
        return this.poziadavka;
    }

    @Override
    public String getTypUlohy() {
        return "zber";
    }
}
