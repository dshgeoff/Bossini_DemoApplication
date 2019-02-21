package com.gtomato.demoapplicationjan2018.engine

import `interface`.IBasePresenter
import `interface`.IBaseView

/**
 * Created by Geoffrey Ma on 2018-01-15.
 */
class SplashScreenPresenter(private val splashScreenView: IBaseView<IBasePresenter>) : IBasePresenter {

    init {
        splashScreenView.presenter = this
    }

}
