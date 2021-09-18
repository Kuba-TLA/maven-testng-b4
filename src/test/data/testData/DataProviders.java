package data.testData;

import org.testng.annotations.DataProvider;

import java.util.ArrayList;

public class DataProviders {

    @DataProvider(name = "userInfo")
    public static Object[][] userInfo(){
        //======================================================
        ArrayList<String> list = new ArrayList<>();
        list.add("Personal Details");
        list.add("Contact Details");
        list.add("Emergency Contacts");
        list.add("Dependents");
        list.add("Immigration");
        list.add("Job");
        list.add("Salary");
//        list.add("Tax Exemptions");
        list.add("Report-to");
        list.add("Qualifications");
        list.add("Memberships");
        //======================================================
        Object[][] data = new Object[1][1];
        data[0][0] = list;

        return data;
    }
}
