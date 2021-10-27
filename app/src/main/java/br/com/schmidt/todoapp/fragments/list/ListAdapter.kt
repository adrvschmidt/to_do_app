package br.com.schmidt.todoapp.fragments.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.schmidt.todoapp.CardViewClick
import br.com.schmidt.todoapp.R
import br.com.schmidt.todoapp.data.models.ToDoData
import br.com.schmidt.todoapp.databinding.RowLayoutBinding

class ListAdapter() : RecyclerView.Adapter<MyViewHolder>() {

    var dataList = emptyList<ToDoData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun setData(toDoData: List<ToDoData>) {
        dataList = toDoData
        notifyDataSetChanged()
    }
}