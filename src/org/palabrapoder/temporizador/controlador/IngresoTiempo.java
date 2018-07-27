/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.palabrapoder.temporizador.controlador;

/**
 *
 * @author rescobar
 */
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.palabrapoder.temporizador.vista.*;
import org.palabrapoder.temporizador.controlador.*;



public class IngresoTiempo extends JFrame {
    
    public String numerHoras="";
    public String numerMinu="";
    FormularioIngresoTiempo frmIngreTiempo;
    FormularioErrores frmErrores;
    TimerProgramacion timerProgra;
    FormularioTexto frmtexto;
    FormularioNumero frmnumeros;
    
    private static final int confirmar = KeyEvent.VK_ENTER;
    private static final int cancelo = KeyEvent.VK_ESCAPE;
    
    
    
    public IngresoTiempo ()
    {
        
        
        
    }
    
    public void editarTiempo (FormularioIngresoTiempo frmIngreTiempo)
    {
        frmIngreTiempo.jbentrar.addKeyListener(new KeyAdapter() { // envento de tecla de ingreso de tiempo
            
            
               public void keyReleased(KeyEvent e) {//evento del teclado al hacer enter 

               
                if (e.getKeyCode() == confirmar) {
                    System.out.println("asdasdasdasdasd");
                    timerProgra.NumerosTiempo();
            
                 }//fin confirmar texto superior
                
                  if(e.getKeyCode()==cancelo)
                {
                  
                }
               }

            
            
            
            
            
            
            
} );
    }

          
}//fin clase de ingreso de tiempo
