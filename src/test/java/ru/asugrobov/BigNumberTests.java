package ru.asugrobov;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;

@RunWith(Enclosed.class)
public class BigNumberTests {

    @RunWith(Parameterized.class)
    public static class BigNumberAddTests
    {

        private String bigNumber;
        private String bigNumberToAdd;
        private String expected;

        public BigNumberAddTests(String bigNumber, String bigNumberToAdd, String expected) {
            this.bigNumber = bigNumber;
            this.bigNumberToAdd = bigNumberToAdd;
            this.expected = expected;
        }

        @Parameterized.Parameters(name = "{index}: {0}.Add({1})={2}")
        public static Collection<Object[]> data () {
            return Arrays.asList(new Object[][]{
                    {"8", "1", "9"},
                    {"82", "12", "94"},
                    {"118", "2", "120"},
                    {"2", "118", "120"},
                    {"223", "777", "1000"},
                    {"1", "999", "1000"},
                    {"9", "128", "137"},
                    {"128", "9", "137"},
                    {"1500", "300", "1800"},
                    {"300", "1555", "1855"},
                    {"01", "2001", "2002"}
            });
        }

        @Test
        public void BigNumber_AddTest(){
            BigNumber bigNumber = new BigNumber(this.bigNumber);
            BigNumber bigNumberToAdd = new BigNumber(this.bigNumberToAdd);
            assertThat(bigNumber.Add(bigNumberToAdd).toString(), is(expected));
        }
    }

    public static class BigNumberInitializeTests {
        @Test
        public void BigNumber_CanCreateObject() {
            BigNumber bigNumber = new BigNumber("1234");
            assertNotEquals(null, bigNumber);
        }

        @Test
        public void BigNumber_StringLength3_DigitsLength3() {
            BigNumber bigNumber = new BigNumber("234");
            assertEquals(3, bigNumber.getDigits().length);
        }

        @Test
        public void BigNumber_StringLength4_DigitsLength4() {
            BigNumber bigNumber = new BigNumber("2345");
            assertEquals(4, bigNumber.getDigits().length);
        }

        @Test
        public void BigNumber_DigitsIsReverseString() {
            BigNumber bigNumber = new BigNumber("2345");
            int[] digits = bigNumber.getDigits();
            assertEquals(2, digits[3]);
            assertEquals(3, digits[2]);
            assertEquals(4, digits[1]);
            assertEquals(5, digits[0]);
        }

        @Test
        public void BigNumber_DigitsToStringIsExistingString() {
            BigNumber bigNumber = new BigNumber("2345");
            assertEquals("2345", bigNumber.toString());
            bigNumber = new BigNumber("123");
            assertEquals("123", bigNumber.toString());
        }
    }
}
