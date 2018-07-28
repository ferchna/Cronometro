/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.palabrapoder.temporizador.controlador;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.palabrapoder.temporizador.vista.*;

/**
 *
 * @author rescobar
 */
public class IngresoDatos extends JFrame {

    public String textoCambioSup = " ";
    public String textoCambioInf = " ";
    FormularioBotones frmbotones;
    FormularioNumero frmnumeros;
    private static final int confirmar = KeyEvent.VK_ENTER;
    private static final int cancelo = KeyEvent.VK_ESCAPE;
    
    

    public IngresoDatos() {

    }

    public void editarJlabel(FormularioBotones frmbotones, FormularioNumero frmnumeros) {
        //evento en el label superior
        frmbotones.textoMenu.addMouseListener(new MouseAdapter() {// eventos de raton al hacer clik
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    frmbotones.textSuperIngre.setVisible(true);
                    frmbotones.textSuperIngre.setEditable(true);
                    frmbotones.textoMenu.setVisible(false);
                    frmbotones.textSuperIngre.setCaretPosition(0);
                    System.out.println("HOLA EDITOR DE JLABEL");
                    String txtMenIng = frmbotones.textoMenu.getText();
                    if (txtMenIng.equals("")) {
                        System.out.println("TEXTO DE CLIC" + txtMenIng + "continuacion");
                        txtMenIng = "INGRESAR TEXTO";
                        frmbotones.textoMenu.setVisible(true);
                        frmbotones.textoMenu.setText(txtMenIng);
                    } else {
                        frmbotones.textSuperIngre.setText(txtMenIng);

                    }
                }
            }
        });
        //evento de clic en el label inferior
        frmbotones.txtInfBtn.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    //frmbotones.textSuperIngre.setEditable(false);
                    //frmbotones.textBtnInfe.setEditable(true);
                    frmbotones.txtInfBtn.setVisible(false);
                    frmbotones.textBtnInfe.setVisible(true);
                    String txtInfIng = frmbotones.txtInfBtn.getText();
                    System.out.println("HOLA EDITOR DE JLABEL INFERIOR");
                    if (txtInfIng.equals("")) {
                        System.out.println("TEXTO DE CLIC 2" + txtInfIng + "otro texto");
                        txtInfIng = "INGRESAR TEXTO";
                        frmbotones.txtInfBtn.setVisible(true);
                        frmbotones.txtInfBtn.setText(txtInfIng);
                    } else {
                        
                        frmbotones.textBtnInfe.setText(txtInfIng);
                    }

                }//fin if

            }

        });

        frmbotones.textSuperIngre.addKeyListener(new KeyAdapter() {

            public void keyReleased(KeyEvent e) {//evento del teclado al hacer enter 

                if (e.getKeyCode() == confirmar) {
                    System.out.println("HOLA SALI DEL EDITOR");
                    textoCambioSup = frmbotones.textSuperIngre.getText();
                    frmbotones.textoMenu.setText(textoCambioSup);
                    frmbotones.textSuperIngre.setVisible(false);
                    frmbotones.textoMenu.setVisible(true);

                    if (textoCambioSup.length() > 0 && textoCambioSup.length() < 14) {
                        frmbotones.textoMenu.setText(textoCambioSup.toUpperCase());
                        System.out.println("Este es el texto mas largo que se est6a ingresado" + textoCambioSup);
                        Font fuente = new Font("sanserif", 1, 90);
                        frmnumeros.textoIngresado.setFont(fuente);
                        frmnumeros.textoIngresado.setText(textoCambioSup.toUpperCase());
                        

                    } else {
                        Font fuente = new Font("sanserif", 1, 60);
                        frmnumeros.textoIngresado.setFont(fuente);
                        frmnumeros.textoIngresado.setText(textoCambioSup.toUpperCase());
                        Font fuenteBotn = new Font("sanserif", 1, 12);
                        frmbotones.textoMenu.setFont(fuenteBotn);
                        frmbotones.textoMenu.setText(textoCambioSup.toUpperCase());
                        
                    }

                }//fin confirmar texto superior
                
                  if(e.getKeyCode()==cancelo)
                {
                    System.out.println("Ingrese al escape");
                    //frmbotones.textBtnInfe.setEditable(false);
                    //frmbotones.textSuperIngre.setEditable(false);
                    frmbotones.textSuperIngre.setVisible(false);
                    frmbotones.textoMenu.setVisible(true);
                                        
                }

            }
        });

        frmbotones.textBtnInfe.addKeyListener(new KeyAdapter() {

            public void keyReleased(KeyEvent d) {
                if (d.getKeyCode() == confirmar) {
                    System.out.println("HOLA SALI DEL EDITOR 2");
                    textoCambioInf = frmbotones.textBtnInfe.getText();
                    frmbotones.txtInfBtn.setText(textoCambioInf);
                    frmbotones.textBtnInfe.setVisible(false);
                    frmbotones.txtInfBtn.setVisible(true);
                    

                    if (textoCambioInf.length() > 0 && textoCambioInf.length() < 30) {
                        frmbotones.txtInfBtn.setText(textoCambioInf.toUpperCase());
                        System.out.println("Este es el texto mas largo que se est6a ingresado del inferior" + textoCambioInf);
                        Font fuente = new Font("sanserif", 1, 90);
                        frmnumeros.textoInferior.setFont(fuente);
                        frmnumeros.textoInferior.setText(textoCambioInf.toUpperCase());
                        frmnumeros.textoInferior.setText(textoCambioInf.toUpperCase());
                       
                             

                    } else {
                        Font fuente = new Font("sanserif", 1, 80);
                        frmnumeros.textoInferior.setFont(fuente);
                        frmnumeros.textoInferior.setText(textoCambioInf.toUpperCase());
                        Font fuenteBotn = new Font("sanserif", 1, 12);
                        frmbotones.textoMenu.setFont(fuenteBotn);
                        frmbotones.txtInfBtn.setText(textoCambioInf.toUpperCase());
                    }

                }//fin confirmar
                if(d.getKeyCode()==cancelo)
                {
                    System.out.println("Ingrese al escape");
                    //frmbotones.textBtnInfe.setEditable(false);
                    //frmbotones.textSuperIngre.setEditable(false);
                    frmbotones.textBtnInfe.setVisible(false);
                    frmbotones.txtInfBtn.setVisible(true);
                                        
                }

            }

        });

    }
    
   

    public void confirmar() {

    }

    private void editarJlabel() {
        //frmbotones.textSuperIngre.setVisible(true);

        frmbotones.textSuperIngre.requestFocus();
        frmbotones.textSuperIngre.selectAll();
    }

}
