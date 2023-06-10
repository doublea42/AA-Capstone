package PawDorableApp.activity.results;

public class RemovePetResult {
    private final boolean remove;

    public RemovePetResult(boolean remove) {
        this.remove = remove;
    }

    public boolean isRemove() {
        return remove;
    }

    @Override
    public String toString() {
        return "RemovePetResult{" +
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
        public RemovePetResult build(){return new RemovePetResult(remove);}
    }
}
