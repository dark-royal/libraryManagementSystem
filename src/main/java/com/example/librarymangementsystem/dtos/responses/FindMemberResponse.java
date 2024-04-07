package com.example.librarymangementsystem.dtos.responses;

import com.example.librarymangementsystem.data.models.Member;
import lombok.Data;

@Data
public class FindMemberResponse extends Member {
    private String message;
}
