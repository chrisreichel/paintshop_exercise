package exercise;

import exercise.bean.Color;
import exercise.bean.ColorSummary;
import exercise.bean.Customer;
import exercise.bean.ResponseBuilder;
import exercise.vo.Survey;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static com.google.common.base.Charsets.UTF_8;
import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.io.Files.readLines;
import static java.util.Arrays.asList;
import static java.util.Collections.*;

/**
 * Based on Newton's Square Root Approximation method. <- n3w70N ftw!
 *
 * First it loads all colors from all customers, then set the colors that considered as "immutable"
 * (because it's someone's only option).
 * Then set all possible colors to G -> it's cheapest response...
 * Verify if the proposed colors will satisfy all customers.
 * If not, do an approximation: change one of the colors that is a candidate to satisfy everybody.
 * Check if it is OK to all customers, if not, approximate again...
 * Eventually will generate a response or die trying ;)
 */
public final class PaintShop {

    private static final String LINE_BREAK = "\\n";
    private static final int NUMBER_OF_COLORS_INDEX = 0;
    private static final String LINE_PARSER = "(?<![^a-zA-Z])\\s";

    private final Map<Integer, ColorSummary> colorsMap = new HashMap<>();
    private List<String> content = new ArrayList<>();
    private final List<Customer> customersList = new ArrayList<>();
    private int amountOfColors = -1;

    PaintShop(){}

    public PaintShop(File input) throws IOException {
        checkArgument(input != null);
        parseInput(readLines(input, UTF_8));
    }

    public PaintShop(String input){
        checkArgument(input != null);
        parseInput(asList(input.split(LINE_BREAK)));
    }

    public PaintShop(List<String> input){
        checkArgument(input != null && !input.isEmpty());
        parseInput(input);
    }

    private void parseInput(List<String> input){
        checkArgument(input != null && !input.isEmpty());
        amountOfColors = Integer.parseInt(input.get(NUMBER_OF_COLORS_INDEX));
        content = unmodifiableList(input.subList(NUMBER_OF_COLORS_INDEX + 1, input.size()));
    }

    /**
     *
     * @return
     */
    public String processColors(){
        final ResponseBuilder response = new ResponseBuilder(this.amountOfColors);
        try{
            mapColorAndCustomersExpectations();
            for(Integer colorKey : colorsMap.keySet()){
                response.addColor(colorKey, colorsMap.get(colorKey));
            }
            Survey customersSurvey = verify(response);
            while(!customersSurvey.isSatisfatory()){
                improveGuess(response, customersSurvey.getOffendingColor());
                customersSurvey = verify(response);
            }
        } catch (IllegalStateException ex){
            response.setError(ex.getMessage());
        }
        return response.generateFinishingString();
    }

    /**
     * Improve guess by changing the color that are marked as should be attended
     * @param response
     */
    private void improveGuess(ResponseBuilder response, Integer color) {
        response.toggle(color);
    }

    /**
     *
     * @param response
     * @return
     */
    private Survey verify(ResponseBuilder response) {
        for(Customer customer : customersList){
            if(!customer.hasOnlyOneColor()){
                final Survey survey = customer.isSatisfiedWith(response);
                if(!survey.isSatisfatory()){
                    return survey;
                }
            }
        }
        return new Survey(true);
    }

    /**
     *
     */
    private void mapColorAndCustomersExpectations() {
        for(String line : content){
            if(line != null && line.trim().length() > 0){
                final Map<Integer, Color> customerColors = parseColors(line);
                final Customer customer = new Customer(customerColors);
                customersList.add(customer);
                for(Color color : customerColors.values()){
                    final ColorSummary colorSetting = colorsMap.get(color.getCode());
                    if(colorSetting != null){
                        colorSetting.addCustomer(customer);
                    }
                    else{
                        final ColorSummary newColorSetting = new ColorSummary();
                        newColorSetting.addCustomer(customer);
                        colorsMap.put(color.getCode(), newColorSetting);
                    }
                }
            }
        }
        if(colorsMap.size() != amountOfColors){
            System.err.println("The amount of colors parsed: " + colorsMap.size() + " is different from the amount provided: " + amountOfColors);
            throw new IllegalStateException("No solution exists");
        }
        sortCustomers();
    }

    private void sortCustomers() {
        sort(customersList, new Comparator<Customer>() {
            @Override
            public int compare(Customer o1, Customer o2) {
                if (o1.getColors().size() > o2.getColors().size()) {
                    return 1;
                } else if (o1.getColors().size() < o2.getColors().size()) {
                    return -1;
                }
                return 0;
            }
        });
    }

    /**
     *
     * @param line
     * @return
     */
    Map<Integer, Color> parseColors(String line) {
        final Map<Integer, Color> colors = new HashMap<>();
        String[] colorMetaCol = line.split(LINE_PARSER);
        for(String colorDef : colorMetaCol){
            final Color color = new Color(colorDef);
            colors.put(color.getCode(), color);
        }
        return unmodifiableMap(colors);
    }

    List<String> getContent() {
        return content;
    }

    int getAmountOfColors() {
        return amountOfColors;
    }
}
