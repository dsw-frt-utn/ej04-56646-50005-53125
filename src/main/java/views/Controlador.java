package views;

import data.Persistencia;
import domain.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

public class Controlador {
    
    public static ArrayList<VehiculoViewModel> getVehiculos(){
        ArrayList<VehiculoViewModel> vehiculos = new ArrayList<>();
        for(Vehiculo vehiculo : Persistencia.getVehiculos()) {
            vehiculos.add(new VehiculoViewModel(vehiculo));
        }
        return vehiculos;
    }
    
    public static ArrayList<MarcaViewModel> getMarcas(){
        ArrayList<MarcaViewModel> marcas = new ArrayList<>();
        for(Marca marca : Persistencia.getMarcas()){
            marcas.add(new MarcaViewModel(marca));
        }
        return marcas;
    }
    
    public static MarcaViewModel getMarca(String marca){
        return new MarcaViewModel(Persistencia.getMarca(marca));
    }
    
    public static ArrayList<SucursalViewModel> getSucursales(){
        ArrayList<SucursalViewModel> sucursales = new ArrayList<>();
        for(Sucursal sucursal : Persistencia.getSucursales()){
            sucursales.add(new SucursalViewModel(sucursal));
        }
        return sucursales;
    }
    
    public static boolean registrarVehiculoElectrico(
            String marca, String modelo, String anio,
            String capacidad, String patente, String sucursal,
            String kwh ){
        try{
            int anioNum = Integer.parseInt(anio);
            double capacidadNum = Double.parseDouble(capacidad);
            double kwhNum = Double.parseDouble(kwh);
            Marca marcaSel = Persistencia.getMarca(marca);
            Sucursal sucursalSel = Persistencia.getSucursal(sucursal);
            
            return Persistencia.agregarVehiculo(
                    new VehiculoElectrico(patente, marcaSel, modelo, anioNum,
                            capacidadNum, sucursalSel, kwhNum));
        }catch(Exception e){
            IngresarVehiculoView.MostrarError("Error de Datos. Revise el formulario ingresado e inténtelo nuevamente.");
        }
        return false;
    }
    
    public static boolean registrarVehiculoCombustible(
            String marca, String modelo, String anio,
            String capacidad, String patente, String sucursal,
            String kmPorLitro, String litrosExtra){
        try{
            int anioNum = Integer.parseInt(anio);
            double capacidadNum = Double.parseDouble(capacidad);
            double kmPorLitroNum = Double.parseDouble(kmPorLitro);
            double litrosExtraNum = Double.parseDouble(litrosExtra);
            Marca marcaSel = Persistencia.getMarca(marca);
            Sucursal sucursalSel = Persistencia.getSucursal(sucursal);
            
            return Persistencia.agregarVehiculo(
                new VehiculoCombustible(patente,marcaSel,modelo,anioNum,
                        capacidadNum,sucursalSel,kmPorLitroNum,litrosExtraNum));
        } catch (Exception e){
            IngresarVehiculoView.MostrarError("Error de Datos. Revise el formulario ingresado e inténtelo nuevamente.");
        }
        return false;
    }
    
    public static double[] calcularConsumos(Map<String, Double> vehiculos){
        double consumoElectricos = 0;
        double consumoCombustible= 0;
        for(Map.Entry<String, Double> entry : vehiculos.entrySet()){
           double consumo = 0;
           Optional<Vehiculo> vehiculo = Persistencia.getVehiculo(entry.getKey());
           if(vehiculo.isPresent()){
               consumo = vehiculo.get().calcularConsumo(entry.getValue());
               consumoElectricos += vehiculo.get().esDe(VehiculoTipo.ELECTRICO) ? consumo : 0;
               consumoCombustible += vehiculo.get().esDe(VehiculoTipo.COMBUSTIBLE) ? consumo : 0;
           }
        }
        return new double[] {consumoElectricos, consumoCombustible};
    }
    
    public static void NavegarMenuPrincipal(){
        MenuPrincipalView vistaMenuPrincipal = new MenuPrincipalView();
    }
    
    public static void NavegarIngresarVehiculo(){
        IngresarVehiculoView vistaIngresarVehiculo = new IngresarVehiculoView();
    }
    
    public static void NavegarListarVehiculos(){
        ListarVehiculosView vistaListarVehiculos = new ListarVehiculosView();
    }
    
    public static void Salir(){
        System.exit(0);
    }
}
