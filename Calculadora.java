import com.sun.org.apache.regexp.internal.RE;

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
    private JButton RaizButton;
    private JButton a7Button;
    private JButton a8Button;
    private JButton a9Button;
    private JButton logButton;
    private JButton a0Button;
    private JButton decimal;
    private JButton sinButton;
    private JButton equal;
    private JButton cosButton;
    private JButton porcentajeButton;
    private JPanel PanelPrincipal;
    private JPanel Botones;
    private Map<String, JDialog> dialogs = new HashMap<>();
    private final JButton[] numeros = new JButton[] {
            a1Button, a2Button, a3Button, a4Button, a5Button, a6Button, a7Button ,a8Button, a9Button, a0Button
    };
    private final JButton[] simples = new JButton[] {
            sumar, restar, multiplicar, dividir
    };

    public Calculadora() {
        TipoOperacion.addItem("Operaciones simples");
        TipoOperacion.addItem("Romanos");
        TipoOperacion.addItem("Notacion RPN");
        TipoOperacion.addItem("Vectores y Matrices");
        TipoOperacion.addItem("Polinomios");
        TipoOperacion.addItem("Estadisticas");
        TipoOperacion.addItem("Cambio de unidades");
        TipoOperacion.addItem("Cambio de moneda");
        TipoOperacion.addItem("Funciones");

        Base.addItem("Decimal");
        Base.addItem("Octal");
        Base.addItem("Hexadecimal");

        Historial.addItem("Mostrar Keypad");
        Historial.addItem("Ocultar Keypad");

        dialogs.put("Cambio de moneda", new CambioDeDivisas());
        dialogs.put("Cambio de unidades", new CambioUnidades());
        dialogs.put("Funciones",new Fx());

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
            if (Objects.equals(TipoOperacion.getSelectedItem().toString(), "Romanos")) {
                Resultado.setText(convertirANumerosRomanos());
            }
            String resultado = String.valueOf(operacionesSimples());
            Resultado.setText(resultado);
        });

        expButton.addActionListener(e -> {
            try {
                int exp = Math.getExponent(Integer.parseInt(Operaciones.getText()));
                Resultado.setText(String.valueOf(exp));
            } catch (Exception e1){
                JOptionPane.showMessageDialog(null,"No puedes dejar el campo vacío","Error",JOptionPane.ERROR_MESSAGE);
            }
        });

        DELButton.addActionListener(e -> {
            Operaciones.setText("");
            Resultado.setText("");
        });

        sinButton.addActionListener(e -> {
            try {
                double seno = Math.sin(Double.parseDouble(Operaciones.getText()));
                Resultado.setText(String.valueOf(seno));
            } catch (Exception e1){
                JOptionPane.showMessageDialog(null,"No puedes dejar el campo vacío","Error",JOptionPane.ERROR_MESSAGE);
            }
        });

        cosButton.addActionListener(e -> {
            try {
                double cos = Math.cos(Double.parseDouble(Operaciones.getText()));
                Resultado.setText(String.valueOf(cos));
            } catch (Exception e1){
                JOptionPane.showMessageDialog(null,"No puedes dejar el campo vacío","Error",JOptionPane.ERROR_MESSAGE);
            }
        });

        RaizButton.addActionListener(e -> {
            try {
                double raiz = Math.sqrt(Double.parseDouble(Operaciones.getText()));
                Resultado.setText(String.valueOf(raiz));
            } catch (Exception e1){
                JOptionPane.showMessageDialog(null,"No puedes dejar el campo vacío","Error",JOptionPane.ERROR_MESSAGE);
            }
        });

        xButton.addActionListener(e ->{
            int numero = Integer.parseInt(Operaciones.getText());
            int  op = 1;
            while (numero != 0){
                op=op*numero;
                numero--;
            }
            Operaciones.setText(Operaciones.getText()+"!");
            Resultado.setText(String.valueOf(op));
        });

        Historial.addActionListener(e -> {
            if (Objects.equals(Historial.getSelectedItem().toString(), "Ocultar Keypad")){
                Botones.setVisible(false);
            } else {
                Botones.setVisible(true);
            }
        });


        //TipoOperacion.addActionListener(actionEvent -> addActions());
        TipoOperacion.addItemListener(e -> {
            if(Objects.equals(TipoOperacion.getSelectedItem(), e.getItem())) {
                try {
                    JDialog dialog = dialogs.get(e.getItem());
                    dialog.pack();
                    dialog.setVisible(true);
                } catch (Exception ex) {
                    // TODO: Implementar en calculadora sin dialogo

                }
            }
        });
    }

    public void mostrar() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.add(PanelPrincipal);
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

    public String convertirANumerosRomanos() {
        int numero = Integer.parseInt(Operaciones.getText());
        int i, miles, centenas, decenas, unidades;
        StringBuilder romano = new StringBuilder();
        //obtenemos cada cifra del número
        miles = numero / 1000;
        centenas = numero / 100 % 10;
        decenas = numero / 10 % 10;
        unidades = numero % 10;

        //millar
        for (i = 1; i <= miles; i++) {
            romano.append("M");
        }

        //centenas
        if (centenas == 9) {
            romano.append("CM");
        } else if (centenas >= 5) {
            romano.append("D");
            for (i = 6; i <= centenas; i++) {
                romano.append("C");
            }
        } else if (centenas == 4) {
            romano.append("CD");
        } else {
            for (i = 1; i <= centenas; i++) {
                romano.append("C");
            }
        }

        //decenas
        if (decenas == 9) {
            romano.append("XC");
        } else if (decenas >= 5) {
            romano.append("L");
            for (i = 6; i <= decenas; i++) {
                romano.append("X");
            }
        } else if (decenas == 4) {
            romano.append("XL");
        } else {
            for (i = 1; i <= decenas; i++) {
                romano.append("X");
            }
        }

        //unidades
        if (unidades == 9) {
            romano.append("IX");
        } else if (unidades >= 5) {
            romano.append("V");
            for (i = 6; i <= unidades; i++) {
                romano.append("I");
            }
        } else if (unidades == 4) {
            romano.append("IV");
        } else {
            for (i = 1; i <= unidades; i++) {
                romano.append("I");
            }
        }
        return romano.toString();
    }

    public static void main(String[] args) {
        Calculadora c = new Calculadora();
        c.mostrar();
    }
}
