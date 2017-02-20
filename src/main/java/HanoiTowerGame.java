import mamorydrivenstuff.MemoryRodFactory;
import tower.RodException;
import tower.RodPosition;
import tower.Tower;

public class HanoiTowerGame {
    public static void main(String[] args) {
        Tower tower = new Tower(new MemoryRodFactory());
        try {
            tower.init(RodPosition.LEFT, 3);
            doMove(tower, 3, RodPosition.LEFT, RodPosition.RIGHT, RodPosition.MIDDLE);
            System.out.println("Done");
        } catch (RodException e) {
            e.printStackTrace();
        }
    }

    private static void doMove(Tower tower, int diskCount, RodPosition src, RodPosition dst, RodPosition tmp) throws RodException {
        if(diskCount > 1)
            doMove(tower, diskCount-1, src, tmp, tmp);
        tower.move(src, dst);
        if(diskCount > 1)
            doMove(tower, diskCount-1, tmp, dst, src);
    }
}
