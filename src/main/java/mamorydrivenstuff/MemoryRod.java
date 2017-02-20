package mamorydrivenstuff;

import tower.Rod;
import tower.RodEmptyException;
import tower.RodOversizeException;

import java.util.ArrayList;
import java.util.List;

public class MemoryRod implements Rod {
    private List<Integer> disks = new ArrayList<>();

    public void push(int diskToPush) throws RodOversizeException {
        if (disks.size() != 0 && disks.get(disks.size()-1) <= diskToPush){
            throw new RodOversizeException("disk '" + diskToPush + "' is larger than top disk - '" + disks.get(disks.size()-1) + "'");
        }

        disks.add(diskToPush);
    }

    public int pop() throws RodEmptyException {
        if (disks.isEmpty()) {
            throw new RodEmptyException("mamorydrivenstuff.MemoryRod is empty");
        }

        int disk = disks.get(disks.size() - 1);
        disks.remove(disks.size() - 1);

        return disk;
    }
}
