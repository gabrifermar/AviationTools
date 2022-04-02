/*
 * Created by gabrifermar on 2/4/22 19:22
 * Copyright Ⓒ 2022. All rights reserved
 * Last modified: 11/3/22 20:18
 */

package com.gabrifermar.watch

import android.app.Activity
import android.os.Bundle
import com.gabrifermar.watch.databinding.ActivityMainBinding

class Main : Activity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}