# Student Management System

This project involves the development of a database-backed software solution for managing processes at the "Adyapana" institute. The system is designed to automate and streamline manual tasks such as student registration, teacher enrollment, subject registration, payments, and class management.

---

## Features

1. **Student Management**
   - Add, Update, Delete, and Search student records.
   
2. **Teacher Management**
   - Add, Update, Delete, and Search teacher records.

3. **Subject Management**
   - Add, Update, Delete, and Search subject details.

4. **Class Management**
   - Register classes and manage student enrollment.

5. **Payment Management**
   - Add, Update, and Search payment records.
   - Generate invoices for payments.

6. **Reports**
   - View attendance and due lists for students and classes.

---

## Database Schema

The system is backed by a relational database with the following tables:

1. **Student**
   - `Sno` (Primary Key)
   - `Name`
   - `Address`
   - `dob`

2. **Teacher**
   - `Tno` (Primary Key)
   - `Name`
   - `Address`
   - `subjects`

3. **Subject**
   - `Subno` (Primary Key)
   - `Description`
   - `Price`

4. **Class**
   - `ClassNo` (Primary Key)
   - `subNo` (Foreign Key -> Subject.Subno)
   - `Tno` (Foreign Key -> Teacher.Tno)
   - `timeslot`

5. **Invoice**
   - `Sno` (Foreign Key -> Student.Sno)
   - `Tno` (Foreign Key -> Teacher.Tno)
   - `Subno` (Foreign Key -> Subject.Subno)
   - `month`
   - `Value`

---
