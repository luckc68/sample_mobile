import UIKit

class TodoListViewController: UITableViewController {
    var todos: [String] = []

    override func viewDidLoad() {
        super.viewDidLoad()
        title = "TODO by Swift"
        navigationItem.rightBarButtonItem = UIBarButtonItem(barButtonSystemItem: .add, target: self, action: #selector(addTodo))
        loadTodos()
    }

    @objc func addTodo() {
        let alert = UIAlertController(title: "New TODO", message: "Enter a task", preferredStyle: .alert)
        alert.addTextField()

        let addAction = UIAlertAction(title: "Add", style: .default) { _ in
            if let text = alert.textFields?.first?.text, !text.isEmpty {
                self.todos.append(text)
                self.saveTodos()
                self.tableView.reloadData()
            }
        }

        alert.addAction(addAction)
        present(alert, animated: true)
    }

    func loadTodos() {
        if let saved = UserDefaults.standard.stringArray(forKey: "todos") {
            todos = saved
        }
    }

    func saveTodos() {
        UserDefaults.standard.setValue(todos, forKey: "todos")
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return todos.count
    }

    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "Cell") ?? UITableViewCell(style: .default, reuseIdentifier: "Cell")
        cell.textLabel?.text = todos[indexPath.row]
        return cell
    }

    override func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCell.EditingStyle, forRowAt indexPath: IndexPath) {
        if editingStyle == .delete {
            todos.remove(at: indexPath.row)
            saveTodos()
            tableView.deleteRows(at: [indexPath], with: .fade)
        }
    }
}
