/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.palabrapoder.temporizador.ejecucion;
import org.palabrapoder.temporizador.controlador.TimerProgramacion;
import org.palabrapoder.temporizador.vista.FormularioNumero;
import org.palabrapoder.temporizador.vista.FormularioBotones;
import org.palabrapoder.temporizador.vista.FormularioTexto;
import org.palabrapoder.temporizador.vista.FormularioIngresoTiempo;
//import org.palabrapoder.temporizador.controlador.relogLabel;
/**
 *
 * @author Fernand
 */
public class TimerExe {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FormularioBotones btn=new FormularioBotones();
        FormularioNumero nume= new FormularioNumero();
        // FormularioTexto txto= new FormularioTexto();
         FormularioIngresoTiempo intTem=new FormularioIngresoTiempo();
        new TimerProgramacion(btn, nume,intTem).go();
        //new relogLabel();
        
    }
    
}
