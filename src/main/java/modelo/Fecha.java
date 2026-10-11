/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Jurbano
 */
public class Fecha {
    private int dia;
    private int mes;
    private int anio;

    public Fecha(int dia, int mes, int anio) {
        if (!esValida(dia, mes, anio)){                         //evaluamos fecha en constructor
            throw new IllegalArgumentException("Fecha invalida");
        }
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
    }
    
    public static boolean esValida(int dia, int mes, int anio){
        if (anio < 2024 || anio >2100) return false; // banco se creo en 2024 cuando entré a la U
        if (mes <1 || mes > 12) return false;
        return dia >=1 && dia <= diasDelMes(mes,anio);
    }
    
    public static int diasDelMes(int mes, int anio){
        switch (mes){
            case 2: return esBisiesto(anio) ? 29:28;
            case 4: case 6: case 9: case 11: return 30;
            default: return 31;
        }
    }
    
    public static boolean esBisiesto(int anio){
        return (anio % 4 == 0 && anio % 100 != 0 || anio % 400 == 0);
    }
    
    
    // getters
    public int getDia() {
        return dia;
    }

    public int getMes() {
        return mes;
    }

    public int getAnio() {
        return anio;
    }
    
    @Override
    public String toString(){
        return String.format("%02d/%02d/%04d", dia, mes, anio);
    }
}
