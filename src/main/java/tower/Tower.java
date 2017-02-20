package tower;

import java.util.HashMap;
import java.util.Map;

public class Tower {
    private Map<RodPosition, Rod> rods = new HashMap<>();

    public Tower(RodFactory rodFactory) {
        this.rods.put(RodPosition.LEFT, rodFactory.createRod());
        this.rods.put(RodPosition.MIDDLE, rodFactory.createRod());
        this.rods.put(RodPosition.RIGHT, rodFactory.createRod());
    }

    public void init(RodPosition rodPosition, int diskCount) throws RodOversizeException{
        for (int i = diskCount; i > 0; i--) {
            this.rods.get(rodPosition).push(i);
        }
    }

    public void move(RodPosition src, RodPosition dst) throws RodException {
        int disk = this.rods.get(src).pop();
        try {
            this.rods.get(dst).push(disk);
        } catch (RodOversizeException e) {
            this.rods.get(src).push(disk);
            throw e;
        }
    }
}
