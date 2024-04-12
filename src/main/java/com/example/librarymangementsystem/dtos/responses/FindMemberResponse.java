package com.example.librarymangementsystem.dtos.responses;

import com.example.librarymangementsystem.data.models.Member;
import lombok.Data;

@Data
public class FindMemberResponse {
    private String message;
    private Member members;
}
