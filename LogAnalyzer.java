/**
 * Read web server data and analyse hourly, daily, and monthly access patterns.
 * Supplies multiple kinds of trends occuring in the log file
 * 
 * @author David J. Barnes and Michael Kölling.
 * @editor Abdullah
 * @version    2022.10.16
 */
public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;

    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer()
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        // Create the reader to obtain the data.
        reader = new LogfileReader("demo.log");
    }
    public LogAnalyzer(String fileName)
        // Create the array object to hold the hourly access counts.
        hourCounts = new int[24];
        reader = new LogfileReader(fileName);        

    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }

    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts()
    {
        System.out.println("Hr: Count");
        for(int hour = 0; hour < hourCounts.length; hour++) {
            System.out.println(hour + ": " + hourCounts[hour]);
        }
    }
    
    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
    /**
     * This method returns the amount of log accesses from the log file.
     * @return accesses
     */
    public int numberOfAccesses()
    {
        int accesses = 0;
        for(int i = 0;i <hourCounts.length; i ++)
        {
            accesses += hourCounts[i];
        }
        
        return accesses;
    }
    /**
     * This method will return the busiest hour
     * @return hour
     */
    public int busiestHour()
    {
        int hour = hourCounts[0];
        for( int i = 0; i < hourCounts.length; i++)
        {
            if(hourCounts[i] > hourCounts[hour])
                {
                    hour = i;
                }
        }
        return hour;
    }
    /**
     * This method will return the quietest hour
     * @return hour
     */
    public int quietestHour()
    {
        int hour = hourCounts[0];
        for( int i = 0; i < hourCounts.length; i++)
        {
            if(hourCounts[i] < hourCounts[hour])
                {
                    hour = i;
                }
        }
        return hour;
    }
    
    /**
     * This method calculates the busiest pair of hours and returns the first 
     * value of the pair.
     * @return firstPair 
     */
    public int busiestTwoHourPeriod()
    {
        int firstOfPair = 0;
        
        for(int i = 0; i < hourCounts.length; i++)
            if( hourCounts[(firstOfPair + 1)] + hourCounts[firstOfPair]< hourCounts[i] + hourCounts[(i + 1)%24])
            {
                firstOfPair = i;
            }
                
        return firstOfPair;
    }
}
