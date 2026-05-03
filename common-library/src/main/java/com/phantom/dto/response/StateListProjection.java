package com.phantom.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class StateListProjection implements Comparable<StateListProjection>, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String stateName;
    @Override
    public int compareTo(StateListProjection s) {
        return this.stateName.compareTo(s.stateName);
    }
}
