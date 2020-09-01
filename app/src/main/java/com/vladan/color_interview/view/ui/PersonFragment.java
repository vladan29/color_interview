package com.vladan.color_interview.view.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.vladan.color_interview.R;
import com.vladan.color_interview.databinding.FragmentPersonBinding;
import com.vladan.color_interview.model.ApiResponsePerson;
import com.vladan.color_interview.model.Person;
import com.vladan.color_interview.utils.AppConstants;
import com.vladan.color_interview.viewmodel.PersonViewModel;

import java.util.Map;

/**
 * Created by vladan on 8/29/2020
 */
public class PersonFragment extends Fragment {

    private FragmentPersonBinding mBinding;
    private PersonViewModel mViewModel;
    private TextView tvId;
    private TextView tvFirstName;
    private TextView tvLastName;
    private TextView tvAge;
    private TextView tvGender;
    private TextView tvCountry;
    private static String mId;
    Map<String, String> person;
    ViewModelProvider.Factory mFactory = new ViewModelProvider.Factory() {
        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new PersonViewModel(getActivity(), mId);
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(getActivity(), mFactory).get(PersonViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_person, container, false);
        return (View) mBinding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvFirstName = view.findViewById(R.id.tvFirstName);
        tvId = mBinding.tvId;
        tvLastName = mBinding.tvLastName;
        tvAge = mBinding.tvAge;
        tvGender = mBinding.tvGender;
        tvCountry = mBinding.tvCountry;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        observeViewModel(mViewModel);
    }

    private void setPerson(Person person) {
        tvId.setText(person.id);
        tvFirstName.setText(person.firstName);
        tvLastName.setText(person.lastName);
        tvAge.setText(person.age);
        tvGender.setText(person.gender);
        tvCountry.setText(person.country);

    }

    private void observeViewModel(PersonViewModel personViewModel) {
        personViewModel.getPersonLiveData().observe(getViewLifecycleOwner(), new Observer<Person>() {
            @Override
            public void onChanged(Person person) {
                setPerson(person);
            }
        });
    }

    public static PersonFragment forPerson(String id) {
        mId = id;
        PersonFragment fragment = new PersonFragment();
        Bundle args = new Bundle();

        args.putString(AppConstants.KEY_PERSON_ID, id);
        fragment.setArguments(args);
        return fragment;
    }
}
