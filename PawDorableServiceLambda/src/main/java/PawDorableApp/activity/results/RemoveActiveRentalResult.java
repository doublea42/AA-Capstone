package PawDorableApp.activity.results;

import PawDorableApp.activity.request.RemoveActiveRentalRequest;

public class RemoveActiveRentalResult {
    private final boolean remove;

    public RemoveActiveRentalResult(boolean remove) {
        this.remove = remove;
    }

    public boolean isRemove() {
        return remove;
    }

    @Override
    public String toString() {
        return "RemoveActiveRentalResult{" +
                "remove=" + remove +
                '}';
    }
    public static Builder builder() {return new Builder();}
    public static class Builder{
        private boolean remove;
        public Builder withRemove(boolean remove){
            this.remove = remove;
            return this;
        }
        public RemoveActiveRentalResult build(){return new RemoveActiveRentalResult(remove);}
    }
}
