package test;
import main.GradeCalculator;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GradeCalculatorTest {
    private GradeCalculator calc;

    @Before
    public void setUp() {
        calc = new GradeCalculator();
    }

    // TC01 - TC08: เคสปกติ (EP) กลางช่วงของทุกเกรด
    @Test
    public void normalScoresReturnExpectedGrades() {
        assertEquals("A",  calc.gradeOf(90)); // TC01
        assertEquals("B+", calc.gradeOf(77)); // TC02
        assertEquals("B",  calc.gradeOf(72)); // TC03
        assertEquals("C+", calc.gradeOf(67)); // TC04
        assertEquals("C",  calc.gradeOf(62)); // TC05
        assertEquals("D+", calc.gradeOf(57)); // TC06
        assertEquals("D",  calc.gradeOf(52)); // TC07
        assertEquals("F",  calc.gradeOf(25)); // TC08
    }

    // TC09, TC24: ค่าขอบบนสุด-ล่างสุดของช่วงคะแนนที่รับได้ (R1: 0-100)
    @Test
    public void validRangeBoundariesReturnAAndF() {
        assertEquals("A", calc.gradeOf(100)); // TC09
        assertEquals("F", calc.gradeOf(0));   // TC24
    }

    // TC10, TC12, TC14, TC16, TC18, TC20, TC22: ค่าขอบล่าง (BVA) ของแต่ละเกรด
    @Test
    public void lowerBoundsOfEachGradeReturnExpectedGrades() {
        assertEquals("A",  calc.gradeOf(80)); // TC10
        assertEquals("B+", calc.gradeOf(75)); // TC12
        assertEquals("B",  calc.gradeOf(70)); // TC14
        assertEquals("C+", calc.gradeOf(65)); // TC16
        assertEquals("C",  calc.gradeOf(60)); // TC18
        assertEquals("D+", calc.gradeOf(55)); // TC20
        assertEquals("D",  calc.gradeOf(50)); // TC22
    }

    // TC11, TC13, TC15, TC17, TC19, TC21, TC23: ค่าขอบบน (BVA) ของแต่ละเกรด
    @Test
    public void upperBoundsOfEachGradeReturnExpectedGrades() {
        assertEquals("B+", calc.gradeOf(79)); // TC11
        assertEquals("B",  calc.gradeOf(74)); // TC13
        assertEquals("C+", calc.gradeOf(69)); // TC15
        assertEquals("C",  calc.gradeOf(64)); // TC17
        assertEquals("D+", calc.gradeOf(59)); // TC19
        assertEquals("D",  calc.gradeOf(54)); // TC21
        assertEquals("F",  calc.gradeOf(49)); // TC23
    }

    // TC25: คะแนนต่ำกว่า 0 ที่ขอบทันที (-1)
    @Test(expected = IllegalArgumentException.class)
    public void scoreBelowZeroAtBoundaryThrowsException() {
        calc.gradeOf(-1);
    }

    // TC26: คะแนนเกิน 100 ที่ขอบทันที (101)
    @Test(expected = IllegalArgumentException.class)
    public void scoreAboveHundredAtBoundaryThrowsException() {
        calc.gradeOf(101);
    }

    // TC27: คะแนนติดลบทั่วไป (-50)
    @Test(expected = IllegalArgumentException.class)
    public void negativeScoreThrowsException() {
        calc.gradeOf(-50);
    }

    // TC28: คะแนนเกิน 100 ทั่วไป (150)
    @Test(expected = IllegalArgumentException.class)
    public void scoreFarAboveHundredThrowsException() {
        calc.gradeOf(150);
    }

    // TC29: ค่าต่ำสุดของ int (Robustness)
    @Test(expected = IllegalArgumentException.class)
    public void minIntValueThrowsException() {
        calc.gradeOf(Integer.MIN_VALUE);
    }

    // T
}
