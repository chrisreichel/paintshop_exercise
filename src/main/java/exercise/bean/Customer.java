package exercise.bean;

import exercise.vo.Survey;

import java.util.Map;

import static java.util.Collections.unmodifiableMap;

public final class Customer{

    private Map<Integer, Color> colors;

    public Customer() {}

    public Customer(Map<Integer, Color> colors) {
        this.colors = colors;
    }

    public Map<Integer, Color> getColors() {
        return unmodifiableMap(colors);
    }

    public void setColors(Map<Integer, Color> colors) {
        this.colors = colors;
    }

    public boolean hasOnlyOneColor() {
        return this.colors.size() == 1;
    }

    /**
     *
     * @param response
     * @return
     */
    public Survey isSatisfiedWith(ResponseBuilder response) {
        Integer offendingColor = null;
        boolean thisCustomerWereAttended = true;
        for(Integer responseColor : response.getColorsFinishesMap().keySet()){
            if(this.colors.containsKey(responseColor)){
                final ColorSummary responseColorSetting = response.getColorsFinishesMap().get(responseColor);
                if(!responseColorSetting.isMustBeSatisfied()){
                    final FinishingType expectedFinish = colors.get(responseColor).getFinishingType();
                    if(expectedFinish.equals(responseColorSetting.getSatisfyingFinishing())){
                        return new Survey(true);
                    }
                    offendingColor = responseColor;
                    thisCustomerWereAttended = false;
                }
            }
        }
        return new Survey(thisCustomerWereAttended, offendingColor);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "colors=" + colors +
                '}';
    }
}
