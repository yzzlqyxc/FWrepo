# COMS 4156 Service: 100-of-100
GitHub repository for service of the Team Project associated with COMS 4156 Advanced Software Engineering. 
Our team name is 100-of-100 and our members are: Yifei Luo, Phoebe Wang, Alex Xu and Xintong Yu.
## Building and Running a Local Instance
In order to build and use the service of the project, you must install the following:

1. [Maven 3.9.5](https://maven.apache.org/download.cgi) Download and follow the installation instructions. Set the bin
   as a new path variable for both Windows and MacOS.
2. [JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) used for development.
3. [IntelliJ IDE](https://www.jetbrains.com/idea/download/?section=windows) Or use other IDE of your preference.

In order to build the project, run under `/100of100Service` directory: `mvn -B package --file pom.xml`.
## Running Test Suite
Our unit tests are located under directory `src/test`. After setting up and building the project, run `mvn test`. You may
also run the tests by right click any class in src/test directory and run to see the results if you are using IntelliJ IDEA as IDE.
## Endpoints
### **GET /getEmpInfo**
- **Expected Input Parameters**:
   - `cid` (int) - The client ID.
   - `eid` (int) - The employee ID.
- **Expected Output**:
   - Returns the information of the specified employee.
- **Upon Success**:
   - HTTP 200 Status Code is returned with the employee details in the response body.
- **Upon Failure**:
   - HTTP 404 Status Code is returned with "Employee Not Found" in the response body.
   - HTTP 500 Status Code is returned with "An unexpected error has occurred" in the response body.

### **GET /getDeptInfo**
- **Expected Input Parameters**:
   - `cid` (int) - The client ID.
   - `did` (int) - The department ID.
- **Expected Output**:
   - Returns the information of the specified department.
- **Upon Success**:
   - HTTP 200 Status Code is returned with the department details in the response body.
- **Upon Failure**:
   - HTTP 404 Status Code is returned with "Department Not Found" in the response body.
   - HTTP 500 Status Code is returned with "An unexpected error has occurred" in the response body.

### **GET /getOrgInfo**
- **Expected Input Parameters**:
   - `cid` (int) - The client ID.
- **Expected Output**:
   - Returns the information of the organization associated with the client.
- **Upon Success**:
   - HTTP 200 Status Code is returned with the organization details in the response body.
- **Upon Failure**:
   - HTTP 404 Status Code is returned with "Organization Not Found" in the response body.
   - HTTP 500 Status Code is returned with "An unexpected error has occurred" in the response body.

### **PATCH /setDeptHead**
- **Expected Input Parameters**:
   - `cid` (int) - The client ID.
   - `did` (int) - The department ID.
   - `eid` (int) - The employee ID (new head).
- **Expected Output**:
   - A success message indicating that the department head was successfully updated.
- **Upon Success**:
   - HTTP 200 Status Code is returned indicating the head of the department is set.
- **Upon Failure**:
   - HTTP 404 Status Code is returned with "Department or Employee Not Found" in the response body.
   - HTTP 500 Status Code is returned with "An unexpected error has occurred" in the response body.

### **POST /addEmployeeToDept**
- **Expected Input Parameters**:
   - `cid` (int) - The client ID.
   - `did` (int) - The department ID.
   - `name` (String) - The name of the employee.
   - `hireDate` (String) - The hire date of the employee in the format `"yyyy-MM-dd"`.
- **Expected Output**:
   - A success message indicating the employee has been added to the department.
- **Upon Success**:
   - HTTP 201 Status Code is returned with a success message.
- **Upon Failure**:
   - HTTP 404 Status Code is returned with "Department Not Found" in the response body.
   - HTTP 500 Status Code is returned with "An unexpected error has occurred" in the response body.

### **DELETE /removeEmployeeFromDept**
- **Expected Input Parameters**:
   - `cid` (int) - The client ID.
   - `did` (int) - The department ID.
   - `eid` (int) - The employee ID.
- **Expected Output**:
   - A success message indicating the employee has been removed from the department.
- **Upon Success**:
   - HTTP 200 Status Code is returned with a success message.
- **Upon Failure**:
   - HTTP 404 Status Code is returned with "Department or Employee Not Found" in the response body.
   - HTTP 500 Status Code is returned with "An unexpected error has occurred" in the response body.

## Style Checking Report

## Branch Coverage Reporting

## Tool Used
Maven, JUnit, JaCoCo, Maven Checkstyle.