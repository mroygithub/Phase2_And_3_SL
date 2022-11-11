package action;


import java.io.File;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

    public class WriteIntoXL {


        public static Map<String, Object[]> ST_DATA;
        public static int Key = 1;
        public static XSSFSheet spreadsheet;
        // creating a row object
        public static XSSFRow row;
        public static XSSFWorkbook workbook;

        // any exceptions need to be caught
        public static void main(String[] args) throws Exception
        {
            // workbook object
            XSSFWorkbook workbook = new XSSFWorkbook();

            // spreadsheet object
            XSSFSheet spreadsheet
                    = workbook.createSheet(" Student Data ");

            // creating a row object
            XSSFRow row;

            // This data needs to be written (Object[])
            Map<String, Object[]> studentData
                    = new TreeMap<String, Object[]>();

            studentData.put(
                    "1",
                    new Object[] { "Roll No", "NAME", "Year" });

            studentData.put("2", new Object[] { "128", "Aditya",
                    "2nd year" });

            studentData.put(
                    "3",
                    new Object[] { "129", "Narayana", "2nd year" });

            studentData.put("4", new Object[] { "130", "Mohan",
                    "2nd year" });

            studentData.put("5", new Object[] { "131", "Radha",
                    "2nd year" });

            studentData.put("6", new Object[] { "132", "Gopal",
                    "2nd year" });

            Set<String> keyid = studentData.keySet();

            int rowid = 0;

            // writing the data into the sheets...

            for (String key : keyid) {

                row = spreadsheet.createRow(rowid++);
                Object[] objectArr = studentData.get(key);
                int cellid = 0;

                for (Object obj : objectArr) {
                    Cell cell = row.createCell(cellid++);
                    cell.setCellValue((String)obj);
                }
            }

            // .xlsx is the format for Excel Sheets...
            // writing the workbook into the file...
            FileOutputStream out = new FileOutputStream(
                    new File("/Users/mithunroy/Desktop/SL_Training/GFGsheet.xlsx"));

            workbook.write(out);
            out.close();
        }


        public Map XL_InitialTest_Report(){


            // workbook object
             workbook = new XSSFWorkbook();

            // spreadsheet object
             spreadsheet
                    = workbook.createSheet(" Student Data ");

            // creating a row object
           // XSSFRow row;

            // This data needs to be written (Object[])
            ST_DATA = new TreeMap<String, Object[]>();

            ST_DATA.put(
                    "1",
                    new Object[] { "Test Case Name", "Test Case Description", "Test Case Status" , "Time"});

            return ST_DATA;


        }

        public void create_Actual_Report(String TCN , String TCD , String status , String TC_Time){

            Key = Key+1;

            ST_DATA.put(
                    String.valueOf(Key),
                    new Object[] { TCN, TCD, status , TC_Time});

        }

        public void CreateAndCloseReport() throws Exception{

            Set<String> keyid = ST_DATA.keySet();

            int rowid = 0;

            // writing the data into the sheets...

            for (String key : keyid) {

                row = spreadsheet.createRow(rowid++);
                Object[] objectArr = ST_DATA.get(key);
                int cellid = 0;

                for (Object obj : objectArr) {
                    Cell cell = row.createCell(cellid++);
                    cell.setCellValue((String)obj);
                }
            }

            // .xlsx is the format for Excel Sheets...
            // writing the workbook into the file...
            FileOutputStream out = new FileOutputStream(
                    new File("/Users/mithunroy/Desktop/SL_Training/GFGsheet2.xlsx"));

            workbook.write(out);
            out.close();

        }

}
