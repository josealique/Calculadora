import javax.swing.*;
import java.util.*;

public class Calculadora {
    private JTextField Operaciones;
    private JTextField Resultado;
    private JComboBox<String> TipoOperacion;
    private JComboBox<String> Base;
    private JComboBox<String> Historial;
    private JButton a1Button;
    private JButton xButton;
    private JButton a2Button;
    private JButton a3Button;
    private JButton expButton;
    private JButton DELButton;
    private JButton a4Button;
    private JButton a5Button;
    private JButton a6Button;
    private JButton multiplicar;
    private JButton dividir;
    private JButton sumar;
    private JButton restar;
    private JButton inButton;
    private JButton a7Button;
    private JButton a8Button;
    private JButton a9Button;
    private JButton logButton;
    private JButton a0Button;
    private JButton decimal;
    private JButton sinButton;
    private JButton equal;
    private JButton cosButton;
    private JButton fXButton;
    private JPanel Coso;
    private JPanel Botones;
    private Map<String, JDialog> dialogs = new HashMap<>();
    private final JButton[] numeros = new JButton[] {
            a1Button, a2Button, a3Button, a4Button, a5Button, a6Button, a7Button ,a8Button, a9Button, a0Button
    };
    private final JButton[] simples = new JButton[] {
            sumar, restar, multiplicar, dividir
    };

    public Calculadora() {
        TipoOperacion.addItem("Operaciones simples"); // index 0
        TipoOperacion.addItem("Romanos"); // index 1
        TipoOperacion.addItem("Notacion RPN"); // index 2
        TipoOperacion.addItem("Vectores y Matrices"); // index 3
        TipoOperacion.addItem("Polinomios"); // index 4
        TipoOperacion.addItem("Estadisticas"); // index 5
        TipoOperacion.addItem("Cambio de unidades"); // index 6
        TipoOperacion.addItem("Cambio de moneda"); // index 7

        Base.addItem("Decimal");
        Base.addItem("Octal");
        Base.addItem("Hexadecimal");

        Historial.addItem("Historial");

        dialogs.put("Cambio de moneda", new CambioDeDivisas());
        dialogs.put("Cambio de unidades", new CambioUnidades());

        for (JButton boton : numeros) {
            boton.addActionListener(e -> {
                String txt = Operaciones.getText() + boton.getText();
                Operaciones.setText(txt);
            });
        }

        for (JButton boton: simples){
            boton.addActionListener(e -> {
                String string =
                        Operaciones.getText() + " " + boton.getText() + " ";
                Operaciones.setText(string);
            });
        }
        equal.addActionListener(e -> {
            String resultado = String.valueOf(operacionesSimples());
            Resultado.setText(resultado);

        });

        DELButton.addActionListener(e -> {
            Operaciones.setText("");
            Resultado.setText("");
        });

        sinButton.addActionListener(e -> {
            double seno = Math.sin(Double.parseDouble(Operaciones.getText()));
            Resultado.setText(String.valueOf(seno));
        });

        cosButton.addActionListener(e -> {
            double cos = Math.cos(Double.parseDouble(Operaciones.getText()));
            Resultado.setText(String.valueOf(cos));
        });


        //TipoOperacion.addActionListener(actionEvent -> addActions());
        TipoOperacion.addItemListener(e -> {
            if(TipoOperacion.getSelectedItem().equals(e.getItem())) {
                JDialog dialog = dialogs.get(e.getItem());
                dialog.pack();
                dialog.setVisible(true);
            }
        });
    }

    public void mostrar() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.add(Coso);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }

    public int operacionesSimples(){
        String[] valores = Operaciones.getText().split(" ");
        int op = 0;
        for (int i = 0; i < valores.length; i++) {
            int valor1;
            String denom;
            int valor2;
            try {
                valor1 = Integer.parseInt(valores[i]);
                denom = valores[++i];
                valor2 = Integer.parseInt(valores[++i]);
            } catch (Exception e) {
                valor1 = op;
                denom = valores[i];
                valor2 = Integer.parseInt(valores[++i]);
            }
            if (denom.equals("+")){
                op = valor1 + valor2;
            } else if (denom.equals("-")){
                op = valor1 - valor2;
            } else if (denom.equals("*")){
                op = valor1 * valor2;
            } else {
                op = valor1 / valor2;
            }
        }
        return op;
    }

    public static void main(String[] args) {
        Calculadora c = new Calculadora();
        c.mostrar();
    }
}
