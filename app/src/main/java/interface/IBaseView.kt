package `interface`

import com.gtomato.demoapplicationjan2018.dialog.AlertDialogFragment

/**
 * Created by Geoffrey Ma on 2018-01-15.
 */
interface IBaseView<T : IBasePresenter> {

    var presenter: T

    val isActive: Boolean

    fun goToPageAfterSplash()

    fun displayAlertMessage(msg: String)

    fun displayAlertMessage(title: String, desc: String)

    fun displayAlertMessage(title: String, desc: String, buttonText: String)

    fun displayAlertMessage(title: String, desc: String, positiveText: String, negativeText: String,
                            cancelable: Boolean,
                            positiveOnClickListener: AlertDialogFragment.AlertDialogButtonClickListener?,
                            negativeOnClickListener: AlertDialogFragment.AlertDialogButtonClickListener?)

    fun setLoadingIndicator(active: Boolean)

}