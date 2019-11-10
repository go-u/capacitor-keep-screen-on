import Foundation
import Capacitor

/**
 * Please read the Capacitor iOS Plugin Development Guide
 * here: https://capacitor.ionicframework.com/docs/plugins/ios
 */
@objc(CapacitorKeepScreenOn)
public class CapacitorKeepScreenOn: CAPPlugin {

    @objc func enable(_ call: CAPPluginCall) {
        dispatch(call: call, mode: true)
    }

    @objc func disable(_ call: CAPPluginCall) {
        dispatch(call: call, mode: false)
    }

    func dispatch(call: CAPPluginCall, mode: Bool) {
        DispatchQueue.main.async {
            UIApplication.shared.isIdleTimerDisabled = mode

            // for test dialog
            // let dialog = UIAlertController(title: "Dialog Title", message: "Message", preferredStyle: .alert)
            // dialog.addAction(UIAlertAction(title: "OK", style: .default, handler: nil))
            // dialog.addAction(UIAlertAction(title: "Cancel", style: .cancel, handler: nil))
            // self.bridge.viewController.present(dialog, animated: true, completion: nil)
         }

        call.success([
            "isEnabled": Bool(mode)
        ])
    }

}
