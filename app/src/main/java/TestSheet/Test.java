package TestSheet;

public class Test {
    private int correct = 0;
    private int wrong = 0;
    private boolean favorite = false;

    public void answer(boolean ans) {
        if (ans)
            correct++;
        else
            wrong++;
    }

    public void toggleFavoriteStatus () {
        favorite = !favorite;
    }

    public boolean isFavorite () {
        return favorite;
    }

    public int getCorrect() {
        return correct;
    }

    public int getWrong() {
        return wrong;
    }

    public int getTotalTimes () {
        return wrong + correct;
    }

    public double wrong_correctRatio () {
        return (double) wrong / correct;
    }

    public double correct_wrongRatio () {
        return (double) correct / wrong;
    }

    public double correct_totalRatio () {
        return (double) correct / (correct + wrong);
    }

    public double wrong_total_Ratio () {
        return (double) wrong / (correct + wrong);
    }
}
