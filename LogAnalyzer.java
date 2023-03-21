/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version    2016.02.29
 */
public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    private int[] dayCounts;
    private int[] monthCounts;
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

    /**
     * 
     * @param fileName the name of the file
     */
    public LogAnalyzer(String fileName) {
        hourCounts = new int[24];
        reader = new LogfileReader(fileName);
    }
    
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
     * Number of accesses in the log file
     */
    public int numberOfAccesses() {
        int total = 0;
            for(int numAccesses : hourCounts) {
                total += numAccesses;
            }
        return total;
    }
    
    public int busiestHour() {
        int busiestHour = 0;
        int maxCount = 0;

        for(int i = 0; i < hourCounts.length; i++) {
            if(hourCounts[i] > maxCount) {
                busiestHour = i;
                maxCount = hourCounts[i];
            }
        }
        return busiestHour;
    }
    
    public int quietestHour() {
        int quietestHour = 0;
        int minCount = 0;
        
        for(int i = 0; i < hourCounts.length; i++) {
            if(hourCounts[i] < minCount) {
                quietestHour = i;
                minCount = hourCounts[i];
            }
        }
        return quietestHour;
    }
    
    public int busiestTwoHour() {
        int busiestTwoHour = 0;
        int maxCount = 0;
        
        for(int i = 0; i < hourCounts.length / 2; i++) {
            int busiestCounts = hourCounts[i * 2] + hourCounts[i * 2 + 1];
            if(busiestCounts > maxCount) {
                busiestTwoHour = i;
            }
        }
        return busiestTwoHour;
    }
    
    /**
     * Logs the busiest day
     */
    public int busiestDay() {
        int busiestDay = 0;
        int maxCount = 0;
        
        for(int i = 0; i < dayCounts.length; i++) {
            if(dayCounts[i] < maxCount) {
                busiestDay = i;
                maxCount = dayCounts[i];
            }
        }
        return busiestDay;        
    }
    
    /**
     * Logs the quietest day
     */
    public int quietestDay() {
        int quietestDay = 0;
        int minCount = 0;
        
        for(int i = 0; i < dayCounts.length; i++) {
            if(dayCounts[i] < minCount) {
                quietestDay = i;
                minCount = dayCounts[i];
            }
        }
        return quietestDay;
    }
    
    /**
     * Logs the busiest Month
     */
    public int busiestMonth() {
        int busiestMonth = 0;
        int maxCount = 0;
        
        for(int i = 0; i < monthCounts.length; i++) {
            if(monthCounts[i] < maxCount) {
                busiestMonth = i;
                maxCount = monthCounts[i];
            }
        }
        return busiestMonth;        
    }
    
    /**
     * Logs the quietest month
     */
    public int quietestMonth() {
        int quietestMonth = 0;
        int minCount = 0;
        
        for(int i = 0; i < monthCounts.length; i++) {
            if(monthCounts[i] < minCount) {
                quietestMonth = i;
                minCount = monthCounts[i];
            }
        }
        return quietestMonth;
    }
}