package views;

import domain.Sucursal;

public class SucursalViewModel {
    private String codigo;
    private String direccion;
    private String ciudad;
    private String responsable;
    
    public SucursalViewModel(Sucursal sucursal){
        if(sucursal == null) return;
        codigo = sucursal.getCodigo();
        direccion = sucursal.getDireccion();
        ciudad = sucursal.getCiudad();
        responsable = sucursal.getResponsable().getNombre();
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getResponsable() {
        return responsable;
    }
}