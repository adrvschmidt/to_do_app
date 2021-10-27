package br.com.schmidt.todoapp.fragments.add

import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import br.com.schmidt.todoapp.R
import br.com.schmidt.todoapp.data.models.Priority
import br.com.schmidt.todoapp.data.models.ToDoData
import br.com.schmidt.todoapp.data.viewmodel.ToDoViewModel
import br.com.schmidt.todoapp.databinding.FragmentAddBinding
import br.com.schmidt.todoapp.databinding.FragmentListBinding
import br.com.schmidt.todoapp.fragments.ShareViewModel

class AddFragment : Fragment() {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!
    private val mToDoViewModel: ToDoViewModel by viewModels()
    private val mShareViewModel: ShareViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        val view = binding.root
        setHasOptionsMenu(true)
        binding.prioritiesSpinner.onItemSelectedListener = mShareViewModel.listener
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_add){
            insertDataToDb()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun insertDataToDb() {
        val mTitle = binding.titleEt.text.toString()
        val mPriority = binding.prioritiesSpinner.selectedItem.toString()
        val mDescription = binding.descriptionEt.text.toString()
        if(mShareViewModel.verifyDataFromUser(mTitle, mDescription)){
            val newData = ToDoData(0, mTitle, mShareViewModel.parsePriority(mPriority), mDescription)
            mToDoViewModel.insertData(newData)
            Toast.makeText(requireContext(), "Adicionado com Sucesso", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Falha em criar nova tarefa", Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}