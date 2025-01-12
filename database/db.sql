-- Create the Student table
CREATE TABLE Student (
    Sno INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(100),
    Address VARCHAR(255),
    dob DATE
);

-- Create the Teacher table
CREATE TABLE Teacher (
    Tno INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(100),
    Address VARCHAR(255)
);

-- Create the Subject table
CREATE TABLE Subject (
    Subno INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(100),
    Description VARCHAR(255),
    Price DECIMAL(10, 2)
);

-- Create the Class table
CREATE TABLE Class (
    ClassNo INT PRIMARY KEY AUTO_INCREMENT,
    subNo INT,
    Tno INT,
    timeSlot VARCHAR(50),
    FOREIGN KEY (subNo) REFERENCES Subject(Subno),
    FOREIGN KEY (Tno) REFERENCES Teacher(Tno)
);

-- Create the Invoice table
CREATE TABLE Invoice (
    Sno INT,
    Tno INT,
    Subno INT,
    month DATE,
    Value DECIMAL(10, 2),
    PRIMARY KEY (Sno, Tno, Subno, month),
    FOREIGN KEY (Sno) REFERENCES Student(Sno),
    FOREIGN KEY (Tno) REFERENCES Teacher(Tno),
    FOREIGN KEY (Subno) REFERENCES Subject(Subno)
);

-- Create the Admin table
CREATE TABLE Admin (
    AdminID INT PRIMARY KEY AUTO_INCREMENT,
    Username VARCHAR(100) UNIQUE NOT NULL,
    Password VARCHAR(255) NOT NULL
);

-- Create the StudentClass table to handle the many-to-many relationship
CREATE TABLE StudentClass (
    Sno INT,
    ClassNo INT,
    PRIMARY KEY (Sno, ClassNo),
    FOREIGN KEY (Sno) REFERENCES Student(Sno),
    FOREIGN KEY (ClassNo) REFERENCES Class(ClassNo)
);

-- Create the TeacherSubject table to handle the many-to-many relationship
CREATE TABLE TeacherSubject (
    Tno INT,
    Subno INT,
    PRIMARY KEY (Tno, Subno),
    FOREIGN KEY (Tno) REFERENCES Teacher(Tno),
    FOREIGN KEY (Subno) REFERENCES Subject(Subno)
);


-- Sample data for testing --

-- Insert sample data into the Admin table
INSERT INTO Admin (Username, Password) VALUES
('admin', '$2a$10$xlsMdglnlZ1nEsDhc7c6vO1sYJorpm5G5bID3NMl1ow8vpmOQr/TO');

-- Insert sample data into the Subject table
INSERT INTO Subject (Name, Description, Price) VALUES
('Mathematics', 'Basic and advanced mathematics', 100.00),
('Physics', 'Fundamentals of physics', 120.00),
('Chemistry', 'Introduction to chemistry', 110.00),
('Biology', 'Study of living organisms', 115.00),
('English', 'English language and literature', 90.00),
('History', 'World history and events', 80.00),
('Geography', 'Study of the Earth and its features', 85.00),
('Computer Science', 'Introduction to computer science', 130.00),
('Art', 'Fundamentals of art and design', 95.00),
('Music', 'Music theory and practice', 105.00),
('Physical Education', 'Physical fitness and sports', 75.00),
('Economics', 'Principles of economics', 125.00);
