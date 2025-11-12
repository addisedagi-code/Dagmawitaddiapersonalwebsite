#include <iostream>
#include <fstream>
#include <string>
//structure to hold student information 
struct Student {
    std::string name;
    int rollNumber;
    float marks;
};
//function to add a new Student record 
void addStudentRecord() {
    Student newStudent;
    std::ofstream file("management.txt", std::ios::app);
    
    std::cout << "Enter student name: ";
    std::cin.ignore();
    std::getline(std::cin, newStudent.name);
    
    std::cout << "Enter roll number: ";
    std::cin >> newStudent.rollNumber;
    
    std::cout << "Enter marks: ";
    std::cin >> newStudent.marks;
    
    file << newStudent.name << std::endl;
    file << newStudent.rollNumber << std::endl;
    file << newStudent.marks << std::endl;
    
    file.close();
}
//function to display all Student Records
void displayStudentRecords() {
    std::ifstream file("management.txt");
    std::string line;
    
    while (std::getline(file, line)) {
        std::cout << line << std::endl;
    }
    
    file.close();
}
//function to delete all student record 
void deleteStudentRecord() {
    std::string nameToDelete;
    std::cout << "Enter the name of the student to delete: ";
    std::cin.ignore();
    std::getline(std::cin, nameToDelete);
    
    std::ifstream file("management.txt");
    std::ofstream tempFile("temp.txt");
    
    std::string line;
    bool found = false;
    
    while (std::getline(file, line)) {
        if (line == nameToDelete) {
            found = true;
            for (int i = 0; i < 2; ++i) {
                std::getline(file, line);
            }
        } else {
            tempFile << line << std::endl;
        }
    }
    
    file.close();
    tempFile.close();
    
    remove("management.txt");
    rename("temp.txt", "management.txt");
    
    if (found) {
        std::cout << "Student record deleted successfully." << std::endl;
    } else {
        std::cout << "Student record not found." << std::endl;
    }
}
//function to update all student record 
void updateStudentRecord() {
    std::string nameToUpdate;
    std::cout << "Enter the name of the student to update: ";
    std::cin.ignore();
    std::getline(std::cin, nameToUpdate);
    
    std::ifstream file("management.txt");
    std::ofstream tempFile("temp.txt");
    
    std::string line;
    bool found = false;
    
    while (std::getline(file, line)) {
        if (line == nameToUpdate) {
            found = true;
            Student updatedStudent;
            std::cout << "Enter new roll number: ";
            std::cin >> updatedStudent.rollNumber;
            std::cout << "Enter new marks: ";
            std::cin >> updatedStudent.marks;
            
            tempFile << updatedStudent.name << std::endl;
            tempFile << updatedStudent.rollNumber << std::endl;
            tempFile << updatedStudent.marks << std::endl;
        } else {
            tempFile << line << std::endl;
        }
    }
    
    file.close();
    tempFile.close();
    
    remove("management.txt");
    rename("temp.txt", "management.txt");
    
    if (found) {
        std::cout << "Student record updated successfully." << std::endl;
    } else {
        std::cout << "Student record not found." << std::endl;
    }
}
//Main function to demonstrate the system 
int main() {
    int choice;
    
    do {
        std::cout << "\nStudent Management System Menu\n";
        std::cout << "1. Add Student Record\n";
        std::cout << "2. Display Student Records\n";
        std::cout << "3. Delete Student Record\n";
        std::cout << "4. Update Student Record\n";
        std::cout << "5. Exit\n";
        std::cout << "Enter your choice: ";
        std::cin >> choice;
        
        switch (choice) {
            case 1:
                addStudentRecord();
                break;
            case 2:
                displayStudentRecords();
                break;
            case 3:
                deleteStudentRecord();
                break;
            case 4:
                updateStudentRecord();
                break;
            case 5:
                std::cout << "Exiting program. Goodbye!\n";
                break;
            default:
                std::cout << "Invalid choice. Please try again.\n";
        }
    } while (choice != 5);
    
    return 0;
}
