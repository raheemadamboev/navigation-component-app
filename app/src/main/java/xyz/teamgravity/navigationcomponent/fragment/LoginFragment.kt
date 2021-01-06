package xyz.teamgravity.navigationcomponent.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import xyz.teamgravity.navigationcomponent.databinding.FragmentLoginBinding

class LoginFragment: Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<LoginFragmentArgs>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.apply {
            usernameField.setText(args.username)

            confirmB.setOnClickListener {
                val username = usernameField.text.toString()
                val password = passwordField.text.toString()

                val action = LoginFragmentDirections.actionLoginFragmentToWelcomeFragment(username, password)
                findNavController().navigate(action)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}