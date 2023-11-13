import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Converter {
    private JFrame frame;
    private JComboBox<String> conversionTypeComboBox;
    private JComboBox<String> inputUnitComboBox;
    private JComboBox<String> outputUnitComboBox;
    private JTextField inputTextField;
    private JTextField resultTextField;

    private String[] conversionTypes = {"Length", "Mass", "Volume"};
    private String[] lengthUnits = {"Meters", "Centimeters", "Inches", "Feet"};
    private String[] massUnits = {"Kilograms", "Grams", "Pounds", "Ounces"};
    private String[] volumeUnits = {"Liters", "Milliliters", "Gallons", "Quarts"};

    public Converter() {
        //inicjalizacja frame i jego ustawien
        frame = new JFrame("Unit Converter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setLayout(new FlowLayout());
        frame.setLocationRelativeTo(null);

        //Label wybierajace, wczytujace i wynikowe
        JLabel conversionTypeLabel = new JLabel("Select Conversion Type:");
        conversionTypeComboBox = new JComboBox<>(conversionTypes);

        JLabel inputLabel = new JLabel("Input:");
        inputUnitComboBox = new JComboBox<>();
        inputTextField = new JTextField(10);

        JLabel outputLabel = new JLabel("Output:");
        outputUnitComboBox = new JComboBox<>();
        resultTextField = new JTextField(10);
        resultTextField.setEditable(false);


        //metoda "sluchajaca"
        conversionTypeComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateUnitComboBoxes();
            }
        });

        //inicjalizacja przycisku
        JButton convertButton = new JButton("Convert");
        convertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                convertUnits();
            }
        });

        //dodanie do frame
        frame.add(conversionTypeLabel);
        frame.add(conversionTypeComboBox);
        frame.add(inputLabel);
        frame.add(inputUnitComboBox);
        frame.add(inputTextField);
        frame.add(outputLabel);
        frame.add(outputUnitComboBox);
        frame.add(resultTextField);
        frame.add(convertButton);

        frame.pack();
        frame.setVisible(true);
    }

    private void updateUnitComboBoxes() {
        //pobranie wybranego typu konwersji
        String selectedConversionType = conversionTypeComboBox.getSelectedItem().toString();
        String[] units = {};

        //wczytanie do tablicy odpowiednich jednostek
        if (selectedConversionType.equals("Length")) {
            units = lengthUnits;
        } else if (selectedConversionType.equals("Mass")) {
            units = massUnits;
        } else if (selectedConversionType.equals("Volume")) {
            units = volumeUnits;
        }

        //aktualizacja zawartosci comboboxow
        inputUnitComboBox.setModel(new DefaultComboBoxModel<>(units));
        outputUnitComboBox.setModel(new DefaultComboBoxModel<>(units));
    }

    private void convertUnits() {
        //pobranie wybranych jednoistek
        String inputUnit = inputUnitComboBox.getSelectedItem().toString();
        String outputUnit = outputUnitComboBox.getSelectedItem().toString();
        double inputValue;

        //proba konwersji na typ double
        try {
            inputValue = Double.parseDouble(inputTextField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Invalid input value. Please enter a valid number.");
            return;
        }

        //zmienna wynikowa
        double result = 0.0;

        // (nie)wszystkie zamiany
        if (inputUnit.equals("Meters") && outputUnit.equals("Centimeters")) {
            result = inputValue * 100;
        } else if (inputUnit.equals("Centimeters") && outputUnit.equals("Meters")) {
            result = inputValue / 100;
        }
         else if (inputUnit.equals("Centimeters") && outputUnit.equals("Inches")) {
             result = inputValue / 2.54;
        } else if (inputUnit.equals("Inches") && outputUnit.equals("Centimeters")) {
            result = inputValue * 2.54;
        } else if (inputUnit.equals("Feet") && outputUnit.equals("Meters")) {
            result = inputValue * 0.3048;
        } else if (inputUnit.equals("Meters") && outputUnit.equals("Inches")) {
                result = inputValue / 254;
        } else if (inputUnit.equals("Meters") && outputUnit.equals("Feet")) {
             result = inputValue / 0.3048;
        } else if (inputUnit.equals("Kilograms") && outputUnit.equals("Grams")) {
            result = inputValue * 1000;
        } else if (inputUnit.equals("Grams") && outputUnit.equals("Kilograms")) {
            result = inputValue / 1000;
        } else if (inputUnit.equals("Pounds") && outputUnit.equals("Kilograms")) {
            result = inputValue * 0.453592;
        } else if (inputUnit.equals("Ounces") && outputUnit.equals("Grams")) {
            result = inputValue * 28.3495;
        } else if (inputUnit.equals("Liters") && outputUnit.equals("Milliliters")) {
            result = inputValue * 1000;
        } else if (inputUnit.equals("Milliliters") && outputUnit.equals("Liters")) {
            result = inputValue / 1000;
        } else if (inputUnit.equals("Gallons") && outputUnit.equals("Liters")) {
            result = inputValue * 3.78541;
        } else if (inputUnit.equals("Quarts") && outputUnit.equals("Liters")) {
            result = inputValue * 0.946353;
        }

        resultTextField.setText(Double.toString(result));
    }
    public void display()
    {
        frame.setVisible(true);
    }
}
