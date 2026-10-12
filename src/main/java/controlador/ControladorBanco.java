/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

/**
 *
 * @author Daniel
 */
import modelo.*;
import vista.VistaBanco;

public class ControladorBanco {
    private static final int MAX = 50;
    private Cuenta[] cuentas = new Cuenta[MAX];
    private int cantidad = 0;
    private VistaBanco vista;
    private int proximoNumero = 1001;

    public ControladorBanco(VistaBanco vista) {
        this.vista = vista;
        this.vista.getBtnCrear().addActionListener(e -> crearCuenta());
        this.vista.getBtnLimpiar().addActionListener(e -> vista.limpiarFormulario());
        this.vista.getBtnDepositar().addActionListener(e -> depositar());
        this.vista.getBtnRetirar().addActionListener(e -> retirar());
        this.vista.getBtnSaldo().addActionListener(e -> consultarSaldo());
        this.vista.getBtnDatos().addActionListener(e -> consultarDatos());
        this.vista.setNumeroCuenta(String.valueOf(proximoNumero));
    }

    private void crearCuenta() {
        if (cantidad == MAX) {
            vista.mostrarMensaje("Se alcanzó el máximo de " + MAX + " cuentas.");
            return;
        }
         try {
            String tipo = vista.getTipo();
            String numero = String.valueOf(proximoNumero);   // número automático
            String titular = vista.getTitular().trim();
            if (titular.isEmpty()) {
                vista.mostrarMensaje("El titular es obligatorio.");
                return;
            }
            if (buscar(numero) >= 0) {
                vista.mostrarMensaje("Ya existe una cuenta con ese número.");
                return;
            }
            int dia = Integer.parseInt(vista.getDia().trim());
            int mes = Integer.parseInt(vista.getMes().trim());
            int anio = Integer.parseInt(vista.getAnio().trim());
            if (!Fecha.esValida(dia, mes, anio)) {
                vista.mostrarMensaje("La fecha de apertura no es válida.");
                return;
            }
            String textoSaldo = vista.getSaldoInicial().trim();
            boolean conSaldo = !textoSaldo.isEmpty();
            double saldo = conSaldo ? Double.parseDouble(textoSaldo) : 0;
            if (saldo < 0) {
                vista.mostrarMensaje("El saldo inicial no puede ser negativo.");
                return;
            }

            Cuenta nueva;   // polimorfismo: variable de la superclase
            if (tipo.equals("Ahorros")) {
                if (conSaldo) {
                    nueva = new CuentaAhorros(numero, titular, dia, mes, anio, saldo);
                } else {
                    nueva = new CuentaAhorros(numero, titular, dia, mes, anio);
                }
            } else {
                double cupo = Double.parseDouble(vista.getCupo().trim());
                if (cupo < 0) {
                    vista.mostrarMensaje("El cupo de sobregiro no puede ser negativo.");
                    return;
                }
                if (conSaldo) {
                    nueva = new CuentaCorriente(numero, titular, dia, mes, anio, saldo, cupo);
                } else {
                    nueva = new CuentaCorriente(numero, titular, dia, mes, anio, cupo);
                }
            }
            cuentas[cantidad] = nueva;
            cantidad++;
            proximoNumero++;
            vista.setNumeroCuenta(String.valueOf(proximoNumero));
            refrescar();
            vista.limpiarFormulario();
            vista.mostrarInfo("Cuenta creada:\n" + nueva.imprimirDatos());
        } catch (NumberFormatException ex) {
            vista.mostrarMensaje("Revise los campos numéricos.");
        }
    }

    private void depositar() {
        Cuenta c = cuentaSeleccionada();
        if (c == null) return;
        double valor = leerValor();
        if (valor <= 0) return;
        if (c.consignar(valor)) {
            actualizarTrasOperacion(c, "Consignación exitosa.");
        }
    }

    private void retirar() {
        Cuenta c = cuentaSeleccionada();
        if (c == null) return;
        double valor = leerValor();
        if (valor <= 0) return;
        if (c.retirar(valor)) {
            actualizarTrasOperacion(c, "Retiro exitoso.");
        } else {
            vista.mostrarMensaje("Retiro rechazado. El máximo que puede retirar es $"
                    + String.format("%.2f", c.getDisponible()) + ".");
        }
    }

    private void consultarSaldo() {
        Cuenta c = cuentaSeleccionada();
        if (c != null) {
            vista.mostrarInfo(c.imprimirDatos(true));
        }
    }

    private void consultarDatos() {
        Cuenta c = cuentaSeleccionada();
        if (c != null) {
            vista.mostrarInfo(c.imprimirDatos());
        }
    }

    // Auxiliares
    private Cuenta cuentaSeleccionada() {
        int fila = vista.getFilaSeleccionada();
        if (fila < 0 || fila >= cantidad) {
            vista.mostrarMensaje("Seleccione una cuenta en la tabla.");
            return null;
        }
        return cuentas[fila];
    }

    private double leerValor() {
        try {
            double valor = Double.parseDouble(vista.getValor().trim());
            if (valor <= 0) {
                vista.mostrarMensaje("El valor debe ser mayor que 0.");
                return -1;
            }
            return valor;
        } catch (NumberFormatException ex) {
            vista.mostrarMensaje("Ingrese un valor numérico válido.");
            return -1;
        }
    }

    private void actualizarTrasOperacion(Cuenta c, String mensaje) {
        int fila = vista.getFilaSeleccionada();
        refrescar();
        vista.seleccionarFila(fila);
        vista.mostrarInfo(mensaje + "\n" + c.imprimirDatos(true));
    }

    private int buscar(String numero) {
        for (int i = 0; i < cantidad; i++) {
            if (cuentas[i].getNumero().equals(numero)) {
                return i;
            }
        }
        return -1;
    }

    private void refrescar() {
        vista.cargarTabla(cuentas, cantidad);
    }
}
