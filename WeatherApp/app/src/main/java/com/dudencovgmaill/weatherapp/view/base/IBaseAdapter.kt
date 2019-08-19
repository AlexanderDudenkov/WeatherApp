package com.dudencovgmaill.weatherapp.view.base

interface IBaseAdapter {
    var onClickListener: ((pos: Int) -> Unit)?
    fun setList(list: List<Any>)
}