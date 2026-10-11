/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author Jurbano
 */
import vista.VistaBanco;

import javax.swing.SwingUtilities;

public class PruebaVista {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VistaBanco().setVisible(true));
    }
}