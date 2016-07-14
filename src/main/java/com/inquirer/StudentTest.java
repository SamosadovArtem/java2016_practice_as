package com.eharmony.singles.admin;

import org.junit.Test;

public class StudentTest {

    @Test public void testStringMagic() {

        System.out.println("*********************************");
        System.out.println("Some String magic");

        String s1 = "test";
        String s2 = "test";

        String s3 = new String("test");
        String s4 = new String("test");

        System.out.println("S1 = S2: " + (s1 == s2));
        System.out.println("S1 = S3: " + (s1 == s3));
        System.out.println("S1 = S4: " + (s1 == s4));
        System.out.println("S3 = S4: " + (s3 == s4));
        
        /*
        Some String magic
        S1 = S2: true
        S1 = S3: false
        S1 = S4: false
        S3 = S4: false
        */
    }

    @Test public void testIntegerMagic() {

        System.out.println("*********************************");
        System.out.println("Some Integer magic");

        Integer i1 = 125;
        Integer i2 = 125;

        Integer i3 = -125;
        Integer i4 = -125;

        Integer i5 = 0;
        Integer i6 = 0;

        Integer i7 = 130;
        Integer i8 = 130;

        Integer i9 = -130;
        Integer i10 = -130;

        System.out.println("I1 = I2: " + (i1 == i2));
        System.out.println("I3 = I4: " + (i3 == i4));
        System.out.println("I5 = I6: " + (i5 == i6));
        System.out.println("I7 = I8: " + (i7 == i8));
        System.out.println("I9 = I10: " + (i9 == i10));
        
        /*
        Some Integer magic
        I1 = I2: true
        I3 = I4: true
        I5 = I6: true
        I7 = I8: false
        I9 = I10: false
         */
    }
}
