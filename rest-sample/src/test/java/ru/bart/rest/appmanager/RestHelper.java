package ru.bart.rest.appmanager;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import ru.bart.rest.model.Issue;

import java.io.IOException;
import java.util.Set;

public class RestHelper {

    private ApplicationManager app;

    public RestHelper(ApplicationManager app) {
        this.app = app;
    }

    protected Executor getExecuter() {
        return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490", "");
    }

    public Set<Issue> getIssues() throws IOException {
        String json = getExecuter().execute(Request.Get("https://bugify.stqa.ru/api/issues.json")).returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues,new TypeToken<Set<Issue>>(){}.getType());
    }

    public int createIssue(Issue newIssue) throws IOException {
        String json = getExecuter().execute(Request.Post("https://bugify.stqa.ru/api/issues.json")
                        .bodyForm(new BasicNameValuePair("subject", newIssue.getSubject()),
                                new BasicNameValuePair("description", newIssue.getDescription())))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        return parsed.getAsJsonObject().get("issue_id").getAsInt();
    }


    public Set<Issue> getIssueStatus(int id) throws IOException {
        String json = getExecuter().execute(Request.Get(String.format("https://bugify.stqa.ru/api/issues/%s.json", id))).returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues,new TypeToken<Set<Issue>>(){}.getType());
    }
}
