package cn.bugstack.infrastructure.persistent.repository;

import cn.bugstack.domain.task.model.entity.TaskEntity;
import cn.bugstack.domain.task.repository.ITaskRepository;
import cn.bugstack.infrastructure.event.EventPublisher;
import cn.bugstack.infrastructure.persistent.dao.ITaskDao;
import cn.bugstack.infrastructure.persistent.po.Task;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author luke
 * @date 2025年03月02日 10:11
 */
@Service
public class TaskRepository implements ITaskRepository {


    @Resource
    private ITaskDao taskDao;
    @Resource
    private EventPublisher eventPublisher;


    @Override
    public List<TaskEntity> querySendMessageTaskList() {
        List<Task> tasks = taskDao.queryNoSendMessageTaskList();
        List<cn.bugstack.domain.task.model.entity.TaskEntity> taskEntities = new ArrayList<>(tasks.size());
        for (Task task : tasks) {
            cn.bugstack.domain.task.model.entity.TaskEntity taskEntity = new cn.bugstack.domain.task.model.entity.TaskEntity();
            taskEntity.setUserId(task.getUserId());
            taskEntity.setTopic(task.getTopic());
            taskEntity.setMessageId(task.getMessageId());
            taskEntity.setMessage(task.getMessage());
            taskEntities.add(taskEntity);
        }
        return taskEntities;
    }

    @Override
    public void sendMessage(TaskEntity taskEntity) {
        eventPublisher.publish(taskEntity.getTopic(), taskEntity.getMessage());
    }

    @Override
    public void updateTaskSendMessage(String userId, String messageId) {
        Task taskReq = new Task();
        taskReq.setUserId(userId);
        taskReq.setMessageId(messageId);
        taskDao.updateTaskSendMessageCompleted(taskReq);
    }

    @Override
    public void updateTaskSendMessageFail(String userId, String messageId) {
        Task taskReq = new Task();
        taskReq.setUserId(userId);
        taskReq.setMessageId(messageId);
        taskDao.updateTaskSendMessageFail(taskReq);
    }
}
