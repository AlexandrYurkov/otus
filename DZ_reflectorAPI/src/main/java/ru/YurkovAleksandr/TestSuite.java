package ru.YurkovAleksandr;


import ru.YurkovAleksandr.annotations.*;

public class TestSuite {

    @BeforeSuite
    @Disabled
    public static void init (){
        System.out.println("BeforeSuite");
    }

    @Test(priority = 10)
    public static void test_1(){
        System.out.println("Test 1");
    }

    @Test(priority = 9)
    public static void test_2(){
        System.out.println("Test 2");
    }
    @Disabled
    @Test(priority = 9)
    public static void test_3(){
        System.out.println("Test 3");
    }


    @Test(priority = 10)
    public static void test_4(){
        System.out.println("Test 4");
    }
    @AfterSuite
    public static void destroy(){
        System.out.println("AfterSuite");
    }



}
