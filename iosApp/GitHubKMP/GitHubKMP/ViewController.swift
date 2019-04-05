//
//  ViewController.swift
//  GitHubKMP
//
//  Created by Rafael Gabriel on 04/04/2019.
//  Copyright © 2019 Rafael Gabriel. All rights reserved.
//

import UIKit
import shared

class ViewController: UIViewController, MembersView, UITableViewDelegate {
    
    var memberList = MemberList()
    var isUpdating = false
    
    @IBOutlet var tableView:UITableView!
    @IBOutlet var lblGreeting:UILabel!
    
    func onUpdate(members: [Member]) {
        memberList.updateMembers(members)
        tableView.reloadData()
    }
    
    lazy var presenter: MembersPresenter = {
        MembersPresenter(view: self, repository: AppDelegate.appDelegate.dataRepository)
    }()

    override func viewDidLoad() {
        super.viewDidLoad()
        
        lblGreeting.text = Greeting().greeting()
        
        tableView.register(UINib(nibName: "MemberTableViewCell", bundle: nil), forCellReuseIdentifier: "MemberCell")
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        presenter.onCreate()
    }
    
    override func viewDidDisappear(_ animated: Bool) {
        super.viewDidDisappear(animated)
        presenter.onDestroy()
    }


}

extension ViewController: UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.memberList.memberList.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "MemberCell",
                                                 for: indexPath) as! MemberTableViewCell
        let member = self.memberList.memberList[indexPath.row]
        cell.memberLogin.text = member.login
        guard let url = URL(string: member.avatarUrl) else { return cell }
        cell.memberAvatar.loadFrom(url: url)
        return cell
    }
}

extension UIImageView {
    func loadFrom(url:URL) {
        let key = Date().hashValue
        self.tag = key
        self.image = nil
        DispatchQueue.global().async { [weak self] in
            guard let data = try? Data(contentsOf: url) else { return }
            DispatchQueue.main.async {
                if (self?.tag == key) {
                    self?.image = UIImage(data: data)
                }
            }
        }
    }
}

