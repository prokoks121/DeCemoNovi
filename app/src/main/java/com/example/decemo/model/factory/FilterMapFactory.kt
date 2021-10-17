package com.example.decemo.model.factory

import com.example.decemo.R
import com.example.decemo.model.FilterMap

object FilterMapFactory {
   fun getList():ArrayList<FilterMap>{
        val list:ArrayList<FilterMap> = ArrayList()
        list.add(FilterMap("Kafići", true, 0, R.id.kaficF))
        list.add(FilterMap("Pivnice", true, 1, R.id.pabF))
        list.add(FilterMap("Klubovi", true, 2, R.id.klubF))
        list.add(FilterMap("Splavovi", true, 3, R.id.splavF))
        list.add(FilterMap("Kafane", true, 4, R.id.kafanaF))
        list.add(FilterMap("Samo svirke", true, 5, R.id.svirke))

        return list
    }
}