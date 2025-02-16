package com.myproject.for_try_api.app.repositories;
import com.myproject.for_try_api.app.model.dbs.TodoListModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoListRepositories extends JpaRepository<TodoListModel, Integer> {

}
