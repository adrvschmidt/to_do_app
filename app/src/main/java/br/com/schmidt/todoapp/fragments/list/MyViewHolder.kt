package br.com.schmidt.todoapp.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import br.com.schmidt.todoapp.R
import br.com.schmidt.todoapp.data.models.Priority
import br.com.schmidt.todoapp.data.models.ToDoData
import br.com.schmidt.todoapp.databinding.RowLayoutBinding

class MyViewHolder(private val binding: RowLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
  //  private val titleText: TextView = itemView.findViewById(R.id.title_txt)
 //   private val descriptionText: TextView = itemView.findViewById(R.id.description_txt)
  //  private val priority: CardView = itemView.findViewById(R.id.priority_indicator)

    fun bind(toDoData: ToDoData) {
        binding.toDoData = toDoData
        binding.executePendingBindings()
        /*titleText.text = toDoData.title
        descriptionText.text = toDoData.description
        when (toDoData.priority) {
            Priority.LOW -> priority.setCardBackgroundColor(
                ContextCompat.getColor(itemView.context, R.color.green)
            )
            Priority.MEDIUM -> priority.setCardBackgroundColor(
                ContextCompat.getColor(itemView.context, R.color.yellow)
            )
            Priority.HIGH -> priority.setCardBackgroundColor(
                ContextCompat.getColor(itemView.context, R.color.red)
            )
        }*/
    }

    companion object{
        fun from(parent: ViewGroup): MyViewHolder{
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = RowLayoutBinding.inflate(layoutInflater, parent, false)
            return MyViewHolder(binding)
        }
    }
}
