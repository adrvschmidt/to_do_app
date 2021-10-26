package br.com.schmidt.todoapp.data.repository

import androidx.lifecycle.LiveData
import br.com.schmidt.todoapp.data.ToDoDao
import br.com.schmidt.todoapp.data.models.ToDoData

class ToDoRepository(private val toDoDao: ToDoDao) {

    val getAllData: LiveData<List<ToDoData>> = toDoDao.getAllData()

    suspend fun insertData(toDoData: ToDoData){
        toDoDao.insertData(toDoData)
    }
}