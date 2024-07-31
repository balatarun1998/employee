## Create employee
    method : Post.
    url: http://127.0.0.1:6060/api/employees
    raw data :


    {
    "employeeId": "E123",
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com",
    "phoneNumbers": ["1234567890", "0987654321"],
    "doj": "2023-05-16",
    "salary": 50000
    }

    ddl for employee:
    CREATE TABLE public.employee (
    employee_id varchar(50) NOT NULL,
    first_name varchar(100) NULL,
    last_name varchar(100) NULL,
    email varchar(100) NULL,
    phone_numbers _text NULL,
    doj date NULL,
    salary float8 NULL,
    CONSTRAINT employee_pkey PRIMARY KEY (employee_id)
    );

## Tax-deductions

    method :Get.
    url : http://127.0.0.1:6060/api/employees/{employeeId}/tax-deductions.


    tax calculation:

    public double calculateTax(double salary) {
        double taxAmount = 0.0;

        if (salary <= 250000) {
            return taxAmount;
        } else if (salary <= 500000) {
            taxAmount = (salary - 250000) * 0.05;
        } else if (salary <= 1000000) {
            taxAmount = (500000 - 250000) * 0.05
                    + (salary - 500000) * 0.10;
        } else {
            taxAmount = (500000 - 250000) * 0.05
                    + (1000000 - 500000) * 0.10
                    + (salary - 1000000) * 0.20;
        }

        return taxAmount;
    }

    @Override
    public double calculateCess(double annualSalary) {
        double excessAmount = Math.max(0, annualSalary - 2500000);
        return excessAmount * 0.02;
    }