/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.muriloao.b2w_test.payloads;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author Murilo Oliveira
 */
public class ResponsePayloadText {

    final int statusCode = 200;
    final String message = "This is a message";

    @Test
    void shouldReturnResponse_whenMessageInformed() {
        ResponseEntity<ResponsePayload> payload = ResponsePayload.message(this.statusCode, this.message);
        Assertions.assertThat(payload.getBody()).isNotNull();
        Assertions.assertThat(payload.getBody().getStatus()).isEqualTo(this.statusCode);
        Assertions.assertThat(payload.getBody().getMessage()).isEqualTo(this.message);
        Assertions.assertThat(payload.getBody().getContent()).isNull();
    }

    @Test
    void shouldReturnResponse_whenContentInformed() {
        final List messages = new ArrayList<>(Arrays.asList(this.message));
        ResponseEntity<ResponsePayload<List>> payload = ResponsePayload.content(this.statusCode, messages);
        Assertions.assertThat(payload.getBody()).isNotNull();
        Assertions.assertThat(payload.getBody().getStatus()).isEqualTo(this.statusCode);
        Assertions.assertThat(payload.getBody().getContent()).isEqualTo(messages);
        Assertions.assertThat(payload.getBody().getMessage()).isNull();
    }
}
