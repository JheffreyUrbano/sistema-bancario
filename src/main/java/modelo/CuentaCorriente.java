/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Jurbano
 */
public class CuentaCorriente extends Cuenta {
    private double cupoSobregiro;
    
    // constructores para sobrecarga
    public CuentaCorriente(String numero, String titular, int dia, int mes, int anio, double cupoSobregiro) {
        super(numero, titular, dia, mes, anio);
        this.cupoSobregiro = cupoSobregiro;
    }

    public CuentaCorriente(String numero, String titular, int dia, int mes, int anio, double saldoInicial, double cupoSobregiro) {
        super(numero, titular, dia, mes, anio, saldoInicial);
        this.cupoSobregiro = cupoSobregiro;
    }
    
    public double getCupoSobregiro(){return cupoSobregiro;}
    
    @Override
    public boolean retirar(double valor){
        if(valor > 0 && valor <= saldo + cupoSobregiro){
            saldo -= valor; // si saldo=0, queda el cupo sobreGiro
            return true;
        } return false;
    }
    
    // getters en override
    @Override
    public double getDisponible(){return saldo + cupoSobregiro;}
    
    @Override
    public String getTipo(){return "Corriente";}
    
    // "toString" en override
    public String imprimirDatos(){
        return "Cuenta Ahorros\n"+super.imprimirDatos()
                +"\nCupo de Sobregiro: $"+formato(cupoSobregiro)    // formato: metodo para decimales en modelo.Cuenta
                +"Disponible para retiro: $"+formato(getDisponible());
    }
}
