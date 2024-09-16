package ru.YurkovAleksandr;

public class TestCaseException extends  ReflectiveOperationException{
    private String nameCase;


    public TestCaseException(String message,String nameCase) {
        super(message);
        this.nameCase = nameCase;
    }

    public String getNameCase() {
        return nameCase;
    }
}
