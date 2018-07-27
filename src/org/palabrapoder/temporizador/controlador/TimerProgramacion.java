/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.palabrapoder.temporizador.controlador;

//import com.sun.xml.internal.fastinfoset.EncodingConstants;
import java.awt.Color;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import org.palabrapoder.temporizador.vista.FormularioBotones;
import org.palabrapoder.temporizador.vista.FormularioNumero;
import org.palabrapoder.temporizador.vista.FormularioTexto;
import org.palabrapoder.temporizador.vista.FormularioTextoInt;
import org.palabrapoder.temporizador.vista.FormularioIngresoTiempo;
import org.palabrapoder.temporizador.vista.SplashScreen;
//import org.palabrapoder.temporizador.controlador.relogLabel;
import javax.swing.Timer;
import org.palabrapoder.temporizador.vista.FormularioErrores;

/**
 *
 * @author Fernand
 */
public class TimerProgramacion implements ActionListener, Runnable {

    FormularioBotones frmbotones;
    FormularioNumero frmnumeros;
    FormularioTexto frmtexto;
    FormularioIngresoTiempo frmIngreTiempo;
    FormularioTextoInt frmIngreso;
    FormularioErrores frmErrores;
    SplashScreen splashScreen;
    //FormularioTextoInt frmtextint;
    String horas, minutos, segundos;
    Calendar calendario;
    Thread hilo;
    Timer cronometro;
    public int segundosUnidad, segundosDecena, minutosUnidad, minutosDecenas, horasUnidad, horasDecenas;
    IngresoDatos ingreDatos = new IngresoDatos();
    IngresoTiempo ingreTiempo= new IngresoTiempo();

    public TimerProgramacion(FormularioBotones btn, FormularioNumero nume, FormularioIngresoTiempo intTem) {
        segundosUnidad = 10;
        segundosDecena = 5;
        minutosUnidad = 0;
        minutosDecenas = 0;
        horasUnidad = 0;
        horasDecenas = 0;
        cronometro = new Timer(1000, this);
        this.frmbotones = btn;
        this.frmnumeros = nume;
        this.frmIngreTiempo = intTem;
        frmIngreTiempo();
        //frmIngreTiempo();
        hilo = new Thread(this);
        hilo.start();

    }//fin constructor que llama a los formularios

    private void iniciar() {
        frmbotones.execute.setActionCommand("run");
        frmbotones.execute.addActionListener(this);
        //frmbotones.enviarTexto.setActionCommand("enviar");
        //this.frmbotones.enviarTexto.addActionListener(this);
        //this.frmbotones.enviarTexto.setEnabled(true);
        frmbotones.parar.setActionCommand("parar");
        this.frmbotones.parar.addActionListener(this);
        this.frmbotones.parar.setEnabled(false);
        this.frmbotones.jbreinicio.setActionCommand("reinicio");
        this.frmbotones.jbreinicio.addActionListener(this);
        this.frmbotones.jbreinicio.setEnabled(false);
        this.frmbotones.setVisible(true);
        ingreDatos.editarJlabel(frmbotones, frmnumeros);
        
        //this.frmnumeros.setLocationRelativeTo(null);

    }

    public void go() {

        this.frmIngreTiempo.setVisible(true);
        this.frmIngreTiempo.setLocationRelativeTo(null);
        ingreTiempo.editarTiempo(frmIngreTiempo);
        //this.frmbotones.setVisible(true);
        //this.frmbotones.setLocationRelativeTo(null);
        //this.frmnumeros.setVisible(true);

    }

    /*acciones de botones*/
    @Override
    public void actionPerformed(ActionEvent eventosBtn) {
        String evt = eventosBtn.getActionCommand();

        if (evt == "salir") {
            System.exit(0);
            evt = "";
        } else if (evt == "entraH") {
           NumerosTiempo();
            evt = "";
        }//finevento
        else if (evt == "valError") {
            this.frmErrores.dispose();
            frmIngreTiempo();
            evt = "";

        } else if (evt == "enviar") {//POR SI ACA

            //formTexto();
            //frmIngt();
            //System.out.println("Este es el texto mas largo que se est6a desplegando" + evt);
            evt = "";
            // frmIngreTiempo();
        } else if (evt == "ingersarTexto") {
            //formTexto();
            //frmtexto.dispose();
            //frmtexto = new FormularioTexto();
            this.frmnumeros.setVisible(true);
            frmbotones.setLocationRelativeTo(null);
            String texto;
            String textoInfe;
            texto = frmtexto.txtNombre.getText();
            textoInfe = frmtexto.txtInfer.getText();
            if (texto.equals("") || textoInfe.equals("")) {
                String camVa = "INGRESE TEXTO";
                frmbotones.textoMenu.setText(camVa);
                frmbotones.txtInfBtn.setText(camVa);

            } else {
                if (texto.length() > 0 && texto.length() < 14) {
                    System.out.println("Este es el texto mas largo que se est6a ingresado" + texto);
                    frmbotones.textoMenu.setText(texto.toUpperCase());
                    frmbotones.txtInfBtn.setText(textoInfe.toUpperCase());
                    frmnumeros.textoIngresado.setText(texto.toUpperCase());
                    frmnumeros.textoInferior.setText(textoInfe.toUpperCase());
                } else {
                    Font fuente = new Font("sanserif", 1, 60);
                    frmnumeros.textoIngresado.setFont(fuente);
                    frmnumeros.textoIngresado.setText(texto.toUpperCase());
                    frmnumeros.textoInferior.setText(textoInfe.toUpperCase());
                    Font fuenteBotn = new Font("sanserif", 1, 12);
                    frmbotones.textoMenu.setFont(fuenteBotn);
                    frmbotones.textoMenu.setText(texto.toUpperCase());
                    frmbotones.txtInfBtn.setText(textoInfe.toUpperCase());
                }
            }
            iniciar();
            this.frmtexto.dispose();
            evt = "";

        } else if (evt == "otrotex") {
            this.frmnumeros.setVisible(true);
            frmbotones.setLocationRelativeTo(null);
            String texto;
            String textoInfe;
            texto = frmIngreso.txtNombreInt.getText();
            textoInfe = frmtexto.txtInfer.getText();
            System.out.println("este es el ingreso del otro texto" + texto);
            frmbotones.textoMenu.setText(texto.toUpperCase());
            frmnumeros.textoIngresado.setText(texto.toUpperCase());
            // frmbotones.txtInfBtn.setText(textoInfe.toUpperCase());
            // frmnumeros.textoInferior.setText(textoInfe.toUpperCase());
            iniciar();
            frmIngreso.dispose();
            evt = "";

        } else if (this.frmbotones.execute.getText().equals("INICIO") || eventosBtn.getSource() instanceof Timer) {

            //if()
            cronometro.start();

            if (segundosUnidad != 11) {
                System.out.println("Entroooo");
                segundosUnidad--;
                if (segundosUnidad == -1) {

                    segundosUnidad = 10;
                    segundosDecena--;
                    segundosUnidad--;
                }
                if (segundosDecena == -1) {
                    segundosDecena = 6;
                    segundosDecena--;
                }
                String v = String.valueOf(segundosDecena) + String.valueOf(segundosUnidad);
                int k = Integer.parseInt(v);
                System.out.println("el valor de v con" + v);
                if (k == 59) {
                    minutosUnidad--;

                }
                if (minutosUnidad == -1) {
                    minutosUnidad = 10;
                    minutosDecenas--;
                    minutosUnidad--;
                }
                if (minutosDecenas == -1) {
                    minutosDecenas = 6;
                    minutosDecenas--;

                }
                String z = String.valueOf(minutosDecenas) + String.valueOf(minutosUnidad);
                int u = Integer.parseInt(z);
                System.out.println("el valor de z con" + z);
                if (u == 59 && k == 59) {
                    horasUnidad--;
                }
                if (horasUnidad == -1) {
                    horasUnidad = 10;
                    minutosUnidad = 10;
                    horasDecenas--;
                    horasUnidad--;
                    //minutosDecenas--;
                    minutosUnidad--;
                }
                if (horasDecenas == -1) {
                    horasDecenas = 10;
                    horasDecenas--;
                    // minutosDecenas=6;
                    // minutosDecenas--;

                }
                //creacion de variables para los controles de cuenta y efectos de colores
                String conHo = frmnumeros.horasDecenas.getText() + frmnumeros.horasUnidad.getText();
                String conMi = frmnumeros.minutosDecenas.getText() + frmnumeros.minutosUnidad.getText();
                String conSeg = frmnumeros.segundosDecenas.getText() + frmnumeros.segundosUnidad.getText();
                int contH = Integer.parseInt(conHo);
                int contMi = Integer.parseInt(conMi);
                int contSe = Integer.parseInt(conSeg);

                if (contH == 0 && contMi == 0 && contSe == 0) {
                    segundosUnidad = 0;
                    segundosDecena = 0;
                    minutosUnidad = 0;
                    minutosDecenas = 0;
                    horasUnidad = 0;
                    horasDecenas = 0;
                    this.frmnumeros.segundosUnidad.setText(String.valueOf(segundosUnidad));
                    this.frmnumeros.segundosDecenas.setText(String.valueOf(segundosDecena));
                    this.frmnumeros.minutosUnidad.setText(String.valueOf(minutosUnidad));
                    this.frmnumeros.minutosDecenas.setText(String.valueOf(minutosDecenas));
                    this.frmnumeros.horasDecenas.setText(String.valueOf(horasUnidad));
                    this.frmnumeros.horasUnidad.setText(String.valueOf(horasDecenas));
                    this.frmbotones.jlHoDePe.setText(String.valueOf(horasDecenas));
                    this.frmbotones.jLHoUPe.setText(String.valueOf(horasUnidad));
                    this.frmbotones.jlMinDePeq.setText(String.valueOf(minutosDecenas));
                    this.frmbotones.jLMinUPe.setText(String.valueOf(minutosUnidad));
                    this.frmbotones.jlSegDePe.setText(String.valueOf(segundosDecena));
                    this.frmbotones.jlSegUnPeq.setText(String.valueOf(segundosUnidad));
                    this.frmnumeros.segundosUnidad.setText(String.valueOf(segundosUnidad));
                    this.frmnumeros.segundosDecenas.setText(String.valueOf(segundosDecena));
                    this.frmnumeros.minutosUnidad.setText(String.valueOf(minutosUnidad));
                    this.frmnumeros.minutosDecenas.setText(String.valueOf(minutosDecenas));
                    this.frmnumeros.horasUnidad.setText(String.valueOf(horasUnidad));
                    this.frmnumeros.horasDecenas.setText(String.valueOf(horasDecenas));
                    this.frmbotones.execute.setEnabled(false);
                    this.frmbotones.parar.setEnabled(false);

                }//fin control para que no cuente siga la cuenta regresiva

                if (contH == 0 && (contMi >= 1 && contMi <= 5) && contSe == 0) {
                    this.frmnumeros.segundosUnidad.setForeground(Color.red);
                    this.frmnumeros.segundosDecenas.setForeground(Color.red);
                    this.frmnumeros.minutosUnidad.setForeground(Color.red);
                    this.frmnumeros.minutosDecenas.setForeground(Color.red);
                    this.frmnumeros.horasDecenas.setForeground(Color.red);
                    this.frmnumeros.horasUnidad.setForeground(Color.red);
                    this.frmbotones.jlHoDePe.setForeground(Color.red);
                    this.frmbotones.jLHoUPe.setForeground(Color.red);
                    this.frmbotones.jlMinDePeq.setForeground(Color.red);
                    this.frmbotones.jLMinUPe.setForeground(Color.red);
                    this.frmbotones.jlSegDePe.setForeground(Color.red);
                    this.frmbotones.jlSegUnPeq.setForeground(Color.red);
                    this.frmnumeros.dot3.setForeground(Color.red);
                    this.frmnumeros.dot2.setForeground(Color.red);
                    this.frmnumeros.dot4.setForeground(Color.red);
                    this.frmnumeros.dot1.setForeground(Color.red);

                }//fin de control para cambiar de color de lo labels numeros

                if (contH == 0 && contMi == 0 && (contSe >= 0 && contSe <= 59)) {

                    if (contSe % 2 != 0) {

                        this.frmnumeros.segundosUnidad.setForeground(new Color(23, 150, 207));
                        this.frmnumeros.segundosDecenas.setForeground(new Color(23, 150, 207));
                        this.frmnumeros.minutosUnidad.setForeground(new Color(23, 150, 207));
                        this.frmnumeros.minutosDecenas.setForeground(new Color(23, 150, 207));
                        this.frmnumeros.horasDecenas.setForeground(new Color(23, 150, 207));
                        this.frmnumeros.horasUnidad.setForeground(new Color(23, 150, 207));
                        this.frmbotones.jlHoDePe.setForeground(new Color(23, 150, 207));
                        this.frmbotones.jLHoUPe.setForeground(new Color(23, 150, 207));
                        this.frmbotones.jlMinDePeq.setForeground(new Color(23, 150, 207));
                        this.frmbotones.jLMinUPe.setForeground(new Color(23, 150, 207));
                        this.frmbotones.jlSegDePe.setForeground(new Color(23, 150, 207));
                        this.frmbotones.jlSegUnPeq.setForeground(new Color(23, 150, 207));
                        this.frmnumeros.dot3.setForeground(Color.red);
                        this.frmnumeros.dot2.setForeground(Color.red);
                        this.frmnumeros.dot4.setForeground(Color.red);
                        this.frmnumeros.dot1.setForeground(Color.red);

                    } else {
                        this.frmnumeros.segundosUnidad.setForeground(Color.red);
                        this.frmnumeros.segundosDecenas.setForeground(Color.red);
                        this.frmnumeros.minutosUnidad.setForeground(Color.red);
                        this.frmnumeros.minutosDecenas.setForeground(Color.red);
                        this.frmnumeros.horasDecenas.setForeground(Color.red);
                        this.frmnumeros.horasUnidad.setForeground(Color.red);
                        this.frmbotones.jlHoDePe.setForeground(Color.red);
                        this.frmbotones.jLHoUPe.setForeground(Color.red);
                        this.frmbotones.jlMinDePeq.setForeground(Color.red);
                        this.frmbotones.jLMinUPe.setForeground(Color.red);
                        this.frmbotones.jlSegDePe.setForeground(Color.red);
                        this.frmbotones.jlSegUnPeq.setForeground(Color.red);
                        this.frmnumeros.dot3.setForeground(Color.red);
                        this.frmnumeros.dot2.setForeground(Color.red);
                        this.frmnumeros.dot4.setForeground(Color.red);
                        this.frmnumeros.dot1.setForeground(Color.red);

                    }

                }//fin cambio de color encedido

            }//fin ifs de conteo

            this.frmnumeros.segundosUnidad.setText(String.valueOf(segundosUnidad));
            this.frmnumeros.segundosDecenas.setText(String.valueOf(segundosDecena));
            this.frmnumeros.minutosUnidad.setText(String.valueOf(minutosUnidad));
            this.frmnumeros.minutosDecenas.setText(String.valueOf(minutosDecenas));
            this.frmnumeros.horasDecenas.setText(String.valueOf(horasDecenas));
            this.frmnumeros.horasUnidad.setText(String.valueOf(horasUnidad));
            this.frmbotones.jlHoDePe.setText(String.valueOf(horasDecenas));
            this.frmbotones.jLHoUPe.setText(String.valueOf(horasUnidad));
            this.frmbotones.jlMinDePeq.setText(String.valueOf(minutosDecenas));
            this.frmbotones.jLMinUPe.setText(String.valueOf(minutosUnidad));
            this.frmbotones.jlSegDePe.setText(String.valueOf(segundosDecena));
            this.frmbotones.jlSegUnPeq.setText(String.valueOf(segundosUnidad));
            System.out.println("las horas decenas" + horasDecenas + "horas unidad" + horasUnidad);
            System.out.println("los min en dec es" + minutosDecenas + "los min en unida" + minutosUnidad);
            System.out.println("los segundos decenas" + segundosDecena + "segudnos unidad" + segundosUnidad);
            // System.out.println(horasDecenas + " " + minutosDecenas + " " + minutosUnidad + " " + segundosDecena + " " + segundosUnidad );
            //System.out.println("El valor de z es " + z);
            this.frmbotones.execute.setText("RESTART");
            //frmbotones.execute.setText(evt);

            //this.frmbotones.enviarTexto.setEnabled(true);
            this.frmbotones.parar.setEnabled(true);
            this.frmbotones.jbreinicio.setEnabled(true);
            this.frmbotones.execute.setEnabled(false);
            evt = "";
            System.out.println("El evento es" + evt);

        }// fin if
        else if (evt == "parar") {
            deten();
            System.out.println("El evento del boton parar" + evt);
            evt = "";
        } else if (evt == "reinicio") {

            System.out.println("El evento del boton reinicio" + evt);
            int nuHo1 = Integer.parseInt(frmnumeros.horasDecenas.getText());
            int nuHo2 = Integer.parseInt(frmnumeros.horasUnidad.getText());
            int min1 = Integer.parseInt(frmnumeros.minutosDecenas.getText());
            int min2 = Integer.parseInt(frmnumeros.minutosUnidad.getText());
            int seg1 = Integer.parseInt(frmnumeros.segundosDecenas.getText());
            int seg2 = Integer.parseInt(frmnumeros.segundosUnidad.getText());

            this.frmnumeros.segundosUnidad.setForeground(Color.WHITE);
            this.frmnumeros.segundosDecenas.setForeground(Color.WHITE);
            this.frmnumeros.minutosUnidad.setForeground(Color.WHITE);
            this.frmnumeros.minutosDecenas.setForeground(Color.WHITE);
            this.frmnumeros.horasDecenas.setForeground(Color.WHITE);
            this.frmnumeros.horasUnidad.setForeground(Color.WHITE);
            this.frmbotones.jlHoDePe.setForeground(Color.WHITE);
            this.frmbotones.jLHoUPe.setForeground(Color.WHITE);
            this.frmbotones.jlMinDePeq.setForeground(Color.WHITE);
            this.frmbotones.jLMinUPe.setForeground(Color.WHITE);
            this.frmbotones.jlSegDePe.setForeground(Color.WHITE);
            this.frmbotones.jlSegUnPeq.setForeground(Color.WHITE);
            this.frmnumeros.dot3.setForeground(Color.WHITE);
            this.frmnumeros.dot2.setForeground(Color.WHITE);
            this.frmnumeros.dot4.setForeground(Color.WHITE);
            this.frmnumeros.dot1.setForeground(Color.WHITE);

            if ((seg1 == 5 && seg2 == 9) && (min1 == 5 && min2 == 9) && (nuHo2 == 9 && nuHo1 == 9)) {
                cronometro.stop();
                segundosUnidad = 0;
                segundosDecena = 0;
                minutosUnidad = 0;
                minutosDecenas = 0;
                horasUnidad = 0;
                horasDecenas = 0;
                this.frmnumeros.segundosUnidad.setText(String.valueOf(segundosUnidad));
                this.frmnumeros.segundosDecenas.setText(String.valueOf(segundosDecena));
                this.frmnumeros.minutosUnidad.setText(String.valueOf(minutosUnidad));
                this.frmnumeros.minutosDecenas.setText(String.valueOf(minutosDecenas));
                this.frmnumeros.horasDecenas.setText(String.valueOf(horasUnidad));
                this.frmnumeros.horasUnidad.setText(String.valueOf(horasDecenas));
                this.frmbotones.jlHoDePe.setText(String.valueOf(horasDecenas));
                this.frmbotones.jLHoUPe.setText(String.valueOf(horasUnidad));
                this.frmbotones.jlMinDePeq.setText(String.valueOf(minutosDecenas));
                this.frmbotones.jLMinUPe.setText(String.valueOf(minutosUnidad));
                this.frmbotones.jlSegDePe.setText(String.valueOf(segundosDecena));
                this.frmbotones.jlSegUnPeq.setText(String.valueOf(segundosUnidad));
            }
            devuelta();
            evt = "";
        }

        //Fin constructor acciones botones
    }

    public void formTexto() {
        String text;
        frmtexto = new FormularioTexto();
        this.frmtexto.setTitle("MINISTERIO PALABRA Y PODER");
        this.frmtexto.jbIngresar.setActionCommand("ingersarTexto");
        this.frmtexto.jbIngresar.addActionListener(this);
        this.frmtexto.jbCancelar.setActionCommand("salir");
        this.frmtexto.jbCancelar.addActionListener(this);
        this.frmtexto.setVisible(true);
        this.frmtexto.setLocationRelativeTo(null);

    }

    private void frmIngreTiempo() {
        frmIngreTiempo = new FormularioIngresoTiempo();
        this.frmIngreTiempo.setVisible(true);
        this.frmIngreTiempo.setLocationRelativeTo(null);
        this.frmIngreTiempo.jbentrar.setActionCommand("entraH");
        this.frmIngreTiempo.jbentrar.addActionListener(this);
    }

    private void frmIngt() {
        frmIngreso = new FormularioTextoInt();
        this.frmIngreso.setVisible(true);
        this.frmIngreso.jbIngresarInt.setActionCommand("otrotex");
        this.frmIngreso.jbIngresarInt.addActionListener(this);
        this.frmIngreso.jbCancelarInt.setActionCommand("salir");

    }

    public void frmErr() {
        frmErrores = new FormularioErrores();
        this.frmErrores.setVisible(true);
        this.frmErrores.jbvalError.setActionCommand("valError");
        this.frmErrores.jbvalError.addActionListener(this);
        this.frmErrores.setLocationRelativeTo(null);

    }
    
    
    public void NumerosTiempo()
    {
        // iniciar();
            String txt1 = frmIngreTiempo.jTextField1.getText();
            String txt2 = frmIngreTiempo.jTextField2.getText();
            frmIngreTiempo.dispose();
            //formTexto();
            if (txt1.length() != 0 && txt2.length() != 0) {
                if (txt1.matches("[0-9]*") && txt2.matches("[0-9]*")) {

                    if (txt1.length() >= 3 && txt2.length() >= 3) {
                        String errDat = "INGRESE VALORES ENTRE 99 O 59";
                        frmErr();
                        this.frmErrores.jlbError.setText(errDat);

                    } else {

                        formTexto();
                        if (txt1.length() == 2 && txt2.length() == 2) { // MAYOR DE 10 HORAS Y MAYOR DE 10 MINUTOS
                            int hu = Integer.parseInt(frmIngreTiempo.jTextField1.getText());
                            int mi = Integer.parseInt(frmIngreTiempo.jTextField2.getText());
                            if (hu <= 99 && mi <= 59) {
                                String minUni = frmIngreTiempo.jTextField2.getText().substring(1);
                                String minDec = frmIngreTiempo.jTextField2.getText();
                                char mini;
                                mini = minDec.charAt(0);
                                String minus = String.valueOf(mini);
                                System.out.println("el valor de mini " + mini);
                                minutosDecenas = Integer.parseInt(minus);
                                minutosUnidad = Integer.parseInt(minUni);
                                String horUni = frmIngreTiempo.jTextField1.getText().substring(1);
                                String hoDec = frmIngreTiempo.jTextField1.getText();
                                char hor;
                                hor = hoDec.charAt(0);
                                String horus = String.valueOf(hor);
                                horasUnidad = Integer.valueOf(horUni);
                                horasDecenas = Integer.parseInt(horus);
                                // iniciar();
                                // frmnumeros.setVisible(true);
                                this.frmnumeros.minutosUnidad.setText(String.valueOf(minutosUnidad));
                                this.frmnumeros.minutosDecenas.setText(String.valueOf(minutosDecenas));
                                this.frmnumeros.horasDecenas.setText(String.valueOf(horasDecenas));
                                this.frmnumeros.horasUnidad.setText(String.valueOf(horasUnidad));
                                this.frmbotones.jlHoDePe.setText(String.valueOf(horasDecenas));
                                this.frmbotones.jLHoUPe.setText(String.valueOf(horasUnidad));
                                this.frmbotones.jlMinDePeq.setText(String.valueOf(minutosDecenas));
                                this.frmbotones.jLMinUPe.setText(String.valueOf(minutosUnidad));
                            }//if hasta 99
                            else {

                                System.out.println("Ingrese datos correctos");

                            }//if longitud
                        } //if longitug que no supere de 2
                        else if (txt1.length() == 1 && txt2.length() == 1) {// HORAS EN UNIDAD Y MINUTOS MENOR QUE 10
                            String hr = "0" + txt1;
                            String m = "0" + txt2;
                            int hu = Integer.parseInt(frmIngreTiempo.jTextField1.getText());
                            int mi = Integer.parseInt(frmIngreTiempo.jTextField2.getText());
                            if (hu <= 99 && mi <= 59) {
                                String minUni = m.substring(1);
                                String minDec = m;
                                char mini;
                                mini = minDec.charAt(0);
                                String minus = String.valueOf(mini);
                                System.out.println("el valor de mini " + mini);
                                minutosDecenas = Integer.parseInt(minus);
                                minutosUnidad = Integer.parseInt(minUni);
                                String horUni = hr.substring(1);
                                String hoDec = hr;
                                char hor;
                                hor = hoDec.charAt(0);
                                String horus = String.valueOf(hor);
                                horasUnidad = Integer.valueOf(horUni);
                                horasDecenas = Integer.parseInt(horus);
                                // iniciar();
                                // frmnumeros.setVisible(true);
                                this.frmnumeros.minutosUnidad.setText(String.valueOf(minutosUnidad));
                                this.frmnumeros.minutosDecenas.setText(String.valueOf(minutosDecenas));
                                this.frmnumeros.horasDecenas.setText(String.valueOf(horasDecenas));
                                this.frmnumeros.horasUnidad.setText(String.valueOf(horasUnidad));
                                this.frmbotones.jlHoDePe.setText(String.valueOf(horasDecenas));
                                this.frmbotones.jLHoUPe.setText(String.valueOf(horasUnidad));
                                this.frmbotones.jlMinDePeq.setText(String.valueOf(minutosDecenas));
                                this.frmbotones.jLMinUPe.setText(String.valueOf(minutosUnidad));;

                            }

                        }//FIN longitud de una unidad
                        else if (txt1 == "0" && txt2.length() == 1) {//CERO HORAS CON  MINUTOS MENORES QUE 10
                            String hr = "0" + txt1;
                            String m = "0" + txt2;
                            int hu = Integer.parseInt(frmIngreTiempo.jTextField1.getText());
                            int mi = Integer.parseInt(frmIngreTiempo.jTextField2.getText());
                            if (hu <= 99 && mi <= 59) {
                                String minUni = m.substring(1);
                                String minDec = m;
                                char mini;
                                mini = minDec.charAt(0);
                                String minus = String.valueOf(mini);
                                System.out.println("el valor de mini " + mini);
                                minutosDecenas = Integer.parseInt(minus);
                                minutosUnidad = Integer.parseInt(minUni);
                                horasUnidad = 0;
                                horasDecenas = 0;
                                // iniciar();
                                //frmnumeros.setVisible(true);
                                this.frmnumeros.minutosUnidad.setText(String.valueOf(minutosUnidad));
                                this.frmnumeros.minutosDecenas.setText(String.valueOf(minutosDecenas));
                                this.frmnumeros.horasDecenas.setText(String.valueOf(horasDecenas));
                                this.frmnumeros.horasUnidad.setText(String.valueOf(horasUnidad));
                                this.frmbotones.jlHoDePe.setText(String.valueOf(horasDecenas));
                                this.frmbotones.jLHoUPe.setText(String.valueOf(horasUnidad));
                                this.frmbotones.jlMinDePeq.setText(String.valueOf(minutosDecenas));
                                this.frmbotones.jLMinUPe.setText(String.valueOf(minutosUnidad));

                            }

                        }//if cuando la hora es cero
                        else if (Integer.parseInt(txt1) == 0 && txt2.length() == 2) { //CERO HORAS Y MINUTOS MAYORES QUE 10
                            System.out.println("entro al valor de cero");
                            int hu = Integer.parseInt(frmIngreTiempo.jTextField1.getText());
                            int mi = Integer.parseInt(frmIngreTiempo.jTextField2.getText());
                            if (hu <= 99 && mi <= 59) {
                                String minUni = frmIngreTiempo.jTextField2.getText().substring(1);
                                String minDec = frmIngreTiempo.jTextField2.getText();
                                char mini;
                                mini = minDec.charAt(0);
                                String minus = String.valueOf(mini);
                                System.out.println("el valor de mini " + mini);
                                minutosDecenas = Integer.parseInt(minus);
                                minutosUnidad = Integer.parseInt(minUni);
                                horasUnidad = 0;
                                horasDecenas = 0;
                                // iniciar();
                                //frmnumeros.setVisible(true);
                                this.frmnumeros.minutosUnidad.setText(String.valueOf(minutosUnidad));
                                this.frmnumeros.minutosDecenas.setText(String.valueOf(minutosDecenas));
                                this.frmnumeros.horasDecenas.setText(String.valueOf(horasDecenas));
                                this.frmnumeros.horasUnidad.setText(String.valueOf(horasUnidad));
                                this.frmbotones.jlHoDePe.setText(String.valueOf(horasDecenas));
                                this.frmbotones.jLHoUPe.setText(String.valueOf(horasUnidad));
                                this.frmbotones.jlMinDePeq.setText(String.valueOf(minutosDecenas));
                                this.frmbotones.jLMinUPe.setText(String.valueOf(minutosUnidad));
                            }
                        }//if cuando la hora es 0 pero mas de 10 minutos
                        else if (txt1.length() == 2 && Integer.parseInt(txt2) == 0) { //HORAS MAYORES Y MINUTOS IGUALES QUE 10
                            System.out.println("Hola");
                            int hu = Integer.parseInt(frmIngreTiempo.jTextField1.getText());
                            int mi = Integer.parseInt(frmIngreTiempo.jTextField2.getText());
                            if (hu <= 99 && mi <= 59) {
                                minutosDecenas = 0;
                                minutosUnidad = 0;
                                String horUni = frmIngreTiempo.jTextField1.getText().substring(1);
                                String hoDec = frmIngreTiempo.jTextField1.getText();
                                char hor;
                                hor = hoDec.charAt(0);
                                String horus = String.valueOf(hor);
                                horasUnidad = Integer.valueOf(horUni);
                                horasDecenas = Integer.parseInt(horus);
                                // iniciar();
                                // frmnumeros.setVisible(true);
                                this.frmnumeros.minutosUnidad.setText(String.valueOf(minutosUnidad));
                                this.frmnumeros.minutosDecenas.setText(String.valueOf(minutosDecenas));
                                this.frmnumeros.horasDecenas.setText(String.valueOf(horasDecenas));
                                this.frmnumeros.horasUnidad.setText(String.valueOf(horasUnidad));
                                this.frmbotones.jlHoDePe.setText(String.valueOf(horasDecenas));
                                this.frmbotones.jLHoUPe.setText(String.valueOf(horasUnidad));
                                this.frmbotones.jlMinDePeq.setText(String.valueOf(minutosDecenas));
                                this.frmbotones.jLMinUPe.setText(String.valueOf(minutosUnidad));
                            }

                        }//fin if con mas de 10 horas y con 0 min
                        else if (txt1.length() == 2 && txt2.length() == 1) {//HORAS MAYORES QUE 10 Y MINUTOS MENORES QUE 10
                            System.out.println("hola dos");
                            int hu = Integer.parseInt(frmIngreTiempo.jTextField1.getText());
                            int mi = Integer.parseInt(frmIngreTiempo.jTextField2.getText());
                            String m = "0" + txt2;
                            if (hu <= 99 && mi <= 59) {
                                String minUni = m.substring(1);
                                String minDec = m;
                                char mini;
                                mini = minDec.charAt(0);
                                String minus = String.valueOf(mini);
                                System.out.println("el valor de mini " + mini);
                                minutosDecenas = Integer.parseInt(minus);
                                minutosUnidad = Integer.parseInt(minUni);
                                String horUni = frmIngreTiempo.jTextField1.getText().substring(1);
                                String hoDec = frmIngreTiempo.jTextField1.getText();
                                char hor;
                                hor = hoDec.charAt(0);
                                String horus = String.valueOf(hor);
                                horasUnidad = Integer.valueOf(horUni);
                                horasDecenas = Integer.parseInt(horus);
                                // iniciar();
                                //frmnumeros.setVisible(true);
                                this.frmnumeros.minutosUnidad.setText(String.valueOf(minutosUnidad));
                                this.frmnumeros.minutosDecenas.setText(String.valueOf(minutosDecenas));
                                this.frmnumeros.horasDecenas.setText(String.valueOf(horasDecenas));
                                this.frmnumeros.horasUnidad.setText(String.valueOf(horasUnidad));
                                this.frmbotones.jlHoDePe.setText(String.valueOf(horasDecenas));
                                this.frmbotones.jLHoUPe.setText(String.valueOf(horasUnidad));
                                this.frmbotones.jlMinDePeq.setText(String.valueOf(minutosDecenas));
                                this.frmbotones.jLMinUPe.setText(String.valueOf(minutosUnidad));

                            }
                        }//fin if mas de 10 horas y con menos de 10 min 
                        else if (txt1.length() == 1 && txt2.length() == 2) {// HORAS EN UNIDAD Y MINUTOS MAYORES QUE 10
                            String hr = "0" + txt1;
                            int hu = Integer.parseInt(frmIngreTiempo.jTextField1.getText());
                            int mi = Integer.parseInt(frmIngreTiempo.jTextField2.getText());
                            if (hu <= 99 && mi <= 59) {
                                String minUni = frmIngreTiempo.jTextField2.getText().substring(1);
                                String minDec = frmIngreTiempo.jTextField2.getText();
                                char mini;
                                mini = minDec.charAt(0);
                                String minus = String.valueOf(mini);
                                System.out.println("el valor de mini " + mini);
                                minutosDecenas = Integer.parseInt(minus);
                                minutosUnidad = Integer.parseInt(minUni);
                                String horUni = hr.substring(1);
                                String hoDec = hr;
                                char hor;
                                hor = hoDec.charAt(0);
                                String horus = String.valueOf(hor);
                                horasUnidad = Integer.valueOf(horUni);
                                horasDecenas = Integer.parseInt(horus);
                                // iniciar();
                                //frmnumeros.setVisible(true);
                                this.frmnumeros.minutosUnidad.setText(String.valueOf(minutosUnidad));
                                this.frmnumeros.minutosDecenas.setText(String.valueOf(minutosDecenas));
                                this.frmnumeros.horasDecenas.setText(String.valueOf(horasDecenas));
                                this.frmnumeros.horasUnidad.setText(String.valueOf(horasUnidad));
                                this.frmbotones.jlHoDePe.setText(String.valueOf(horasDecenas));
                                this.frmbotones.jLHoUPe.setText(String.valueOf(horasUnidad));
                                this.frmbotones.jlMinDePeq.setText(String.valueOf(minutosDecenas));
                                this.frmbotones.jLMinUPe.setText(String.valueOf(minutosUnidad));;

                            }
                        }//fin if de  horas unidad y minutos mas de 10

                    }
                }//if matches
                else {
                    String errNu = "INGRESE NÚMEROS";
                    frmErr();
                    this.frmErrores.jlbError.setText(errNu);

                    System.out.println("Ingrese numeros");
                }

            }//if de los campos vacios 
            else {

                String errDat = "DATOS VACIOS";
                frmErr();
                this.frmErrores.jlbError.setText(errDat);

            }
        
        
        
    }
    

    private void devuelta() // boton reinicio
    {

        //frmbotones.setVisible(false);
        if ((segundosDecena == 0 && segundosUnidad == 0) && (minutosDecenas == 0 && minutosUnidad == 0) && (horasUnidad == 0 && horasDecenas == 0)) {
            cronometro.stop();
            segundosUnidad = 0;
            segundosDecena = 0;
            minutosUnidad = 0;
            minutosDecenas = 0;
            horasUnidad = 0;
            horasDecenas = 0;
            this.frmnumeros.segundosUnidad.setText(String.valueOf(segundosUnidad));
            this.frmnumeros.segundosDecenas.setText(String.valueOf(segundosDecena));
            this.frmnumeros.minutosUnidad.setText(String.valueOf(minutosUnidad));
            this.frmnumeros.minutosDecenas.setText(String.valueOf(minutosDecenas));
            this.frmnumeros.horasDecenas.setText(String.valueOf(horasUnidad));
            this.frmnumeros.horasUnidad.setText(String.valueOf(horasDecenas));
            this.frmbotones.jlHoDePe.setText(String.valueOf(horasDecenas));
            this.frmbotones.jLHoUPe.setText(String.valueOf(horasUnidad));
            this.frmbotones.jlMinDePeq.setText(String.valueOf(minutosDecenas));
            this.frmbotones.jLMinUPe.setText(String.valueOf(minutosUnidad));
            this.frmbotones.jlSegDePe.setText(String.valueOf(segundosDecena));
            this.frmbotones.jlSegUnPeq.setText(String.valueOf(segundosUnidad));
        }
        frmbotones.dispose();
        frmbotones = new FormularioBotones();
        frmbotones.dispose();
        frmtexto.dispose();

        frmIngreTiempo();
        cronometro.stop();
        segundosUnidad = 0;
        segundosDecena = 0;
        minutosUnidad = 0;
        minutosDecenas = 0;
        horasUnidad = 0;
        horasDecenas = 0;
        this.frmnumeros.segundosUnidad.setText(String.valueOf(segundosUnidad));
        this.frmnumeros.segundosDecenas.setText(String.valueOf(segundosDecena));
        this.frmnumeros.minutosUnidad.setText(String.valueOf(minutosUnidad));
        this.frmnumeros.minutosDecenas.setText(String.valueOf(minutosDecenas));
        this.frmnumeros.horasDecenas.setText(String.valueOf(horasUnidad));
        this.frmnumeros.horasUnidad.setText(String.valueOf(horasDecenas));
        this.frmbotones.jlHoDePe.setText(String.valueOf(horasDecenas));
        this.frmbotones.jLHoUPe.setText(String.valueOf(horasUnidad));
        this.frmbotones.jlMinDePeq.setText(String.valueOf(minutosDecenas));
        this.frmbotones.jLMinUPe.setText(String.valueOf(minutosUnidad));
        this.frmbotones.jlSegDePe.setText(String.valueOf(segundosDecena));
        this.frmbotones.jlSegUnPeq.setText(String.valueOf(segundosUnidad));
        frmbotones.execute.setEnabled(true);
        this.frmbotones.execute.setText("INICIO");
        frmbotones.parar.setEnabled(false);

    }

    private void deten()//boton parar
    {
        cronometro.stop();
        frmbotones.execute.setEnabled(true);
        this.frmbotones.execute.setText("INICIO");
        frmbotones.jbreinicio.setEnabled(false);
        frmbotones.parar.setEnabled(false);

    }

    public void hora() {
        Calendar calendario = new GregorianCalendar();
        Date fechaHoraActual = new Date();
        calendario.setTime(fechaHoraActual);
        horas = calendario.get(Calendar.HOUR_OF_DAY) > 9 ? "" + calendario.get(Calendar.HOUR_OF_DAY) : "0" + calendario.get(Calendar.HOUR_OF_DAY);
        minutos = calendario.get(Calendar.MINUTE) > 9 ? "" + calendario.get(Calendar.MINUTE) : "0" + calendario.get(Calendar.MINUTE);
        segundos = calendario.get(Calendar.SECOND) > 9 ? "" + calendario.get(Calendar.SECOND) : "0" + calendario.get(Calendar.SECOND);
        //System.out.println("hora"+horas+"minutos"+minutos+"segundos"+segundos);
        //System.out.println("hora"+horas+"minutos"+minutos+"segundos"+segundos);
        // System.out.println("hora"+horas+"minutos"+minutos+"segundos"+segundos);
        frmnumeros.lhora.setText(horas + ":" + minutos + ":" + segundos);
        frmbotones.jlHoraPeq.setText(horas + ":" + minutos + ":" + segundos);

    }

    @Override
    public void run() {
        Thread ct = Thread.currentThread();
        while (ct == hilo) {
            hora();
            System.out.println("hora" + horas + "minutos" + minutos + "segundos" + segundos);
            //this.frmnumeros.reloj.setText(String.valueOf(horas));  
            //frmnumeros.reloj.setText(horas + ":" + minutos + ":" + segundos);  
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
        //System.out.println("hora"+horas+"minutos"+minutos+"segundos"+segundos);
        // frmnumeros.reloj.setText(horas + ":" + minutos + ":" + segundos);

    }

   
}//fin de clase TimerExe
