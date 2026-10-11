/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Jurbano
 */
public class CuentaAhorros extends Cuenta {
    
    public CuentaAhorros(String numero, String titular, int dia, int mes, int anio){
        super(numero, titular, dia, mes, anio);
    }
    
    public CuentaAhorros(String numero, String titular, int dia, int mes, int anio, double saldoInicial){
        super(numero, titular, dia, mes, anio, saldoInicial);
    }
    
    @Override
    public boolean retirar(double valor){
        if(valor > 0 && valor <= saldo){
            saldo -= valor;
            return true;
        }return false;
    }
    
    @Override
    public double getDisponible(){return saldo;}
    
    @Override
    public String getTipo(){return "Ahorros";}
    
    @Override
    public String imprimirDatos(){
        return "Cuenta Ahorros \n"+super.imprimirDatos();
    }
}
