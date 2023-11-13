import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class BMI {
    private JFrame frame;
    private JTextField weightField;
    private JTextField heightField;
    private JLabel resultField;


    public static double calculateBMI(double weight, double height)
    {
        //Wzor na bmi: waga(kg) / (wzrost * wzrost)(m)
        return weight / ( height * height);
    }
    public BMI() {
        //Inicjalizacja frame z wszystkimi parametrami
        frame = new JFrame("BMI Calculator");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(4, 2));
        frame.setLocationRelativeTo(null);

        //inicjalizacja etykiet i pol tekstowych
        JLabel weightLabel = new JLabel("Waga (kg):");
        weightField = new JTextField();
        JLabel heightLabel = new JLabel("Wzrost (m):");
        heightField = new JTextField();

        //inicjalizacja przycisku i pola wynikowego
        JButton calculateButton = new JButton("Oblicz");
        resultField = new JLabel();

        //dodanie do frame
        frame.add(weightLabel);
        frame.add(weightField);
        frame.add(heightLabel);
        frame.add(heightField);
        frame.add(calculateButton);
        frame.add(resultField);

        //metoda "sluchajaca"
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //pobranie wagi i wzrostu
                    double weight = Double.parseDouble(weightField.getText());
                    double height = Double.parseDouble(heightField.getText());

                    //obliczenie bmi za pomaca metody
                    double bmi = BMI.calculateBMI(weight, height);

                    //Ustawienie tekstu wynikowego
                    resultField.setText("BMI: " + String.format("%.2f",bmi));
                } catch (NumberFormatException ex) {
                    resultField.setText("Wprowadź poprawne dane.");
                }
            }
        });

    }
    public void display() {
        frame.setVisible(true);
    }
}
