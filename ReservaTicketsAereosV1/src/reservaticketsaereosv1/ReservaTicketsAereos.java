/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reservaticketsaereosv1;

import javax.swing.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Luis Bedoya Jaime
 */
public class ReservaTicketsAereos implements Runnable {
    private static final boolean[] asientos = new boolean[30];
    private static final ReentrantLock lock = new ReentrantLock();

    private String idCliente;
    private int numeroAsiento;
    private String tipoOperacion;
    private JTextArea areaEstado;
    private int nuevoNumeroAsiento;

    //Constructor para reservar/cancelar asiento
    public ReservaTicketsAereos(String idCliente, int numeroAsiento, String tipoOperacion, JTextArea areaEstado) {
        this.idCliente = idCliente;
        this.numeroAsiento = numeroAsiento - 1;
        this.tipoOperacion = tipoOperacion;
        this.areaEstado = areaEstado;
    }

    //Constructor para cambiar asiento
    public ReservaTicketsAereos(String idCliente, int numeroAsiento, String tipoOperacion, JTextArea areaEstado, int nuevoNumeroAsiento) {
        this(idCliente, numeroAsiento, tipoOperacion, areaEstado);
        this.nuevoNumeroAsiento = nuevoNumeroAsiento - 1;
    }

    @Override
    public void run() {
        try {
            //Simular el tiempo de procesamiento
            Thread.sleep((int) (Math.random() * 3000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        switch (tipoOperacion) {
            case "Reservar":
                reservarAsiento();
                break;
            case "Cancelar":
                cancelarAsiento();
                break;
            case "Cambiar":
                cambiarAsiento();
                break;
        }

    }

    private void reservarAsiento() {
        lock.lock();
        try {
            if (!asientos[numeroAsiento]) {
                asientos[numeroAsiento] = true;
                areaEstado.append("El asiento " + (numeroAsiento + 1) + " ha sido reservado por el cliente " + idCliente + "\n");
            } else {
                areaEstado.append("Error: El asiento " + (numeroAsiento + 1) + " ya se encuentra ocupado\n");
            }
        } finally {
            lock.unlock();
        }
    }

    private void cancelarAsiento() {
        lock.lock();
        try {
            if (asientos[numeroAsiento]) {
                asientos[numeroAsiento] = false;
                areaEstado.append("El asiento " + (numeroAsiento + 1) + " ha sido cancelado por el cliente " + idCliente + "\n");
            } else {
                areaEstado.append("Error: El asiento " + (numeroAsiento + 1) + " no se encuentra ocupado\n");
            }
        } finally {
            lock.unlock();
        }
    }

    private void cambiarAsiento() {
        lock.lock();
        try {
            if (asientos[numeroAsiento]) {
                if (!asientos[nuevoNumeroAsiento]) {
                    asientos[numeroAsiento] = false;
                    asientos[nuevoNumeroAsiento] = true;
                    areaEstado.append("El asiento " + (numeroAsiento + 1) + " ha sido cambiado por el asiento " + (nuevoNumeroAsiento + 1) + " por el cliente " + idCliente + "\n");
                } else {
                    areaEstado.append("Error: El asiento " + (nuevoNumeroAsiento + 1) + " ya se encuentra ocupado\n");
                }
            } else {
                areaEstado.append("Error: El asiento " + (numeroAsiento + 1) + " no se encuentra ocupado\n");
            }
        } finally {
            lock.unlock();
        }
    }

}
