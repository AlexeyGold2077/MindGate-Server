package com.alexeygold2077.api.proxyapi.DTO;

import java.util.List;

public class ResponseDefault {
    private String id;
    private String object;
    private String created;
    private String model;
    private List<Choice> choices;
    private Usage usage;
    private String system_fingerprint;

    public static class Choice {
        private String index;
        private Message message;
        private String logprobs;
        private String finish_reason;

        public static class Message {
            private String role;
            private String content;

            public String getRole() {
                return role;
            }

            public void setRole(String role) {
                this.role = role;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }

        public String getIndex() {
            return index;
        }

        public void setIndex(String index) {
            this.index = index;
        }

        public Message getMessage() {
            return message;
        }

        public void setMessage(Message message) {
            this.message = message;
        }

        public String getLogprobs() {
            return logprobs;
        }

        public void setLogprobs(String logprobs) {
            this.logprobs = logprobs;
        }

        public String getFinish_reason() {
            return finish_reason;
        }

        public void setFinish_reason(String finish_reason) {
            this.finish_reason = finish_reason;
        }
    }

    public static class Usage {
        private String prompt_tokens;
        private String completion_tokens;
        private String total_tokens;

        public String getPrompt_tokens() {
            return prompt_tokens;
        }

        public void setPrompt_tokens(String prompt_tokens) {
            this.prompt_tokens = prompt_tokens;
        }

        public String getCompletion_tokens() {
            return completion_tokens;
        }

        public void setCompletion_tokens(String completion_tokens) {
            this.completion_tokens = completion_tokens;
        }

        public String getTotal_tokens() {
            return total_tokens;
        }

        public void setTotal_tokens(String total_tokens) {
            this.total_tokens = total_tokens;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

    public Usage getUsage() {
        return usage;
    }

    public void setUsage(Usage usage) {
        this.usage = usage;
    }

    public String getSystem_fingerprint() {
        return system_fingerprint;
    }

    public void setSystem_fingerprint(String system_fingerprint) {
        this.system_fingerprint = system_fingerprint;
    }
}
