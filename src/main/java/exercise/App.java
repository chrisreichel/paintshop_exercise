package exercise;

import java.io.File;
import java.io.IOException;

public final class App {

    private App(){}

    public static void main(String[] args) {
        try {
            final PaintShop paintShop = new PaintShop(new File(args[0]));
            System.out.println(paintShop.processColors());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

}
