import jxl.*
import jxl.write.*
import java.io.File;
import com.eviware.soapui.support.*;
import java.util.*;
import java.lang.*;
import jxl.read.biff.BiffException;

Workbook wk;
def fr;

try
{
	fr = new File("F:\\SoupUI\\Book12.xls")
	wk = Workbook.getWorkbook(fr);
	//log.info wk
	def s1 = wk.getSheet("Sheet1");
	def r = s1.getRows();
	log.info r
for(def i=1; i < r; i++)
{
	def c1 = s1.getCell(0, i)//getCell(Column, row)
	def c2 = s1.getCell(1, i)//getCell(Column, row)

	def cDataFirst  = c1.getContents()
	def cDataSecond = c2.getContents()
}
}catch(Exception e)
// -------------------------------------------------------------
// Catching exception and writing it to error log
// -------------------------------------------------------------
{
log.error(e)
}
finally
{
	if (wk != null) 
	{
		wk.close();
	}
}