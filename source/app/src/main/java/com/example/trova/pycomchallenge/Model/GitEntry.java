
package com.example.trova.pycomchallenge.Model;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "sha",
    "commit"
})
public class GitEntry {

    @JsonProperty("sha")
    private String sha;
    @JsonProperty("commit")
    private Commit commit;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("sha")
    public String getSha() {
        return sha;
    }

    @JsonProperty("sha")
    public void setSha(String sha) {
        this.sha = sha;
    }

    @JsonProperty("commit")
    public Commit getCommit() {
        return commit;
    }

    @JsonProperty("commit")
    public void setCommit(Commit commit) {
        this.commit = commit;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }


    /**
     * Utility methods added to retreive data simpler
     * */
    public String getAuthorName() {
        return commit.getAuthor().getName();
    }

    public String getCommitId() {
        return sha;
    }

    public String getCommitMessage() {
        return commit.getMessage();
    }
}
