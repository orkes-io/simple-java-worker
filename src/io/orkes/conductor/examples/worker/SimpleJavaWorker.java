package io.orkes.conductor.examples.worker;

import com.netflix.conductor.client.worker.Worker;
import com.netflix.conductor.common.metadata.tasks.Task;
import com.netflix.conductor.common.metadata.tasks.TaskResult;
import io.orkes.conductor.client.ApiClient;
import io.orkes.conductor.client.OrkesClients;
import io.orkes.conductor.client.TaskClient;
import io.orkes.conductor.client.automator.TaskRunnerConfigurer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;

public class SimpleJavaWorker implements Worker {

    private static final Logger LOGGER = LogManager.getLogger(SimpleJavaWorker.class);

    // Get your KEY and SECRET from your server - https://www.youtube.com/watch?v=f1b5vZRKn2Q
    private static final String ROOT_URI = "http://localhost:8080/api";
    private static final String KEY = "_CHANGE_ME_";
    private static final String SECRET = "_CHANGE_ME_";

    @Override
    public String getTaskDefName() {
        return "simple-java-worker";
    }

    @Override
    public TaskResult execute(Task task) {
        LOGGER.info("Executing task: {} from workflow {}", task.getTaskId(), task.getWorkflowInstanceId());
        TaskResult taskResult = new TaskResult(task);
        taskResult.setStatus(TaskResult.Status.COMPLETED);
        taskResult.getOutputData().put("message", "Hello World!");
        return taskResult;
    }

    public static void main(String[] args) {
        TaskClient taskClient = new OrkesClients(new ApiClient(ROOT_URI, KEY, SECRET))
                .getTaskClient();
        TaskRunnerConfigurer runnerConfigurer = new TaskRunnerConfigurer
                .Builder(taskClient, List.of(new SimpleJavaWorker()))
                .withThreadCount(10)
                .build();
        runnerConfigurer.init();
    }
}
