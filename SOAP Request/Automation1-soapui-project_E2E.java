import com.eviware.soapui.support.XmlHolder
import com.eviware.soapui.impl.wsdl.testcase.WsdlTestRunContext
//This will get the Request Object and Hold it in XMLHolder
def reqAddEmpData= testRunner.testCase.testSuite.testCases["addEmployee TestCase"].testSteps["addEmployee"].getPropertyValue('Request')
def xmlAddEmpData= new XmlHolder(reqAddEmpData)
def reqGetEmpData= testRunner.testCase.testSuite.testCases['getEmployeeDetails TestCase'].testSteps["getEmployeeDetails"].getPropertyValue('Request')
def addGetEmpXML = new XmlHolder(reqGetEmpData)
log.info reqGetEmpData
log.info addGetEmpXML
def name= testRunner.testCase.testSuite.getPropertyValue('name')
def id= testRunner.testCase.testSuite.testCases["addEmployee TestCase"].getPropertyValue('id')
def age= testRunner.testCase.testSuite.testCases["addEmployee TestCase"].getPropertyValue('age')
def dept= testRunner.testCase.testSuite.testCases["addEmployee TestCase"].getPropertyValue('dept')

//This set the SetNodeVale of name, id...
//For Add
xmlAddEmpData.setNodeValue("//typ:addEmployee/typ:name", name)
xmlAddEmpData.setNodeValue("//typ:addEmployee/typ:id", id)
xmlAddEmpData.setNodeValue("//typ:addEmployee/typ:age", age)
xmlAddEmpData.setNodeValue("//typ:addEmployee/typ:Department", dept)
//For Get
addGetEmpXML.setNodeValue("//typ:getEmployeeDetails/typ:employeeName", name)

//This will SAVE the SetNodeValue to assignToRequest variable
def assignToRequestAddEmp = xmlAddEmpData.getXml();
def assignToRequestGetEmp = addGetEmpXML.getXml();
//This will assigng the value of AssignToRequest to Request using Request XML
//For Add
testRunner.testCase.testSuite.testCases["addEmployee TestCase"].testSteps["addEmployee"].setPropertyValue("Request",assignToRequestAddEmp)
//For Get
testRunner.testCase.testSuite.testCases["getEmployeeDetails TestCase"].testSteps["getEmployeeDetails"].setPropertyValue("Request", assignToRequestGetEmp)
//Run
//For Add
def addEmpContext= testRunner.testCase.testSuite.testCases["addEmployee TestCase"].testSteps["addEmployee"]
def wsdlContext = new WsdlTestRunContext(addEmpContext)
//For Get
def getEmpContext= testRunner.testCase.testSuite.testCases["getEmployeeDetails TestCase"].testSteps["getEmployeeDetails"]
def contextData = new WsdlTestRunContext(getEmpContext)
//Runner 
addEmpContext.run(testRunner,wsdlContext)
getEmpContext.run(testRunner, contextData)

//Validation
//Create object for XML Response
def resGetEmpData= testRunner.testCase.testSuite.testCases['getEmployeeDetails TestCase'].testSteps["getEmployeeDetails"].getPropertyValue('Response')
def resXMLData= new XmlHolder(resGetEmpData)
//get the Response data using Relative Xpath
def resData = resXMLData.getNodeValue("//ns:name")
//Print the Respose Data "name"
log.info resData
//Print the "name" which is passed form Custom Properties
log.info name
//Compare the both
assert resData==name