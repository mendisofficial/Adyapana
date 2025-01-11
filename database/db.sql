-- Create the Student table
CREATE TABLE Student (
    Sno INT PRIMARY KEY,
    Name VARCHAR(100),
    Address VARCHAR(255),
    dob DATE
);

-- Create the Teacher table
CREATE TABLE Teacher (
    Tno INT PRIMARY KEY,
    Name VARCHAR(100),
    Address VARCHAR(255),
    subjects VARCHAR(255)
);

-- Create the Subject table
CREATE TABLE Subject (
    Subno INT PRIMARY KEY,
    Description VARCHAR(255),
    Price DECIMAL(10, 2)
);

-- Create the Class table
CREATE TABLE Class (
    ClassNo INT PRIMARY KEY,
    subNo INT,
    Tno INT,
    timeslot VARCHAR(50),
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
