//
//  BaseView.swift
//  GitHubKMP
//
//  Created by Rafael Gabriel on 04/04/2019.
//  Copyright © 2019 Rafael Gabriel. All rights reserved.
//

import Foundation
import shared
import UIKit

extension UIViewController: BaseView {
    
    public func showError(error: KotlinThrowable) {
        let title = "Error"
        var message = ""
        switch error {
        case is UpdateProblem:
            message = "Failed to get data from server. Please check you internet connection"
            break
        default:
            message = "Unknow message"
        }
        
        showError(title: title, message: message)
    }
    
    func showError(title:String, message:String) -> () {
        let alertController = UIAlertController(title: title, message: message, preferredStyle: .alert)
        alertController.addAction(UIAlertAction(title: "Ok", style: .default, handler: nil))
        self.present(alertController, animated: true, completion: nil)
    }
}
