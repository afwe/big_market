package cn.bugstack.domain.task.service;

import cn.bugstack.domain.task.model.entity.TaskEntity;

import java.util.List;

/**
 * @author luke
 * @date 2025年03月02日 10:04
 */
public interface ITaskService {
    List<TaskEntity> querySendMessageTaskList();
    void sendMessage(TaskEntity taskEntity);

    void updateTaskSendMessage(String userId,String messageId);
    void updateTaskSendMessageFail(String userId,String messageId);
}
