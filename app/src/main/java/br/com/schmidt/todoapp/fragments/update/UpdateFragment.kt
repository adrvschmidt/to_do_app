package br.com.schmidt.todoapp.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.renderscript.RenderScript
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.com.schmidt.todoapp.R
import br.com.schmidt.todoapp.data.models.Priority
import br.com.schmidt.todoapp.data.models.ToDoData
import br.com.schmidt.todoapp.data.viewmodel.ToDoViewModel
import br.com.schmidt.todoapp.databinding.FragmentListBinding
import br.com.schmidt.todoapp.databinding.FragmentUpdateBinding
import br.com.schmidt.todoapp.fragments.ShareViewModel

class UpdateFragment : Fragment() {

    private lateinit var binding: FragmentUpdateBinding
    private val args by navArgs<UpdateFragmentArgs>()
    private val mShareViewModel: ShareViewModel by viewModels()
    private val mToDoViewModel: ToDoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUpdateBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)

        binding.currentTitleEt.setText(args.currentItem.title, TextView.BufferType.EDITABLE)
        binding.currentDescriptionEt.setText(args.currentItem.description, TextView.BufferType.EDITABLE)
        binding.currentPrioritiesSpinner.setSelection(mShareViewModel.parsePriorityToInt(args.currentItem.priority))
        binding.currentPrioritiesSpinner.onItemSelectedListener = mShareViewModel.listener
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_save -> updateItem()
            R.id.menu_delete -> deleteItem()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteItem() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("OK"){_, _ ->
            mToDoViewModel.deleteItem(args.currentItem)
            Toast.makeText(
                requireContext(),
                "Apagado item com Sucesso",
                Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("CANCELAR"){_, _ -> }
        builder.setTitle("Apagar '${args.currentItem.title}'")
        builder.setMessage("Você tem certeza que deseja apagar '${args.currentItem.title}'")
        builder.create().show()
    }

    private fun updateItem() {
        val title = binding.currentTitleEt.text.toString()
        val description = binding.currentDescriptionEt.text.toString()
        val getPriority = binding.currentPrioritiesSpinner.selectedItem.toString()
        val validation = mShareViewModel.verifyDataFromUser(title, description)
        if(validation){
            val updateItem = ToDoData(
                args.currentItem.id,
                title,
                mShareViewModel.parsePriority(getPriority),
                description
            )
            mToDoViewModel.updateData(updateItem)
            Toast.makeText(requireContext(), "Atualizado com Sucesso", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Falha na atualização", Toast.LENGTH_LONG).show()
        }
    }
}