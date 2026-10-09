public class HemmingDecoder
{
    public static void main(String[] args)
    {
        final String MESSAGE;
        

        MESSAGE = args[0];
        final int ERROR_BIT = analyseErrorBit(MESSAGE);
        final String RECOVERED_MESSAGE = messageRecovery(MESSAGE,ERROR_BIT);
        System.out.println(RECOVERED_MESSAGE);
        
    }

    private static int analyseErrorBit(final String MESSAGE)
    {
        final int BITS_COUNT = MESSAGE.length();
        final int CORRECTION_BITS_COUNT = (int)Math.floor(Math.log(BITS_COUNT+1)/Math.log(2));
        final int[] SINDROMS = new int[CORRECTION_BITS_COUNT];

        for(int i = 0; i < SINDROMS.length; i++)
        {
            int sindrom = 0;

            final int POWERED_TWO = (int)Math.pow(2,i);
            for(int j = POWERED_TWO-1; j < BITS_COUNT; j+=2*(POWERED_TWO))
            {
                
                for(int k = j; k < j+POWERED_TWO; k++)
                {
                    sindrom += Integer.parseInt(Character.toString(MESSAGE.charAt(k)));
                }
            }
            SINDROMS[i] = sindrom%2;
        }

        int error_bit = 0;
        for(int i = 0; i < SINDROMS.length; i++)
        {
            error_bit += SINDROMS[i]*Math.pow(2, i);
        }

        System.out.println(error_bit);
        return error_bit;
    }

    private static String messageRecovery(final String MESSAGE, final int error_bit)
    {
        String recoveredMessage = "";
        for(int i = 0; i < MESSAGE.length(); i++)
        {
            
            final double LOG_OF_TWO = Math.log(i+1)/Math.log(2);
            if(Math.floor(LOG_OF_TWO) == LOG_OF_TWO){continue;}
            if(i+1 == error_bit)
            {
                switch(MESSAGE.charAt(i))
                {
                    case '0' -> recoveredMessage += "1";
                    case '1' -> recoveredMessage += "0";
                }
                continue;
            }
            recoveredMessage += Character.toString(MESSAGE.charAt(i));
        }
        return recoveredMessage;
    }
    
}