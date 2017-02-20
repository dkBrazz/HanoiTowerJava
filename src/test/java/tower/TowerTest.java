package tower;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;
import static tower.RodPosition.*;

public class TowerTest {

    private Tower tower;
    private Rod leftRod;
    private Rod midRod;
    private Rod rightRod;
    private RodFactory rodFactory;

    @Before
    public void setup() {
        leftRod = mock(Rod.class);
        midRod = mock(Rod.class);
        rightRod = mock(Rod.class);

        rodFactory = mock(RodFactory.class);
        when(rodFactory.createRod())
                .thenReturn(leftRod)
                .thenReturn(midRod)
                .thenReturn(rightRod);

        tower = new Tower(rodFactory);
    }

    @Test
    public void shouldInitTowerRight() throws Exception {
        tower.init(LEFT, 3);

        verify(leftRod, times(3)).push(anyInt());
    }

    @Test
    public void shouldMoveDisk() throws Exception {
        when(rightRod.pop()).thenReturn(42);

        tower.move(RIGHT, LEFT);

        verify(rightRod, times(1)).pop();
        verify(leftRod, times(1)).push(eq(42));
    }

    @Test(expected = RodEmptyException.class)
    public void shouldFailOnMoveFromEmpty() throws Exception {
        when(leftRod.pop()).thenThrow(new RodEmptyException("Error"));

        tower.move(LEFT, MIDDLE);
    }

    @Test(expected = RodOversizeException.class)
    public void shouldFailOnMoveToSmaller() throws Exception {
        doThrow(new RodOversizeException("Error")).when(midRod).push(anyInt());

        tower.move(RIGHT, MIDDLE);
    }

    @Test
    public void shouldPushDiskBackOnPushFailed() throws Exception {
        when(rightRod.pop()).thenReturn(78);
        doThrow(new RodOversizeException("Error")).when(midRod).push(anyInt());

        try {
            tower.move(RIGHT, MIDDLE);
        } catch (RodOversizeException e) {
            // do nohing
        }

        verify(rightRod, times(1)).push(eq(78));
    }
}