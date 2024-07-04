package com.alexeygold2077.api.DTO;

import java.util.List;

public record ChatCompletionResponse(String detail,
                                     String id,
                                     List<Choice> choices,
                                     String created,
                                     String model,
                                     String system_fingerprint,
                                     String object,
                                     Usage usage) {

    public record Choice(String finish_reason,
                         String index,
                         Message message,
                         String logprobs) {

        public record Message(String role,
                              String content) {
            @Override
            public String toString() {
                return "Message{" +
                        "role='" + role + '\'' +
                        ", content='" + content + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "Choice{" +
                    "finish_reason='" + finish_reason + '\'' +
                    ", index='" + index + '\'' +
                    ", message=" + message +
                    ", logprobs='" + logprobs + '\'' +
                    '}';
        }
    }
    public record Usage(String prompt_tokens,
                        String completion_tokens,
                        String total_tokens) {
        @Override
        public String toString() {
            return "Usage{" +
                    "prompt_tokens='" + prompt_tokens + '\'' +
                    ", completion_tokens='" + completion_tokens + '\'' +
                    ", total_tokens='" + total_tokens + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ChatCompletionResponse{" +
                "detail='" + detail + '\'' +
                ", id='" + id + '\'' +
                ", choices=" + choices +
                ", created='" + created + '\'' +
                ", model='" + model + '\'' +
                ", system_fingerprint='" + system_fingerprint + '\'' +
                ", object='" + object + '\'' +
                ", usage=" + usage +
                '}';
    }
}
