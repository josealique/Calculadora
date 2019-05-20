import javax.swing.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

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
    private Map<String,Double> map = new HashMap<>();
//    private double floor = Math.floor(Double.parseDouble(Operacion1.getText()));
//    private double round = Math.round(Double.parseDouble(Operacion1.getText()));
//    private double abs = Math.abs(Integer.parseInt(Operacion1.getText()));
//    private double log = Math.log(Double.parseDouble(Operacion1.getText()));
//    private double pi = Math.PI;

    public Fx() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        opciones.addItem("floor()");
        opciones.addItem("round()");
        opciones.addItem("abs()");
        opciones.addItem("log()");
        opciones.addItem("pi(π)");

//        map.put("floor()",floor);
//        map.put("round()",round);
//        map.put("abs()",abs);
//        map.put("log()",log);
//        map.put("pi(π)",pi);

        buttonOK.addActionListener(e -> funciones());

        buttonCancel.addActionListener(e -> onCancel());

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

    private void funciones(){
        if (opciones.getSelectedItem().toString() == "floor()"){
//            Resultado.setText("floor("+floor+")");
        }
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
