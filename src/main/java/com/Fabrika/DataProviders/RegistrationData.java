package com.Fabrika.DataProviders;


import org.testng.annotations.DataProvider;

public class RegistrationData {

    @DataProvider(name = "regData")
    public Object[][] invalidRegData(){
        return new Object[][]{
                {"Savva", "Genchevskiy", "savva", "19021992qa", "19021992qa", "savva_genchevskiy", "invalid email!"},
                {"Savva", "Genchevskiy", "savva@gmail.com", "19021992qa", "19021992qq", "savva_genchevskiy", "Password fields don't match"},
        };
    }

}
