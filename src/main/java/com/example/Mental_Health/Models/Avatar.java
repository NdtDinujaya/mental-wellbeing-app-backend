package com.example.Mental_Health.Models;

public enum Avatar {
    AVATAR1("ZenMaster"),
    AVATAR2("DigitalDetoxer"),
    AVATAR3("CalmCaptain"),
    AVATAR4("SunshineSeeker"),
    AVATAR5("DreamGuardian"),
    AVATAR6("ConfidenceChampion"),
    AVATAR7("PeaceWarrior"),
    DEFAULT("DefaultAvatar");

    private String avatarName;

    Avatar(String avatarName) {
        this.avatarName = avatarName;
    }

    public String getAvatarName() {
        return avatarName;
    }
}