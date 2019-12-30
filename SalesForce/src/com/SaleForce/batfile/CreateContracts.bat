set projectLocation=C:\Users\iDeliver20\Desktop\Selenium_Workspace\Saleforce\SalesForce
cd %projectLocation%
set classpath=%projectLocation%\bin;C:\Java_jar\*
java org.testng.TestNG %projectLocation%\Contracts.xml
pause
