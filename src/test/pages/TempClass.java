package pages;

import utils.ConfigReader;

public class TempClass {
    public static void main(String[] args) {
        String s= ConfigReader.readProperty("src/test/data/config/config.properties", "username");
        System.out.println(s);
    }
}
