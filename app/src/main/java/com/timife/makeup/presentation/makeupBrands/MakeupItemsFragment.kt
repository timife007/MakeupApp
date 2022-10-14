package com.timife.makeup.presentation.makeupBrands

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.chip.Chip
import com.timife.makeup.R
import com.timife.makeup.databinding.FragmentMakeupItemsBinding
import com.timife.domain.model.Brand
import com.timife.domain.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MakeupItemsFragment : Fragment() {
    private lateinit var brandsBinding: FragmentMakeupItemsBinding
    private val makeupItemsViewModel by viewModels<MakeupItemsViewModel>()

    private lateinit var makeupItemsAdapter: MakeupItemsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        brandsBinding = FragmentMakeupItemsBinding.inflate(inflater, container, false)
        makeupItemsAdapter = MakeupItemsAdapter(MakeupItemsAdapter.BrandClickListener {
            makeupItemsViewModel.displayMakeupItems(it)
        })
        brandsBinding.brandRecycler.adapter = makeupItemsAdapter
        return brandsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModels()
        makeupItemsViewModel.navigateToSelectedItem.observe(viewLifecycleOwner) {
            if (it != null) {
                this.findNavController().navigate(
                    MakeupItemsFragmentDirections.actionMakeupItemsFragmentToMakeupDetailsFragment(it)
                )
                makeupItemsViewModel.displayBrandItemsComplete()
            }
        }
    }

    private fun hideProgressBar() {
        brandsBinding.brandProgress.visibility = View.INVISIBLE
    }

    private fun showErrorMessage() {
        brandsBinding.errorMessage.visibility = View.VISIBLE
    }

    private fun showProgressBar() {
        brandsBinding.brandProgress.visibility = View.VISIBLE
    }

    private fun observeViewModels() {
        with(makeupItemsViewModel) {
            brandData.observe(viewLifecycleOwner) {
                when (it) {
                    is Resource.Success -> {
                        if (it.data is List<Brand> && it.data?.isNotEmpty() == true) {
                            val chipGroup = brandsBinding.brandChipGroup
                            chipGroup.removeAllViews()
                            val chip = Chip(requireActivity(), null, R.style.CustomChipChoice)
                            chip.text = "ALL"
                            chip.isCheckable = true
                            chip.isClickable = true
                            chip.isFocusable = true
                            chip.chipStrokeWidth = 1F
                            chip.isCloseIconVisible = false
                            chip.isChecked = true
                            chipGroup.addView(chip)

                            it.data?.forEach { brandItem ->
                                val chipItems = Chip(requireActivity(), null, R.style.CustomChipChoice)
                                chipItems.text = brandItem.brand
                                chipItems.isCheckable = true
                                chipItems.isClickable = true
                                chipItems.isFocusable = true
                                chipItems.chipStrokeWidth = 1F
                                chipItems.isCloseIconVisible = false
                                chipGroup.addView(chipItems)
                            }
                            chipGroup.setOnCheckedChangeListener { group, checkedId ->
                                val clickedChip = group.findViewById<Chip>(checkedId)
                                if (clickedChip?.text != "ALL") {
                                    makeupItemsViewModel.getMakeupItems(brand = clickedChip?.text.toString())
                                } else {
                                    makeupItemsViewModel.getAllMakeupItems()
                                }
                            }
                        }
                    }
                    is Resource.Loading ->Unit
                    is Resource.Error -> {
                        hideProgressBar()
                        brandsBinding.errorMessage.text = it.message
                    }
                }
            }

            loading.observe(viewLifecycleOwner) {
                when (it) {
                    true -> {
                        showProgressBar()
                    }
                    false -> {
                        hideProgressBar()
                    }
                }
            }

            itemsData.observe(viewLifecycleOwner){
                when(it){
                    is Resource.Success ->{
                        makeupItemsAdapter.submitList(it.data)
                        makeupItemsAdapter.notifyDataSetChanged()
                        hideProgressBar()
                    }
                    is Resource.Loading -> {
                        if(it.isLoading){
                            showProgressBar()
                        }else{
                            hideProgressBar()
                        }
                    }
                    is Resource.Error -> {
                        hideProgressBar()
                        showErrorMessage()
                        brandsBinding.errorMessage.text = it.message
                    }
                }
            }
        }
    }
}