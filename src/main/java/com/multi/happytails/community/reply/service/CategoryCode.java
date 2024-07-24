package com.multi.happytails.community.reply.model.dto;

public enum CategoryCode {
    C("C", "떠들개"),
    O("O", "집사회의"),
    L("L", "내새꾸자랑"),
    R("R", "릴스");

    private final String code;
    private final String description;

    CategoryCode(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static CategoryCode fromCode(String code) {
        for (CategoryCode category : values()) {
            if (category.getCode().equals(code)) {
                return category;
            }
        }
        throw new IllegalArgumentException("Unknown code: " + code);
    }
}
