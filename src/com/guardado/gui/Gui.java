package com.guardado.gui;

import com.guardado.threads.AnimalThread;
import com.guardado.threads.Calendario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;

public class Gui extends JFrame {

    private JPanel[] paneles;
    private JLabel[] labels;
    private JButton inicio;
    private JButton reiniciar;
    private JLabel hora;
    Calendario calendario;
    private int flag = 0;
    private String[] nombres = {"canguro", "tortuga", "dragon"};

    public Gui() {
        super("Carrera de animales");
        initialComponents();

    }

    public void initialComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        
        paneles = new JPanel[3];
        labels = new JLabel[3];
        
        hora= new JLabel();
        calendario= new Calendario(hora);
        calendario.start();
        
        inicio = new JButton("Inicio");
        reiniciar = new JButton("Reiniciar");
        Container container = getContentPane();
        hora.setBounds(500, 0, 250, 30);
        
        container.add(hora);
        //llenando el panel
        for (int i = 0; i < 3; i++) {
            labels[i] = new JLabel();
            labels[i].setIcon(new ImageIcon(getClass().getResource(nombres[i] + ".gif")));
            labels[i].setBounds(10, (i * 220) + 10, 200, 200);
            container.add(labels[i]);
        }
        inicio.setBounds(10, 0, 100, 30);
        reiniciar.setBounds(300, 0, 100, 30);
        container.add(inicio);
        container.add(reiniciar);
        reiniciar.setVisible(false);
        inicio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AnimalThread canguro = new AnimalThread("canguro", labels[0].getX(), labels[0].getY(), 510, labels[0]);
                AnimalThread tortuga = new AnimalThread("colibri", labels[1].getX(), labels[1].getY(), 510, labels[1]);
                AnimalThread dragon = new AnimalThread("dragon", labels[2].getX(), labels[2].getY(), 510, labels[2]);
                canguro.start();
                tortuga.start();
                dragon.start();
                inicio.setVisible(false);
                reiniciar.setVisible(true);
            }
        });

        reiniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                flag=0;
                for (int i = 0; i < 3; i++){
                    if(labels[i].getX()==510){
                        flag=1;
                    }
                }
                if(flag==1){
                    for (int i = 0; i < 3; i++) {
                        labels[i].setBounds(10, (i * 220) + 10, 200, 200);

                    }
                    inicio.setVisible(true);
                    reiniciar.setVisible(false);
                }
            }

        });
        
        
        setSize(700, 650);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Gui().setVisible(true);
            }
        });
    }

}
