import com.eviware.soapui.support.XmlHolder
import com.eviware.soapui.impl.wsdl.testcase.WsdlTestRunContext

def reqData= testRunner.testCase.testSuite.testCases["addEmployee TestCase"].testSteps["addEmployee"].getPropertyValue('Request')
def xmlData= new XmlHolder(reqData)

def data ="mana1313111z"
xmlData.setNodeValue("//typ:name", data)

//To save Data
def savedXMLData = xmlData.getXml()

//Assign to Request
def req= testRunner.testCase.testSuite.testCases["addEmployee TestCase"].testSteps["addEmployee"].setPropertyValue('Request',savedXMLData )

//Run the TestStep of REQUEST
def reqRUN= testRunner.testCase.testSuite.testCases["addEmployee TestCase"].testSteps["addEmployee"]
def wsdlreq= new WsdlTestRunContext(reqRUN)
reqRUN.run(testRunner, context)

//Validation
def resData1= testRunner.testCase.testSuite.testCases["addEmployee TestCase"].testSteps["addEmployee"].getPropertyValue('Response')
def xmlData1= new XmlHolder(resData1)
log.info resData1 
def resDataValue= xmlData1.getNodeValue("//ns:return")

//To read Status code
def httpCode= testRunner.testCase.testSuite.testCases["addEmployee TestCase"].testSteps["addEmployee"].testRequest.response.responseHeaders["#status#"].toString()

//Assertion for Status code
assert httpCode.contains("200")
assert testRunner.timeTaken < 800
