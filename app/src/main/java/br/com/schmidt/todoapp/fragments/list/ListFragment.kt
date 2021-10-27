package br.com.schmidt.todoapp.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.schmidt.todoapp.R
import br.com.schmidt.todoapp.data.viewmodel.ToDoViewModel
import br.com.schmidt.todoapp.databinding.FragmentListBinding
import br.com.schmidt.todoapp.fragments.ShareViewModel
import br.com.schmidt.todoapp.fragments.list.adapter.ListAdapter

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val adapter: ListAdapter by lazy { ListAdapter() }
    private val mToDoViewModel: ToDoViewModel by viewModels()
    private val mShareViewModel: ShareViewModel by viewModels()
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        val view = binding.root
        binding.mShareViewModel = mShareViewModel

        setAdapterView()

        setHasOptionsMenu(true)

        return view
    }

    private fun setAdapterView() {
        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())

        mToDoViewModel.getAllData.observe(viewLifecycleOwner, { data ->
            mShareViewModel.checkIfDatabaseEmpty(data)
            adapter.setData(data)
        })
        swipeToDelete(recyclerView)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_fragment_menu, menu)
    }

/*    override fun onClickCardView(toDoData: ToDoData) {
        val action = ListFragmentDirections.actionListFragmentToUpdateFragment(toDoData)
        findNavController().navigate(action)
    }*/

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete_all){
            confirmDeleteAll()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun confirmDeleteAll() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("OK"){_, _ ->
            mToDoViewModel.deleteAll()
            Toast.makeText(
                requireContext(),
                "Apagado todas as tarefas com sucesso",
                Toast.LENGTH_LONG).show()
        }
        builder.setNegativeButton("CANCELAR"){_, _ -> }
        builder.setTitle("Apagar Tudo?")
        builder.setMessage("Você tem certeza que deseja apagar todas as tarefas")
        builder.create().show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun swipeToDelete(recyclerView: RecyclerView){
        val swipeToDeleteCallback = object : SwipeToDelete(){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val itemToDelete = adapter.dataList[viewHolder.adapterPosition]
                mToDoViewModel.deleteItem(itemToDelete)
                Toast.makeText(
                    requireContext(),
                    "Apagado tarefa '${itemToDelete.title}' com sucesso",
                    Toast.LENGTH_LONG).show()
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }
}