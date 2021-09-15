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
        list.add("Tax Exemptions");
        list.add("Report-to");
        list.add("Qualifications");
        list.add("Memberships");
        //======================================================
        Object[][] data = new Object[1][3];
        data[0][0] = "Admin";
        data[0][1] = "admin123";
        data[0][2] = list;

        return data;
    }
}
