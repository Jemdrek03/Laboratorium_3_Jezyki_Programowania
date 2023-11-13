import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GUI extends Main {
    private JFrame frame;
    private JPanel panel;
    private JTextField colorInput;


    public void createAndShowGUI() {

        //Tworzenie Frame
        frame = new JFrame("Color Change App");
        //Zakonczenie dzialania aplikacji po zamknieciu wyskakujacego okna
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //ustawienie wielkosci a nastepnie lokalizacji okna
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);
        //Tworzenie Panelu
        panel = new JPanel();
        frame.add(panel);


        JPanel inputPanel = new JPanel();
        panel.add(inputPanel, BorderLayout.CENTER);
        inputPanel.setLayout(new BorderLayout());

        JLabel label = new JLabel("Zmień kolor na:");
        inputPanel.add(label, BorderLayout.NORTH);

        colorInput = new JTextField(20);
        inputPanel.add(colorInput, BorderLayout.CENTER);

        JButton confirmChange = new JButton("Potwierdź");
        panel.add(confirmChange, BorderLayout.EAST);



        //metoda zmieniajaca kolor tla
        confirmChange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String colorName = colorInput.getText().toLowerCase();
                Color color = getColorFromName(colorName);

                if (color != null) {
                    panel.setBackground(color);
                } else {
                    JOptionPane.showMessageDialog(frame, "Nieprawidłowa nazwa koloru!", "Błąd", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frame.setVisible(true);
    }

    //Metoda zwracajaca kod koloru
    private Color getColorFromName(String colorName) {
        switch (colorName) {
            case "czerwony":
                return Color.RED;
            case "zielony":
                return Color.GREEN;
            case "niebieski":
                return Color.BLUE;
            default:
                return null;
        }
    }
}

