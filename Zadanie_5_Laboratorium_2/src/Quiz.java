public class Quiz {
    private String[] questions;
    private String[] correctAnswers;
    private int currentQuestionIndex;
    private int score;

    public Quiz() {
        //tablica przechowująca pytania
        questions = new String[]{
                "Pytanie 1: Jaki jest stolica Polski?",
                "Pytanie 2: Ile wynosi 7 + 25?",
                "Pytanie 3: Który kraj jest największy pod względem powierzchni?",
                "Pytanie 4: Jakie mięso smaży się najszybciej",
        };

        //tablica przechowująca odpowiedzi
        correctAnswers = new String[]{
                "Warszawa",
                "32",
                "Rosja",
                "Kurczak",
        };

        currentQuestionIndex = 0;
        score = 0;
    }

    //metoda zwracajaca aktualne pytanie
    public String getCurrentQuestion() {
        return questions[currentQuestionIndex];
    }

    //metoda sprawdzajaca czy quiz sie skonczyl
    public boolean isQuizOver() {
        return currentQuestionIndex >= questions.length;
    }


    //metoda sprawdzajaca czy odpowiedz jest poprawna
    public void checkAnswer(String userAnswer) {
        if (!isQuizOver() && userAnswer.equalsIgnoreCase(correctAnswers[currentQuestionIndex])) {
            score++;
        }
        currentQuestionIndex++;
    }


    //gettery
    public int getScore() {
        return score;
    }

    public int getLenght()
    {
        return questions.length;
    }
}