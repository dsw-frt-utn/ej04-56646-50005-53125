package views;

import domain.Marca;

public class MarcaViewModel {
    private String marcaNombre;
    private String pais;
    
    public MarcaViewModel(Marca marca){
        if(marca == null) return;
        marcaNombre = marca.getNombre();
        pais = marca.getPais();
    }

    public String getMarcaNombre() {
        return marcaNombre;
    }

    public String getPais() {
        return pais;
    }
}
