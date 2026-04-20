package data;

import domain.*;
import java.util.ArrayList;
import java.util.Optional;

public class Persistencia {
    private static ArrayList<Vehiculo> vehiculos = new ArrayList<>();
    private static ArrayList<Responsable> responsables = new ArrayList<>();
    private static ArrayList<Sucursal> sucursales = new ArrayList<>();
    private static ArrayList<Marca> marcas = new ArrayList<>();
    
    private static void inicializarResponsables(){
        Responsable r1 = new Responsable("Carlos Gómez", "25444111", "3815551111");
        Responsable r2 = new Responsable("Laura Pérez", "30111222", "3815552222");
        responsables.add(r1);
        responsables.add(r2);
    }
    
    private static void inicializarSucursales(){
        Sucursal s1 = new Sucursal("SUC01", "Av. Belgrano 1200", "Tucumán", responsables.get(0));
        Sucursal s2 = new Sucursal("SUC02", "San Martín 450", "Yerba Buena", responsables.get(1));
        
        sucursales.add(s1);
        sucursales.add(s2);
    }
    
    private static void inicializarMarcas(){
        Marca m1 = new Marca("BMW","Alemania");
        Marca m2 = new Marca("Ford","Estados Unidos");
        Marca m3 = new Marca("Honda","Japón");
        Marca m4 = new Marca("Renault","Francia");
        
        marcas.add(m1);
        marcas.add(m2);
        marcas.add(m3);
        marcas.add(m4);
    }
    
    public static boolean agregarVehiculo(Vehiculo vehiculo){
        try{
            vehiculos.add(vehiculo);
        } catch (Exception e){
            return false;
        }
        return true;
    }
    
    public static ArrayList<Vehiculo> getVehiculos(){
        return vehiculos;
    }
    
    public static ArrayList<Marca> getMarcas(){
        return marcas;
    }
    
    public static Marca getMarca(String marca){
        return marcas.stream()
                 .filter(m -> m.getNombre().equalsIgnoreCase(marca))
                 .findFirst()
                 .orElse(null);
    }
    
    public static ArrayList<Sucursal> getSucursales(){
        return sucursales;
    }
    
    public static Sucursal getSucursal(String sucursal){
        return sucursales.stream()
                .filter(s -> s.getCodigo().equalsIgnoreCase(sucursal))
                .findFirst()
                .orElse(null);
    }
    
    public static Optional<Vehiculo> getVehiculo(String patente){
        return vehiculos.stream()
                .filter(v -> v.getPatente().equals(patente))
                .findFirst();
    }
    
    public static void inicializar(){
        inicializarResponsables();
        inicializarSucursales();
        inicializarMarcas();
    }
}
