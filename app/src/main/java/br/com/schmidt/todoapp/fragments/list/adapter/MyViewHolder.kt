package br.com.schmidt.todoapp.fragments.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.schmidt.todoapp.data.models.ToDoData
import br.com.schmidt.todoapp.databinding.RowLayoutBinding

class MyViewHolder(private val binding: RowLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(toDoData: ToDoData) {
        binding.toDoData = toDoData
        binding.executePendingBindings()
    }

    companion object{
        fun from(parent: ViewGroup): MyViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = RowLayoutBinding.inflate(layoutInflater, parent, false)
            return MyViewHolder(binding)
        }
    }
}
