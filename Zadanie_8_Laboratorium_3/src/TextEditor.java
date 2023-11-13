import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
public class TextEditor {
    private JFrame frame;
    private JTextArea textArea;
    private JButton saveButton;
    private JButton loadButton;
    private JLabel errorLabel;

    public TextEditor()
    {
        //Inicjalizacja frame i jego parametrow
        frame = new JFrame("Text Editor");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        //Inicjalizacja pola tekstowego i dwoch przyciskow
        textArea = new JTextArea();
        saveButton = new JButton("Save");
        loadButton = new JButton("Load");

        //metody sluchajace save i load
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                saveTextToFile();
            }
        });

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                loadTextFromFile();;
            }
        });


        //dodanie panelu z przyciskami i miejscem na wyswietlenie poprawnego lub
        //nieudanego zapisu/wczytania pliku
        JPanel Panel = new JPanel();
        Panel.add(saveButton);
        Panel.add(loadButton);
        errorLabel = new JLabel();
        Panel.add(errorLabel);

        //ustawienie frame i dodanie panelu i pola tekstowego
        frame.setLayout(new BorderLayout());
        frame.add(new JScrollPane(textArea), BorderLayout.CENTER);
        frame.add(Panel, BorderLayout.SOUTH);


    }

    //metoda zapisujaca tekst do pliku
    private void saveTextToFile() {
        //wybranie miejsca do zapisu pliku
        JFileChooser fileChooser = new JFileChooser();
        //wyswietlenie okna dialogowego
        int returnValue = fileChooser.showSaveDialog(null);

        //sprawdzenie czy uzytkownik wybral opcje
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            try {
                //tworzenie pliku
                File file = fileChooser.getSelectedFile();
                //obiekt uzywany do zapisu tekstu do pliku z nim powiazanego
                FileWriter writer = new FileWriter(file);
                //zapisanie tekstu do pliku
                writer.write(textArea.getText());
                //zamkniecie writera
                writer.close();
                //komunikat o poprawnym zapisie do pliku
                errorLabel.setText("Plik zapisany.");
            } catch (IOException e) {
                e.printStackTrace();
                //komunikat bledu
                errorLabel.setText("Wystąpił błąd podczas zapisu pliku.");
            }
        }
    }

    //metoda wczytujaca tekst do aplikacji
    private void loadTextFromFile() {
        //wybranie pliku do odczytu
        JFileChooser fileChooser = new JFileChooser();
        //wyswietlenie okna dialogowego
        int returnValue = fileChooser.showOpenDialog(null);

        //sprawdzenie czy uzytkownik wybral opcje
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            try {
                //zapisanie pliku do zmiennej
                File file = fileChooser.getSelectedFile();
                //obiekt uzywany do odczytu pliku
                BufferedReader reader = new BufferedReader(new FileReader(file));
                //obiekt przechowujacy linie
                String line;
                //odczytywanie i dodanie do "content" tekstu
                //tym samym przepisanie zawartosci pliku
                StringBuilder content = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
                reader.close();
                textArea.setText(content.toString());
                //komunikat o poprawnym odczycie pliku
                errorLabel.setText("Plik odczytany");
            } catch (IOException e) {
                e.printStackTrace();
                //komunikat o bledzie
                errorLabel.setText("Wystąpił błąd podczas wczytywania pliku.");
            }
        }
    }
    public void display()
    {
        frame.setVisible(true);
    }
}
