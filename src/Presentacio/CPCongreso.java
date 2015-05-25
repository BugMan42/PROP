package Presentacio;

import Domini.ControladorCongreso;
import Domini.ControladorRelaciones;


public class CPCongreso {

    private PanelCongreso PC;
    private ControladorCongreso CC;
    private CPRelaciones CPR;

    public CPCongreso() {
        CC = new ControladorCongreso();
    }
    public ControladorCongreso obtCC() {
        return CC;
    }
    public CPRelaciones obtCPR() {
        return CPR;
    }
    public void modCC(ControladorCongreso aux) {
        CC = aux;
    }
    public void modCPR(CPRelaciones aux) {
        CPR = aux;
    }

    public PanelCongreso obtPanel() {
        if (PC == null) PC = new PanelCongreso(this);
        return PC;
    }
    public void sortByDni() {
        CC.sortByDni();
    }
    public void sortByName() {
        CC.sortByName();
    }
    public void sortBySurName() {
        CC.sortBySurName();
    }
    public void sortByAge() {
        CC.sortByAge();
    }
    public void sortByCity() {
        CC.sortByCity();
    }
    public void sortByState() {
        CC.sortByState();
    }
    public void sortByParty() {
        CC.sortByParty();
    }

}
