//
//  MemberTableViewCell.swift
//  GitHubKMP
//
//  Created by Rafael Gabriel on 04/04/2019.
//  Copyright © 2019 Rafael Gabriel. All rights reserved.
//

import UIKit

class MemberTableViewCell: UITableViewCell {
    
    @IBOutlet var memberAvatar:UIImageView!
    @IBOutlet var memberLogin:UILabel!

    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}
