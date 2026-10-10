package org.example;

import org.example.HemmingDecoder;

public class HemmingDecoderTests
{
    private static int testCounter = 1;
    public static void main(String[] args)
    {
        analysatorTest();
        decoderTests();
    }

    private static void analysatorTest()
    {
        for(int i = 0; i < 16; i++)
        {
            String message = HemmingDecoder.coder(String.format("%4s",Integer.toBinaryString(i)).replace(' ', '0'));
            for(int j = 0; j < 7; j++)
            {
                String errorMessage = message.substring(0, j) + (message.charAt(j) == '1' ? "0" : "1") + message.substring(j+1);
                //System.out.println(errorMessage);
                if(HemmingDecoder.analyseErrorBit(errorMessage)-1 == j)
                {
                    System.out.printf("Test %d passed\n", testCounter++);
                }
                else{
                    System.out.printf("Test %d failed\n", testCounter++);
                }
            }
        }
        //System.out.println(passedTestsCounter);
    }

    private static void decoderTests()
    {
        for(int i = 0; i < 16; i++)
        {
            String message = String.format("%4s",Integer.toBinaryString(i)).replace(' ', '0');
            String codeMessage = HemmingDecoder.coder(message);
            for(int j = 0; j < 7; j++)
            {
                String errorMessage = codeMessage.substring(0, j) + (codeMessage.charAt(j) == '1' ? "0" : "1") + codeMessage.substring(j+1);
                String recoveredMessage = HemmingDecoder.messageRecovery(errorMessage, HemmingDecoder.analyseErrorBit(errorMessage));
                if(recoveredMessage.equals(message))
                {
                    System.out.printf("Test %d passed\n", testCounter++);
                }
                else
                {
                    System.out.printf("Test %d failed\n", testCounter++);
                }
            }
        }
    }
}