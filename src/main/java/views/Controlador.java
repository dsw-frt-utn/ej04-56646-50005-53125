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
    
    public static void registrarVehiculoElectrico(String patente, Marca marca, String modelo, int anio, double capacidadCarga,
                             Sucursal sucursal, double kwhBase){
        VehiculoElectrico ve = new VehiculoElectrico(patente, marca, modelo, anio, capacidadCarga, sucursal, kwhBase);
        Persistencia.agregarVehiculo(ve);
        
    }
    
    public static void registrarVehiculoCombustible(String patente, Marca marca, String modelo, int anio, double capacidadCarga,
                               Sucursal sucursal, double kilometrosPorLitro, double litrosExtra){
        VehiculoCombustible vc = new VehiculoCombustible(patente, marca, modelo, anio, capacidadCarga, sucursal, kilometrosPorLitro, litrosExtra);
        Persistencia.agregarVehiculo(vc);
        
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
}
