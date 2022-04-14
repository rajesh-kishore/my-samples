class SnapshotArray {
    
    private final Map<Integer,Integer>[] indexMap;
    
    private int currentSnapId = 0;

    public SnapshotArray(int length) {
        indexMap = new HashMap[length];
    }
    
   
    
    
    public void set(int index, int val) {
        
        if (Objects.isNull(indexMap[index])) {
            indexMap[index] = new HashMap<>();
        }
        indexMap[index].put(currentSnapId, val);
    }
    
    public int snap() {
        ++currentSnapId;
        return currentSnapId - 1;
    }
    
    public int get(int index, int snap_id) {
        
        // look for index and look for snap_id if its present
        // if its not present , keep decrementing till we get snap_id in that bucket
        /*
        example
        
        [0]
        [1]
        ..
        [5] -> [{snap_id(2), value - 3}, {snap_id(4), value - 5}
        
        
        */
        
       if (Objects.isNull(indexMap[index])) {
           snap_id = 0; // we will always have snap_id zero as all the values are 
            // considered mark to 0 initially
       } else {
            while (snap_id > 0 && !indexMap[index].containsKey(snap_id)) {
                snap_id--;
             }
        
            if (snap_id == -1) {
                snap_id = 0; // we will always have snap_id zero as all the values are 
                // considered mark to 0 initially
            }
       }
        
        return  Objects.isNull(indexMap[index]) ? 0 : indexMap[index].getOrDefault(snap_id, 0); // return default as 0
    }
}

/**
 * Your SnapshotArray object will be instantiated and called as such:
 * SnapshotArray obj = new SnapshotArray(length);
 * obj.set(index,val);
 * int param_2 = obj.snap();
 * int param_3 = obj.get(index,snap_id);
 */
