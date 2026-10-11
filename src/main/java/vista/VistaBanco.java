/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

/**
 *
 * @author Jurbano
 */

import modelo.Cuenta;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VistaBanco extends JFrame {
    private JComboBox<String> cmbTipo = new JComboBox<>(new String[]{"Ahorros", "Corriente"});
    private JLabel lblNumero = new JLabel("1001");
    private JTextField txtTitular = new JTextField(10);
    private JTextField txtSaldoInicial = new JTextField(10);
    private JTextField txtDia = new JTextField(10);
    private JTextField txtMes = new JTextField(10);
    private JTextField txtAnio = new JTextField(10);
    private JTextField txtCupo = new JTextField(10);
    private JTextField txtValor = new JTextField(10);
    private JTextArea txtInfo = new JTextArea(6, 40);

    private JButton btnCrear = new JButton("Crear cuenta");
    private JButton btnLimpiar = new JButton("Limpiar");
    private JButton btnDepositar = new JButton("Depositar");
    private JButton btnRetirar = new JButton("Retirar");
    private JButton btnSaldo = new JButton("Consultar saldo");
    private JButton btnDatos = new JButton("Consultar datos");

    private DefaultTableModel modelo = new DefaultTableModel(
            new String[]{"Número", "Tipo", "Titular", "Apertura", "Saldo"}, 0) {
        @Override
        public boolean isCellEditable(int fila, int columna) {
            return false;
        }
    };
    private JTable tabla = new JTable(modelo);

    public VistaBanco() {
        super("Sistema Bancario");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(8, 8));

        // Formulario de creación
        JPanel form = new JPanel(new GridLayout(4, 4, 6, 6));
        form.setBorder(BorderFactory.createTitledBorder("Nueva cuenta"));
        form.add(new JLabel("Tipo:"));                  form.add(cmbTipo);
        form.add(new JLabel("Nº de cuenta:"));          form.add(lblNumero);
        form.add(new JLabel("Titular:"));               form.add(txtTitular);
        form.add(new JLabel("Saldo inicial:"));         form.add(txtSaldoInicial);
        form.add(new JLabel("Día apertura:"));          form.add(txtDia);
        form.add(new JLabel("Mes apertura:"));          form.add(txtMes);
        form.add(new JLabel("Año apertura:"));          form.add(txtAnio);
        form.add(new JLabel("Cupo sobregiro:"));        form.add(txtCupo);

        txtCupo.setEnabled(false);   // solo aplica a cuenta corriente
        cmbTipo.addActionListener(e ->
                txtCupo.setEnabled("Corriente".equals(cmbTipo.getSelectedItem())));

        JPanel botonesCrear = new JPanel(new FlowLayout(FlowLayout.CENTER));
        botonesCrear.add(btnCrear);
        botonesCrear.add(btnLimpiar);

        JPanel norte = new JPanel(new BorderLayout());
        norte.add(form, BorderLayout.CENTER);
        norte.add(botonesCrear, BorderLayout.SOUTH);

        // Operaciones sobre la cuenta seleccionada
        JPanel operaciones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        operaciones.add(new JLabel("Valor:"));
        operaciones.add(txtValor);
        operaciones.add(btnDepositar);
        operaciones.add(btnRetirar);
        operaciones.add(btnSaldo);
        operaciones.add(btnDatos);

        txtInfo.setEditable(false);
        JPanel sur = new JPanel(new BorderLayout());
        sur.setBorder(BorderFactory.createTitledBorder("Operaciones sobre la cuenta seleccionada"));
        sur.add(operaciones, BorderLayout.NORTH);
        sur.add(new JScrollPane(txtInfo), BorderLayout.CENTER);

        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        add(norte, BorderLayout.NORTH);
        add(new JScrollPane(tabla), BorderLayout.CENTER);
        add(sur, BorderLayout.SOUTH);

        setSize(880, 700);
        setLocationRelativeTo(null);
    }

    public void cargarTabla(Cuenta[] lista, int cantidad) {
        modelo.setRowCount(0);
        for (int i = 0; i < cantidad; i++) {
            Cuenta c = lista[i];
            modelo.addRow(new Object[]{c.getNumero(), c.getTipo(), c.getTitular(),
                    c.getFechaApertura().toString(), String.format("%.2f", c.getSaldo())});
        }
    }

    public void limpiarFormulario() {
        cmbTipo.setSelectedIndex(0);
        txtTitular.setText("");
        txtSaldoInicial.setText("");
        txtDia.setText("");
        txtMes.setText("");
        txtAnio.setText("");
        txtCupo.setText("");
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void mostrarInfo(String texto) {
        txtInfo.setText(texto);
    }

    public void seleccionarFila(int fila) {
        if (fila >= 0 && fila < tabla.getRowCount()) {
            tabla.setRowSelectionInterval(fila, fila);
        }
    }

    public String getTipo() { return (String) cmbTipo.getSelectedItem(); }
    public void setNumeroCuenta(String numero) { lblNumero.setText(numero); }
    public String getTitular() { return txtTitular.getText(); }
    public String getSaldoInicial() { return txtSaldoInicial.getText(); }
    public String getDia() { return txtDia.getText(); }
    public String getMes() { return txtMes.getText(); }
    public String getAnio() { return txtAnio.getText(); }
    public String getCupo() { return txtCupo.getText(); }
    public String getValor() { return txtValor.getText(); }
    public int getFilaSeleccionada() { return tabla.getSelectedRow(); }

    public JButton getBtnCrear() { return btnCrear; }
    public JButton getBtnLimpiar() { return btnLimpiar; }
    public JButton getBtnDepositar() { return btnDepositar; }
    public JButton getBtnRetirar() { return btnRetirar; }
    public JButton getBtnSaldo() { return btnSaldo; }
    public JButton getBtnDatos() { return btnDatos; }
}