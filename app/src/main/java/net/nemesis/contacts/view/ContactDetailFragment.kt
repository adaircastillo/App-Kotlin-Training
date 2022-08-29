package net.nemesis.contacts.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import net.nemesis.contacts.databinding.FragmentContactDetailBinding
import net.nemesis.contacts.model.ContactInfo
import net.nemesis.contacts.viewmodel.ContactDetailViewModel

class ContactDetailFragment: Fragment() {

    private object Params{
        const val ID_CONTACT = "id_contact"
    }

    private lateinit var binding: FragmentContactDetailBinding

    private var idContact: Int = 0

    private val viewModel: ContactDetailViewModel by lazy {
        ViewModelProvider(this)[ContactDetailViewModel::class.java]
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
    ): View? {
        binding = FragmentContactDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        viewModel.loadContact(idContact)
    }

    private fun setupObservers() {

        viewModel.contactLiveData.observe(viewLifecycleOwner) {
            showContact(it)
        }

        viewModel.errorLiveData.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun showContact(contact: ContactInfo) {
        with(binding) {

//            root.setOnClickListener {
//
//            }

            Glide.with(root)
                .load(contact.photo)
                .into(contactDetailPhoto)

            contactDetailName.text = contact.name
            contactDetailJob.text = contact.job
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(idContact: Int) =
            ContactDetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(Params.ID_CONTACT, idContact)
                }
            }
    }
}