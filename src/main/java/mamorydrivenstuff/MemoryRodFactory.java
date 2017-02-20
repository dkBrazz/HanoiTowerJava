package mamorydrivenstuff;

import tower.Rod;
import tower.RodFactory;

public class MemoryRodFactory implements RodFactory {
    @Override
    public Rod createRod() {
        return new MemoryRod();
    }
}
