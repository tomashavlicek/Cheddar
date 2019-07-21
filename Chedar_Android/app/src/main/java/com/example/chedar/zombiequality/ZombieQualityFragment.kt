package com.example.chedar.zombiequality

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.chedar.R
import com.example.chedar.database.Zombie
import com.example.chedar.database.ZombieDatabase
import com.example.chedar.databinding.FragmentZombieQualityBinding

class ZombieQualityFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentZombieQualityBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_zombie_quality, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = ZombieDatabase.getInstance(application).zombieDatabaseDao

        val viewModelFactory = ZombieQualityViewModelFactory(application, dataSource)

        val viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(ZombieQualityViewModel::class.java)

        binding.lifecycleOwner = this

        binding.saveButton.setOnClickListener {
            viewModel.insert(Zombie(
                name = binding.accountText.text.toString(),
                birthday = binding.birthdayCheckbox.isChecked,
                address = binding.addressCheckbox.isChecked,
                card = binding.cardCheckbox.isChecked))
            this.activity!!.supportFragmentManager.popBackStack()
        }

        return binding.root
    }
}