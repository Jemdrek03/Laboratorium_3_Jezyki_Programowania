import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class ShoppingList {
    private DefaultListModel<String> listModel;
    private JList<String> shoppingList;
    private JTextField productNameTextField;
    private JButton addButton;
    private JButton removeButton;

    public ShoppingList() {
        //tworzenie pustego modelu uzywanego do przechowywania elementow
        listModel = new DefaultListModel<>();
        //Wykorzystywany jest do wyswietlania listy, z ktora jest polaczony
        shoppingList = new JList<>(listModel);

        //Stworzenie pola tekstowego i dwoch przyciskow
        productNameTextField = new JTextField();
        addButton = new JButton("Add");
        removeButton = new JButton("Remove");

        //Dodanie dwoch przyciskow odpowiadajacych za dodanie i usuniecie produktu
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addProduct();
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeProduct();
            }
        });
    }

    //Tworzenie i wyswietlenie GUI
    public void createAndShowGUI() {
        //Tworzenie frame, o danym rozmiarze, ustawienie zamkniecia w momencie
        //gdy uzytkownik zamknie okno aplikacji, oraz ustawienie jej lokalizacji
        //na srodku ekranu
        JFrame frame = new JFrame("Shopping List");
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);


        //Tworzenie panelu, gdzie znajduje sie pole tekstowe i dwa przyciski
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(productNameTextField, BorderLayout.PAGE_START);
        panel.add(addButton, BorderLayout.LINE_START);
        panel.add(removeButton, BorderLayout.LINE_END);

        //Ustawienie gdzie wyswietla sie lista i panel
        frame.add(shoppingList, BorderLayout.CENTER);
        frame.add(panel, BorderLayout.PAGE_END);
        frame.setVisible(true);
    }

    private void addProduct() {
        //Pobranie do product tekstu wpisanego przez uzytkownika
        String product = productNameTextField.getText();
        //Sprawdzenie czy pole nie jest puste
        if (!product.isEmpty()) {
            //Dodanie elemntu do listy
            listModel.addElement(product);
            productNameTextField.setText("");
        }
    }

    private void removeProduct() {

        //pobranie indeksu zaznaczanego elementu z listy
        int selectedIndices = shoppingList.getSelectedIndex();
        //usuwanie zaznaczonego elementu
        listModel.removeElementAt(selectedIndices);
    }
}

