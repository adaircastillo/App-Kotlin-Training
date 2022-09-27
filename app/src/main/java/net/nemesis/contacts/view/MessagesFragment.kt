package net.nemesis.contacts.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import net.nemesis.contacts.databinding.FragmentMessagesBinding
import net.nemesis.contacts.model.Message
import net.nemesis.contacts.viewmodel.MessagesViewModel

class MessagesFragment : Fragment() {

    private object Params {
        const val ID_CONTACT = "id_contact"
    }

    private lateinit var binding: FragmentMessagesBinding

    private var idContact: Int = 0

    private val messages = ArrayList<Message>()

    private val viewModel: MessagesViewModel by lazy {
        ViewModelProvider(this)[MessagesViewModel::class.java]
    }

    private val messagesAdapter by lazy {
        MessagesAdapter(messages)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            idContact = it.getInt(Params.ID_CONTACT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMessagesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupFragment()
        setupObservers()
    }

    private fun setupFragment() {
        with(binding) {

            messagesRecyclerView.adapter = messagesAdapter
            messagesRecyclerView.setHasFixedSize(true)

            val layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            messagesRecyclerView.layoutManager = layoutManager

            sendMessage.setOnClickListener {
                sendMessage(messageInput.text.toString())
                messageInput.setText("")
            }

        }
    }

    private fun setupObservers() {
        viewModel.idContact = idContact

        viewModel.messagesLiveData.observe(viewLifecycleOwner) {
            messages.clear()
            messages.addAll(it)
            messagesAdapter.notifyDataSetChanged()
        }

        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.messagesProgress.isVisible = it
        }
    }

    private fun sendMessage(text: String) {
        Log.d("MSG", text)
        val message = Message()
        message.message = text
        message.idContact = idContact
        viewModel.sendMessage(message)
    }

    companion object {

        @JvmStatic
        fun newInstance(idContact: Int) =
            MessagesFragment().apply {
                arguments = Bundle().apply {
                    putInt(Params.ID_CONTACT, idContact)
                }
            }
    }
}