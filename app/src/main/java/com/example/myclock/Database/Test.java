package com.example.myclock.Database;

public class Test {
    private int total;
    private int wrong;
    private int correct;
    private Double time;

    public Test(int total, int wrong, int correct, Course course) {
        this.total = total;
        this.wrong = wrong;
        this.correct = correct;
    }

    public void addToCurrentTest (Test test) {
        this.total += test.total;
        this.wrong += test.wrong;
        this.correct += test.correct;
    }

    public Double getTime() {
        return time;
    }

    public int getCorrect() {
        return correct;
    }

    public int getTotal() {
        return total;
    }

    public int getWrong() {
        return wrong;
    }
}
