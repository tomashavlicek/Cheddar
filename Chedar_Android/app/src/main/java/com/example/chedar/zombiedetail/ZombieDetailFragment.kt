package com.example.chedar.zombiedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.chedar.R
import com.example.chedar.database.ZombieDatabase
import com.example.chedar.databinding.FragmentZombieDetailBinding

class ZombieDetailFragment : Fragment() {

    companion object {
        fun newInstance(zombieId: Long): ZombieDetailFragment {
            val args: Bundle = Bundle()
            args.putLong("zombieId", zombieId)
            val fragment = ZombieDetailFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentZombieDetailBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_zombie_detail, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = ZombieDatabase.getInstance(application).zombieDatabaseDao

        val viewModelFactory = ZombieDetailViewModelFactory(arguments!!.getLong("zombieId"), dataSource)

        val viewModel = ViewModelProviders.of(this, viewModelFactory).get(ZombieDetailViewModel::class.java)

        binding.viewModel = viewModel

        binding.lifecycleOwner = this

        viewModel.navigateToZombieTrackerFragment.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                this.activity!!.supportFragmentManager.popBackStack()
                viewModel.doneNavigatingToZombieTrackerFragment()
            }
        })

        return binding.root
    }
}