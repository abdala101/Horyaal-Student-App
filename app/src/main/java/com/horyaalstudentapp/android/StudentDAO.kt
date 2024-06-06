package com.horyaalstudentapp.android

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface StudentDAO {
    @Query("SELECT * from Student")
    fun getStudentList(): List<Student>

    @Query("SELECT * FROM Student WHERE id = :studentId")
    fun getStudentById(studentId: Int): Student

    @Update
    fun updateStudent(student: Student)

    @Query("DELETE FROM Student WHERE id = :studentId")
    fun deleteStudent(studentId: Int)

    @Insert
    fun saveStudent(student: Student)
}
