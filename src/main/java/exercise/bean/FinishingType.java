package exercise.bean;

public enum FinishingType {

    M("M"), G("G");

    private String value;

    private FinishingType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public FinishingType opposite(){
        if(this.getValue().equalsIgnoreCase(FinishingType.M.getValue())){
            return G;
        }
        return M;
    }

    public static FinishingType lookup(String value){
        for(FinishingType f: FinishingType.values()){
            if(f.getValue().equalsIgnoreCase(value)){
                return f;
            }
        }
        throw new IllegalArgumentException("Invalid value " + value);
    }
}
