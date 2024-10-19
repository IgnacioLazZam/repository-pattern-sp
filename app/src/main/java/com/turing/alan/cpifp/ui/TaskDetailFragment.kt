package com.turing.alan.cpifp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.turing.alan.cpifp.R
import com.turing.alan.cpifp.data.InMemoryTaskRepository
import com.turing.alan.cpifp.data.TaskRepository
import com.turing.alan.cpifp.databinding.FragmentTaskDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class TaskDetailFragment: Fragment() {
    private val viewModel:TaskDetailViewModel by viewModels()
    private val args:TaskDetailFragmentArgs by navArgs()
    private lateinit var binding: FragmentTaskDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding = FragmentTaskDetailBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.taskDetailTb.setOnClickListener{
            findNavController().popBackStack()
        }

        super.onViewCreated(view, savedInstanceState)

        val taskId = args.taskId

        viewLifecycleOwner.lifecycleScope.launch {

            withContext(Dispatchers.IO) {

                val task = viewModel.readTask(taskId)
                binding.taskTitle.text = task.title
                binding.taskBody.text = task.body
                binding.taskCreatedAt.text = task.createdAt.toString()

            }

        }



    }
}