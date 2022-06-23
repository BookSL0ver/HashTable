import java.io.*;
import java.util.ArrayList;
import java.util.TreeMap;

public class PerformanceAnalysisHash implements PerformanceAnalysis 
{

    // The input data from each file is stored in this/ per file
    private ArrayList<String> inputData;
    private ArrayList<String> smInt;
    private ArrayList<String> larInt;
    private ArrayList<String> smStr;
    private ArrayList<String> larStr;
    private ArrayList<String> outputLines;
    private double memInHash;
    private double memInTree;
    private double memLookHash;
    private double memLookTree;
    private double memOutHash;
    private double memOutTree;
    
    
    public PerformanceAnalysisHash()
    {
    }

    public PerformanceAnalysisHash(String details_filename)
    {
        //TODO: Save the details of the test data files
    	try
    	{
    		loadData(details_filename);
    	}catch(Exception e){}
    	
    	try {
    		File intLar = new File("IntegerLarge.txt");
    		BufferedReader br = new BufferedReader(new FileReader(intLar));
            larInt = new ArrayList<>();
            String line = br.readLine();
            while (line != null) {
                larInt.add(line);
                line = br.readLine();
            }
            br.close();
    		File intSm = new File ("IntegerSmall.txt");
    		br = new BufferedReader(new FileReader(intSm));
            smInt = new ArrayList<>();
            line = br.readLine();
            while (line != null) {
                smInt.add(line);
                line = br.readLine();
            }
            br.close();
    		File strLar = new File("StringLarge.txt");
    		br = new BufferedReader(new FileReader(strLar));
            larStr = new ArrayList<>();
            line = br.readLine();
            while (line != null) {
                larStr.add(line);
                line = br.readLine();
            }
            br.close();
    		File strSm = new File("StringSmall.txt");
    		br = new BufferedReader(new FileReader(strSm));
            smStr = new ArrayList<>();
            line = br.readLine();
            while (line != null) {
                smStr.add(line);
                line = br.readLine();
            }
            br.close();
    	}catch(Exception e) {}
    }
    
    @Override
    public void compareDataStructures() 
    {
        //TODO: Complete this function which compares the ds and generates the details
    	compareInsertion();
    	compareDeletion();
    	compareSearch();
    }

    @Override
    public void printReport() 
    {
        //TODO: Complete this method
    	System.out.println("The report name : Performance Analysis Report");
    	System.out.println("------------------------------------------------------------------------------------------------ |");
    	System.out.println("        FileName|      Operation| Data Structure|   Time Taken (micro sec)|     Bytes Used|");
    	System.out.println("------------------------------------------------------------------------------------------------ |");
    	for(int i = 0; i < outputLines.size(); i++)
    	{
    		System.out.println(outputLines.get(i));
    	}
    	System.out.println("------------------------------------------------------------------------------------------------- ");
    }

    @Override
    public void compareInsertion() 
    {
        //TODO: Complete this method
    	HashTable<Integer, Integer> h = new HashTable<Integer, Integer>(11, 10);
    	double initialTimeInHash = java.lang.System.nanoTime();
    	double befMemHash = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    	for(int i = 0; i < smInt.size(); i++)
    	{
    		h.put(Integer.parseInt(smInt.get(i)), Integer.parseInt(smInt.get(i)));
    	}
    	double finalTimeInHash = java.lang.System.nanoTime();
    	double afMemHash = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    	double timeInHash = finalTimeInHash - initialTimeInHash;
    	double usedMemHash = afMemHash - befMemHash;
    	TreeMap<Integer, Integer> t = new TreeMap<Integer, Integer>();
    	double befMemTree = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    	double initialTimeInTree = java.lang.System.nanoTime();
    	for(int i = 0; i < smInt.size(); i++)
    	{
    		t.put(Integer.parseInt(smInt.get(i)), Integer.parseInt(smInt.get(i)));
    	}
    	double finalTimeInTree = java.lang.System.nanoTime();
    	double afMemTree = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    	double timeInTree = finalTimeInTree - initialTimeInTree;
    	double usedMemTree = afMemTree - befMemTree;
    	outputLines.add("IntegerSmall.txt|            PUT|      HASHTABLE|                   "+ timeInHash+"|              "+usedMemHash+"||");
    	outputLines.add("IntegerSmall.txt|            PUT|        TREEMAP|                   "+ timeInTree+"|              "+usedMemTree+"||");
    	h = new HashTable<Integer, Integer>(0, 10);
    	initialTimeInHash = java.lang.System.nanoTime();
    	befMemHash = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    	for(int i = 0; i < larInt.size(); i++)
    	{
    		h.put(Integer.parseInt(larInt.get(i)), Integer.parseInt(larInt.get(i)));
    	}
    	finalTimeInHash = java.lang.System.nanoTime();
    	afMemHash = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    	timeInHash = finalTimeInHash - initialTimeInHash;
    	usedMemHash = afMemHash - befMemHash;
    	t = new TreeMap<Integer, Integer>();
    	initialTimeInTree = java.lang.System.nanoTime();
    	befMemTree = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    	for(int i = 0; i < larInt.size(); i++)
    	{
    		t.put(Integer.parseInt(larInt.get(i)), Integer.parseInt(larInt.get(i)));
    	}
    	finalTimeInTree = java.lang.System.nanoTime();
    	afMemTree = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    	timeInTree = finalTimeInTree - initialTimeInTree;
    	usedMemTree = afMemTree - befMemTree;
    	outputLines.add("IntegerLarge.txt|            PUT|      HASHTABLE|                   "+ timeInHash+"|              "+usedMemHash+"||");
    	outputLines.add("IntegerLarge.txt|            PUT|        TREEMAP|                   "+ timeInTree+"|              "+usedMemTree+"||");
    	HashTable<String, String>h1 = new HashTable<String, String>(11, 10);
    	initialTimeInHash = java.lang.System.nanoTime();
    	befMemHash = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    	for(int i = 0; i < smStr.size(); i++)
    	{
    		h1.put(smStr.get(i), smStr.get(i));
    	}
    	finalTimeInHash = java.lang.System.nanoTime();
    	afMemHash = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    	timeInHash = finalTimeInHash - initialTimeInHash;
    	usedMemHash = afMemHash - befMemHash;
    	TreeMap<String, String> t1 = new TreeMap<String, String>();
    	initialTimeInTree = java.lang.System.nanoTime();
    	befMemTree = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    	for(int i = 0; i < smStr.size(); i++)
    	{
    		t1.put(smStr.get(i), smStr.get(i));
    	}
    	finalTimeInTree = java.lang.System.nanoTime();
    	afMemTree = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    	timeInTree = finalTimeInTree - initialTimeInTree;
    	usedMemTree = afMemTree - befMemTree;
    	outputLines.add(" StringSmall.txt|            PUT|      HASHTABLE|                   "+ timeInHash+"|              "+usedMemHash+"||");
    	outputLines.add(" StringSmall.txt|            PUT|        TREEMAP|                   "+ timeInTree+"|              "+usedMemTree+"||");
    	h1 = new HashTable<String, String>(0, 10);
    	initialTimeInHash = java.lang.System.nanoTime();
    	befMemHash = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    	for(int i = 0; i < larStr.size(); i++)
    	{
    		h1.put(larStr.get(i), larStr.get(i));
    	}
    	finalTimeInHash = java.lang.System.nanoTime();
    	afMemHash = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    	timeInHash = finalTimeInHash - initialTimeInHash;
    	usedMemHash = afMemHash - befMemHash;
    	t1 = new TreeMap<String, String>();
    	initialTimeInTree = java.lang.System.nanoTime();
    	befMemTree = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    	for(int i = 0; i < larStr.size(); i++)
    	{
    		t1.put(larStr.get(i), larStr.get(i));
    	}
    	finalTimeInTree = java.lang.System.nanoTime();
    	afMemTree = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    	timeInTree = finalTimeInTree - initialTimeInTree;
    	usedMemTree = afMemTree - befMemTree;
    	outputLines.add(" StringLarge.txt|            PUT|      HASHTABLE|                   "+ timeInHash+"|              "+usedMemHash+"||");
    	outputLines.add(" StringLarge.txt|            PUT|        TREEMAP|                   "+ timeInTree+"|              "+usedMemTree+"||");
    }

    @Override
    public void compareDeletion() 
    {
    	HashTable<Integer, Integer> h = new HashTable<Integer, Integer>(11, 10);
    	for(int i = 0; i < smInt.size(); i++)
    	{
    		h.put(Integer.parseInt(smInt.get(i)), Integer.parseInt(smInt.get(i)));
    	}
    	double initialTimeInHash = java.lang.System.nanoTime();
    	for(int i = 0; i < smInt.size(); i++)
    	{
    		h.remove(Integer.parseInt(smInt.get(i)));
    	}
    	double finalTimeInHash = java.lang.System.nanoTime();
    	double timeInHash = finalTimeInHash - initialTimeInHash;
    	TreeMap<Integer, Integer> t = new TreeMap<Integer, Integer>();
    	for(int i = 0; i < smInt.size(); i++)
    	{
    		t.put(Integer.parseInt(smInt.get(i)), Integer.parseInt(smInt.get(i)));
    	}
    	double initialTimeInTree = java.lang.System.nanoTime();
    	for(int i = 0; i < smInt.size(); i++)
    	{
    		t.remove(Integer.parseInt(smInt.get(i)));
    	}
    	double finalTimeInTree = java.lang.System.nanoTime();
    	double timeInTree = finalTimeInTree - initialTimeInTree;
    	outputLines.add("IntegerSmall.txt|         REMOVE|      HASHTABLE|                   "+ timeInHash+"|              "+memInHash+"||");
    	outputLines.add("IntegerSmall.txt|         REMOVE|        TREEMAP|                   "+ timeInTree+"|              "+memInTree+"||");
    	h = new HashTable<Integer, Integer>(11, 10);
    	for(int i = 0; i < larInt.size(); i++)
    	{
    		h.put(Integer.parseInt(larInt.get(i)), Integer.parseInt(larInt.get(i)));
    	}
    	initialTimeInHash = java.lang.System.nanoTime();
    	for(int i = 0; i < larInt.size(); i++)
    	{
    		h.remove(Integer.parseInt(larInt.get(i)));
    	}
    	finalTimeInHash = java.lang.System.nanoTime();
    	timeInHash = finalTimeInHash - initialTimeInHash;
    	t = new TreeMap<Integer, Integer>();
    	for(int i = 0; i < larInt.size(); i++)
    	{
    		t.put(Integer.parseInt(larInt.get(i)), Integer.parseInt(larInt.get(i)));
    	}
    	initialTimeInTree = java.lang.System.nanoTime();
    	for(int i = 0; i < larInt.size(); i++)
    	{
    		t.remove(Integer.parseInt(larInt.get(i)));
    	}
    	finalTimeInTree = java.lang.System.nanoTime();
    	timeInTree = finalTimeInTree - initialTimeInTree;
    	outputLines.add("IntegerLarge.txt|         REMOVE|      HASHTABLE|                   "+ timeInHash+"|              "+memInHash+"||");
    	outputLines.add("IntegerLarge.txt|         REMOVE|        TREEMAP|                   "+ timeInTree+"|              "+memInTree+"||");
    	HashTable<String, String>h1 = new HashTable<String, String>(11, 10);
    	for(int i = 0; i < smStr.size(); i++)
    	{
    		h1.put(smStr.get(i), smStr.get(i));
    	}
    	initialTimeInHash = java.lang.System.nanoTime();
    	for(int i = 0; i < smStr.size(); i++)
    	{
    		h1.remove(smStr.get(i));
    	}
    	finalTimeInHash = java.lang.System.nanoTime();
    	timeInHash = finalTimeInHash - initialTimeInHash;
    	TreeMap<String, String> t1 = new TreeMap<String, String>();
    	for(int i = 0; i < smStr.size(); i++)
    	{
    		t1.put(smStr.get(i), smStr.get(i));
    	}
    	initialTimeInTree = java.lang.System.nanoTime();
    	for(int i = 0; i < smStr.size(); i++)
    	{
    		t1.remove(smStr.get(i));
    	}
    	finalTimeInTree = java.lang.System.nanoTime();
    	timeInTree = finalTimeInTree - initialTimeInTree;
    	outputLines.add(" StringSmall.txt|         REMOVE|      HASHTABLE|                   "+ timeInHash+"|              "+memInHash+"||");
    	outputLines.add(" StringSmall.txt|         REMOVE|        TREEMAP|                   "+ timeInTree+"|              "+memInTree+"||");
    	h1 = new HashTable<String, String>(11, 10);
    	for(int i = 0; i < larStr.size(); i++)
    	{
    		h1.put(larStr.get(i), larStr.get(i));
    	}
    	initialTimeInHash = java.lang.System.nanoTime();
    	for(int i = 0; i < larStr.size(); i++)
    	{
    		h1.remove(larStr.get(i));
    	}
    	finalTimeInHash = java.lang.System.nanoTime();
    	timeInHash = finalTimeInHash - initialTimeInHash;
    	t1 = new TreeMap<String, String>();
    	for(int i = 0; i < larStr.size(); i++)
    	{
    		t1.put(larStr.get(i), larStr.get(i));
    	}
    	initialTimeInTree = java.lang.System.nanoTime();
    	for(int i = 0; i < larStr.size(); i++)
    	{
    		t1.remove(larStr.get(i));
    	}
    	finalTimeInTree = java.lang.System.nanoTime();
    	timeInTree = finalTimeInTree - initialTimeInTree;
    	outputLines.add(" StringLarge.txt|         REMOVE|      HASHTABLE|                   "+ timeInHash+"|              "+memInHash+"||");
    	outputLines.add(" StringLarge.txt|         REMOVE|        TREEMAP|                   "+ timeInTree+"|              "+memInTree+"||");
    }

    @Override
    public void compareSearch() 
    {
        //TODO: Complete this method
    	HashTable<Integer, Integer> h = new HashTable<Integer, Integer>(11, 10);
    	for(int i = 0; i < smInt.size(); i++)
    	{
    		h.put(Integer.parseInt(smInt.get(i)), Integer.parseInt(smInt.get(i)));
    	}
    	double initialTimeInHash = java.lang.System.nanoTime();
    	for(int i = 0; i < smInt.size(); i++)
    	{
    		h.get(Integer.parseInt(smInt.get(i)));
    	}
    	double finalTimeInHash = java.lang.System.nanoTime();
    	double timeInHash = finalTimeInHash - initialTimeInHash;
    	TreeMap<Integer, Integer> t = new TreeMap<Integer, Integer>();
    	for(int i = 0; i < smInt.size(); i++)
    	{
    		t.put(Integer.parseInt(smInt.get(i)), Integer.parseInt(smInt.get(i)));
    	}
    	double initialTimeInTree = java.lang.System.nanoTime();
    	for(int i = 0; i < smInt.size(); i++)
    	{
    		t.get(Integer.parseInt(smInt.get(i)));
    	}
    	double finalTimeInTree = java.lang.System.nanoTime();
    	double timeInTree = finalTimeInTree - initialTimeInTree;
    	outputLines.add("IntegerSmall.txt|            GET|      HASHTABLE|                   "+ timeInHash+"|              "+memInHash+"||");
    	outputLines.add("IntegerSmall.txt|            GET|        TREEMAP|                   "+ timeInTree+"|              "+memInTree+"||");
    	h = new HashTable<Integer, Integer>(11, 10);
    	for(int i = 0; i < larInt.size(); i++)
    	{
    		h.put(Integer.parseInt(larInt.get(i)), Integer.parseInt(larInt.get(i)));
    	}
    	initialTimeInHash = java.lang.System.nanoTime();
    	for(int i = 0; i < larInt.size(); i++)
    	{
    		h.get(Integer.parseInt(larInt.get(i)));
    	}
    	finalTimeInHash = java.lang.System.nanoTime();
    	timeInHash = finalTimeInHash - initialTimeInHash;
    	t = new TreeMap<Integer, Integer>();
    	for(int i = 0; i < larInt.size(); i++)
    	{
    		t.put(Integer.parseInt(larInt.get(i)), Integer.parseInt(larInt.get(i)));
    	}
    	initialTimeInTree = java.lang.System.nanoTime();
    	for(int i = 0; i < larInt.size(); i++)
    	{
    		t.get(Integer.parseInt(larInt.get(i)));
    	}
    	finalTimeInTree = java.lang.System.nanoTime();
    	timeInTree = finalTimeInTree - initialTimeInTree;
    	outputLines.add("IntegerLarge.txt|            GET|      HASHTABLE|                   "+ timeInHash+"|              "+memInHash+"||");
    	outputLines.add("IntegerLarge.txt|            GET|        TREEMAP|                   "+ timeInTree+"|              "+memInTree+"||");
    	HashTable<String, String>h1 = new HashTable<String, String>(11, 10);
    	for(int i = 0; i < smStr.size(); i++)
    	{
    		h1.put(smStr.get(i), smStr.get(i));
    	}
    	initialTimeInHash = java.lang.System.nanoTime();
    	for(int i = 0; i < smStr.size(); i++)
    	{
    		h1.get(smStr.get(i));
    	}
    	finalTimeInHash = java.lang.System.nanoTime();
    	timeInHash = finalTimeInHash - initialTimeInHash;
    	TreeMap<String, String> t1 = new TreeMap<String, String>();
    	for(int i = 0; i < smStr.size(); i++)
    	{
    		t1.put(smStr.get(i), smStr.get(i));
    	}
    	initialTimeInTree = java.lang.System.nanoTime();
    	for(int i = 0; i < smStr.size(); i++)
    	{
    		t1.get(smStr.get(i));
    	}
    	finalTimeInTree = java.lang.System.nanoTime();
    	timeInTree = finalTimeInTree - initialTimeInTree;
    	outputLines.add(" StringSmall.txt|            GET|      HASHTABLE|                   "+ timeInHash+"|              "+memInHash+"||");
    	outputLines.add(" StringSmall.txt|            GET|        TREEMAP|                   "+ timeInTree+"|              "+memInTree+"||");
    	h1 = new HashTable<String, String>(11, 10);
    	for(int i = 0; i < larStr.size(); i++)
    	{
    		h1.put(larStr.get(i), larStr.get(i));
    	}
    	initialTimeInHash = java.lang.System.nanoTime();
    	for(int i = 0; i < larStr.size(); i++)
    	{
    		h1.get(larStr.get(i));
    	}
    	finalTimeInHash = java.lang.System.nanoTime();
    	timeInHash = finalTimeInHash - initialTimeInHash;
    	t1 = new TreeMap<String, String>();
    	for(int i = 0; i < larStr.size(); i++)
    	{
    		t1.put(larStr.get(i), larStr.get(i));
    	}
    	initialTimeInTree = java.lang.System.nanoTime();
    	for(int i = 0; i < larStr.size(); i++)
    	{
    		t1.get(larStr.get(i));
    	}
    	finalTimeInTree = java.lang.System.nanoTime();
    	timeInTree = finalTimeInTree - initialTimeInTree;
    	outputLines.add(" StringLarge.txt|            GET|      HASHTABLE|                   "+ timeInHash+"|              "+memInHash+"||");
    	outputLines.add(" StringLarge.txt|            GET|        TREEMAP|                   "+ timeInTree+"|              "+memInTree+"||");
    }

    /*
    An implementation of loading files into local data structure is provided to you
    Please feel free to make any changes if required as per your implementation.
    However, this function can be used as is.
     */
    @Override
    public void loadData(String filename) throws IOException 
    {

        // Opens the given test file and stores the objects each line as a string
        File file = new File(filename);
        BufferedReader br = new BufferedReader(new FileReader(file));
        inputData = new ArrayList<>();
        String line = br.readLine();
        while (line != null) {
            inputData.add(line);
            line = br.readLine();
        }
        br.close();
    }
}
