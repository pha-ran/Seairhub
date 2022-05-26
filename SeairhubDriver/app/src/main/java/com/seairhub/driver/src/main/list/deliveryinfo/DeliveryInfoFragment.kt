package com.seairhub.driver.src.main.list.deliveryinfo

import android.os.Bundle
import android.view.View
import com.seairhub.driver.R
import com.seairhub.driver.config.ApplicationClass
import com.seairhub.driver.config.BaseFragment
import com.seairhub.driver.databinding.FragmentDeliveryInfoBinding
import com.seairhub.driver.src.main.list.deliveryinfo.models.NotificationData
import com.seairhub.driver.src.main.list.deliveryinfo.models.NotificationRequest
import com.seairhub.driver.src.main.list.deliveryinfo.models.NotificationResponse

class DeliveryInfoFragment : BaseFragment<FragmentDeliveryInfoBinding>(FragmentDeliveryInfoBinding::bind, R.layout.fragment_delivery_info), NotificationView {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        set()
    }

    private fun set() {
        binding.buttonDelay.setOnClickListener {
            sendMessage()
        }
    }

    override fun onPostNotificationSuccess(response: NotificationResponse) {
        dismissLoadingDialog()
        showCustomToast(response.message)
    }

    override fun onPostNotificationFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast(message)
    }

    private fun sendMessage() {
        showLoadingDialog(requireContext())
        NotificationService(this).tryPostNotification(
            // ToDo
            NotificationRequest(ApplicationClass.messageToken, NotificationData("배송 기사가 메세지를 보냈습니다.", "배송이 지연될 것으로 예상됩니다. 자세한 사항은 앱에서 확인해주세요."))
        )
    }
}