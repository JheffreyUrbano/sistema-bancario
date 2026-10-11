/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Jurbano
 */

import modelo.Fecha;

public class Cuenta {
    private int numero;
    private String titular;
    private Fecha fechaApertura; // registra la creación de la cuenta
    protected double saldo;      // usamos protected para que lo usen las subclases
}
