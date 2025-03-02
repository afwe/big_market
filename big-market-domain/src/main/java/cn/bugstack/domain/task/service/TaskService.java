package cn.bugstack.domain.task.service;

import cn.bugstack.domain.task.model.entity.TaskEntity;
import cn.bugstack.domain.task.repository.ITaskRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author luke
 * @date 2025年03月02日 10:08
 */
@Service
public class TaskService implements ITaskService{
    @Resource
    private ITaskRepository taskRepository;
    @Override
    public List<TaskEntity> querySendMessageTaskList() {
        return taskRepository.querySendMessageTaskList();
    }

    @Override
    public void sendMessage(TaskEntity taskEntity) {
        taskRepository.sendMessage(taskEntity);
    }

    @Override
    public void updateTaskSendMessage(String userId, String messageId) {
        taskRepository.updateTaskSendMessage(userId,messageId);
    }

    @Override
    public void updateTaskSendMessageFail(String userId, String messageId) {
        taskRepository.updateTaskSendMessageFail(userId,messageId);
    }
}
