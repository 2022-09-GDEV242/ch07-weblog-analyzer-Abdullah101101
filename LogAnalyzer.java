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
    // Where to calculate the day access counts.
    private int[] dayCounts;
    // Where to calculate the month access counts.
    private int[] monthCounts;

    /**
     * Create an object to analyzes the following hourly, dialy, 
     * and monthly  web accesses.
     */
    public LogAnalyzer()
    { 
        // Create the array object to hold the hourly access counts.
        hourCounts = new int[24];
        //creates the array object to hold the day access counts.
        dayCounts = new int[28];
        //creates the array object to hold the month access counts.
        monthCounts = new int[12];
        // Create the reader to obtain the data.
        reader = new LogfileReader("demo.log");
    }
    
    /**
     * This method takes a file name as a parameter and 
     * create an object to analyzes the following hourly, dialy, 
     * and monthly  web accesses.
     * @param fileName name of the file to be handled
     */
    public LogAnalyzer(String fileName)
    { 
        // Create the array object to hold the hourly access counts.
        hourCounts = new int[24];
        //creates the array object to hold the day access counts.
        dayCounts = new int[28];
        //creates the array object to hold the month access counts.
        monthCounts = new int[12];
        // Create the reader to obtain the data.
        reader = new LogfileReader(fileName);        
    }

    /**
     * Analyze the hourly access data from the log file.
     */
    
    public void AnalyzeAllData()
    {
        while(reader.hasNext()) 
        {
            LogEntry entry = reader.next();
            //hourly data
            int hour = entry.getHour();
            hourCounts[hour]++;
            //daily data
            int day = entry.getDay();
            dayCounts[day-1]++;
            //monthly data
            int month = entry.getMonth();
            monthCounts[month-1]++;
                        
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
    /**
     * This  method will return the busiest day
     * @return day
     */
        public int busiestDay()
    {
        int day = dayCounts[0];
        for( int i = 0; i < dayCounts.length; i++)
        {
            if(dayCounts[i] > dayCounts[day])
                {
                    day = i;
                }
        }
        return day;
    }
    /**
     * This method method will return the quieteest day
     * @return day
     */
    public int quietestDay()
    {
        int day = dayCounts[0];
        for( int i = 0; i < dayCounts.length; i++)
        {
            if(dayCounts[i] < dayCounts[day])
                {
                    day = i;
                }
        }
        return day;
    }
    /**
     * This  method will return the busiest month
     * @return month
     */
        public int busiestMonth()
    {
        int month = monthCounts[0];
        for( int i = 0; i < monthCounts.length; i++)
        {
            if(monthCounts[i] > monthCounts[month])
                {
                    month = i;
                }
        }
        return month;
    }
    /**
     * This method will return the quieteest month
     * @return month
     */
    public int quitestMonth()
    {
        int month = monthCounts[0];
        for( int i = 0; i < monthCounts.length; i++)
        {
            if(monthCounts[i] < monthCounts[month])
                {
                    month = i;
                }
        }
        return month;
    }
    /**
     * This method will return the total number off access per month
     * @return monthlyAccess
     */
    public int totalAccessesPerMonth()
    {
        int monthlyAccess = 0;
        for (int i= 0; i < monthCounts.length;i++)
        {
           monthlyAccess =  monthCounts[i] + monthlyAccess;
        }
        return monthlyAccess;
    }
    /**
     * This method will returns the average access per month
     * @return average access per month
     */
    public int averageAccessesPerMonth()
    {
        int months = 12;
        int totalMonthlyAccess = 0;
        int average= 0 ;
        for(int i = 1; i < monthCounts.length; i++)
        {
            totalMonthlyAccess = monthCounts[i] + totalMonthlyAccess;
            average = totalMonthlyAccess / months;
        }
        return average;
    }
    
}
