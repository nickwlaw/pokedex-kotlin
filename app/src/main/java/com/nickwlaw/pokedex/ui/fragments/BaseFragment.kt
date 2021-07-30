package com.nickwlaw.pokedex.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<Binder : ViewBinding> : Fragment() {
    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> Binder
    private lateinit var _binding: Binder

    /**
     * Only available *after* [onCreateView]
     */
    protected val binding: Binder
        get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = bindingInflater.invoke(inflater, container, false).apply { _binding = this }.root
}