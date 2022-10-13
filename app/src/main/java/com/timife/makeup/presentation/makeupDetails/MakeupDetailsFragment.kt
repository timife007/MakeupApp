package com.timife.makeup.presentation.makeupDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.timife.makeup.R
import com.timife.makeup.databinding.FragmentMakeupDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MakeupDetailsFragment : Fragment() {
    private lateinit var detailsBinding: FragmentMakeupDetailsBinding
    private val viewModel by viewModels<MakeupDetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        detailsBinding = FragmentMakeupDetailsBinding.inflate(inflater, container, false)

        viewModel.itemData.observe(viewLifecycleOwner){

            detailsBinding.apply {
                productName.text = it.name
                productDetails.text = it.description
                rating.rating = it.rating.toFloat()
                price.text = "$${it.price}"

                Glide.with(requireContext()).load(it.imageLink)
                    .error(R.drawable.ic_image_error)
                    .into(detailsBinding.detailImage)
            }
        }

        return detailsBinding.root
    }
}