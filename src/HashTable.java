import java.util.ArrayList;
import java.util.NoSuchElementException;

public class HashTable<K, V> implements HashTableADT<K, V> 
{
    /* Instance variables and constructors
     */
	private double maxLoadFactor;
	private int size;
	private int capacity;
	private ArrayList<ArrayList<K>> hashTable;
	private ArrayList<ArrayList<V>> values;
	private final ArrayList<Integer> PRIMES;
	
	public HashTable(int initialCapacity, double loadFactor)
	{
		maxLoadFactor = loadFactor;
		size = 0;
		capacity = initialCapacity;
		hashTable = new ArrayList<ArrayList<K>>(initialCapacity);
		values = new ArrayList<ArrayList<V>>(initialCapacity);
		for(int i = 0; i < hashTable.size(); i++)
		{
			hashTable.set(i, new ArrayList<K>());
			values.set(i, new ArrayList<V>());
		}
		PRIMES = getPrimes(initialCapacity);
	}

    @Override
    public V put(K key, V value) 
    {
        int hashKey = hash(key);
        if(hashTable.get(hashKey).get(0) == null)
        {
        	hashTable.get(hashKey).set(0, key);
        	values.get(hashKey).set(0, value);
        }
        else
        {
        	K tempKey = hashTable.get(hashKey).get(0);
        	int i = 0;
        	while(tempKey != null)
        	{
        		i++;
        		tempKey = hashTable.get(hashKey).get(i);
        	}
        	hashTable.get(hashKey).set(i, key);
        	values.get(hashKey).set(i, value);
        }
        size++;
        resize();
        return value;
    }

    @Override
    public void clear() 
    {
    	for(int i = 0; i < size; i++)
    	{
    		hashTable.set(i, new ArrayList<K>());
    		values.set(i, new ArrayList<V>());
    	}
    	size = 0;
    }

    @Override
    public V get(K key) 
    {
        int hashKey = hash(key);
        if(hashTable.get(hashKey) == null)
        	throw new NoSuchElementException();
        else
        {
        	ArrayList<K> temp = hashTable.get(hashKey);
        	int i = 0;
        	for(int x = 0; x < temp.size(); x++)
        	{
        		if(temp.get(x).equals(key))
        			i = x;
        	}
        	if(i != 0)
        		return values.get(hashKey).get(i);
        	else
        		throw new NoSuchElementException();
        }
    }

    @Override
    public boolean isEmpty() 
    {
    	if(size == 0)
    		return true;
    	else
    		return false;
    }

    @Override
    public V remove(K key) 
    {
    	if(key == null)
    		throw new NullPointerException();
        int hashKey = hash(key);
        if(hashTable.get(hashKey).get(0) == null)
        	return null;
        else
        {
        	ArrayList<K> temp = hashTable.get(hashKey);
        	int i = 0;
        	for(int x = 0; x < temp.size(); x++)
        	{
        		if(temp.get(x).equals(key))
        			i = x;
        	}
        	V value = values.get(hashKey).get(i);
        	values.get(hashKey).remove(i);
        	hashTable.get(hashKey).remove(i);
        	size--;
        	return value;
        }
    }

    @Override
    public int size() 
    {
        return size;
    }
    
    private int hash(K key)
    {
    	return key.hashCode() % capacity;
    }
    
    private void resize()
    {
    	if(size / hashTable.size() > maxLoadFactor)
    	{
    		//find next prime number, rehash and move
    		int dubSize = size * 2;
    		int nextPrime = 0;
    		for(int i = 0; i < PRIMES.size(); i++)
    		{
    			if(PRIMES.get(i) >= dubSize)
    			{
    				nextPrime = PRIMES.get(i);
    				break;
    			}
    		}
    		ArrayList<ArrayList<K>> tempHash = hashTable;
    		hashTable = new ArrayList<ArrayList<K>>(nextPrime);
    		ArrayList<ArrayList<V>> tempValues = values;
    		values = new ArrayList<ArrayList<V>>(nextPrime);
    		for(int i = 0; i < hashTable.size(); i++)
    		{
    			hashTable.set(i, new ArrayList<K>());
    			values.set(i, new ArrayList<V>());
    		}
    		int tempSize = size;
    		while(!isEmpty())
    		{
    			K tempKey = nextKey(tempHash);
    			V tempVal = nextValue(tempValues, tempHash);
    			put(tempKey, tempVal);
    			size--;
    		}
    		size = tempSize;
    		capacity = nextPrime;
    	}
    }
    
    private int nextIndex(ArrayList<ArrayList<K>> keys)
    {
    	for(int i = 0; i < keys.size(); i++)
    	{
    		if(keys.get(i).get(0) != null)
    			return i;
    	}
    	return -1;
    }
    
    private K nextKey(ArrayList<ArrayList<K>> keys)
    {
    	int i = nextIndex(keys);
    	return keys.get(i).remove(keys.get(i).size()-1);
    }
    
    private V nextValue(ArrayList<ArrayList<V>> vals, ArrayList<ArrayList<K>> keys)
    {
    	int i = nextIndex(keys);
    	return vals.get(i).remove(vals.get(i).size()-1);
    }
    
    private ArrayList<Integer> getPrimes(int capacity)
    {
    	ArrayList<Integer> primes = new ArrayList<Integer>();
    	for(int i = capacity; i < 1000000; i ++)
    	{
    		primes.add(i);
    	}
    	for(int i = 0; i < primes.size(); i ++)
    	{
    		for(int j = 2; j < i; j++)
    		{
    			if(primes.get(i) % j == 0)
    			{
    				primes.remove(i);
    				i--;
    				break;
    			}
    		}
    	}
    	return primes;
    }
}
