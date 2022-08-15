package com.timife.makeup.presentation.makeupDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.timife.makeup.R
import com.timife.makeup.databinding.FragmentMakeupDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MakeupDetailsFragment : Fragment() {
    private lateinit var detailsBinding: FragmentMakeupDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        detailsBinding = FragmentMakeupDetailsBinding.inflate(inflater, container, false)
        val makeupItem = MakeupDetailsFragmentArgs.fromBundle(arguments!!).selectedMakeup

        detailsBinding.productDetails.text = makeupItem.description
        detailsBinding.productName.text = makeupItem.name
        detailsBinding.price.text = "$${makeupItem.price}"

        Glide.with(requireContext()).load(makeupItem.imageLink)
            .error(R.drawable.ic_image_error)
            .into(detailsBinding.detailImage)

        detailsBinding.rating.rating = makeupItem.rating.toFloat()

        return detailsBinding.root
    }
}