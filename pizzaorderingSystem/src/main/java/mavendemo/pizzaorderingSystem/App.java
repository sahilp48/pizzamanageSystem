package mavendemo.pizzaorderingSystem;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class App 
{
	static String[] columns = {"ID", "OrderName", "QTY", "Price","Total"};
	Scanner sc = new Scanner(System.in);
	 String id,name;
	 int Qty,Rupees,Total;
	

	public pizzaorder addorder() {
		System.out.println("Enter Order no : ");
		id=sc.next();
		System.out.println("Enter Order Name : ");
		name=sc.next();
		System.out.println("Enter Quantity : ");
		Qty=sc.nextInt();
		System.out.println("Enter Price :");
		
		Rupees=sc.nextInt();
		pizzaorder po = new pizzaorder();
		Total=Qty*Rupees;
		po.setOrder_id(id);
		po.setOrder_name(name);
		po.setQty(Qty);
		po.setRupees(Rupees);
		po.setTotal(Total);
//		a1.add(po);
		return po;
	}
	public void XlsUpdate(ArrayList<pizzaorder> a1) throws IOException {
		// TODO Auto-generated method stub
		Workbook workbook = new XSSFWorkbook(); 
        CreationHelper createHelper = workbook.getCreationHelper();
        // Create a Sheet
        Sheet sheet = workbook.createSheet("Pizza");
        // Create a Row
        Row headerRow = sheet.createRow(0);
        // Create cells
        for(int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
        }
        // Create Cell Style for formatting Date
        CellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));
        // Create Other rows and cells with employees data
        int rowNum = 1;
        for(pizzaorder pi: a1) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0)
                    .setCellValue(pi.getOrder_id());
            row.createCell(1)
                    .setCellValue(pi.getOrder_name());
            row.createCell(2)
            .setCellValue(pi.getQty());
            row.createCell(3)
            .setCellValue(pi.getRupees());
            row.createCell(4)
            .setCellValue(pi.getTotal());
        }
		// Resize all columns to fit the content size
        for(int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }
        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream("src/main/resources/file.xls");
        workbook.write(fileOut);
        fileOut.close();
        // Closing the workbook
        workbook.close();
	}
	public void dispayorders(ArrayList<pizzaorder> a1) {
		if (a1.size()==0) {
			System.out.println("No Records Found !!");
		}
		else
		{
			System.out.println();
			System.out.println("Order_ID"+"\t "+"Order_Name"+"\t"+"QTY"+"\t"+"\t"+"Price"+"\t"+"\t"+"Total");
			System.out.println();
			for (pizzaorder pi : a1) {
				System.out.println(pi.getOrder_id()+"\t "+"\t "+pi.getOrder_name()+"\t"+"\t "+pi.getQty()+"\t"+"\t "+pi.getRupees()+"\t"+"\t "+pi.getTotal());
			}
			System.out.println();
		}
	}
	public void searchorder(ArrayList<pizzaorder> a1) {
		// TODO Auto-generated method stub
		int count=0;
		System.out.println("Enter Order Number");
		String in=sc.next();
		for (pizzaorder pi : a1) {
			if (pi.getOrder_id().equals(in)) {
				System.out.println("Order_ID"+"\t "+"Order_Name"+"\t"+"QTY"+"\t"+"\t"+"Price"+"\t"+"\t"+"Total");
				break; //For printing heading
			}
		}
		for (pizzaorder pi : a1) {
			if (pi.getOrder_id().equals(in)) {
				System.out.println(pi.getOrder_id()+"\t "+"\t "+pi.getOrder_name()+"\t"+"\t "+pi.getQty()+"\t"+"\t "+pi.getRupees()+"\t"+"\t "+pi.getTotal());
				count++;
			}
		}
		if (count==0) {
			System.out.println("No order Found !!");
		}
		
	}
	public void deleteorder(ArrayList<pizzaorder> a1) {
		System.out.println("Enter order number you want to delete");
		String in=sc.next();
		int count=0;
		for (pizzaorder po : a1) {
			if (po.getOrder_id().equals(in)) {
				a1.remove(po);
				System.out.println();
				System.out.println("Order Deleted Succesfully !!");
				System.out.println();
				count++;
				break;
			}
		}
		if (count==0) {
			System.out.println("Record Does not Exist !!");
		}
		
		
	}
}
