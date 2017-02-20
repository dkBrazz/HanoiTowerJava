package mamorydrivenstuff;

import org.junit.Before;
import org.junit.Test;
import tower.RodException;

import static org.junit.Assert.assertEquals;

public class MemoryRodTest {
    private MemoryRod memoryRod;

    @Before
    public void setUp(){
        memoryRod = new MemoryRod();
    }

    @Test
    public void shouldFillRod() throws Exception {
        memoryRod.push(42);
        memoryRod.push(3);
        memoryRod.push(1);
    }

    @Test(expected = RodException.class)
    public void shouldFailOnLargerDisk() throws Exception {
        memoryRod.push(1);
        memoryRod.push(10);
    }

    @Test
    public void shouldReturnDesiredDisk() throws Exception {
        int disk1 = 524;
        int disk2 = 52;
        int disk3 = 5;
        memoryRod.push(disk1);
        memoryRod.push(disk2);
        memoryRod.push(disk3);

        int returnedDisk3 = memoryRod.pop();
        int returnedDisk2 = memoryRod.pop();
        int returnedDisk1 = memoryRod.pop();

        assertEquals(returnedDisk1, disk1);
        assertEquals(returnedDisk2, disk2);
        assertEquals(returnedDisk3, disk3);
    }

    @Test(expected = RodException.class)
    public void shouldFailOnEmptyRod() throws Exception {
        memoryRod.pop();
    }
}