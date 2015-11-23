package model;

public class Question {

    private String enunciat;
    private String[] answers;
    private int correctAnswerIndex;
    private int questionValue;

    public Question(String enunciat, String[] answers, int correctAnswerIndex, int questionValue) {

        this.enunciat = enunciat;
        this.answers = answers;
        this.correctAnswerIndex = correctAnswerIndex;
        this.questionValue = questionValue;

    }

    public String getStatement() {
        return this.enunciat;
    }

    public boolean comprovarRespuesta(int respuestaSeleccionada) {
        if (correctAnswerIndex == respuestaSeleccionada) {
            return true;
        } else {
            return false;
        }
    }

    public String[] getAnswers() {
        return this.answers;
    }

    public int getQuestionValue() {
        return this.questionValue;
    }
    
    public int getCorrectIndexValue(){
        return this.correctAnswerIndex;
    }
}
