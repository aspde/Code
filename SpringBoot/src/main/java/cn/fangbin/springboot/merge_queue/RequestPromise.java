package cn.fangbin.springboot.merge_queue;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestPromise {

    private UserRequest userRequest;

    private Result result;

    public RequestPromise(UserRequest userRequest) {
        this.userRequest = userRequest;
    }
}
