import groovy.json.JsonSlurper
import groovy.json.*
import com.eviware.soapui.impl.wsdl.testcase.WsdlTestRunContext

def postBookREQUEST = testRunner.testCase.testSuite.testCases["AddbookTestCase"].testSteps["postBook"].getPropertyValue('Request')
def jsonData = new JsonSlurper().parseText(postBookREQUEST)
log.info jsonData
log.info jsonData.name

//assign JSON Data
jsonData.name = 'java1'
jsonData.isbn= '1911208'
jsonData.aisle= '456'
def jsonReqAsString = JsonOutput.toJson(jsonData)
log.info jsonReqAsString

//To SAVE JSON data
def restRequest = testRunner.testCase.testSuite.testCases["AddbookTestCase"].testSteps["postBook"].getPropertyValue('Request');
testRunner.testCase.testSuite.testCases["AddbookTestCase"].testSteps["postBook"].setPropertyValue('Request',jsonReqAsString);
def postBookREQUEST1= testRunner.testCase.testSuite.testCases["AddbookTestCase"].testSteps["postBook"].getPropertyValue('Request');

//To run
def postBook= testRunner.testCase.testSuite.testCases["AddbookTestCase"].testSteps["postBook"]
def contextData = new WsdlTestRunContext(postBook)
postBook.run(testRunner, contextData)
//Validation
def reponseData= testRunner.testCase.testSuite.testCases['AddbookTestCase'].testSteps["postBook"].getPropertyValue('Response')
def jsonResponse = new JsonSlurper().parseText(reponseData)
def message = jsonResponse.Msg
log.info message
log.info jsonResponse.ID
assert jsonResponse.ID == "1234567890"

