package net.nemesis.contacts.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import net.nemesis.contacts.databinding.FragmentMessagesBinding

class MessagesFragment : Fragment() {

    private object Params {
        const val ID_CONTACT = "id_contact"
    }

    private lateinit var binding: FragmentMessagesBinding

    private var idContact: Int = 0

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