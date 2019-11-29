package com.kotlininjas1.moviesninja1.landing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.kotlininjas1.moviesninja1.databinding.FragmentLandingBinding

class LandingFragment : Fragment() {
    val TAG = "LandingFragment"
    /**
     * Lazily initialize our [LandingViewModel].
     */
    private val viewModel: LandingViewModel by lazy {
        ViewModelProviders.of(this).get(LandingViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentLandingBinding.inflate(inflater)

        val adapter = LandingPageAdapter(context!!)
        binding.moviesList.adapter = adapter

        viewModel.movieList.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        // Inflate the layout for this fragment
        return binding.root
    }
}
