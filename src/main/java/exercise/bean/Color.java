package exercise.bean;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.google.common.base.Preconditions.checkArgument;

public final class Color {

    private final Pattern colorPattern = Pattern.compile("(\\d+)\\s{1,}([MGmg]{1,})");

    private int code;
    private FinishingType finishingType;

    public Color(int code) {
        this.code = code;
    }

    public Color(String metadata) {
        checkArgument(metadata != null && metadata.trim() != null);
        final Matcher matcher = colorPattern.matcher(metadata);
        if(matcher.find()){
            this.code = Integer.parseInt(matcher.group(1));
            this.finishingType = FinishingType.lookup(matcher.group(2));
        }
        else{
            throw new IllegalStateException("Line: " + metadata + " is unparseable");
        }
    }

    public int getCode() {
        return code;
    }

    public FinishingType getFinishingType() {
        return finishingType;
    }

    public void setFinishingType(FinishingType finishingType) {
        this.finishingType = finishingType;
    }

    @Override
    public String toString() {
        return "Color{" +
                "colorPattern=" + colorPattern +
                ", code=" + code +
                ", finishingType=" + finishingType +
                '}';
    }
}
