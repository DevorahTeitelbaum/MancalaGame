package dt;

class Pit {
    private int stonesCount;
    
    public Pit(int stonesCount){
    	this.stonesCount = stonesCount;
    }

    public void addStones(int numStones) {
        stonesCount += numStones;
    }

    public void removeAllStones() {
        stonesCount = 0;
    }
    
    public int getStonesCount() {
    	return stonesCount;
    }
}