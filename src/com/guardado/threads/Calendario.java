/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guardado.threads;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;
import javax.swing.JLabel;

/**
 *
 * @author Alejandro Olmedo <00097017@uca.edu.sv>
 */
public class Calendario extends Thread {
    public Calendar calendar;
    public JLabel cajita;
    public Calendario(JLabel cajita) {
        calendar = GregorianCalendar.getInstance();
        this.cajita= cajita;
    }

    @Override
    public void run() {
        while (true) {
            calendar = GregorianCalendar.getInstance();
            this.cajita.setText(calendar.getTime().getHours()+":"+calendar.getTime().getMinutes()+":"+calendar.getTime().getSeconds());
            //System.out.println(calendar.getTime().toLocaleString());
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            yield();
        }
        
    }
}
