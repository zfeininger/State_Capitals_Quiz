package edu.uga.cs.statecapitalsquiz;

public class readQuizzes {
    private long id;
    private String quizDate;
    private int question1;
    private int question2;
    private int question3;
    private int question4;
    private int question5;
    private int question6;
    private int number_correct_answers;
    private int number_completed_answers;

    public readQuizzes() {
        this.id = -1;
        this.quizDate = null;
        this.question1 = 0;
        this.question2 = 0;
        this.question3 = 0;
        this.question4 = 0;
        this.question5 = 0;
        this.question6 = 0;
        this.number_correct_answers = 0;
        this.number_completed_answers = 0;
    }

    public readQuizzes(String quizDate, int question1, int question2, int question3, int question4, int question5, int question6, int number_correct_answers, int number_completed_answers) {
        this.id = -1;
        this.quizDate = quizDate;
        this.question1 = question1;
        this.question2 = question2;
        this.question3 = question3;
        this.question4 = question4;
        this.question5 = question5;
        this.question6 = question6;
        this.number_correct_answers = number_correct_answers;
        this.number_completed_answers = number_completed_answers;
    }

    public long getIdSecondDB() {return id;}
    public void setIdSecondDB(long id) {this.id = id;}
    public String getQuizDate() {return quizDate;}
    public void setQuizDate(String quizDate) {this.quizDate = quizDate;}
    public int getQuestion1() {return question1;}
    public void setQuestion1(int question1) {this.question1 = question1;}
    public int getQuestion2() {return question2;}
    public void setQuestion2(int question2) {this.question2 = question2;}
    public int getQuestion3() {return question3;}
    public void setQuestion3(int question3) {this.question3 = question3;}
    public int getQuestion4() {return question4;}
    public void setQuestion4(int question4) {this.question4 = question4;}
    public int getQuestion5() {return question5;}
    public void setQuestion5(int question5) {this.question5 = question5;}
    public int getQuestion6() {return question6;}
    public void setQuestion6(int question6) {this.question6 = question6;}
    public int getNumber_correct_answers() {return number_correct_answers;}
    public void setNumber_correct_answers(int number_correct_answers) {this.number_correct_answers = number_correct_answers;}
    public int getNumber_completed_answers() {return number_completed_answers;}
    public void setNumber_completed_answers(int number_completed_answers) {this.number_completed_answers = number_completed_answers;}

    public String toString() {
        return id + ": " + quizDate + " " + question1 + " " + question2 + " " + question3 + " " + question4 + " " + question5 + " " + question6 + " " + number_correct_answers + " " + number_completed_answers;
    }


}
