/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reservaticketsaereosv1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Luis Bedoya Jaime
 */
public class Ventanilla extends JFrame {
    private JButton btnReservar, btnCancelar, btnCambiar;
    private JTextField idCliente, asiento, nuevoAsiento;
    private JTextArea areaEstado;

    public Ventanilla() {
        //Configurar la ventana
        setTitle("Sistema de Reserva de Tickets Aéreos");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblIdCliente = new JLabel("ID Cliente:");
        lblIdCliente.setBounds(20, 20, 100, 25);
        add(lblIdCliente);

        idCliente = new JTextField();
        idCliente.setBounds(120, 20, 150, 25);
        add(idCliente);

        JLabel lblAsiento = new JLabel("N° Asiento:");
        lblAsiento.setBounds(20, 60, 100, 25);
        add(lblAsiento);

        asiento = new JTextField();
        asiento.setBounds(120, 60, 150, 25);
        add(asiento);

        JLabel lblNuevoAsiento = new JLabel("Nuevo N° Asiento:");
        lblNuevoAsiento.setBounds(20, 100, 120, 25);
        add(lblNuevoAsiento);

        nuevoAsiento = new JTextField();
        nuevoAsiento.setBounds(150,100,120,25);
        add(nuevoAsiento);

        btnReservar = new JButton("Reservar");
        btnReservar.setBounds(20, 140, 100, 25);
        add(btnReservar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(140, 140, 100, 25);
        add(btnCancelar);

        btnCambiar = new JButton("Cambiar");
        btnCambiar.setBounds(260, 140, 100, 25);
        add(btnCambiar);

        areaEstado = new JTextArea();
        areaEstado.setBounds(20, 180, 450, 150);
        areaEstado.setEditable(false);
        add(areaEstado);

        JScrollPane scroll = new JScrollPane(areaEstado);
        scroll.setBounds(20, 180, 450, 150);
        add(scroll);

        btnReservar.addActionListener(e -> new Thread(new ReservaTicketsAereos(idCliente.getText(), Integer.parseInt(asiento.getText()), "Reservar", areaEstado)).start());

        btnCancelar.addActionListener(e -> new Thread(new ReservaTicketsAereos(idCliente.getText(), Integer.parseInt(asiento.getText()), "Cancelar", areaEstado)).start());

        btnCambiar.addActionListener(e -> new Thread(new ReservaTicketsAereos(idCliente.getText(), Integer.parseInt(asiento.getText()), "Cambiar", areaEstado, Integer.parseInt(nuevoAsiento.getText()))).start());

        setVisible(true);
    }

    public static void main(String[] args) {
        new Ventanilla();
    }
    
}
