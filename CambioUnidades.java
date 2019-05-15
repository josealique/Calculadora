import sun.security.krb5.internal.crypto.Des;

import javax.swing.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class CambioUnidades extends JDialog {
    private JPanel contentPane;
    private JButton Convertir;
    private JButton Cancelar;
    private JTextField Origen;
    private JTextField Destino;
    private JComboBox<String> UnidadPrincipal;
    private JComboBox<String> Prefijo;
    Map<String,Double> mapa = new HashMap<>();
    double mega = 1_000_000;
    double kilo = 1000;
    double hecto = 100;
    double deca = 10;
    double deci = 0.1;
    double centi = 0.01;
    double mili = 0.001;
    double micro = 0.000_001;

    public CambioUnidades() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(Convertir);


        UnidadPrincipal.addItem("Metro");
        UnidadPrincipal.addItem("Gramo");
        UnidadPrincipal.addItem("Litro");

        Prefijo.addItem("mega");
        Prefijo.addItem("kilo");
        Prefijo.addItem("hecto");
        Prefijo.addItem("deca");
        Prefijo.addItem("deci");
        Prefijo.addItem("centi");
        Prefijo.addItem("mili");
        Prefijo.addItem("micro");

        mapa.put("mega",mega);
        mapa.put("kilo",kilo);
        mapa.put("hecto",hecto);
        mapa.put("deca",deca);
        mapa.put("deci",deci);
        mapa.put("centi",centi);
        mapa.put("mili",mili);
        mapa.put("micro",micro);


        Convertir.addActionListener(e -> cambiarUnidad());

        Cancelar.addActionListener(e -> onCancel());

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

    private void cambiarUnidad() {
        double cantidad = Double.parseDouble(Origen.getText());
        double resultado = 0.0;
        resultado = cantidad / mapa.get(Prefijo.getSelectedItem().toString());
        Destino.setText(String.valueOf(resultado) + " " + Prefijo.getSelectedItem().toString() + UnidadPrincipal.getSelectedItem().toString().toLowerCase() + "s");
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        CambioUnidades dialog = new CambioUnidades();
        dialog.pack();
        dialog.setVisible(true);
    }
}
