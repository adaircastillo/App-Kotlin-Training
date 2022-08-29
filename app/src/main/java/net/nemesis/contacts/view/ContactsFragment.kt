package net.nemesis.contacts.view

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import net.nemesis.contacts.databinding.FragmentContactsBinding
import net.nemesis.contacts.model.Contact
import net.nemesis.contacts.viewmodel.ContactsViewModel
import java.util.*

class ContactsFragment : Fragment() {

    private lateinit var binding: FragmentContactsBinding
    private var listener: ContactsListener? = null

    private val contacts = ArrayList<Contact>()

    private val contactsAdapter by lazy {
        ContactsAdapter(contacts, listener)
    }

    private val viewModel: ContactsViewModel by lazy {
        ViewModelProvider(this).get(ContactsViewModel::class.java)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? ContactsListener
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentContactsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupFragment()
        setupObservers()

        viewModel.context = requireContext()

        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {

        }
    }

    private fun setupFragment() {
        with(binding) {
            contactsRecyclerView.adapter = contactsAdapter
            contactsRecyclerView.setHasFixedSize(true)
            // GridLayoutManager
            val layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            contactsRecyclerView.layoutManager = layoutManager
        }
    }

    private fun setupObservers(){
        viewModel.contactsLiveData.observe(viewLifecycleOwner) {
            contacts.addAll(it)
            contactsAdapter.notifyDataSetChanged()
        }

        viewModel.errorLiveData.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }

}
















