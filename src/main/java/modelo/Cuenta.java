/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Jurbano
 */

public abstract class Cuenta{   // abstract para en Ahorros o Corriente definir metodos
    private String numero;      //string ya que no vamos a procesar este numero (id)
    private String titular;
    private Fecha fechaApertura; // registra la creación de la cuenta
    protected double saldo;      // usamos protected para que lo usen las subclases
    
    // constructores para sobrecarga
    public Cuenta(String numero, String titular, int dia, int mes, int anio) {
        this(numero, titular, dia, mes, anio, 0);
    }

    public Cuenta(String numero, String titular, int dia, int mes, int anio, double saldoInicial) {
        this.numero = numero;
        this.titular = titular;
        this.fechaApertura = new Fecha(dia, mes, anio);
        this.saldo = saldoInicial;
    }
    
    public boolean consignar(double valor){
        if(valor > 0){
            saldo += valor;
            return true;
        } return false;
    }
    
    // metodos que se heredan en Ahorros y Corriente y se define el codigo alla
    public abstract boolean retirar(double valor);
    public abstract double getDisponible();
    public abstract String getTipo();
    
    // getters
    public String getNumero() { return numero; }
    public String getTitular() { return titular; }
    public Fecha getFechaApertura() { return fechaApertura; }
    public double getSaldo() { return saldo; }
    
    protected static String formato(double valor) {
        return String.format("%.2f", valor);
    }
    
    // overloads
        // Impresión: todos los datos
    public String imprimirDatos() {
        return "Número: " + numero
                + "\nTitular: " + titular
                + "\nFecha de apertura: " + fechaApertura
                + "\nSaldo: $" + formato(saldo);
    }

        // Impresión: solo saldos o todos los datos
    public String imprimirDatos(boolean soloSaldo) {
        if (soloSaldo) {
            return "Saldo de la cuenta " + numero + ": $" + formato(saldo);
        }
        return imprimirDatos();
    }
}
