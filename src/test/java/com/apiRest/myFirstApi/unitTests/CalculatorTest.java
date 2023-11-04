package com.apiRest.myFirstApi.unitTests;

import com.apiRest.myFirstApi.entityTest.Calculator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {
    private Calculator calculator;

    //@Before == @BeforeEach en JUnit 5
    @BeforeEach
    public void configureCalculator(){
        calculator = new Calculator();
        System.out.println("Configuración antes de cada prueba");
    }
    @AfterEach
    public void destroyCalculator(){
        calculator = null;
        System.out.println("Configuración después de cada prueba");
    }
    @Test
    public void addPositiveTest(){
        int result = calculator.add(3,5);
        assertEquals(8,result);
    }

    @Test
    public void addNegativeTest(){
        int result = calculator.add(-7, 5);
        assertEquals(-2, result);
    }

    @Test
    public void shouldBeTrue(){
        boolean result = calculator.isBiggerThan(7,5);
        assertTrue(result);
    }

    @Test
    public void shouldBeFalse(){
        boolean result = calculator.isBiggerThan(5,7);
        assertFalse(result);
    }
}
