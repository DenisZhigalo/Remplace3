package by.dz.remplace3.ui.base.messenger

import by.dz.remplace3.ui.view.screenmessage.IScreenMessageView

class ActivityScreenMessenger constructor(private val messageView: IScreenMessageView) :
    ScreenMessenger() {
    override fun getMessageView() = messageView
}