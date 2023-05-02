package com.example.Mental_Health.Models;

import java.util.Objects;

public class ConcernAndGoal {
    String concern;
    String improvementGoal;

    public ConcernAndGoal(String concern, String improvementGoal) {
        this.concern = concern;
        this.improvementGoal = improvementGoal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConcernAndGoal that = (ConcernAndGoal) o;
        return Objects.equals(concern, that.concern) && Objects.equals(improvementGoal, that.improvementGoal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(concern, improvementGoal);
    }
}