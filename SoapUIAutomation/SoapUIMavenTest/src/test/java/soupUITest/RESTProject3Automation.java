package soupUITest;

import java.io.IOException;

import org.apache.xmlbeans.XmlException;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.eviware.soapui.impl.wsdl.WsdlProject;
import com.eviware.soapui.impl.wsdl.WsdlTestSuite;
import com.eviware.soapui.impl.wsdl.testcase.WsdlTestCase;
import com.eviware.soapui.impl.wsdl.testcase.WsdlTestCaseRunner;
import com.eviware.soapui.impl.wsdl.teststeps.WsdlTestStep;
import com.eviware.soapui.model.iface.Submit.Status;
import com.eviware.soapui.model.support.PropertiesMap;
import com.eviware.soapui.model.testsuite.TestStepResult;
import com.eviware.soapui.support.SoapUIException;

public class RESTProject3Automation {
	
	
	@Test
	public void automationTest() throws XmlException, IOException, SoapUIException {
		SoftAssert sAssert = new SoftAssert();
		WsdlProject project = new WsdlProject("F:\\SoupUI\\REST-Project-3-soapui-project.xml");
		WsdlTestSuite testSuite = project.getTestSuiteByName("AutomationTestSuite");
		System.out.println("COUNT:   " + testSuite.getTestCaseCount());
		
		for (int i = 0; i < testSuite.getTestCaseCount(); i++)
		{
			WsdlTestCase testCase = testSuite.getTestCaseAt(i);
			WsdlTestCaseRunner runner = new WsdlTestCaseRunner(testCase, new PropertiesMap());
			
			for (int j = 0; j < testCase.getTestStepCount(); j++)
			{
				System.out.println("TestStep Count :   " + testCase.getTestStepCount());
				WsdlTestStep testStep = testCase.getTestStepAt(j);
				TestStepResult status = runner.runTestStep(testStep);

				sAssert.assertEquals(status.getStatus(), Status.FINISHED);
				System.out.println("ERROR    : " + status.getError());
				System.out.println("Message    : " + status.getMessages());
				System.out.println("TestStep Name :   " + status.getTestStep().getName().toString());

			}

		}
//		System.out.println("TESTCASE :  " + runner.getTestCase().getName().toString());
		sAssert.assertAll();
	}

	
}
