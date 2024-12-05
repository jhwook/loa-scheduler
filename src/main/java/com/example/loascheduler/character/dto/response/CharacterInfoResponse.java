package com.example.loascheduler.character.dto.response;

import lombok.Getter;

@Getter
public class CharacterInfoResponse {
    private String className;
    private String level;
    private String nickName;


    public CharacterInfoResponse(String className, String nickName, String level) {
        this.className = className;
        this.level = level;
        this.nickName = nickName;
    }
}
