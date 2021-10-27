package br.com.schmidt.todoapp

import br.com.schmidt.todoapp.data.models.ToDoData

interface CardViewClick {
    abstract fun onClickCardView(toDoData: ToDoData)
}