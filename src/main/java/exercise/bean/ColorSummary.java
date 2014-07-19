package exercise.bean;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

/**
 * Holds the summary of that specific color.
 * It is set to G as default, because it's cheaper.
 */
public final class ColorSummary {

    private boolean mustBeSatisfied = false;
    private FinishingType satisfyingFinishing = FinishingType.G;

    private final List<Customer> customers = new ArrayList<>();

    /*
        Sets the flag that there's a customer that requires this color to be on this finishing
     */
    public void mustBeSatisfied() {
        mustBeSatisfied = true;
    }

    public boolean isMustBeSatisfied() {
        return mustBeSatisfied;
    }

    public List<Customer> getCustomers() {
        return unmodifiableList(customers);
    }

    public void addCustomer(Customer customers) {
        this.customers.add(customers);
        if(customers.getColors().size() == 1){
            final Color color = customers.getColors().entrySet().iterator().next().getValue();
            if(mustBeSatisfied && !color.getFinishingType().equals(satisfyingFinishing)){
                throw new IllegalStateException("No solution exists");
            }
            setSatisfyingFinishing(color.getFinishingType());
        }
    }

    public void setSatisfyingFinishing(FinishingType satisfyingFinishing) {
        if(!mustBeSatisfied){
            this.satisfyingFinishing = satisfyingFinishing;
            mustBeSatisfied();
        }
    }

    public FinishingType getSatisfyingFinishing() {
        return satisfyingFinishing;
    }

    @Override
    public String toString() {
        return "CustomerColorSetting{" +
                "mustBeSatisfied=" + mustBeSatisfied +
                ", satisfyingFinishing=" + satisfyingFinishing +
                ", customers=" + customers +
                '}';
    }
}
