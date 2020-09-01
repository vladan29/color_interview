package com.vladan.color_interview.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vladan.color_interview.R
import com.vladan.color_interview.databinding.FragmentPersonBinding
import com.vladan.color_interview.model.Person
import com.vladan.color_interview.utils.AppConstants
import com.vladan.color_interview.viewmodel.PersonViewModel

/**
 * Created by vladan on 8/29/2020
 */
class PersonFragment : Fragment() {
    private lateinit var personBinding: FragmentPersonBinding
    private var mViewModel: PersonViewModel? = null
    private var tvId: TextView? = null
    private var tvFirstName: TextView? = null
    private var tvLastName: TextView? = null
    private var tvAge: TextView? = null
    private var tvGender: TextView? = null
    private var tvCountry: TextView? = null
    var person: Map<String, String>? = null
    var mFactory: ViewModelProvider.Factory? = object : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return PersonViewModel(activity, mId) as T
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProvider(activity!!, mFactory!!).get(PersonViewModel::class.java)
        mFactory=null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        personBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_person, container, false)
        return personBinding.getRoot()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvFirstName = view.findViewById(R.id.tvFirstName)
        tvId = personBinding.tvId
        tvLastName = personBinding.tvLastName
        tvAge = personBinding.tvAge
        tvGender = personBinding.tvGender
        tvCountry = personBinding.tvCountry
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeViewModel(mViewModel)
    }

    private fun setPerson(person: Person) {
        tvId!!.text = person.id
        tvFirstName!!.text = person.firstName
        tvLastName!!.text = person.lastName
        tvAge!!.text = person.age
        tvGender!!.text = person.gender
        tvCountry!!.text = person.country
    }

    private fun observeViewModel(personViewModel: PersonViewModel?) {
        personViewModel!!.personLiveData?.observe(
            viewLifecycleOwner, Observer { person -> setPerson(person!!) })
    }

    companion object {
        @JvmStatic
        var mId : String? = null
        fun forPerson(id: String?): PersonFragment {
            mId = id
            val fragment = PersonFragment()
            val args = Bundle()
            args.putString(AppConstants.KEY_PERSON_ID, id)
            fragment.arguments = args
            return fragment
        }
    }
}