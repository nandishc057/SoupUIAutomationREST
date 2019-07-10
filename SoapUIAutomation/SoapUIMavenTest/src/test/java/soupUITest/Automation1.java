package soupUITest;

import java.io.IOException;

import org.apache.xmlbeans.XmlException;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.eviware.soapui.impl.wsdl.WsdlProject;
import com.eviware.soapui.impl.wsdl.WsdlTestSuite;
import com.eviware.soapui.impl.wsdl.testcase.WsdlTestCase;
import com.eviware.soapui.impl.wsdl.testcase.WsdlTestCaseRunner;
import com.eviware.soapui.model.iface.Submit.Status;
import com.eviware.soapui.model.support.PropertiesMap;
import com.eviware.soapui.support.SoapUIException;

public class Automation1 {
	@Test(enabled=false)
	public void employeePortalTest() throws XmlException, IOException, SoapUIException {
		SoftAssert sAssert = new SoftAssert();
		WsdlProject project = new WsdlProject("F:\\SoupUI\\Automation1-soapui-project.xml");
		WsdlTestSuite testSuite = project.getTestSuiteByName("AutomationTesting");
		for (int i = 0; i < testSuite.getTestCaseCount(); i++) {
			WsdlTestCase testCase = testSuite.getTestCaseAt(i);
			WsdlTestCaseRunner runner = testCase.run(new PropertiesMap(), false);
			sAssert.assertEquals(Status.FINISHED, runner.getStatus());
			System.out.println("TESTCASE :  " + runner.getTestCase().getName().toString());
		}
	}

}
