package com.myproject.for_try_api.app.service.Impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myproject.for_try_api.app.model.dbs.TodoListModel;
import com.myproject.for_try_api.app.model.dto.TodoListRequest;
import com.myproject.for_try_api.app.model.dto.TodoListResponse;
import com.myproject.for_try_api.app.repositories.TodoListRepositories;
import com.myproject.for_try_api.app.service.TodoListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TodoListServiceImpl implements TodoListService {
    private static final Logger log = LoggerFactory.getLogger(TodoListServiceImpl.class);
    private final TodoListRepositories todoListRepositories;

    @Autowired
    public TodoListServiceImpl(TodoListRepositories todoListRepositories) {
        this.todoListRepositories = todoListRepositories;
    }

    @Override
    public List<TodoListResponse> getTodoList() {
        List<TodoListResponse> response = new ArrayList<>();
        try {
            List<TodoListModel> listObj = todoListRepositories.findAll();
            if (listObj.isEmpty()) {
                return response;
            }
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            response = listObj.stream().map(obj -> mapper.convertValue(obj, TodoListResponse.class)).toList();
        }catch (Exception e) {
            log.error(e.getMessage(), e);
            return List.of();
        }
        return response;
    }

    @Override
    public TodoListResponse getTodo(int id) {
        TodoListResponse response = new TodoListResponse();
        try {
            Optional<TodoListModel> obj = todoListRepositories.findById(id);
            if (obj.isPresent()) {
                ObjectMapper mapper = new ObjectMapper();
                mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                response = mapper.convertValue(obj.get(), TodoListResponse.class);
            }else {
                return null;
            }
        }catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
        return response;
    }

    @Override
    public String createTodo(TodoListRequest todoListRequest) {
        try {
            todoListRepositories.saveAndFlush(TodoListModel.builder()
                    .title(todoListRequest.getTitle())
                    .build());
        }catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
        return "create success";
    }

    @Override
    public String updateTodo(TodoListRequest todoListRequest) {
        try {
            java.util.Optional<TodoListModel> obj = todoListRepositories.findById(todoListRequest.getId());
            if (obj.isPresent()) {
                obj.get().setId(todoListRequest.getId());
                obj.get().setTitle(todoListRequest.getTitle());
                todoListRepositories.saveAndFlush(obj.get());
            }else{
                return "not found";
            }
        }catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
        return "update success";
    }

    @Override
    public String deleteTodo(int id) {
        try {
            todoListRepositories.deleteById(id);
        }catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
        return "delete success";
    }
}
