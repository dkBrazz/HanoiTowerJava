package tower;

public interface Rod {
    void push(int diskToPush) throws RodOversizeException;

    int pop() throws RodEmptyException;
}
