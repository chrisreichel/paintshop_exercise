package exercise.bean;

import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.unmodifiableMap;

public final class ResponseBuilder {

    private final Map<Integer, ColorSummary> colorsFinishesMap;
    private String error = null;

    public ResponseBuilder(int size) {
        colorsFinishesMap = new HashMap<>(size);
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void addColor(Integer key, ColorSummary colorSetting) {
        colorsFinishesMap.put(key, colorSetting);
    }

    public String generateFinishingString() {
        final StringBuffer stringBuffer = new StringBuffer();
        if(error == null){
            for(Integer key : colorsFinishesMap.keySet()){
                stringBuffer.append(colorsFinishesMap.get(key).getSatisfyingFinishing()).append(" ");
            }
        }
        else{
            stringBuffer.append(error);
        }
        return stringBuffer.toString().trim();
    }

    public Map<Integer, ColorSummary> getColorsFinishesMap() {
        return unmodifiableMap(colorsFinishesMap);
    }

    public void toggle(Integer color) {
        final ColorSummary actual = colorsFinishesMap.get(color);
        actual.setSatisfyingFinishing(actual.getSatisfyingFinishing().opposite());
    }
}
