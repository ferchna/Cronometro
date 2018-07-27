/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.palabrapoder.temporizador.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.palabrapoder.temporizador.vista.*;

/**
 *
 * @author rescobar
 */
public class SeleccionEvento implements ActionListener{

    FormularioEscoge frmEscoge;
    FormularioIngresoTiempo frmIngreTiempo;
    FormularioPredicador frmPredicador;

    public String even = "true";

    public SeleccionEvento(FormularioEscoge escg) {
        
        this.frmEscoge=escg;
        
    }

   
    public void go() {

        this.frmEscoge.setVisible(true);
        this.frmEscoge.setLocationRelativeTo(null);
    }

    private void frmSelecc() {
        this.frmEscoge.jbSelecEvento.setActionCommand("selectEvento");
        this.frmEscoge.jbSelecEvento.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
       String ent=e.getActionCommand();
       
       


    }

}
