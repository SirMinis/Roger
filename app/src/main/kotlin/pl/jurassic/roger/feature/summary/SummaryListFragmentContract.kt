package pl.jurassic.roger.feature.summary

import pl.jurassic.roger.data.ui.SummaryWorkTime
import pl.jurassic.roger.feature.common.BaseContract

interface SummaryListFragmentContract {

    interface View {
        fun setWorkTimeList(summaryWorkList: List<SummaryWorkTime>)
        fun showNoDataText()
    }

    interface Router

    interface Presenter : BaseContract.Presenter
}