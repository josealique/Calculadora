import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class Fx extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox<String> opciones;
    private JLabel Funcion;
    private JTextField Resultado1;
    private JLabel Resultado;
    private JTextField Operacion1;
    private JLabel Operacion;
    private JButton borrarButton;
    private String resultado = "";

    public Fx() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        opciones.addItem("floor()");
        opciones.addItem("round()");
        opciones.addItem("tan()");
        opciones.addItem("log()");
        opciones.addItem("cbrt()");

        buttonOK.addActionListener(e -> funciones());

        buttonCancel.addActionListener(e -> onCancel());

        borrarButton.addActionListener(e -> borrarTexto());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void borrarTexto() {
        Operacion1.setText("");
        Resultado1.setText("");
    }

    private void funciones() {
        int seleccion = opciones.getSelectedIndex();
        switch (seleccion) {
            case 0:
                try{
                    Double floor = Math.floor(Double.parseDouble(Operacion1.getText()));
                    Operacion1.setText("floor("+Operacion1.getText()+")");
                    resultado = String.valueOf(floor);
                    Resultado1.setText(resultado);
                } catch (Exception e){
                    JOptionPane.showMessageDialog(null,"Error en la función","Error",JOptionPane.ERROR_MESSAGE);
                }
                break;
            case 1:
                try{
                    Double round = (double) Math.round(Double.parseDouble(Operacion1.getText()));
                    resultado = String.valueOf(round);
                    Resultado1.setText(resultado);
                }  catch (Exception e){
                    JOptionPane.showMessageDialog(null,"Error en la función","Error",JOptionPane.ERROR_MESSAGE);
                }
                break;
            case 2:
                try {
                    Resultado1.setText(resultado);
                    Double tan = Math.tan(Double.parseDouble(Operacion1.getText()));
                    Operacion1.setText("tan("+Operacion1.getText()+")");
                    resultado = String.valueOf(tan);
                } catch (Exception e){
                    JOptionPane.showMessageDialog(null,"Error en la función","Error",JOptionPane.ERROR_MESSAGE);
                }
                break;
            case 3:
                try {
                    Double log = Math.log(Double.parseDouble(Operacion1.getText()));
                    Operacion1.setText("log("+Operacion1.getText()+")");
                    resultado = String.valueOf(log);
                    Resultado1.setText(resultado);
                } catch (Exception e){
                    JOptionPane.showMessageDialog(null,"Error en la función","Error",JOptionPane.ERROR_MESSAGE);
                }
                break;
            case 4:
                try{
                    Double cbrt = Math.cbrt(Double.parseDouble(Operacion1.getText()));
                    Operacion1.setText("cbrt("+Operacion1.getText()+")");
                    resultado = String.valueOf(cbrt);
                    Resultado1.setText(resultado);
                } catch (Exception e){
                    JOptionPane.showMessageDialog(null,"Error en la función","Error",JOptionPane.ERROR_MESSAGE);
                }
                break;
        }
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}