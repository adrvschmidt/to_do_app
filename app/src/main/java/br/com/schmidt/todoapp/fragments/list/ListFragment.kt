package br.com.schmidt.todoapp.fragments.list

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.schmidt.todoapp.R
import br.com.schmidt.todoapp.data.viewmodel.ToDoViewModel
import br.com.schmidt.todoapp.databinding.FragmentListBinding

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private val adapter: ListAdapter by lazy { ListAdapter() }
    private val mToDoViewModel: ToDoViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        val view = binding.root

        setAdapterView()

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }
        view.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_updateFragment)
        }

        setHasOptionsMenu(true)

        return view
    }

    private fun setAdapterView() {
        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())

        mToDoViewModel.getAllData.observe(viewLifecycleOwner, Observer { data ->
            if (data != null && data.isNotEmpty()) {
                hideEmptyList()
            }
            adapter.setData(data)
        })
    }

    private fun hideEmptyList(){
        binding.noDataImage.visibility = View.GONE
        binding.noDataTextView.visibility = View.GONE
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_fragment_menu, menu)
    }
}