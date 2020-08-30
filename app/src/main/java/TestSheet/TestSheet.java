package TestSheet;

import java.util.ArrayList;

public class TestSheet {
    private ArrayList<Test> tests;
    private String title;
    public TestSheet (String title, int testCounts) {
        tests = new ArrayList<Test>(200);   // tests are 1-indexed
        for (int i = 1; i <= testCounts; i++) {
            tests.add(i, new Test());
        }
    }


    // will return index of favorite tests (can't return tests because we need test numbers).
    public ArrayList<Integer> getFavoriteTests () {
        ArrayList<Integer> temp = new ArrayList<>();
        for (int i = 1; i <= tests.size(); i++) {
            if (tests.get(i).isFavorite())
                temp.add(i);
        }

        return temp;
    }

    // 1-indexed test number should be passed
    public Test getTestWithNumber (int index) {
        if (index < 0 || index >= tests.size())
            return null;
        return tests.get(index);
    }

    public ArrayList<Test> getTests() {
        return tests;
    }

    public void setTests(ArrayList<Test> tests) {
        this.tests = tests;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
