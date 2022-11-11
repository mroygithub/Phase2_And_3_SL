package action;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class testNG_XLReport extends WriteIntoXL{


    @BeforeClass
    public  void setUp(){

        XL_InitialTest_Report();

    }


    @Test
    public void testing(){


        create_Actual_Report("TestCase001" , "Test Description1" , "PASS" , "Time 1");
        create_Actual_Report("TestCase002" , "Test Description2", "PASS" , "Time 2");

    }




    @AfterClass
    public  void tearDown() throws Exception{



        CreateAndCloseReport();

    }




}
