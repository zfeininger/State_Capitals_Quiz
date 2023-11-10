package edu.uga.cs.statecapitalsquiz;

/*
 * This readQuizzes class is essential in storing data about each quiz instance into the database.
 */
public class readQuizzes {
    private long id;
    private String quizDate;
    private String question1;
    private String question2;
    private String question3;
    private String question4;
    private String question5;
    private String question6;
    private int number_correct_answers;
    private int number_completed_answers;

    public readQuizzes() {
        this.id = -1;
        this.quizDate = null;
        this.question1 = null;
        this.question2 = null;
        this.question3 = null;
        this.question4 = null;
        this.question5 = null;
        this.question6 = null;
        this.number_correct_answers = 0;
        this.number_completed_answers = 0;
    }

    public readQuizzes(String quizDate, String question1, String question2, String question3, String question4, String question5, String question6, int number_correct_answers, int number_completed_answers) {
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
    public String getQuestion1() {return question1;}
    public void setQuestion1(String question1) {this.question1 = question1;}
    public String getQuestion2() {return question2;}
    public void setQuestion2(String question2) {this.question2 = question2;}
    public String getQuestion3() {return question3;}
    public void setQuestion3(String question3) {this.question3 = question3;}
    public String getQuestion4() {return question4;}
    public void setQuestion4(String question4) {this.question4 = question4;}
    public String getQuestion5() {return question5;}
    public void setQuestion5(String question5) {this.question5 = question5;}
    public String getQuestion6() {return question6;}
    public void setQuestion6(String question6) {this.question6 = question6;}
    public int getNumber_correct_answers() {return number_correct_answers;}
    public void setNumber_correct_answers(int number_correct_answers) {this.number_correct_answers = number_correct_answers;}
    public int getNumber_completed_answers() {return number_completed_answers;}
    public void setNumber_completed_answers(int number_completed_answers) {this.number_completed_answers = number_completed_answers;}

    public String toString() {
        return id + ": " + quizDate + " " + question1 + " " + question2 + " " + question3 + " " + question4 + " " + question5 + " " + question6 + " " + number_correct_answers + " " + number_completed_answers;
    }


}
