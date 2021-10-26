package br.com.schmidt.todoapp.fragments

import android.app.Application
import android.text.TextUtils
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import br.com.schmidt.todoapp.R
import br.com.schmidt.todoapp.data.models.Priority

class ShareViewModel(application: Application): AndroidViewModel(application) {

    val listener: AdapterView.OnItemSelectedListener = object
        : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            when(position){
                0 -> { (parent?.getChildAt(0) as TextView)
                    .setTextColor(ContextCompat.getColor(application, R.color.red))}
                1 -> { (parent?.getChildAt(0) as TextView)
                    .setTextColor(ContextCompat.getColor(application, R.color.yellow))}
                2 -> { (parent?.getChildAt(0) as TextView)
                    .setTextColor(ContextCompat.getColor(application, R.color.green))}
            }
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {

        }
    }
    fun verifyDataFromUser(title: String, description: String): Boolean{
        return if(TextUtils.isEmpty(title) || TextUtils.isEmpty(description)) {
            false
        } else !(title.isEmpty() || description.isEmpty())
    }

    fun parsePriority(priority: String): Priority {
        return when(priority){
            "Prioridade Alta" -> {
                Priority.HIGH}
            "Prioridade Média" -> {
                Priority.HIGH}
            "Prioridade Baixa" -> {
                Priority.HIGH}
            else -> Priority.LOW
        }
    }
}