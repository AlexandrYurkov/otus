package ru.YurkovAleksandr;

import ru.YurkovAleksandr.annotations.*;


import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;



public class PerformanceTest {
    Map<Method, Integer> suite = new LinkedHashMap<>();
    ArrayList<Method> disabled = new ArrayList<>();
    private Method beforeSuite = null;
    private Method afterSuite = null;
    private int count = 0;


    public PerformanceTest(Method[] methods) {
        for (Method m : methods) {
            if (m.isAnnotationPresent(Disabled.class))
                this.disabled.add(m);
            else if (m.isAnnotationPresent(BeforeSuite.class))
                this.beforeSuite = m;
            else if (m.isAnnotationPresent(AfterSuite.class))
                this.afterSuite = m;
            else if (m.isAnnotationPresent(Test.class)) {
                Annotation[] tmp = m.getAnnotations();
                for(Annotation a : tmp)
                    if(a instanceof Test)
                        this.suite.put(m, ((Test) a).priority());
            }
        }
    }

    public Method getBeforeSuite() {
        return beforeSuite;
    }

    public Method getAfterSuite() {
        return afterSuite;
    }

    public List<Method> getSuite() {
        return sortPriority();
    }
    public List<Method> getDisabled(){
        return this.disabled;
    }

    public void setCount(int count) {
        this.count += count;
    }

    private static <K, V extends Comparable<? super V>> Comparator<Map.Entry<K, V>> comparingByValue() {
        return (Comparator<Map.Entry<K, V>> & Serializable)
                (c1, c2) -> c2.getValue().compareTo(c1.getValue());
    }

    private List<Method> sortPriority(){
        return suite.entrySet()
                .stream()
                .sorted(comparingByValue())
                .map(Map.Entry::getKey)
                .toList();

    }

    public String result() throws TestCaseException {
        String result = "Пропущенно тестов: " +
                disabled.size() +
                "\n" +
                "Выполнено тестов :" +
                count;
        return result;
    }


}
