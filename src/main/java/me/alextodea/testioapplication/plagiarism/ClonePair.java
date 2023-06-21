package me.alextodea.testioapplication.plagiarism;

public class ClonePair {
    private DetectorEntry leftDetectorEntry;
    private DetectorEntry rightDetectorEntry;

    public ClonePair(DetectorEntry leftDetectorEntry, DetectorEntry rightDetectorEntry) {
        this.leftDetectorEntry = leftDetectorEntry;
        this.rightDetectorEntry = rightDetectorEntry;
    }

    public DetectorEntry getLeftMapEntry() {
        return leftDetectorEntry;
    }

    public void setLeftMapEntry(DetectorEntry leftDetectorEntry) {
        this.leftDetectorEntry = leftDetectorEntry;
    }

    public DetectorEntry getRightMapEntry() {
        return rightDetectorEntry;
    }

    public void setRightMapEntry(DetectorEntry rightDetectorEntry) {
        this.rightDetectorEntry = rightDetectorEntry;
    }
}
