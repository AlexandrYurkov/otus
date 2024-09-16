package ru.YurkovAleksandr;

public class Application {
    public static void main(String[] args) {
        try {
            TestSuite testSuite = new TestSuite();
            TestRunner.run(testSuite.getClass());
        }catch (TestCaseException e){
            System.out.println(e.getNameCase() + ":" + e.getMessage());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}