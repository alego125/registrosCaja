/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

/**
 *
 * @author PC-HOGAR
 */
public class Registros {

    private String fecha, ingreso, egreso, observacion, detalle;
    int id;

    public Registros() {
    }   
    
    public Registros(int id, String fecha, String ingreso, String egreso, String observacion, String detalle) {
        this.id = id;
        this.fecha = fecha;
        this.ingreso = ingreso;
        this.egreso = egreso;
        this.observacion = observacion;
        this.detalle = detalle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }   
    
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getIngreso() {
        return ingreso;
    }

    public void setIngreso(String ingreso) {
        this.ingreso = ingreso;
    }

    public String getEgreso() {
        return egreso;
    }

    public void setEgreso(String egreso) {
        this.egreso = egreso;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    //Colocamos el metodo to string para que cuando llamemos a esta clase en las listas de elementos se meustren de la manera que se muestra en el metodo dependeindo si es un ingreso o un egreso
    @Override
    public String toString() {
        if(ingreso.equals("0")){
            return fecha + " Egreso: $" + egreso +  "   " + detalle;            
        }else{
            return fecha + " Ingreso: $" + ingreso +  "   " + detalle;
        }
    }
    
    
}
