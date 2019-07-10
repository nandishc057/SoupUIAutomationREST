import groovy.json.JsonSlurper
import groovy.json.*
import com.eviware.soapui.impl.wsdl.testcase.WsdlTestRunContext

import jxl.*
import jxl.write.*
import java.io.File;
import com.eviware.soapui.support.*;
import java.util.*;
import java.lang.*;
import jxl.read.biff.BiffException;


Workbook wk;
def fr;
def postBookREQUEST = testRunner.testCase.testSuite.testCases["AddbookTestCase"].testSteps["postBook"].getPropertyValue('Request')
def jsonData = new JsonSlurper().parseText(postBookREQUEST)
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
	//Assign Cell Data to JSON Data
	jsonData.name = cDataFirst
     jsonData.isbn = cDataSecond
     //Convert JSON Data to JSON Object
	def jsonReqAsString = JsonOutput.toJson(jsonData)
	//Save JSON Object to Request
	testRunner.testCase.testSuite.testCases["AddbookTestCase"].testSteps["postBook"].setPropertyValue('Request',jsonReqAsString);
	//Run the JSON object to 
	def postBook= testRunner.testCase.testSuite.testCases["AddbookTestCase"].testSteps["postBook"]
	def contextData = new WsdlTestRunContext(postBook)
	postBook.run(testRunner, contextData)
	 
	//Validation
	 def reponseData= testRunner.testCase.testSuite.testCases['AddbookTestCase'].testSteps["postBook"].getPropertyValue('Response')
	 def jsonResponse = new JsonSlurper().parseText(reponseData)
	 def message = jsonResponse.Msg
	 log.info message
	 log.info jsonResponse.ID
	 def d= jsonData.aisle
	 //Assertion
	 assert jsonResponse.ID == cDataSecond+d
	 assert testRunner.timeTaken < 5000
}
}
catch(Exception e)
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