import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.junit.Assert.assertEquals;

/**
 * @author Julia
 */
public class MyHashMapTest {

    private MyHashMap myHashMap;
    private Map<Integer, Long> littleMap;
    private Map<Integer, Long> bigMap;
    private final Random random = new Random(100);

    @Before
    public void initTest() {
        myHashMap = new MyHashMap();

        littleMap = new HashMap<Integer, Long>(2);
        littleMap.put(null, null);
        littleMap.put(random.nextInt(1000), random.nextLong());

        bigMap = new HashMap<Integer, Long>(Const.DEFAULT_SIZE);
        for (int i=0; i < bigMap.size(); i++) {
            bigMap.put(random.nextInt(1000), random.nextLong());
        }
    }

    @Test
    public void testPutLittleData() throws Exception {
        for(Map.Entry<Integer, Long> entry : littleMap.entrySet()) {
            assertEquals(null, myHashMap.put(entry.getKey(), entry.getValue()));
        }
    }

    @Test
    public void testPutBigData() throws Exception {
        for(Map.Entry<Integer, Long> entry : bigMap.entrySet()) {
            assertEquals(null, myHashMap.put(entry.getKey(), entry.getValue()));
        }
    }

    @Test
    public void testGetWithLittleData() throws Exception {
        for(Map.Entry<Integer, Long> entry : littleMap.entrySet()) {
            myHashMap.put(entry.getKey(), entry.getValue());
        }
        for(Map.Entry<Integer, Long> entry : littleMap.entrySet()) {
            assertEquals(entry.getValue(), myHashMap.get(entry.getKey()));
        }
    }

    @Test
    public void testGetWithBigData() throws Exception {
        for(Map.Entry<Integer, Long> entry : bigMap.entrySet()) {
            myHashMap.put(entry.getKey(), entry.getValue());
        }
        for(Map.Entry<Integer, Long> entry : bigMap.entrySet()) {
            assertEquals(entry.getValue(), myHashMap.get(entry.getKey()));
        }
    }

    @Test
    public void testCheckSizeOfLittleMap() throws Exception {
        for(Map.Entry<Integer, Long> entry : littleMap.entrySet()) {
            myHashMap.put(entry.getKey(), entry.getValue());
        }
        assertEquals(littleMap.size(), myHashMap.size());
    }

    @Test
    public void testCheckSizeOfBigMap() throws Exception {
        for(Map.Entry<Integer, Long> entry : bigMap.entrySet()) {
            myHashMap.put(entry.getKey(), entry.getValue());
        }
        assertEquals(bigMap.size(), myHashMap.size());
    }

    @Test
    public void testOverwriteValue() throws Exception {
        int key = 1;
        Long oldValue = 2L;
        myHashMap.put(key, oldValue);
        Long newValue = 3L;
        myHashMap.put(key, newValue);
        assertEquals(newValue, myHashMap.get(key));
    }

}
