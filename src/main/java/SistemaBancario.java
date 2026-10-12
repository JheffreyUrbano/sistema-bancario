/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

/**
 *
 * @author Daniel
 */
import controlador.ControladorBanco;
import vista.VistaBanco;

import javax.swing.SwingUtilities;

public class SistemaBancario {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VistaBanco vista = new VistaBanco();
            new ControladorBanco(vista);
            vista.setVisible(true);
        });
    }
}