import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class QuizApp {
    private JFrame frame;
    private JLabel questionLabel;
    private JTextField answerField;
    private JButton submitButton;
    private JLabel resultLabel;
    private Quiz quiz;

    public QuizApp() {
        //tworzenie frame z danymi ustawieniami
        frame = new JFrame("Quiz App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new GridLayout(4, 1));
        frame.setLocationRelativeTo(null);

        //Tworzenie obiektu quiz
        quiz = new Quiz();

        //Tworzenie dwoch obszarow z tesktem, pola tekstowego i przycisku
        questionLabel = new JLabel();
        answerField = new JTextField();
        submitButton = new JButton("Potwierdź");
        resultLabel = new JLabel();


        //Tworzenie metody "sluchajacej"
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Sprawdzenie czy quiz sie nie zakonczyl
                if (!quiz.isQuizOver()) {
                    //Pobranie odpowiedzi od uzytkownika
                    String userAnswer = answerField.getText();
                    //Sprawdzenie czy odpowiedz jest poprawna
                    quiz.checkAnswer(userAnswer);
                    //Ponowne sprawdzenie czy quiz sie nie zakonczyl
                    //Tym razem po sprawdzeniu odpowiedzi
                   if (!quiz.isQuizOver()) {
                       //Aktualizacja etykiety i pola tekstowego
                        questionLabel.setText(quiz.getCurrentQuestion());
                        answerField.setText("");
                   }
                else {
                    //Wyswietlenie wyniku i dezaktywacja przycisku
                        resultLabel.setText("Wynik: " + quiz.getScore() + "/" + quiz.getLenght());
                        submitButton.setEnabled(false);
                    }
                }
            }
        });

        //Dodanie do frame
        frame.add(questionLabel);
        frame.add(answerField);
        frame.add(submitButton);
        frame.add(resultLabel);

        //uruchomienie quizu, wyswietlenie pierwszego pytania
        startQuiz();
    }


    public void startQuiz() {
        questionLabel.setText(quiz.getCurrentQuestion());
    }

    //wyswietlenie okna aplikacji
    public void display() {
        frame.setVisible(true);
    }
}