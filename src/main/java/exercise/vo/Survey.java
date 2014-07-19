package exercise.vo;

public final class Survey {

    private final boolean satisfatory;
    private Integer offendingColor;

    public Survey(boolean satisfatory, Integer offendingColor) {
        this.satisfatory = satisfatory;
        this.offendingColor = offendingColor;
    }

    public Survey(boolean satisfatory) {
        this.satisfatory = satisfatory;
    }

    public boolean isSatisfatory() {
        return satisfatory;
    }

    public Integer getOffendingColor() {
        return offendingColor;
    }

    public void setOffendingColor(Integer offendingColor) {
        this.offendingColor = offendingColor;
    }

    @Override
    public String toString() {
        return "Survey{" +
                "satisfatory=" + satisfatory +
                ", offendingColor=" + offendingColor +
                '}';
    }
}
