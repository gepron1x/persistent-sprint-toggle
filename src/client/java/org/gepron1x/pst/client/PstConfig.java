package org.gepron1x.pst.client;

public class PstConfig {


    private boolean sprintingEnabled = false;

    private transient boolean dirty = false;

    public PstConfig() {

    }

    public PstConfig(boolean sprintingEnabled) {
        this.sprintingEnabled = sprintingEnabled;
    }

    public boolean isSprintingEnabled() {
        return this.sprintingEnabled;
    }

    public void setSprintingEnabled(boolean sprintingEnabled) {
        if(this.sprintingEnabled == sprintingEnabled) {
            return;
        }
        this.sprintingEnabled = sprintingEnabled;
        this.dirty = true;
    }

    public boolean isDirty() {
        return this.dirty;
    }

    public void markClean() {
        this.dirty = false;
    }
}
