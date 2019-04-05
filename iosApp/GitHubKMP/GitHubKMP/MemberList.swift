//
//  MemberList.swift
//  GitHubKMP
//
//  Created by Rafael Gabriel on 04/04/2019.
//  Copyright © 2019 Rafael Gabriel. All rights reserved.
//

import Foundation
import shared
import UIKit

class MemberList {
    var memberList: [Member] = []
    
    func updateMembers(_ newMembers: [Member]) {
        memberList.removeAll()
        memberList.append(contentsOf: newMembers)
    }
}
