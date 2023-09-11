#include <stdio.h>
#include <time.h> 
#include <stdbool.h>

struct Date {
    int dd, mm, yy;
};

struct Employee {
    int Empno;
    char EmpName[50];
    struct Date hiredate;
    float BasicSalary;
    float NetSalary;
};

void calculateNetPay(struct Employee *emp) {
    float DA = 0.4 * emp->BasicSalary;
    float TA = 0.1 * emp->BasicSalary;
    float PF = 0.05 * emp->BasicSalary;

    emp->NetSalary = emp->BasicSalary + DA + TA - PF;
}

void acceptEmployeeData(struct Employee *emp, struct Employee employees[], int numEmployees) {
    bool isUnique = false;
    do {
        printf("Enter Employee Number: ");
        scanf("%d", &emp->Empno);
        isUnique = true;
        for (int i = 0; i < numEmployees; i++) {
            if (emp->Empno == employees[i].Empno) {
                printf("Employee number already exist \n");
                isUnique = false;
                break;
            }
        }
    } while (!isUnique);
    
    printf("Enter Employee Name: ");
    scanf("%s", emp->EmpName);

    time_t currentTime;
    struct tm *currentDate;
    time(&currentTime);
    currentDate = localtime(&currentTime);
    
    do {
        printf("Enter Hire Date (Format --> dd mm yyyy): ");
        scanf("%d %d %d", &emp->hiredate.dd, &emp->hiredate.mm, &emp->hiredate.yy);
        if (emp->hiredate.dd < 1 || emp->hiredate.dd > 31 ||
            emp->hiredate.mm < 1 || emp->hiredate.mm > 12 ||
            emp->hiredate.yy > (currentDate->tm_year + 1900)) {
            printf("Invalid hire date \n");
        }
    } while (emp->hiredate.dd < 1 || emp->hiredate.dd > 31 ||
             emp->hiredate.mm < 1 || emp->hiredate.mm > 12 ||
             emp->hiredate.yy > (currentDate->tm_year + 1900));
    
    printf("Enter Basic Salary: ");
    scanf("%f", &emp->BasicSalary);
}

void displayEmployeeData(struct Employee emp) {
    printf("\nEmployee Number: %d\n", emp.Empno);
    printf("Employee Name: %s\n", emp.EmpName);
    printf("Hire Date: %02d/%02d/%04d\n", emp.hiredate.dd, emp.hiredate.mm, emp.hiredate.yy);
    printf("Basic Salary: %.2f\n", emp.BasicSalary);
    printf("Net Salary: %.2f\n", emp.NetSalary);
}

int main() {
    struct Employee employees[100];
    int numEmployees = 0;
    char continueInput;

    printf("Employee Management System\n");
    
    do {
        struct Employee emp;
   
        acceptEmployeeData(&emp, employees, numEmployees);
    
        calculateNetPay(&emp);
     
        displayEmployeeData(emp);
     
        employees[numEmployees] = emp;
        numEmployees++;

        printf("Do you want to enter another employee? (y/n): ");
        scanf(" %c", &continueInput);
    } while (continueInput == 'y' || continueInput == 'Y');

    return 0;
}
