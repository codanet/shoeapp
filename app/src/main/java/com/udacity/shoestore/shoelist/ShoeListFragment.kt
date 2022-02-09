package com.udacity.shoestore.shoelist

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.inflate
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import com.udacity.shoestore.R
import com.udacity.shoestore.SharedViewModel
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ShoelistItemBinding
import timber.log.Timber


class ShoeListFragment : Fragment() {

    private lateinit var bindingShoeList: FragmentShoeListBinding
    private lateinit var navController: NavController
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingShoeList = inflate(
            inflater, R.layout.fragment_shoe_list, container, false
        )
        navController = findNavController()
        setHasOptionsMenu(true)

        bindingShoeList.apply {
            viewModel = sharedViewModel

            floatingButton.setOnClickListener {
                navController.navigate(R.id.action_shoeListFragment_to_shoeDetailsFragment)
            }
        }

        sharedViewModel.addedShoeEvent.observe(viewLifecycleOwner, Observer {
            addShoes()
        })

        return bindingShoeList.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }

    private fun addShoes() {
//        clear list
        bindingShoeList.shoelistLinearlayout.removeAllViews()

//        add a new view for each shoe
        sharedViewModel.shoeList.value?.forEach { shoe ->
//            inflate shoe item layout
            val shoeBinding: ShoelistItemBinding = inflate(
                layoutInflater,
                R.layout.shoelist_item,
                bindingShoeList.shoelistLinearlayout,
                false
            )

//            display shoe details
            shoeBinding.apply {
                nameTitle.text = shoe.name
                companyTitle.text = shoe.company
                sizeTitle.text = shoe.size.toString()
                descriptionTitle.text = shoe.description
            }

            bindingShoeList.shoelistLinearlayout.addView(shoeBinding.root)
        }
    }
}